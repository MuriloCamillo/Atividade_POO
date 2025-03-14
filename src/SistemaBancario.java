import java.util.ArrayList;
import java.util.Scanner;

/* gerenciar contas*/
public class SistemaBancario {
    private ArrayList<Conta> contas;
    private Scanner scanner;

    public SistemaBancario() {
        contas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private void exibirMenu() {
        System.out.println("\n==== SISTEMA BANCÁRIO ====");
        System.out.println("1. Cadastrar nova conta");
        System.out.println("2. Consultar dados de uma conta");
        System.out.println("3. Listar todas as contas");
        System.out.println("4. Realizar depósito");
        System.out.println("5. Realizar saque");
        System.out.println("6. Excluir uma conta");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarConta() {
        System.out.println("\n-- CADASTRO DE NOVA CONTA --");

        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (buscarContaPorNumero(numero) != null) {
            System.out.println("ERRO: Já existe uma conta com este número");
            return;
        }

        System.out.print("Nome da agência: ");
        String agencia = scanner.nextLine();

        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        Conta novaConta = new Conta();
        novaConta.setNumero(numero);
        novaConta.setAgencia(agencia);
        novaConta.setNomeCliente(nomeCliente);
        novaConta.setSaldo(0.0);

        contas.add(novaConta);

        System.out.println("Conta cadastrada com sucesso");
    }

    private Conta buscarContaPorNumero(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    private void consultarConta() {
        System.out.println("\n-- CONSULTA DE CONTA --");

        System.out.print("Informe o número da conta: ");
        int numero = scanner.nextInt();

        Conta conta = buscarContaPorNumero(numero);

        if (conta != null) {
            System.out.println(conta);
        } else {
            System.out.println("Conta não encontrada");
        }
    }

    private void listarContas() {
        System.out.println("\n-- LISTA DE CONTAS --");

        if (contas.isEmpty()) {
            System.out.println("Não há contas cadastradas");
            return;
        }

        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }

    private void realizarDeposito() {
        System.out.println("\n-- DEPÓSITO --");

        System.out.print("Informe o número da conta: ");
        int numero = scanner.nextInt();

        Conta conta = buscarContaPorNumero(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }

        System.out.print("Informe o valor do depósito: R$ ");
        double valor = scanner.nextDouble();

        if (conta.depositar(valor)) {
            System.out.println("Depósito realizado com sucesso");
            System.out.println("Novo saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        } else {
            System.out.println("Erro ao realizar depósito, valor inválido");
        }
    }

    private void realizarSaque() {
        System.out.println("\n-- SAQUE --");

        System.out.print("Informe o número da conta: ");
        int numero = scanner.nextInt();

        Conta conta = buscarContaPorNumero(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }

        System.out.print("Informe o valor do saque: R$ ");
        double valor = scanner.nextDouble();

        if (conta.sacar(valor)) {
            System.out.println("Saque realizado com sucesso");
            System.out.println("Novo saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        } else {
            System.out.println("Erro ao realizar saque.");
        }
    }

    private void excluirConta() {
        System.out.println("\n-- EXCLUSÃO DE CONTA --");

        System.out.print("Informe o número da conta a ser excluída: ");
        int numero = scanner.nextInt();

        Conta conta = buscarContaPorNumero(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada");
            return;
        }

        contas.remove(conta);
        System.out.println("Conta excluída com sucesso");
    }

    public void executar() {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    consultarConta();
                    break;
                case 3:
                    listarContas();
                    break;
                case 4:
                    realizarDeposito();
                    break;
                case 5:
                    realizarSaque();
                    break;
                case 6:
                    excluirConta();
                    break;
                case 7:
                    System.out.println("Encerrando sistema");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        } while (opcao != 7);

        scanner.close();
    }

    public static void main(String[] args) {
        SistemaBancario sistema = new SistemaBancario();
        sistema.executar();
    }
}