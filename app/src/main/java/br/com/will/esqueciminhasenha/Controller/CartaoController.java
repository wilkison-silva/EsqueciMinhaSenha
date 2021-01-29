package br.com.will.esqueciminhasenha.Controller;

import java.util.List;

import br.com.will.esqueciminhasenha.DAO.CartaoDAO;
import br.com.will.esqueciminhasenha.Model.Cartao;

public class CartaoController {

    CartaoDAO cartaoDAO;

    public CartaoController() {
        cartaoDAO = new CartaoDAO();
    }

    public boolean cadastrar(Cartao cartao) {
        return cartaoDAO.cadastrar(cartao);
    }

    public List<Cartao> getListaDeCartoesSalvos() {
        return cartaoDAO.getListaCartoesSalvos();
    }

    public boolean editar(Cartao cartao, int posicao){
        return cartaoDAO.editar(cartao,posicao);
    }

    public boolean excluir(int posicao){
        return false;
    }

}
