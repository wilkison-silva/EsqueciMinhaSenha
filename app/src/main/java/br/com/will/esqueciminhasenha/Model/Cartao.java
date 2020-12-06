package br.com.will.esqueciminhasenha.Model;

public class Cartao {

    private String senha;
    private String categoria;
    private String login;
    private String descricao;
    private String cor;


    public Cartao() {

    }

    public Cartao(String descricao, String categoria, String login, String senha, String cor) {
        this.senha = senha;
        this.categoria = categoria;
        this.login = login;
        this.descricao = descricao;
        this.cor = cor;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
