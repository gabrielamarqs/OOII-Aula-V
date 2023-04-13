import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoVeiculo {
    private Connection conn;
    private Statement st;

    // vai ficar todas minhas querys 

    private void conectar() {
        try {
            this.conn = GerenciadorConexao.pegarConexao();
            // conectado no banco
            st = conn.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void desconectar() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

    }

    public boolean inserir(Veiculo v) {
        boolean resultado = false;
        try {
            this.conectar();
            String comando = "INSERT INTO tb_veiculos VALUES (NULL, '" + v.getMarca() + "', '" + v.getModelo() + "', '" + v.getChassi() + "', " + v.getAno() + ");";
            System.out.println(comando);
            st.executeUpdate(comando);
            resultado = true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    public ArrayList<Veiculo> buscarTodos(){
        ArrayList<Veiculo> resultado = new ArrayList<Veiculo>();
        // criei meu arraylist vazio
        try {
            this.conectar();
            // inserir, editar ou excluir
            // executeUpdate 
            ResultSet rs = st.executeQuery("SELECT * FROM tb_veiculos ORDER BY modelo;");
            // resultset sempre vai retornar uma tabela com dados do que você pediu
            while (rs.next()) {
                // enquanto tiver linha no rs vai continuar rodando
                Veiculo v = new Veiculo();
                v.setCodigo(rs.getInt("codigo"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setChassi(rs.getString("chassi"));
                v.setAno(rs.getInt("ano"));
                resultado.add(v);
                // adicionei minha arraylist no meu objeto veiculo
            }
            
        } catch (Exception e1) {
            System.out.println("Error: " + e1.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    public int excluir(int cod) {
        // vai receber retorno 0 ou 1
        // se excluiu ou não excluiu
        int qtd = 0;
        try {
            this.conectar();
            String comando = "DELETE FROM tb_veiculos WHERE codigo = " + cod + ";";
            st.executeUpdate(comando);
            qtd = st.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return qtd;
    }

    public Veiculo consultar(int cod){
        // public -Veiculo- consultar
        // Veiculo é o que eu quero retornar
        Veiculo v = null;
        // inicializar com null para não dizer que o código não foi inicializado
        // caso fosse  null ia retornar qualquer coisa
        try {
            this.conectar();
            ResultSet rs = st.executeQuery("SELECT * FROM tb_veiculos WHERE codigo = " + cod + ";");
            if(rs.next()) {
                v = new Veiculo();
                v.setCodigo(rs.getInt("codigo"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setChassi(rs.getString("chassi"));
                v.setAno(rs.getInt("ano"));
            }
        } catch (Exception e1) {
            System.out.println("Error: " + e1.getMessage());
        } finally {
            this.desconectar();
        }
        return v;
    }

    public int atualizar(Veiculo v) {
        int qtd = 0;
        try {
            this.conectar();
            String comando = "UPDATE tb_veiculos SET marca ='" + v.getMarca() + " ',  modelo ='" + v.getModelo() + "', chassi = '" + v.getChassi() + "', ano = " + v.getAno() + " WHERE codigo = " + v.getCodigo() + ";";
            System.out.println(comando);
            st.executeUpdate(comando);
            qtd = st.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return qtd;
    }


}
