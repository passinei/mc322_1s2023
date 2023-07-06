import java.util.Date;

public class Sinistro {
    private static int numSinistros = 0;
    
    private final int id;
    private Date data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    public Sinistro(Date data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
        numSinistros++;
    }
    
    public String toString() {
        return (
            "SINISTRO\n" +
            "ID: " + id + "\n" +
            "Data: " + data + "\n" +
            "Endereço: " + endereco + "\n" + 
            "Seguradora: " + seguradora.getNome() + "\n" + 
            "Veículo: " + veiculo.getPlaca() + "\n" +
            "Cliente: " + cliente.getNome() + "\n"
        );
    }

    /**
     * Gera um ID para o sinistro.
     * @return o ID gerado para o sinistro
     */
    private int gerarId() {
        // Gera o ID baseado na quantidade de sinistros instanciados
        return numSinistros + 1;
    }
    
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
