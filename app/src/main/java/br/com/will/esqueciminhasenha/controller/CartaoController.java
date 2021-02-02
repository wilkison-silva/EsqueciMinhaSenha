package br.com.will.esqueciminhasenha.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.asynctasks.BuscaTodosOsCartoes;
import br.com.will.esqueciminhasenha.asynctasks.BuscaUltimoRegistro;
import br.com.will.esqueciminhasenha.asynctasks.CadastraCartao;
import br.com.will.esqueciminhasenha.asynctasks.EditaCartao;
import br.com.will.esqueciminhasenha.asynctasks.ExcluiCartao;
import br.com.will.esqueciminhasenha.database.CartaoDatabase;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.ui.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.ui.adapter.listener.BuscaTodosOsCartoesListener;
import br.com.will.esqueciminhasenha.ui.adapter.listener.BuscaUtimoCartaoListener;

public class CartaoController {

    final RoomCartaoDAO roomCartaoDAO;


    public CartaoController(Context context) {
        roomCartaoDAO = CartaoDatabase.getInstance(context).getRoomCartaoDAO();
    }

    public void cadastrar(Cartao cartao) {
        new CadastraCartao(roomCartaoDAO, cartao).execute();
    }

    public void getListaDeCartoesSalvos(AdapterRecyclerView adapterRecyclerView) {
        new BuscaTodosOsCartoes(roomCartaoDAO, new BuscaTodosOsCartoesListener() {
            @Override
            public void onTodosOsCartoes(List<Cartao> cartaoList) {
               adapterRecyclerView.atualizarLista(cartaoList);
            }
        }).execute();
    }

    public void editar(Cartao cartao) {
        new EditaCartao(roomCartaoDAO, cartao).execute();
    }

    public void excluir(Cartao cartao) {
        new ExcluiCartao(roomCartaoDAO, cartao).execute();
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




