package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import br.com.will.esqueciminhasenha.asynctasks.interfaces.CadastraCartaoListener;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

public class CadastraCartaoTask extends AsyncTask <Void, Void, Cartao> {

    private RoomCartaoDAO roomCartaoDAO;
    private Cartao cartao;
    private CadastraCartaoListener cadastraCartaoListener;

    public CadastraCartaoTask(RoomCartaoDAO roomCartaoDAO, Cartao cartao, CadastraCartaoListener cadastraCartaoListener) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.cartao = cartao;
        this.cadastraCartaoListener = cadastraCartaoListener;
    }


    @Override
    protected Cartao doInBackground(Void... voids) {
        roomCartaoDAO.salvar(cartao);
        return null;
    }

    @Override
    protected void onPostExecute(Cartao cartao) {
        super.onPostExecute(cartao);
        cadastraCartaoListener.cadastroFinalizado();
    }
}
