import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorCliente {
    Scanner scanner;
    DaoCliente daoCliente;

    public GerenciadorCliente(){
        scanner = new Scanner(System.in);
        // da para instanciar dentro do objeto construtor
        daoCliente = new DaoCliente();
    }

    public void menu() {
        int opcao = -1;
        while(opcao != 0){
            try {
                System.out.println("\n------------------------------------");
                System.out.println("GERENCIAMENTO DE CLIENTES");
                System.out.println("[1] - Cadastrar: ");
                System.out.println("[2] - Consultar: ");
                System.out.println("[3] - Alterar: ");
                System.out.println("[4] - Excluir: ");
                System.out.println("[5] - Listar: ");
                System.out.println("[0] - Sair: ");
                
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Erro: Informe um número inteiro.");
                }
                switch (opcao) {
                    case 1:
                    this.cadastrar();
                    break;
                    
                    case 2:
                    this.consultar();
                    break;
                    
                    case 3:
                    this.alterar();
                    break;
                    
                    case 4:
                    this.excluir();;
                    break;
                    
                    case 5:
                    this.listar();
                    break;
                    
                    case 0:
                    System.out.println("Saindo...");
                    break;
                    
                    default:
                    System.out.println("Opção informada inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: caracter inválido.");
            }
        }

    }

    public void cadastrar() {
        Cliente c = new Cliente();
        // cria um novo objeto da classe cliente
        System.out.println("----------------------");
        System.out.println("[Cadastro de Cliente]");

        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        nome = this.verif(nome);
        c.setNome(nome);

        System.out.println("CPF:");
        String cpf = scanner.nextLine();
        cpf = this.verif(cpf);
        c.setCpf(cpf);

        System.out.println("Email:");
        String email = scanner.nextLine();
        email = this.verifEmail(email);
        c.setEmail(email);

        System.out.println("Telefone:");
        String telefone = scanner.nextLine();
        telefone = this.verif(telefone);
        c.setTelefone(telefone);

        System.out.println("Endereço:\nRua:");
        String endRua = scanner.nextLine();
        endRua = this.verif(endRua);
        c.setEndRua(endRua);

        System.out.println("Bairro:");
        String endBairro = scanner.nextLine();
        endBairro = this.verif(endBairro);
        c.setEndBairro(endBairro);

        System.out.println("Número da casa:");
        String endNroCasa = scanner.nextLine();
        endNroCasa = this.verif(endNroCasa);
        c.setEndNroCasa(Integer.parseInt(endNroCasa));
        
        System.out.println("Complemento: \n[Se não há complemento deixe em branco]");
        c.setEndComplemento(scanner.nextLine());

        boolean inserido = daoCliente.inserir(c);
        // o método inserir no DaoCliente retorna um boolean true se foi inserido com sucesso 
        if (inserido) {
            System.out.println("Inserido com sucesso.");
        }
    }

    public String verif (String atributos) {
        while (atributos.equals("") || atributos.equals("null")) {
            System.out.println("Insira a informação:");
            atributos = scanner.nextLine();
        }
        return atributos;
    }

    public String verifEmail(String atributos) {
        while (atributos.equals("") || atributos.equals(null) || (!atributos.contains("@"))) {
            System.out.println("Insira o email no formato correto:");
            atributos = scanner.nextLine();
        }
        return atributos;
    }

    public void listar() {
        ArrayList<Cliente> cliente = daoCliente.buscarTodos();
        // a arrayList cliente vai receber a arrayList que foi retornada pelo método buscarTodos no meu DaoCliente
        System.out.println("\n==========================\n");
        System.out.println("Clientes cadastrados");
        if (cliente.isEmpty()){
            System.out.println("Lista de clientes vazia");
        } else {
            for (Cliente c : cliente) {
                System.out.println("\nCódigo: " + c.getCodigo()
                + ", Nome: " + c.getNome() 
                + ", CPF: " + c.getCpf()
                + ", Email: " + c.getEmail()
                + ", Telefone: " + c.getTelefone()
                + ", \nEndereço: Rua: " + c.getEndRua()
                + ", Bairro: " + c.getEndBairro()
                + ", No. da casa: " + c.getEndNroCasa()
                + ", Complemento: " + c.getEndComplemento());
            }
        }
    }

    public void excluir() {
        System.out.println("\n==========================\n");
        System.out.println("Exclusão de clientes");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        int qtd = daoCliente.excluir(codigo);
        if(qtd > 0) {
            System.out.println("Excluido com sucesso.");
        } else {
            System.out.println("Não encontrado.");
        }

    }

    public void consultar() {
        System.out.println("==========================");
        System.out.println("Consulta de clientes");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Cliente c = daoCliente.consultar(codigo);
        if (c != null) {
            System.out.println("\nCódigo: " + c.getCodigo()
            + ", Nome: " + c.getNome() 
            + ", CPF: " + c.getCpf()
            + ", Email: " + c.getEmail()
            + ", Telefone: " + c.getTelefone()
            + ", \nEndereço: Rua: " + c.getEndRua()
            + ", Bairro: " + c.getEndBairro()
            + ", No. da casa: " + c.getEndNroCasa()
            + ", Complemento: " + c.getEndComplemento());        
        } else {
            System.out.println("Não encontrado.");
        }
    }

    public void alterar() {
        System.out.println("----------------------");
        System.out.println("[Alteração de Clientes]");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Cliente c = daoCliente.consultar(codigo);
        if (c != null) {
            System.out.println("Dados do veículo");
            System.out.println("[Código: " + c.getCodigo() + "]");
            System.out.println("[Nome: " + c.getNome() + "]");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                c.setNome(nome);
            }
            System.out.println("[CPF: " + c.getCpf() + "]");
            // não vai ter a opção editar cpf
            System.out.println("[Email: " + c.getEmail() + "]");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                c.setEmail(email);
            }
            System.out.println("[Telefone: " + c.getTelefone() + "]");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                c.setTelefone(telefone);
            }
            System.out.println("[Endereço:]");
            System.out.println("[Rua "+ c.getEndRua() +"]");
            String rua = scanner.nextLine();
            if (!rua.isEmpty()) {
                c.setEndRua(rua);
            }
            System.out.println("[Bairro: " + c.getEndBairro() + "]");
            String bairro = scanner.nextLine();
            if (!bairro.isEmpty()) {
                c.setEndBairro(bairro);
            }
            System.out.println("[No. da Casa: " + c.getEndNroCasa() + "]");
            String nro = scanner.nextLine();
            if(!nro.isEmpty()) {
                c.setEndNroCasa(Integer.parseInt(nro));
            }

            int qtdAlterado = daoCliente.atualizar(c);
            if(qtdAlterado > 0) {
                System.out.println("Atualizado com sucesso.");
            } else {
                System.out.println("Não encontrado.");
            }
        }
    }
}
