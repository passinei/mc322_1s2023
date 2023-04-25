import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;

    public ClientePJ(
        String nome, String endereco, List<Veiculo> listaVeiculos,
        String cnpj, Date dataFundacao
    ) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    @Override    
    public String toString() {
        String ret = (
            "CLIENTE PJ\n" +
            "Nome: " + getNome() + "\n" +
            "Endereço: " + getEndereco() + "\n" +
            "CNPJ: " + cnpj + " (" + (validarCnpj() ? "válido" : "inválido") + ")\n" +
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
     * Valida o CNPJ do cliente PJ.
     * @return true se o CNPJ for válido, false caso contrário
     */
    public boolean validarCnpj() {
        // Cria a string CNPJ contendo apenas dígitos
        String cnpj = "";
        for (int i = 0; i < this.cnpj.length(); ++i) {
            if (Character.isDigit(this.cnpj.charAt(i))) cnpj += this.cnpj.charAt(i);
        }
        
        // Verifica se o tamanho da string é igual a 14
        if (cnpj.length() != 14) return false;

        // Cria lista de multiplicadores
        int[] multiplicadores = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Verifica o primeiro dígito verificador (13º dígito)
        int soma = 0;
        for (int i = 0; i < 12; ++i) {
            soma += multiplicadores[i + 1] * Integer.parseInt(String.valueOf(cnpj.charAt(i)));
        }
        if (soma < 2) soma = 0;
        else soma = 11 - soma % 11;
        if (Character.getNumericValue(cnpj.charAt(11)) != soma) return false;
        
        // Verifica o segundo dígito verificador (14º dígito)
        soma = 0;
        for (int i = 0; i < 13; ++i) {
            soma += multiplicadores[i] * Integer.parseInt(String.valueOf(cnpj.charAt(i)));
        }
        if (soma < 2) soma = 0;
        else soma = 11 - soma % 11;
        if (Character.getNumericValue(cnpj.charAt(12)) != soma) return false;
        
        return true;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}
