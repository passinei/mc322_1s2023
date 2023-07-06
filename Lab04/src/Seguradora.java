import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaClientes = new ArrayList<Cliente>();
    }

    /**
     * Busca um cliente na seguradora.
     * @param idCliente CPF ou CNPJ do cliente
     * @return o cliente se ele for encontrado, null caso contrário
     */
    Cliente buscarCliente(String idCliente) {
        for (Cliente cliente : listaClientes) {
            if (
                (cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(idCliente)) ||
                (cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(idCliente))
            ) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Cadastra um cliente.
     * @param novoCliente o cliente
     * @return true se o cliente tiver sido cadastrado com sucesso, false caso contrário
     */
    public boolean cadastrarCliente(Cliente cliente) {
        if (cliente instanceof ClientePF) {
            if (buscarCliente(((ClientePF) cliente).getCpf()) != null) return false;
        } else {
            if (buscarCliente(((ClientePJ) cliente).getCnpj()) != null) return false;
        }
        calcularPrecoSeguroCliente(cliente);
        listaClientes.add(cliente);
        return true;
    }

    /**
     * Remove um cliente.
     * @param idCliente CPF ou CNPJ do cliente
     * @return true se o cliente tiver sido removido com sucesso, false caso contrário
     */
    public boolean removerCliente(String idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            return false;
        } else {
            listaClientes.remove(cliente);
            return true;
        }
    }

    /**
     * Lista clientes.
     * @param tipoCliente o tipo do cliente (PF ou PJ)
     */
    public void listarClientes(String tipoCliente) {
        boolean listado = false;
        for (Cliente cliente : listaClientes) {
            if (
                (tipoCliente.equals("PF") && cliente instanceof ClientePF) ||
                (tipoCliente.equals("PJ") && cliente instanceof ClientePJ)
            ) {
                System.out.println(cliente);
                listado = true;
            }
        }
        if (!listado) System.out.println("Não há clientes do tipo " + tipoCliente  + ".");
    }

    /**
     * Gera um sinistro com base na lista de clientes da seguradora.
     * @return true se o sinistro tiver sido gerado com sucesso, false caso contrário
     */
    public boolean gerarSinistro() {
        if (listaClientes.size() == 0) return false;
        for (Cliente cliente : listaClientes) {
            if (cliente.getListaVeiculos().size() != 0) {
                Veiculo veiculo = cliente.getListaVeiculos().get(0);
                String endereco = cliente.getEndereco();
                Seguradora seguradora = this;
                Date data = new Date();
                Sinistro sinistro = new Sinistro(data, endereco, seguradora, veiculo, cliente);
                listaSinistros.add(sinistro);
                return true;
            }
        }
        return false;
    }

    /**
     * Visualiza sinistros de um cliente.
     * @param idCliente o CPF ou CNPJ do cliente
     */
    public void visualizarSinistros(String idCliente) {
        if (buscarCliente(idCliente) == null) {
            System.out.println("Cliente não está cadastrado.");
            return;
        }
        boolean achou = false;
        for (Sinistro sinistro : listaSinistros) {
            Cliente cliente = sinistro.getCliente();
            if (
                (cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(idCliente)) ||
                (cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(idCliente))
            ) {
                System.out.println(sinistro);
                achou = true;
            }
        }
        if (!achou) System.out.println("Cliente não possui sinistros");
    }

    /**
     * Lista sinistros da seguradora.
     */
    public void listarSinistros() {
        if (listaSinistros.size() == 0) {
            System.out.println("Não há sinistros.");
        } else {
            for (Sinistro sinistro : listaSinistros) {
                System.out.println(sinistro);
            }            
        }
    }

    /**
     * Calcula o valor do seguro de um cliente da seguradora.
     * @param cliente o cliente
     * @return o valor do seguro
     */
    public double calcularPrecoSeguroCliente(Cliente cliente) {
        // Conta a quantidade de sinistros do cliente
        int qtdeSinistros = 0;
        for (Sinistro sinistro : listaSinistros) {
            if (cliente.equals(sinistro.getCliente())) ++qtdeSinistros;
        }

        // Faz o cálculo usando o score e a quantidade de sinistros do cliente
        double valorSeguro = cliente.calcularScore() * (1 + qtdeSinistros);
        // Atribui o valor ao cliente
        cliente.setValorSeguro(valorSeguro);
        return valorSeguro;
    }

    /**
     * Calcula o valor da receita da seguradora.
     * @return
     */
    public double calcularReceita() {
        // Soma os valores de seguro de cada cliente da seguradora
        double receita = 0.0;
        for (Cliente cliente : listaClientes) {
            receita += calcularPrecoSeguroCliente(cliente);
        }
        return receita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Sinistro> getListaSinistros() {
        return new ArrayList<>(listaSinistros);
    }

    public List<Cliente> getListaClientes() {
        return new ArrayList<>(listaClientes);
    }
}
