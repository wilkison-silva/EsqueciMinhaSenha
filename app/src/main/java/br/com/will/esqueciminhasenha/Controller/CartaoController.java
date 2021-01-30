package br.com.will.esqueciminhasenha.Controller;

import android.content.Context;
import android.util.Log;

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
        Log.i("cartão", "cartão salvar controller id: " + cartao.getId());
        roomCartaoDAO.salvar(cartao);
    }

    public List<Cartao> getListaDeCartoesSalvos() {
        return roomCartaoDAO.todos();
    }

    public void editar(Cartao cartao) {
        Log.i("cartão", "cartão editar id: " + cartao.getId());
        roomCartaoDAO.editar(cartao);
    }

    public void excluir(Cartao cartao) {
        Log.i("cartão", "cartão excluir id: " + cartao.getId());
        roomCartaoDAO.deletar(cartao);
    }

    public Cartao ultimoRegistro() {
        return roomCartaoDAO.ultimoRegistro();
    }
}
