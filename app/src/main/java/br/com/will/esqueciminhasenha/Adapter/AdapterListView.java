package br.com.will.esqueciminhasenha.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class AdapterListView extends BaseAdapter {

    private List<Cartao> list;
    private Context context;

    public AdapterListView(List<Cartao> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Cartao getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(this.context, R.layout.layout_item_listview,null);

        Cartao cartao = this.list.get(position);

        mostrarDescricao(view, cartao);
        mostrarCategoria(view, cartao);
        mostrarLogin(view, cartao);
        mostrarSenha(view, cartao);
        configurarCor(view, cartao);

        return view;

    }

    private void configurarCor(View view, Cartao cartao) {
        CardView cardView = view.findViewById(R.id.item_listview_card_view);
        cardView.setCardBackgroundColor(Color.parseColor(cartao.getCor()));
    }

    private void mostrarSenha(View view, Cartao cartao) {
        TextView textViewSenha = view.findViewById(R.id.textView_Senha);
        textViewSenha.setText(cartao.getSenha());
    }

    private void mostrarLogin(View view, Cartao cartao) {
        TextView textViewLogin = view.findViewById(R.id.textView_Login);
        textViewLogin.setText(cartao.getLogin());
    }

    private void mostrarCategoria(View view, Cartao cartao) {
        TextView textViewCategoria = view.findViewById(R.id.textView_Categoria);
        textViewCategoria.setText(cartao.getCategoria());
    }

    private void mostrarDescricao(View view, Cartao cartao) {
        TextView textViewDescricao = view.findViewById(R.id.textView_Descricao);
        textViewDescricao.setText(cartao.getDescricao() );
    }


}
