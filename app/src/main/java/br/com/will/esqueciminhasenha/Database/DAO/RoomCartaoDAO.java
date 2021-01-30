package br.com.will.esqueciminhasenha.Database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;

@Dao
public interface RoomCartaoDAO {

    @Insert
    public abstract void salvar(Cartao cartao);

    @Update
    public abstract void editar(Cartao cartao);

    @Delete
    public abstract void deletar(Cartao cartao);

    @Query("SELECT * FROM cartao")
    public abstract List<Cartao> todos();
}
