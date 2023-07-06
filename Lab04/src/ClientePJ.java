import java.time.LocalDate;
import java.util.List;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(
        String nome, String endereco, List<Veiculo> listaVeiculos,
        String cnpj, LocalDate dataFundacao, int qtdeFuncionarios
    ) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    @Override    
    public String toString() {
        String ret = (
            "CLIENTE PJ\n" +
            "Nome: " + getNome() + "\n" +
            "Endereço: " + getEndereco() + "\n" +
            "CNPJ: " + cnpj + "\n" +
            "Data de fundação: " + dataFundacao + "\n" +
            "Lista de veículos: "
        );
        List<Veiculo> listaVeiculos = getListaVeiculos();
        if (listaVeiculos == null) {
            ret += "nenhum";
        } else {
            boolean primeiro = true;
            for (Veiculo veiculo : getListaVeiculos()) {
                if (primeiro) primeiro = false;
                else ret += ", ";
                ret += veiculo.getPlaca();
            }
        }
        ret += "\n";
        return ret;
    }

    /**
     * Calcula o score do cliente PJ.
     * @return o score do cliente
     */
    @Override
    public double calcularScore() {
        // Calcula o score com base na quantidade de funcionários e de veículos do cliente PJ
        double score = (
            CalcSeguro.VALOR_BASE.getValor() *
            (1 + qtdeFuncionarios / 100.0) *
            getListaVeiculos().size()
        );
        return score;
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
}
