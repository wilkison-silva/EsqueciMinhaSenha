package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

public class CadastraCartao extends AsyncTask <Void, Void, Cartao> {

    private RoomCartaoDAO roomCartaoDAO;
    private Cartao cartao;

    public CadastraCartao(RoomCartaoDAO roomCartaoDAO, Cartao cartao) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.cartao = cartao;
    }


    @Override
    protected Cartao doInBackground(Void... voids) {
        roomCartaoDAO.salvar(cartao);
        return null;
    }

    @Override
    protected void onPostExecute(Cartao cartao) {
        super.onPostExecute(cartao);

    }
}
