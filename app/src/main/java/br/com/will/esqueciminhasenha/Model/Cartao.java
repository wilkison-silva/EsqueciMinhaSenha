package br.com.will.esqueciminhasenha.Model;

public class Cartao {

    private String senha;
    private String categoria;
    private String login;
    private String descricao;
    private String corCartao;
    private String corTexto;


    public Cartao() {

    }

    public Cartao(String descricao, String categoria, String login, String senha, String corCartao, String corTexto) {
        this.senha = senha;
        this.categoria = categoria;
        this.login = login;
        this.descricao = descricao;
        this.corCartao = corCartao;
        this.corTexto = corTexto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor_cartao() {
        return corCartao;
    }

    public void setCorCartao(String corCartao) {
        this.corCartao = corCartao;
    }

    public String getCorTexto() {
        return corTexto;
    }

    public void setCorTexto(String corTexto) {
        this.corTexto = corTexto;
    }
}
