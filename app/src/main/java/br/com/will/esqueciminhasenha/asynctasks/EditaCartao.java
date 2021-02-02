package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

public class EditaCartao extends AsyncTask<Void, Void, Cartao> {

    private RoomCartaoDAO roomCartaoDAO;
    private Cartao cartao;

    public EditaCartao(RoomCartaoDAO roomCartaoDAO, Cartao cartao) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.cartao = cartao;
    }

    @Override
    protected Cartao doInBackground(Void... voids) {
        roomCartaoDAO.editar(cartao);
        return cartao;
    }
}
