package br.com.will.esqueciminhasenha.asynctasks;

import android.os.AsyncTask;

import br.com.will.esqueciminhasenha.asynctasks.interfaces.ExcluiCartaoListener;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

public class ExcluiCartaoTask extends AsyncTask <Void, Void, Cartao> {


    private RoomCartaoDAO roomCartaoDAO;
    private Cartao cartao;
    private ExcluiCartaoListener excluiCartaoListener;

    public ExcluiCartaoTask(RoomCartaoDAO roomCartaoDAO, Cartao cartao, ExcluiCartaoListener excluiCartaoListener) {
        this.roomCartaoDAO = roomCartaoDAO;
        this.cartao = cartao;
        this.excluiCartaoListener = excluiCartaoListener;
    }

    @Override
    protected Cartao doInBackground(Void... voids) {
        roomCartaoDAO.deletar(cartao);
        return cartao;
    }

    @Override
    protected void onPostExecute(Cartao cartao) {
        super.onPostExecute(cartao);
        excluiCartaoListener.excluirCartaoFinalizado();
    }
}
