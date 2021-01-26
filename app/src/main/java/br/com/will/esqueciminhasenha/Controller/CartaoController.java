package br.com.will.esqueciminhasenha.Controller;

import java.util.List;

import br.com.will.esqueciminhasenha.DAO.CartaoDAO;
import br.com.will.esqueciminhasenha.Model.Cartao;

public class CartaoController {

    CartaoDAO cartaoDAO;

    public CartaoController() {

        cartaoDAO = new CartaoDAO();

    }

    public boolean cadastrar(Cartao cartao){

        return cartaoDAO.cadastrar(cartao);

    }

    public List<Cartao> getListaDeCartoesSalvos(){
        return cartaoDAO.getListaCartoesSalvos();
    }

}
