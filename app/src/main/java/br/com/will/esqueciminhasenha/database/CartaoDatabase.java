package br.com.will.esqueciminhasenha.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.model.Cartao;

@Database(entities = {Cartao.class}, version = 1, exportSchema = false)
public abstract class CartaoDatabase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "EsqueciMinhaSenha.db";

    public abstract RoomCartaoDAO getRoomCartaoDAO();

    public static CartaoDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, CartaoDatabase.class, NOME_BANCO_DE_DADOS)
                .build();
    }
}
