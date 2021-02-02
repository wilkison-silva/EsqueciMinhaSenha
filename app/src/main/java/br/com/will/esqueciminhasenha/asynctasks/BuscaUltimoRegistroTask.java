package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.asynctasks.interfaces.BuscaUtimoCartaoListener;

public class BuscaUltimoRegistroTask extends AsyncTask<Void, Void, Cartao> {

    private RoomCartaoDAO roomCartaoDAO;
    private BuscaUtimoCartaoListener buscaUtimoCartaoListener;

    public BuscaUltimoRegistroTask(RoomCartaoDAO roomCartaoDAO, BuscaUtimoCartaoListener buscaUtimoCartaoListener) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.buscaUtimoCartaoListener = buscaUtimoCartaoListener;
    }


    @Override
    protected Cartao doInBackground(Void... voids) {
        return roomCartaoDAO.ultimoRegistro();

    }

    @Override
    protected void onPostExecute(Cartao cartao) {
        super.onPostExecute(cartao);
        buscaUtimoCartaoListener.onBuscaUltimoCartao(cartao);
    }
}
