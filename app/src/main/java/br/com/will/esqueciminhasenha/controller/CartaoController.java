package br.com.will.esqueciminhasenha.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import br.com.will.esqueciminhasenha.asynctasks.BuscaTodosOsCartoes;
import br.com.will.esqueciminhasenha.database.CartaoDatabase;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.ui.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.ui.adapter.listener.AsyncTaskListener;

public class CartaoController {

    final RoomCartaoDAO roomCartaoDAO;


    public CartaoController(Context context) {
        roomCartaoDAO = CartaoDatabase.getInstance(context).getRoomCartaoDAO();
    }

    public void cadastrar(Cartao cartao) {
        roomCartaoDAO.salvar(cartao);
    }

    public void getListaDeCartoesSalvos(AdapterRecyclerView adapterRecyclerView) {
        new BuscaTodosOsCartoes(roomCartaoDAO, new AsyncTaskListener() {
            @Override
            public void onTodosOsCartoes(List<Cartao> cartaoList) {
                adapterRecyclerView.atualizarLista(cartaoList);
            }
        }).execute();
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

    public List<Cartao> pesquisar(String descricao, List<Cartao> list) {
        List<Cartao> resultado = new ArrayList<>();
        if (descricao.equals("")) {
            resultado = list;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDescricao().contains(descricao)) {
                    resultado.add(list.get(i));
                }
            }
        }
        return resultado;
    }

}
