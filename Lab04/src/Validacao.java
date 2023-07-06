public class Validacao {
    /**
     * Valida um CPF.
     * @return true se o CPF for válido, false caso contrário
     */
    public static boolean validarCpf(String CPF) {
        // Cria a string cpf contendo apenas dígitos
        String cpf = "";
        for (int i = 0; i < CPF.length(); ++i) {
            if (Character.isDigit(CPF.charAt(i))) cpf += CPF.charAt(i);
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

    /**
     * Valida um CNPJ.
     * @return true se o CNPJ for válido, false caso contrário
     */
    public static boolean validarCnpj(String CNPJ) {
        // Cria a string cnpj contendo apenas dígitos
        String cnpj = "";
        for (int i = 0; i < CNPJ.length(); ++i) {
            if (Character.isDigit(CNPJ.charAt(i))) cnpj += CNPJ.charAt(i);
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

    /**
     * Valida um nome.
     * @return true se o nome for válido, false caso contrário
     */
    public static boolean validarNome(String nome) {
        // Verifica se cada caractere é ou espaço ou letra.
        for (int i = 0; i < nome.length(); ++i) {
            char ch = nome.charAt(i);
            if (ch != ' ' && !Character.isAlphabetic(ch)) return false;
        }
        return true;
    }
}
