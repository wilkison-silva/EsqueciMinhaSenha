package br.com.will.esqueciminhasenha.ItemHelpers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.will.esqueciminhasenha.Adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.Controller.CartaoController;
import br.com.will.esqueciminhasenha.Model.Cartao;

public class CartaoItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private AdapterRecyclerView adapterRecyclerView;
    private CartaoController cartaoController;
    private Context context;

    public CartaoItemTouchHelperCallback(AdapterRecyclerView adapterRecyclerView, Context context) {
        this.adapterRecyclerView = adapterRecyclerView;
        this.context = context;
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
        Log.i("cartão","cartão da lista a ser excluido: " + posicao);
        cartaoController = new CartaoController(context);
        Cartao cartao = adapterRecyclerView.getCartao(posicao);
        Log.i("cartão","cartão do banco a ser excluido: " + cartao.getId());
        cartaoController.excluir(cartao);
        adapterRecyclerView.excluirCartao(posicao);
    }
}
