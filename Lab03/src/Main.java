import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Seguradora seguradora = new Seguradora(
            "Seguradora Seguros", "19912345678", null, null
        );

        Scanner sc = new Scanner(System.in);
        boolean rodar = true;
        while (rodar) {
            System.out.print(
                "------------ MENU ------------\n" +
                "(1) Cadastrar cliente\n" +
                "(2) Remover cliente\n" +
                "(3) Listar clientes\n" +
                "(4) Gerar sinistro\n" +
                "(5) Visualizar sinistro\n" +
                "(6) Listar sinistros\n" +
                "(Outro) Sair\n" +
                "------------------------------\n" +
                "Digite a opção: "
            );
            int op = sc.nextInt();
            sc.nextLine();
            if (op == 1) {
                System.out.println("------------------------------");
                System.out.println("CADASTRAR CLIENTE");

                Cliente cliente;
                ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
                Veiculo veiculoPresente = new Veiculo("AAA-0A00", "Boa", "Bom", 1950);
                listaVeiculos.add(veiculoPresente);

                System.out.print("Digite o nome: ");
                String nome = sc.nextLine();
                System.out.print("Digite o endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Digite o tipo de cliente (PF ou PJ): ");
                String tipo = sc.nextLine();

                if (tipo.equals("PF")) {
                    System.out.print("Digite o CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Digite o gênero: ");
                    String genero = sc.nextLine();
                    System.out.print("Digite a data da licença: ");
                    Date dataLicenca = new Date(sc.nextLine());
                    System.out.print("Digite o nível de educação: ");
                    String educacao = sc.nextLine();
                    System.out.print("Digite a data de nascimento: ");
                    Date dataNascimento = new Date(sc.nextLine());
                    System.out.print("Digite a classe econômica: ");
                    String classeEconomica = sc.nextLine();

                    cliente = new ClientePF(
                        nome, endereco, listaVeiculos,
                        cpf, genero, dataLicenca,
                        educacao, dataNascimento, classeEconomica
                    );
                } else {
                    System.out.print("Digite o CNPJ: ");
                    String cnpj = sc.nextLine();
                    System.out.print("Digite a data de fundação: ");
                    Date dataFundacao = new Date(sc.nextLine());
                    cliente = new ClientePJ(nome, endereco, listaVeiculos, cnpj, dataFundacao);
                }

                boolean cadastrado = seguradora.cadastrarCliente(cliente);
                if (cadastrado) {
                    System.out.println("Cliente cadastrado com sucesso.");
                } else {
                    System.out.println("Cliente já cadastrado.");
                }
            } else if (op == 2) {
                System.out.println("------------------------------");
                System.out.println("REMOVER CLIENTE");
                System.out.print("Digite o CPF ou CNPJ do cliente: ");
                String idCliente = sc.nextLine();
                boolean removido = seguradora.removerCliente(idCliente);
                if (removido) {
                    System.out.println("Cliente removido com sucesso.");
                } else {
                    System.out.println("Cliente inexistente.");
                }
            } else if (op == 3) {
                System.out.println("------------------------------");
                System.out.println("LISTAR CLIENTES");
                System.out.print("Digite o tipo de cliente (PF ou PJ): ");
                String tipo = sc.nextLine();
                seguradora.listarClientes(tipo);
            } else if (op == 4) {
                System.out.println("------------------------------");
                System.out.println("GERAR SINISTRO");
                boolean gerado = seguradora.gerarSinistro();
                if (gerado) {
                    System.out.println("Sinistro gerado com sucesso.");
                } else {
                    System.out.println("Não foi possível gerar o sinistro.");
                }
            } else if (op == 5) {
                System.out.println("------------------------------");
                System.out.println("VISUALIZAR SINISTRO");
                System.out.print("Digite o CPF ou CNPJ do cliente: ");
                String idCliente = sc.nextLine();
                boolean visualizado = seguradora.visualizarSinistro(idCliente);
                if (!visualizado) {
                    System.out.println("Cliente não existe ou não possui sinistros.");
                }
            } else if (op == 6) {
                System.out.println("------------------------------");
                System.out.println("LISTAR SINISTROS");
                seguradora.listarSinistros();
            } else {
                rodar = false;
            }
        }
        sc.close();
    }

}
