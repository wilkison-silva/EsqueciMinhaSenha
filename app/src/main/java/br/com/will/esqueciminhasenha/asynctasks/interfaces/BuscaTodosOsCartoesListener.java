package br.com.will.esqueciminhasenha.asynctasks.interfaces;

import java.util.List;

import br.com.will.esqueciminhasenha.model.Cartao;

public interface BuscaTodosOsCartoesListener {

    void onTodosOsCartoes(List<Cartao> cartaoList);

}
