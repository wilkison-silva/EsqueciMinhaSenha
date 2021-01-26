package br.com.will.esqueciminhasenha.UI.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;
import br.com.will.esqueciminhasenha.UI.Activity.EditarCartaoActivity;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.CartaoViewHolder> {


    private List<Cartao> list;
    private Context context;

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

        CartaoViewHolder cartaoViewHolder = holder;
        cartaoViewHolder.vincularDados(cartao);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void adicionaNovoCartao(Cartao cartao){
        this.list.add(cartao);
        this.notifyDataSetChanged();
    }

    class CartaoViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        public CartaoViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void vincularDados(Cartao cartao) {

            mostrarDescricao(view, cartao);
            mostrarCategoria(view, cartao);
            mostrarLogin(view, cartao);
            mostrarSenha(view, cartao);
            configurarCorCortao(view, cartao);
        }

        private void configurarCorCortao(View view, Cartao cartao) {
            CardView cardView = view.findViewById(R.id.cardview_simulacao);
            cardView.setCardBackgroundColor(Color.parseColor(cartao.getCorCartao()));
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, cartao.getDescricao(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, EditarCartaoActivity.class);
                    context.startActivity(intent);
                }
            });
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
