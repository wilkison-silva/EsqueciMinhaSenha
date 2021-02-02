package br.com.will.esqueciminhasenha.ui.adapter.listener;

import java.util.List;

import br.com.will.esqueciminhasenha.model.Cartao;

public interface AsyncTaskListener {

    void onTodosOsCartoes(List<Cartao> cartaoList);

}
