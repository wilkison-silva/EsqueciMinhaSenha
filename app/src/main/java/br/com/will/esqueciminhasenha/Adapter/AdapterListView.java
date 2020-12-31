package br.com.will.esqueciminhasenha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;
import br.com.will.esqueciminhasenha.UI.Activity.CadastrarCartaoActivity;
import br.com.will.esqueciminhasenha.UI.Activity.EditarCartaoActivity;
import br.com.will.esqueciminhasenha.UI.Activity.MainActivity;

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

    public void removerCartao(int position){
        this.list.remove(position);
        notifyDataSetChanged();
    }

    public void atualizarListaDeCartoes(List<Cartao> cartaoList){
        this.list.clear();
        this.list = cartaoList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(this.context, R.layout.layout_item_listview,null);

        Cartao cartao = this.list.get(position);

        mostrarDescricao(view, cartao);
        mostrarCategoria(view, cartao);
        mostrarLogin(view, cartao);
        mostrarSenha(view, cartao);
        configurarCorCortao(view, cartao);



        return view;

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
        textViewDescricao.setText(cartao.getDescricao() );
        textViewDescricao.setTextColor(Color.parseColor(cartao.getCorTexto()));
    }


}
