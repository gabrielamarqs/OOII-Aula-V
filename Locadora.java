import java.util.Scanner;

public class Locadora {
    public static void main(String[] args) {
        Locadora loc = new Locadora();
        loc.menuPrincipal();
    }

    public void menuPrincipal() {
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSistema de Gerenciamento de Locadora de Veículos\n");
        System.out.println("------------");
        while(opcao != 0){
            System.out.println("\nMENU PRINCIPAL ");
            System.out.println("[1] - Gerenciar Veículos: ");
            System.out.println("[2] - Gerenciar Clientes: ");
            System.out.println("[0] - Sair: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Erro: Informe um número inteiro.");
            }
            switch (opcao) {
                case 1:
                    GerenciadorVeiculo gv = new GerenciadorVeiculo();
                    gv.menu();
                    break;

                case 2:
                    // gerenciar clientes
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção informada inválida.");
            }
        }
    }
}