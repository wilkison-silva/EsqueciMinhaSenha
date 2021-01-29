package br.com.will.esqueciminhasenha.ItemHelpers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.will.esqueciminhasenha.Adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.Controller.CartaoController;

public class CartaoItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private AdapterRecyclerView adapterRecyclerView;
    private CartaoController cartaoController;

    public CartaoItemTouchHelperCallback(AdapterRecyclerView adapterRecyclerView) {
        this.adapterRecyclerView = adapterRecyclerView;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int direcaoDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int direcaoArrastar = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(direcaoArrastar,direcaoDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posicaoFinal = target.getAdapterPosition();
        adapterRecyclerView.troca(posicaoInicial, posicaoFinal);
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
