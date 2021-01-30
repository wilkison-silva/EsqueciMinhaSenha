package br.com.will.esqueciminhasenha.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cartao implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    private String senha;
    private String categoria;
    private String login;
    private String descricao;
    private String corCartao;
    private String corTexto;


    public Cartao() {

    }

    @Ignore
    public Cartao(String descricao, String categoria, String login, String senha, String corCartao, String corTexto) {
        this.senha = senha;
        this.categoria = categoria;
        this.login = login;
        this.descricao = descricao;
        this.corCartao = corCartao;
        this.corTexto = corTexto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCorCartao() {
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
