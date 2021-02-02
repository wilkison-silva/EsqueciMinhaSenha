package br.com.will.esqueciminhasenha.ui.itemHelpers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.will.esqueciminhasenha.asynctasks.interfaces.ExcluiCartaoListener;
import br.com.will.esqueciminhasenha.ui.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.controller.CartaoController;
import br.com.will.esqueciminhasenha.model.Cartao;


public class CartaoItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final AdapterRecyclerView adapterRecyclerView;
    @SuppressWarnings("FieldCanBeLocal")
    private CartaoController cartaoController;
    private final Context context;

    public CartaoItemTouchHelperCallback(AdapterRecyclerView adapterRecyclerView, Context context) {
        this.adapterRecyclerView = adapterRecyclerView;
        this.context = context;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int direcaoDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //int direcaoArrastar = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(0,direcaoDeslize);
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
        Cartao cartao = adapterRecyclerView.getCartao(posicao);

        cartaoController = new CartaoController(context);
        cartaoController.excluir(cartao, new ExcluiCartaoListener() {
            @Override
            public void excluirCartaoFinalizado() {
                adapterRecyclerView.excluirCartao(posicao);
            }
        });
    }
}
