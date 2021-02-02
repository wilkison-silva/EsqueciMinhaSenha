package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import java.util.List;

import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.ui.adapter.listener.BuscaTodosOsCartoesListener;

public class BuscaTodosOsCartoes extends AsyncTask <Void, Void, List<Cartao>> {

    private RoomCartaoDAO roomCartaoDAO;
    private BuscaTodosOsCartoesListener buscaTodosOsCartoesListener;

    public BuscaTodosOsCartoes(RoomCartaoDAO roomCartaoDAO, BuscaTodosOsCartoesListener buscaTodosOsCartoesListener) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.buscaTodosOsCartoesListener = buscaTodosOsCartoesListener;
    }

    @Override
    protected List<Cartao> doInBackground(Void[] objects) {
        return roomCartaoDAO.todos();
    }

    @Override
    protected void onPostExecute(List<Cartao> list) {
        super.onPostExecute(list);
        buscaTodosOsCartoesListener.onTodosOsCartoes(list);
    }
}
