package br.com.will.esqueciminhasenha.Controller;

import android.content.Context;

import java.util.List;

import br.com.will.esqueciminhasenha.Database.CartaoDatabase;
import br.com.will.esqueciminhasenha.Database.DAO.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.Model.Cartao;

public class CartaoController {

    RoomCartaoDAO roomCartaoDAO;

    public CartaoController(Context context) {
        roomCartaoDAO = CartaoDatabase.getInstance(context).getRoomCartaoDAO();
    }

    public void cadastrar(Cartao cartao) {
        roomCartaoDAO.salvar(cartao);
    }

    public List<Cartao> getListaDeCartoesSalvos() {
        return roomCartaoDAO.todos();
    }

    public void editar(Cartao cartao) {
        roomCartaoDAO.editar(cartao);
    }

    public void excluir(Cartao cartao) {
        roomCartaoDAO.deletar(cartao);
    }

    public Cartao ultimoRegistro() {
        return roomCartaoDAO.ultimoRegistro();
    }
}
