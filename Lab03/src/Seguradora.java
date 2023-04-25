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
     * Cadastra um cliente.
     * @param novoCliente o cliente
     * @return true se o cliente tiver sido cadastrado com sucesso, false caso contrário
     */
    public boolean cadastrarCliente(Cliente novoCliente) {
        if (novoCliente instanceof ClientePF) {
            String cpf = ((ClientePF) novoCliente).getCpf();
            for (Cliente cliente : listaClientes) {
                if (cliente instanceof ClientePF && ((ClientePF) cliente).getCpf() == cpf) {
                    return false;
                }
            }
        } else {
            String cnpj = ((ClientePJ) novoCliente).getCnpj();
            for (Cliente cliente : listaClientes) {
                if (cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj() == cnpj) {
                    return false;
                }
            }
        }

        listaClientes.add(novoCliente);

        return true;
    }

    /**
     * Remove um cliente.
     * @param idCliente CPF ou CNPJ do cliente
     * @return true se o cliente tiver sido removido com sucesso, false caso contrário
     */
    public boolean removerCliente(String idCliente) {
        for (Cliente cliente : listaClientes) {
            if (
                (cliente instanceof ClientePF && ((ClientePF) cliente).getCpf() == idCliente) ||
                (cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj() == idCliente)
            ) {
                listaClientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    /**
     * Lista clientes
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
     * Gera um sinistro com base na lista de clientes da seguradora
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
     * Visualiza um sinistro de um cliente
     * @param idCliente o CPF ou CNPJ do cliente
     * @return true se o sinistro tiver sido visualizado com sucesso, false caso contrário
     */
    public boolean visualizarSinistro(String idCliente) {
        for (Sinistro sinistro : listaSinistros) {
            Cliente cliente = sinistro.getCliente();
            if (
                (cliente instanceof ClientePF && ((ClientePF) cliente).getCpf() == idCliente) ||
                (cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj() == idCliente)
            ) {
                System.out.println(sinistro);
                return true;
            }
        }
        return false;
    }

    /**
     * Lista sinistros
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
}
