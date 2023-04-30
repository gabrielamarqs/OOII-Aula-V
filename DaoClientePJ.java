import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoClientePJ {

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

    public boolean inserir(ClientePJ cliPJ) throws NumberFormatException{
        boolean resultado = false;
        try {
            this.conectar();
            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO tb_clientes_pj (cod_cli_pj, nome, cnpj) VALUES (NULL, ?, ?)", 
                Statement.RETURN_GENERATED_KEYS
            );
            pst.setString(1, cliPJ.getNome());
            pst.setString(2, cliPJ.getCnpj());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            // 
            int idCliente = 0;
            if (rs.next()) {
                idCliente = rs.getInt(1);
                PreparedStatement pstEnd = conn.prepareStatement(
                    "INSERT INTO tb_enderecos_pj (cod_end, cod_cli_pj, rua, numero, bairro, cep) VALUES (NULL, ?, ?, ?, ?, ?)");
                pstEnd.setInt(1, idCliente);
                pstEnd.setString(2, cliPJ.getEnderecoPJ().getRua());
                pstEnd.setInt(3, cliPJ.getEnderecoPJ().getNumero());
                pstEnd.setString(4, cliPJ.getEnderecoPJ().getBairro());
                pstEnd.setString(5, cliPJ.getEnderecoPJ().getCep());
                pstEnd.executeUpdate();
                resultado = true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    public ArrayList<ClientePJ> buscarTodos(){
        ArrayList<ClientePJ> resultado = new ArrayList<ClientePJ>();
        // criei meu arraylist vazio
        try {
            this.conectar();
            // inserir, editar ou excluir
            // executeUpdate 
            ResultSet rs = st.executeQuery("SELECT * FROM tb_clientes_pj as c, tb_enderecos_pj as e where c.cod_cli_pj = e.cod_cli_pj order by c.nome;");
            // resultset sempre vai retornar uma tabela com dados do que você pediu
            while (rs.next()) {
                // enquanto tiver linha no rs vai continuar rodando
                ClientePJ cliPJ = new ClientePJ();
                EnderecoPJ endPJ = new EnderecoPJ();

                cliPJ.setCodigoClientePJ(rs.getInt("cod_cli_pj"));
                cliPJ.setNome(rs.getString("nome"));
                cliPJ.setCnpj(rs.getString("cnpj"));
                endPJ.setRua(rs.getString("rua"));
                endPJ.setNumero(rs.getInt("numero"));
                endPJ.setBairro(rs.getString("bairro"));
                endPJ.setCep(rs.getString("cep"));
                
                cliPJ.setEnderecoPJ(endPJ);
                resultado.add(cliPJ);
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
            PreparedStatement pstEnd = conn.prepareStatement(
                "DELETE FROM tb_enderecos_pj WHERE cod_cli_pj = ?"
            );
            pstEnd.setInt(1, cod);
            pstEnd.executeUpdate();

            PreparedStatement pstCli = conn.prepareStatement(
                "DELETE FROM tb_clientes_pj WHERE cod_cli_pj = ?"
            );
            pstCli.setInt(1, cod);
            pstCli.executeUpdate();
            qtd = pstCli.getUpdateCount();
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

    public int atualizar(Veiculo v) throws NumberFormatException {
        int qtd = 0;
        try {
            this.conectar();
            String comando = "UPDATE tb_veiculos SET marca ='" + v.getMarca() + " ',  modelo ='" + v.getModelo() + "', chassi = '" + v.getChassi() + "', ano = " + v.getAno() + " WHERE codigo = " + v.getCodigo() + ";";
            System.out.println(comando);
            st.executeUpdate(comando);
            qtd = st.getUpdateCount();
        } catch (SQLException e1) {
            System.out.println("Chave SQL duplicada: " + e1.getMessage());
        } catch (Exception e2) {
            System.out.println("Error: " + e2.getMessage());
        } finally {
            this.desconectar();
        }
        return qtd;
    }


}


