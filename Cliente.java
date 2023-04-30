import java.time.LocalDate;

public class Cliente {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dtaNasc;
    private String endRua;
    private String endBairro;
    private int endNroCasa;
    private String endComplemento;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public LocalDate getDtaNasc() {
        return dtaNasc;
    }
    public void setDtaNasc(LocalDate dtaNasc) {
        this.dtaNasc = dtaNasc;
    }
    public String getEndRua() {
        return endRua;
    }
    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }
    public String getEndBairro() {
        return endBairro;
    }
    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }
    public int getEndNroCasa() {
        return endNroCasa;
    }
    public void setEndNroCasa(int endNroCasa) {
        this.endNroCasa = endNroCasa;
    }
    public String getEndComplemento() {
        return endComplemento;
    }
    public void setEndComplemento(String endComplemento) {
        this.endComplemento = endComplemento;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
