package br.com.will.esqueciminhasenha.controller;

import android.content.Context;

import java.util.List;

import br.com.will.esqueciminhasenha.database.CartaoDatabase;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

public class CartaoController {

    final RoomCartaoDAO roomCartaoDAO;

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
