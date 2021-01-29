package br.com.will.esqueciminhasenha.ItemHelpers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.will.esqueciminhasenha.Adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.Controller.CartaoController;
import br.com.will.esqueciminhasenha.Model.Cartao;

public class CartaoItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private AdapterRecyclerView adapterRecyclerView;
    private CartaoController cartaoController;

    public CartaoItemTouchHelperCallback(AdapterRecyclerView adapterRecyclerView) {
        this.adapterRecyclerView = adapterRecyclerView;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int direcaoDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(0,direcaoDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicao = viewHolder.getAdapterPosition();
        cartaoController = new CartaoController();
        cartaoController.excluir(posicao);
        adapterRecyclerView.excluirCartao(posicao);
    }
}
