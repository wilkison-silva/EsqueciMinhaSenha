package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import br.com.will.esqueciminhasenha.asynctasks.interfaces.EditaCartaoListener;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

public class EditaCartaoTask extends AsyncTask<Void, Void, Cartao> {

    private RoomCartaoDAO roomCartaoDAO;
    private Cartao cartao;
    private EditaCartaoListener editaCartaoListener;

    public EditaCartaoTask(RoomCartaoDAO roomCartaoDAO, Cartao cartao, EditaCartaoListener editaCartaoListener) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.cartao = cartao;
        this.editaCartaoListener = editaCartaoListener;
    }

    @Override
    protected Cartao doInBackground(Void... voids) {
        roomCartaoDAO.editar(cartao);
        return cartao;
    }

    @Override
    protected void onPostExecute(Cartao cartao) {
        super.onPostExecute(cartao);
        editaCartaoListener.edicaoFinalizada();
    }
}
