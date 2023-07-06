import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Seguradora seguradora = new Seguradora(
            "Seguradora", "19912345678", "seguradora@seguradora.com", "Rua da Seguradora, 1"
        );

        Veiculo brasilia = new Veiculo("BRA-5I22", "Brasil Veículos", "Brasília", 1960);
        ArrayList<Veiculo> listaVeiculosSeuSergio = new ArrayList<Veiculo>();
        listaVeiculosSeuSergio.add(brasilia);
        ClientePF seuSergio = new ClientePF(
            "Seu Sérgio", "Rua Albertino Juaz, 15", listaVeiculosSeuSergio,
            "44711142045", "Homem", LocalDate.parse("1990-01-01"),
            "Ensino médio completo", LocalDate.parse("1940-01-01"), "Alta"
        );
        
        Veiculo goiania = new Veiculo("GOI-4N14", "Brasil Veículos", "Goiânia", 1920);
        Veiculo salvador = new Veiculo("SAL-7A80", "Brasil Veículos", "Salvador", 2004);
        ArrayList<Veiculo> listaVeiculosPensionatoSergio = new ArrayList<Veiculo>();
        listaVeiculosPensionatoSergio.add(goiania);
        listaVeiculosPensionatoSergio.add(salvador);
        ClientePJ pensionatoSergio = new ClientePJ(
            "Pensionato do Seu Sérgio", "Rua Albertino Juaz, 15", listaVeiculosPensionatoSergio,
            "66064023000178", LocalDate.parse("1990-01-01"), 2
        );

        seguradora.cadastrarCliente(seuSergio);
        seguradora.cadastrarCliente(pensionatoSergio);

        for (int i = 0; i < 2; ++i) seguradora.gerarSinistro();
        
        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
        seguradora.visualizarSinistros(seuSergio.getCpf());
        seguradora.listarSinistros();
        System.out.println("Receita da seguradora: " + seguradora.calcularReceita());
        for (Cliente cliente : seguradora.getListaClientes()) {
            seguradora.calcularPrecoSeguroCliente(cliente);
        }

        menu(seguradora);
    }

    private static void menu(Seguradora seguradora) {
        Scanner sc = new Scanner(System.in);
        boolean rodar = true;
        while (rodar) {
            System.out.print(
                "***********************************\n" +
                "MENU\n" +
                "(1) Cadastros\n" +
                "(2) Listar\n" +
                "(3) Excluir cliente\n" +
                "(4) Gerar sinistro\n" +
                "(5) Transferir seguro\n" +
                "(6) Calcular receita seguradora\n" +
                "(0) Sair\n" +
                "***********************************\n" +
                "Digite a opção: "
            );
            int op = sc.nextInt();
            sc.nextLine();
            if (op == MenuOperacoes.CADASTROS.getOperacao()) {
                boolean rodarCadastros = true;
                while (rodarCadastros) {
                    System.out.print(
                        "***********************************\n" +
                        "MENU CADASTROS\n" +
                        "(1) Cadastrar cliente PF\n" +
                        "(2) Cadastrar cliente PJ\n" +
                        "(0) Voltar\n" +
                        "***********************************\n" +
                        "Digite a opção: "
                    );
                    int opCadastros = sc.nextInt();
                    sc.nextLine();
                    if (opCadastros == MenuCadastros.CADASTRAR_CLIENTE_PF.getOperacao()) {
                        System.out.println("***********************************");
                        System.out.println("CADASTRAR CLIENTE PF");
                        System.out.print("Digite o CPF: ");
                        String cpf = sc.nextLine();
                        if (seguradora.buscarCliente(cpf) != null) {
                            System.out.println("Erro: CPF já cadastrado");
                            continue;
                        }
                        if (!Validacao.validarCpf(cpf)) {
                            System.out.println("Erro: CPF inválido");
                            continue;
                        }
                        System.out.print("Digite o nome: ");
                        String nome = sc.nextLine();
                        if (!Validacao.validarNome(nome)) {
                            System.out.println("Erro: nome inválido");
                            continue;
                        }
                        System.out.print("Digite o endereço: ");
                        String endereco = sc.nextLine();
                        System.out.print("Digite o gênero: ");
                        String genero = sc.nextLine();
                        System.out.print("Digite a data de licença: ");
                        String dataLicenca = sc.nextLine();
                        System.out.print("Digite a educação: ");
                        String educacao = sc.nextLine();
                        System.out.print("Digite a data de nascimento: ");
                        String dataNascimento = sc.nextLine();
                        System.out.print("Digite a classe econômica: ");
                        String classeEconomica = sc.nextLine();
                        Cliente cliente = new ClientePF(
                            nome, endereco, null,
                            cpf, genero, LocalDate.parse(dataLicenca), educacao,
                            LocalDate.parse(dataNascimento), classeEconomica
                        );
                        boolean cadastrado = seguradora.cadastrarCliente(cliente);
                        if (cadastrado) System.out.println("Cliente cadastrado com sucesso.");
                        else System.out.println("Erro ao cadastrar. Cliente já cadastrado.");
                    } else if (opCadastros == MenuCadastros.CADASTRAR_CLIENTE_PJ.getOperacao()) {
                        System.out.println("***********************************");
                        System.out.println("CADASTRAR CLIENTE PJ");
                        System.out.print("Digite o CNPJ: ");
                        String cnpj = sc.nextLine();
                        if (seguradora.buscarCliente(cnpj) != null) {
                            System.out.println("Erro: CNPJ já cadastrado");
                            continue;
                        }
                        if (!Validacao.validarCpf(cnpj)) {
                            System.out.println("Erro: CNPJ inválido");
                            continue;
                        }
                        System.out.print("Digite o nome: ");
                        String nome = sc.nextLine();
                        if (!Validacao.validarNome(nome)) {
                            System.out.println("Erro: nome inválido");
                            continue;
                        }
                        System.out.print("Digite o endereço: ");
                        String endereco = sc.nextLine();
                        System.out.print("Digite a data de fundação: ");
                        String dataFundacao = sc.nextLine();
                        System.out.print("Digite a quantidade de funcionários: ");
                        int qtdeFuncionarios = sc.nextInt();
                        sc.nextLine();
                        Cliente cliente = new ClientePJ(
                            nome, endereco, null,
                            cnpj, LocalDate.parse(dataFundacao), qtdeFuncionarios
                        );
                        boolean cadastrado = seguradora.cadastrarCliente(cliente);
                        if (cadastrado) System.out.println("Cliente cadastrado com sucesso.");
                        else System.out.println("Erro ao cadastrar. Cliente já cadastrado.");
                    } else if (opCadastros == MenuCadastros.VOLTAR.getOperacao()) {
                        rodarCadastros = false;
                    } else {
                        System.out.println("***********************************");
                        System.out.println("Digite uma opção válida, por favor.");
                    }                
                }
            } else if (op == MenuOperacoes.LISTAR.getOperacao()) {
                boolean rodarListar = true;
                while (rodarListar) {
                    System.out.print(
                        "***********************************\n" +
                        "MENU LISTAR\n" +
                        "(1) Listar clientes por seguradora\n" +
                        "(2) Listar sinistros por seguradora\n" +
                        "(3) Listar sinistros por cliente\n" +
                        "(4) Listar veículos por cliente\n" +
                        "(0) Voltar\n" +
                        "***********************************\n" +
                        "Digite a opção: "
                    );
                    int opListar = sc.nextInt();
                    sc.nextLine();
                    if (opListar == MenuListar.LISTAR_CLIENTES_POR_SEGURADORA.getOperacao()) {
                        System.out.println("***********************************");
                        System.out.println("LISTAR CLIENTES POR SEGURADORA");
                        System.out.println("Clientes PF:");
                        seguradora.listarClientes("PF");
                        System.out.println("Clientes PJ:");
                        seguradora.listarClientes("PJ");
                    } else if (opListar == MenuListar.LISTAR_SINISTROS_POR_SEGURADORA.getOperacao()) {
                        System.out.println("***********************************");
                        System.out.println("LISTAR SINISTROS POR SEGURADORA");
                        seguradora.listarSinistros();
                    } else if (opListar == MenuListar.LISTAR_SINISTROS_POR_CLIENTE.getOperacao()) {
                        System.out.println("***********************************");
                        System.out.println("LISTAR SINISTROS POR CLIENTE");
                        System.out.print("Digite o CPF ou CNPJ do cliente: ");
                        String idCliente = sc.nextLine();
                        seguradora.visualizarSinistros(idCliente);
                    } else if (opListar == MenuListar.LISTAR_VEICULOS_POR_CLIENTE.getOperacao()) {
                        System.out.println("***********************************");
                        System.out.println("LISTAR VEÍCULOS POR CLIENTE");
                        System.out.print("Digite o CPF ou CNPJ do cliente: ");
                        String idCliente = sc.nextLine();
                        Cliente cliente = seguradora.buscarCliente(idCliente);
                        if (cliente == null) {
                            System.out.println("Cliente não cadastrado.");
                            continue;
                        }
                        if (cliente.getListaVeiculos().size() == 0) {
                            System.out.println("Cliente não possui veículos.");
                            continue;
                        }
                        System.out.println("Lista de veículos:\n");
                        for (Veiculo veiculo : cliente.getListaVeiculos()) {
                            System.out.println(veiculo);
                        }
                    } else if (opListar == MenuListar.VOLTAR.getOperacao()) {
                        rodarListar = false;
                    } else {
                        System.out.println("***********************************");
                        System.out.println("Digite uma opção válida, por favor.");
                    }                
                }
            } else if (op == MenuOperacoes.EXCLUIR_CLIENTE.getOperacao()) {
                System.out.println("***********************************");
                System.out.println("EXCLUIR CLIENTE");
                System.out.print("Digite o CPF ou CNPJ do cliente: ");
                String idCliente = sc.nextLine();
                boolean achou = seguradora.removerCliente(idCliente);
                if (achou) System.out.println("Cliente excluído com sucesso.");
                else System.out.println("Cliente não encontrado.");
            } else if (op == MenuOperacoes.GERAR_SINISTRO.getOperacao()) {
                System.out.println("***********************************");
                System.out.println("GERAR SINISTRO");
                boolean gerado = seguradora.gerarSinistro();
                if (gerado) {
                    System.out.println("Sinistro gerado com sucesso.");
                } else {
                    System.out.println("Não foi possível gerar o sinistro.");
                }
            } else if (op == MenuOperacoes.TRANSFERIR_SEGURO.getOperacao()) {
                System.out.println("***********************************");
                System.out.println("TRANSFERIR SEGURO");
                System.out.print("Digite o CPF ou CNPJ do cliente de origem: ");
                String idOrigem = sc.nextLine();
                Cliente clienteOrigem = seguradora.buscarCliente(idOrigem);
                if (clienteOrigem == null) {
                    System.out.println("Erro: cliente de origem não encontrado.");
                    continue;
                }
                System.out.print("Digite o CPF ou CNPJ do cliente de destino: ");
                String idDestino = sc.nextLine();
                if (idOrigem.equals(idDestino)) {
                    System.out.println("Erro: cliente de origem é o mesmo que cliente de destino.");
                    continue;
                }
                Cliente clienteDestino = seguradora.buscarCliente(idDestino);
                if (clienteDestino == null) {
                    System.out.println("Erro: cliente de destino não encontrado.");
                    continue;
                }
                for (Veiculo veiculo : clienteOrigem.getListaVeiculos()) {
                    clienteDestino.getListaVeiculos().add(veiculo);
                }
                seguradora.removerCliente(idOrigem);
                seguradora.calcularPrecoSeguroCliente(clienteDestino);
                System.out.println("Seguro transferido.");
            } else if (op == MenuOperacoes.CALCULAR_RECEITA_SEGURADORA.getOperacao()) {
                System.out.println("***********************************");
                System.out.println("CALCULAR RECEITA SEGURADORA");
                System.out.println("Receita da seguradora: " + seguradora.calcularReceita());
            } else if (op == MenuOperacoes.SAIR.getOperacao()) {
                rodar = false;
            } else {
                System.out.println("***********************************");
                System.out.println("Digite uma opção válida, por favor.");
            }
        }
        sc.close();
    }

}
