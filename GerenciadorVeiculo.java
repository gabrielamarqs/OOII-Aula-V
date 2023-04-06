import java.util.Scanner;

public class GerenciadorVeiculo {
    Scanner scanner;
    DaoVeiculo daoVeiculo;

    public GerenciadorVeiculo(){
        scanner = new Scanner(System.in);
        // da para instanciar dentro do objeto construtor

        // da pra instanciat o scanner dentro do menu também
        daoVeiculo = new DaoVeiculo();
    }

    public void menu() {
        int opcao = -1;
        while(opcao != 0){
            System.out.println("\n------------------------------------");
            System.out.println("GERENCIAMENTO DE VEICULOS ");
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
                    this.Cadastrar();
                    break;
                
                case 2:
                    break;

                case 3:
                    break;
                    
                case 4:
                    break;
                    
                case 5:
                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção informada inválida.");
            }
        }

    }

    public void Cadastrar() {
        Veiculo v = new Veiculo();
        System.out.println("----------------------");
        System.out.println("[Cadastro de Veiculos]");
        System.out.println("Marca: ");
        v.setMarca(scanner.nextLine());
        System.out.println("Modelo: ");
        v.setModelo(scanner.nextLine());
        System.out.println("Chassi: ");
        v.setChassi(scanner.nextLine());
        System.out.println("Ano: ");
        v.setAno(Integer.parseInt(scanner.nextLine()));

        // inserir no banco

        boolean inserido = daoVeiculo.inserir(v);
        if (inserido) {
            System.out.println("Inserido com sucesso.");
        }

    }

}
