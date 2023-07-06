public enum MenuOperacoes {
    CADASTROS(1),
    LISTAR(2),
    EXCLUIR_CLIENTE(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    SAIR(0);

    private final int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return operacao;
    }
}
