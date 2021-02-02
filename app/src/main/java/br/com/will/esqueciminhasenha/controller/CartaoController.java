package br.com.will.esqueciminhasenha.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.asynctasks.BuscaTodosOsCartoesTask;
import br.com.will.esqueciminhasenha.asynctasks.CadastraCartaoTask;
import br.com.will.esqueciminhasenha.asynctasks.EditaCartaoTask;
import br.com.will.esqueciminhasenha.asynctasks.ExcluiCartaoTask;
import br.com.will.esqueciminhasenha.asynctasks.interfaces.CadastraCartaoListener;
import br.com.will.esqueciminhasenha.asynctasks.interfaces.EditaCartaoListener;
import br.com.will.esqueciminhasenha.asynctasks.interfaces.ExcluiCartaoListener;
import br.com.will.esqueciminhasenha.database.CartaoDatabase;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.ui.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.asynctasks.interfaces.BuscaTodosOsCartoesListener;

public class CartaoController {

    final RoomCartaoDAO roomCartaoDAO;


    public CartaoController(Context context) {
        roomCartaoDAO = CartaoDatabase.getInstance(context).getRoomCartaoDAO();
    }

    public void cadastrar(Cartao cartao, CadastraCartaoListener cadastraCartaoListener) {
        new CadastraCartaoTask(roomCartaoDAO, cartao, cadastraCartaoListener).execute();
    }

    /*public void getListaDeCartoesSalvos(AdapterRecyclerView adapterRecyclerView) {
        new BuscaTodosOsCartoesTask(roomCartaoDAO, new BuscaTodosOsCartoesListener() {
            @Override
            public void onTodosOsCartoes(List<Cartao> cartaoList) {
               adapterRecyclerView.atualizarLista(cartaoList);
            }
        }).execute();
    }*/

    public void getListaDeCartoesSalvos(BuscaTodosOsCartoesListener buscaTodosOsCartoesListener) {
        new BuscaTodosOsCartoesTask(roomCartaoDAO, buscaTodosOsCartoesListener).execute();
    }

    public void editar(Cartao cartao, EditaCartaoListener editaCartaoListener) {
        new EditaCartaoTask(roomCartaoDAO, cartao, editaCartaoListener).execute();
    }

    public void excluir(Cartao cartao, ExcluiCartaoListener excluiCartaoListener) {
        new ExcluiCartaoTask(roomCartaoDAO, cartao, excluiCartaoListener).execute();
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




