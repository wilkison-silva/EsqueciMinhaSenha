package br.com.will.esqueciminhasenha.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import br.com.will.esqueciminhasenha.ui.adapter.listener.OnItemClickListener;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.CartaoViewHolder> {


    public void setList(List<Cartao> list) {
        this.list = list;
    }

    private List<Cartao> list;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AdapterRecyclerView(List<Cartao> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public CartaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_listview, parent, false);
        return new CartaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaoViewHolder holder, int position) {

        Cartao cartao = this.list.get(position);
        holder.vincularDados(cartao);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void adicionaNovoCartao(Cartao cartao){
        this.list.add(cartao);
        this.notifyDataSetChanged();
    }

    public Cartao getCartao(int posicao){
        return list.get(posicao);
    }

    public void editarCartao(Cartao cartao, int posicao){
        this.list.set(posicao, cartao);
        notifyDataSetChanged();
    }

    public void excluirCartao(int posicao){
        this.list.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public void troca(int posicaoInicial, int posicaoFinal) {
        Collections.swap(this.list,posicaoInicial,posicaoFinal);
        notifyItemMoved(posicaoInicial, posicaoFinal);
    }

    public void atualizarLista(List<Cartao> resultadoBusca) {
        this.setList(resultadoBusca);
        notifyDataSetChanged();
    }

    class CartaoViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private Cartao cartao;

        public CartaoViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            itemView.setOnClickListener(v -> onItemClickListener.OnItemClick(cartao, getAdapterPosition()));

        }

        public void vincularDados(Cartao cartao) {
            this.cartao = cartao;
            mostrarDescricao(view, cartao);
            mostrarCategoria(view, cartao);
            mostrarLogin(view, cartao);
            mostrarSenha(view, cartao);
            configurarCorCortao(view, cartao);
        }

        private void configurarCorCortao(View view, Cartao cartao) {
            CardView cardView = view.findViewById(R.id.cardview_simulacao);
            cardView.setCardBackgroundColor(Color.parseColor(cartao.getCorCartao()));
        }

        private void mostrarSenha(View view, Cartao cartao) {
            TextView textViewSenha = view.findViewById(R.id.cardview_textview_senha);
            textViewSenha.setText(cartao.getSenha());
            textViewSenha.setTextColor(Color.parseColor(cartao.getCorTexto()));
        }

        private void mostrarLogin(View view, Cartao cartao) {
            TextView textViewLogin = view.findViewById(R.id.cardview_textview_login);
            textViewLogin.setText(cartao.getLogin());
            textViewLogin.setTextColor(Color.parseColor(cartao.getCorTexto()));
        }

        private void mostrarCategoria(View view, Cartao cartao) {
            TextView textViewCategoria = view.findViewById(R.id.cardview_textview_categoria);
            textViewCategoria.setText(cartao.getCategoria());
            textViewCategoria.setTextColor(Color.parseColor(cartao.getCorTexto()));
        }

        private void mostrarDescricao(View view, Cartao cartao) {
            TextView textViewDescricao = view.findViewById(R.id.cardview_textview_descricao);
            textViewDescricao.setText(cartao.getDescricao());
            textViewDescricao.setTextColor(Color.parseColor(cartao.getCorTexto()));
        }

    }
}
