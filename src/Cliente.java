public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;
    
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    /**
     * Valida o CPF do cliente.
     * @return true se o CPF for válido, false caso contrário
     */
    public boolean validarCpf() {
        // Cria a string CPF contendo apenas dígitos
        String cpf = "";
        for (int i = 0; i < this.cpf.length(); ++i) {
            if (Character.isDigit(this.cpf.charAt(i))) cpf += this.cpf.charAt(i);
        }
        
        // Verifica o tamanho da string é igual a 11
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
    
    /**
     * Retorna uma string representando o cliente.
     * @return uma string com todas informações do cliente
     */
    public String toString() {
        return (
            "CLIENTE\n" +
            "Nome: " + nome + "\n" +
            "CPF: " + cpf + " (" + (validarCpf() ? "válido" : "inválido") + ")\n" +
            "Data de nascimento: " + dataNascimento + "\n" + 
            "Idade: " + idade + "\n" + 
            "Endereço: " + endereco + "\n"
        );
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
