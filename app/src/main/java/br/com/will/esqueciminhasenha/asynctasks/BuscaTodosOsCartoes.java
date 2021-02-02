package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.Console;
import java.util.List;
import java.util.logging.Handler;

import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.ui.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.ui.adapter.listener.AsyncTaskListener;

public class BuscaTodosOsCartoes extends AsyncTask <Void, Void, List<Cartao>> {

    private RoomCartaoDAO roomCartaoDAO;
    private AsyncTaskListener asyncTaskListener;

    public BuscaTodosOsCartoes(RoomCartaoDAO roomCartaoDAO, AsyncTaskListener asyncTaskListener) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.asyncTaskListener = asyncTaskListener;
    }

    @Override
    protected List<Cartao> doInBackground(Void[] objects) {
        return roomCartaoDAO.todos();
    }

    @Override
    protected void onPostExecute(List<Cartao> list) {
        super.onPostExecute(list);
        asyncTaskListener.onTodosOsCartoes(list);
    }
}
