public enum MenuCadastros {
    CADASTRAR_CLIENTE_PF(1),
    CADASTRAR_CLIENTE_PJ(2),
    VOLTAR(0);

    private final int operacao;

    MenuCadastros(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return operacao;
    }
}
