public class ClientePJ {
    private int codigoClientePJ;
    // quando trabalha com chave estrangeira é melhor ser mais específico para não se confundir
    private String nome;
    private String cnpj;
    private EnderecoPJ enderecoPJ;
    
    public int getCodigoClientePJ() {
        return codigoClientePJ;
    }
    public void setCodigoClientePJ(int codigoClientePJ) {
        this.codigoClientePJ = codigoClientePJ;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public EnderecoPJ getEnderecoPJ() {
        return enderecoPJ;
    }
    public void setEnderecoPJ(EnderecoPJ enderecoPJ) {
        this.enderecoPJ = enderecoPJ;
    }
}
