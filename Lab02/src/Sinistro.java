public class Sinistro {
    private static int numSinistros = 0;
    
    private int id;
    private String data;
    private String endereco;
    
    public Sinistro(String data, String endereco) {
        id = gerarId();
        this.data = data;
        this.endereco = endereco;
        numSinistros++;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }		
}
