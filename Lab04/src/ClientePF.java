import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;
    
    public ClientePF(
        String nome, String endereco, List<Veiculo> listaVeiculos,
        String cpf, String genero, LocalDate dataLicenca,
        String educacao, LocalDate dataNascimento, String classeEconomica
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
            "CPF: " + cpf + "\n" +
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
     * Calcula o score do cliente PF.
     * @return o score do cliente
     */
    @Override
    public double calcularScore() {
        // Calcula a idade do cliente PF
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        // Determina o fator com base na idade do cliente PF
        double fatorIdade = 0.0;
        if (idade >= 18 && idade < 30) fatorIdade = CalcSeguro.FATOR_18_30.getValor();
        else if (idade >= 30 && idade < 60) fatorIdade = CalcSeguro.FATOR_30_60.getValor();
        else if (idade >= 60 && idade < 90) fatorIdade = CalcSeguro.FATOR_30_60.getValor();

        // Calcula o score com base na idade e na quantidade de veículos do cliente PF
        double score = (
            CalcSeguro.VALOR_BASE.getValor() *
            fatorIdade *
            getListaVeiculos().size()
        );
        return score;
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

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
}
