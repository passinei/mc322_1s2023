import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;
    
    public ClientePF(
        String nome, String endereco, List<Veiculo> listaVeiculos,
        String cpf, String genero, Date dataLicenca,
        String educacao, Date dataNascimento, String classeEconomica
    ) {
        super(nome, endereco, listaVeiculos);
        this.cpf = cpf;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
    }

    @Override    
    public String toString() {
        String ret = (
            "CLIENTE PF\n" +
            "Nome: " + getNome() + "\n" +
            "Endereço: " + getEndereco() + "\n" +
            "CPF: " + cpf + " (" + (validarCpf() ? "válido" : "inválido") + ")\n" +
            "Gênero: " + genero + "\n" +
            "Data da licença: " + dataLicenca + "\n" +
            "Educação: " + educacao + "\n" +
            "Data de nascimento: " + dataNascimento + "\n" +
            "Classe econômica: " + classeEconomica + "\n" +
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
     * Valida o CPF do cliente PF.
     * @return true se o CPF for válido, false caso contrário
     */
    public boolean validarCpf() {
        // Cria a string CPF contendo apenas dígitos
        String cpf = "";
        for (int i = 0; i < this.cpf.length(); ++i) {
            if (Character.isDigit(this.cpf.charAt(i))) cpf += this.cpf.charAt(i);
        }
        
        // Verifica se o tamanho da string é igual a 11
        if (cpf.length() != 11) return false;
        
        // Verifica se há dígitos distintos no CPF
        boolean todosIguais = true;
        for (int i = 1; i < cpf.length(); ++i) {
            if (cpf.charAt(i) != cpf.charAt(i - 1)) todosIguais = false;
        }
        if (todosIguais) return false;
        
        // Verifica o primeiro dígito verificador (10º dígito)
        int soma = 0;
        for (int i = 0; i < 9; ++i) {
            soma += (10 - i) * Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }
        if (soma < 2) soma = 0;
        else soma = 11 - soma % 11;
        if (Character.getNumericValue(cpf.charAt(9)) != soma) return false;
        
        // Verifica o segundo dígito verificador (11º dígito)
        soma = 0;
        for (int i = 0; i < 10; ++i) {
            soma += (11 - i) * Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }
        if (soma < 2) soma = 0;
        else soma = 11 - soma % 11;
        if (Character.getNumericValue(cpf.charAt(10)) != soma) return false;
        
        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
}
