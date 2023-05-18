import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorClientePJ {

    Scanner scanner;
    DaoClientePJ daoClientePJ;

    public GerenciadorClientePJ(){
        scanner = new Scanner(System.in);
        // da para instanciar dentro do objeto construtor

        // da pra instanciat o scanner dentro do menu também
        daoClientePJ = new DaoClientePJ();
    }

    public void menu() {
        int opcao = -1;
        while(opcao != 0){
            try {
                
                System.out.println("\n------------------------------------");
                System.out.println("GERENCIAMENTO DE CLIENTES PJ");
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
            } catch (NumberFormatException e1) {
                System.out.println("Erro: Caracter inválido.");
            }
        }
            
    }

    public void cadastrar() {
        ClientePJ cliPJ = new ClientePJ();

        System.out.println("----------------------");
        System.out.println("[Cadastro de Cliente PJ]");

        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        nome = this.verif(nome);
        cliPJ.setNome(nome);

        System.out.println("CNPJ: ");
        String cnpj = scanner.nextLine();
        cnpj = this.verif(cnpj);
        cliPJ.setCnpj(cnpj);

        EnderecoPJ endPJ = new EnderecoPJ();

        System.out.println("[Endereço]: ");

        System.out.println("Rua: ");
        String rua = scanner.nextLine();
        rua = this.verif(rua);
        endPJ.setRua(rua);

        System.out.println("Numero: ");
        String numero = scanner.nextLine();
        numero = this.verif(numero);
        endPJ.setNumero(Integer.parseInt(numero));

        System.out.println("Bairro: ");
        String bairro = scanner.nextLine();
        bairro = this.verif(bairro);
        endPJ.setBairro(bairro);

        System.out.println("CEP: ");
        String cep = scanner.nextLine();
        cep = this.verif(cep);
        endPJ.setCep(cep);

        // inserir no banco

        cliPJ.setEnderecoPJ(endPJ);

        boolean inserido = daoClientePJ.inserir(cliPJ);
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

    public void listar() {
        ArrayList<ClientePJ> cliPJ = daoClientePJ.buscarTodos();
        System.out.println("==========================");
        System.out.println("Cliente PJ cadastrados");
        for(ClientePJ cli : cliPJ) {
            System.out.println("Código: " + cli.getCodigoClientePJ()
            + ", Nome: " + cli.getNome() 
            + ", CNPJ: " + cli.getCnpj());
            System.out.println("[Endereço]"
            + ", Rua: " + cli.getEnderecoPJ().getRua()
            + ", Número: " + cli.getEnderecoPJ().getNumero()
            + ", Bairro: " + cli.getEnderecoPJ().getBairro()
            + ", CEP: " + cli.getEnderecoPJ().getCep());
        }
    }

    public void excluir() {
        System.out.println("==========================");
        System.out.println("Exclusão de clientes PJ");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        int qtd = daoClientePJ.excluir(codigo);
        if(qtd > 0) {
            System.out.println("Excluido com sucesso.");
        } else {
            System.out.println("Não encontrado.");
        }
    }

    public void consultar() {
        System.out.println("==========================");
        System.out.println("Consulta de Clientes PJ");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        ClientePJ cliPj = daoClientePJ.consultar(codigo);
        if(cliPj != null) {
            System.out.println("Código: " + cliPj.getCodigoClientePJ() 
            + ", Nome: " + cliPj.getNome() 
            + ", CNPJ: " + cliPj.getCnpj() 
            + "\n[Endereço] Rua: " + cliPj.getEnderecoPJ().getRua() 
            + ", Rua: " + cliPj.getEnderecoPJ().getRua()
            + ", Número: " + cliPj.getEnderecoPJ().getNumero()
            + ", Bairro: " + cliPj.getEnderecoPJ().getBairro()
            + ", CEP: " + cliPj.getEnderecoPJ().getCep()) ; 
        } else {
            System.out.println("Não encontrado.");
        }
    }

    public void alterar() {
        System.out.println("----------------------");
        System.out.println("[Alteração de Veiculos]");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        ClientePJ cliPJ = daoClientePJ.consultar(codigo);
        if (cliPJ != null) {
            System.out.println("Dados do Cliente PJ");
            System.out.println("[Código: " + cliPJ.getCodigoClientePJ() + "]");
            
            System.out.println("[Nome: " + cliPJ.getNome() + "]");
            String nome = scanner.nextLine().trim();
            // trim tira o espaço
            if (!nome.isEmpty()) {
                cliPJ.setNome(nome);
            }

            System.out.println("[CNPJ: " + cliPJ.getCnpj() + "]");
            String cnpj = scanner.nextLine().trim();
            if(!cnpj.isEmpty()) {
                cliPJ.setCnpj(cnpj);
            }
            
            System.out.println("[ ENDEREÇO: ]");
            System.out.println("[Rua: " + cliPJ.getEnderecoPJ().getRua() + "]");
            String rua = scanner.nextLine().trim();
            if(!rua.isEmpty()) {
                cliPJ.getEnderecoPJ().setRua(rua);
            }

            System.out.println("[Numero: " + cliPJ.getEnderecoPJ().getNumero() + "]");
            String numero = scanner.nextLine().trim();
            if(!numero.isEmpty()){
                cliPJ.getEnderecoPJ().setNumero(Integer.parseInt(numero));
            }

            System.out.println("[Bairro: " + cliPJ.getEnderecoPJ().getBairro() + "]");
            String bairro = scanner.nextLine().trim();
            if(!bairro.isEmpty()) {
                cliPJ.getEnderecoPJ().setBairro(bairro);
            }

            System.out.println("[CEP: " + cliPJ.getEnderecoPJ().getCep() + "]");
            String cep = scanner.nextLine().trim();
            if(!cep.isEmpty()) {
                cliPJ.getEnderecoPJ().setCep(cep);
            }
            
            // inserir no banco
            int qtdAlterado = daoClientePJ.atualizar(cliPJ);
            if(qtdAlterado > 0) {
                System.out.println("Atualizado com sucesso");
            } else {
                System.out.println("Não encontrado.");
            }
        }
    }
}


