package br.com.will.esqueciminhasenha.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.will.esqueciminhasenha.model.Cartao;

@Dao
public interface RoomCartaoDAO {

    @Insert
    void salvar(Cartao cartao);

    @Update
    void editar(Cartao cartao);

    @Delete
    void deletar(Cartao cartao);

    @Query("SELECT * FROM cartao")
    List<Cartao> todos();

    @Query("SELECT * FROM cartao ORDER BY id DESC LIMIT 1")
    Cartao ultimoRegistro();
}
