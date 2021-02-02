package br.com.will.esqueciminhasenha.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.asynctasks.BuscaTodosOsCartoes;
import br.com.will.esqueciminhasenha.database.CartaoDatabase;
import br.com.will.esqueciminhasenha.database.dao.RoomCartaoDAO;
import br.com.will.esqueciminhasenha.ui.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.ui.adapter.listener.AsyncTaskListener;
import br.com.will.esqueciminhasenha.ui.adapter.listener.OnItemClickListener;
import br.com.will.esqueciminhasenha.controller.CartaoController;
import br.com.will.esqueciminhasenha.ui.itemHelpers.CartaoItemTouchHelperCallback;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.R;

import static br.com.will.esqueciminhasenha.interfaces.Constantes.*;



public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private List<Cartao> listaCartoes;
    private EditText editTextPesquisar;
    private Button buttonPesquisar;
    private List<Cartao> resultadoBusca;
    private RoomCartaoDAO roomCartaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomCartaoDAO = CartaoDatabase.getInstance(this).getRoomCartaoDAO();
        desativarModoNoturno();
        configuraListaDeCartoes();
        configuraAdapterRecyclerView();
        configuraRecyclerView();
        configurarFlotActionButtonAdicionar();

        configuraComponentesDePesquisa();
    }

    private void configuraComponentesDePesquisa() {
        editTextPesquisar = findViewById(R.id.edittext_pesquisar);
        buttonPesquisar = findViewById(R.id.botao_pesquisar);
        CartaoController cartaoController = new CartaoController(this);
        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = editTextPesquisar.getText().toString();
                Log.i("tamanho", "tamanho da lista : " + listaCartoes.size());
                resultadoBusca =  cartaoController.pesquisar(descricao, listaCartoes);
                adapterRecyclerView.atualizarLista(resultadoBusca);
            }
        });

        editTextPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String descricao = editTextPesquisar.getText().toString();
                if(descricao.equals("")){
                    //cartaoController.getListaDeCartoesSalvos(adapterRecyclerView, new Asy);
                    adapterRecyclerView.atualizarLista(listaCartoes);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void configuraRecyclerView() {
        recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setAdapter(adapterRecyclerView);
        ItemTouchHelper itemTouchHelper = new  ItemTouchHelper(new CartaoItemTouchHelperCallback(adapterRecyclerView, this));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void configuraListaDeCartoes() {

        listaCartoes = new ArrayList<>();

    }

    private void configuraAdapterRecyclerView() {
        CartaoController cartaoController = new CartaoController(this);
        adapterRecyclerView = new AdapterRecyclerView(listaCartoes, this);
        cartaoController.getListaDeCartoesSalvos(adapterRecyclerView);

        new BuscaTodosOsCartoes(roomCartaoDAO, new AsyncTaskListener() {
            @Override
            public void onTodosOsCartoes(List<Cartao> cartaoList) {
                listaCartoes = cartaoList;
            }
        }).execute();


        adapterRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(Cartao cartao, int posicao) {
                chamaActivityEditarCartao(cartao, posicao);
            }

            private void chamaActivityEditarCartao(Cartao cartao, int posicao) {
                Intent intent = new Intent(MainActivity.this, CadastrarCartaoActivity.class);
                intent.putExtra(CHAVE_CARTAO,cartao);
                intent.putExtra(CHAVE_POSICAO, posicao);
                startActivityForResult(intent, CODIGO_ALTERAR);
            }
        });
    }

    private void configurarFlotActionButtonAdicionar() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaActivityCadastrarCartao();
            }

            private void vaiParaActivityCadastrarCartao() {
                Intent intent = new Intent(MainActivity.this, CadastrarCartaoActivity.class);
                startActivityForResult(intent,CODIGO_CADASTRAR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        verificaRequestDeCadastro(requestCode, resultCode, data);
        verificaRequestDeAlteracao(requestCode, resultCode, data);
    }

    private void verificaRequestDeAlteracao(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODIGO_ALTERAR) {
            if(resultCode == Activity.RESULT_OK) {
                assert data != null;
                if (data.hasExtra(CHAVE_CARTAO)) {
                    atualizaRecyclerViewAltera(data);
                }
            }
        }
    }

    private void verificaRequestDeCadastro(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODIGO_CADASTRAR){
            if(resultCode == RESULT_OK) {
                assert data != null;
                if (data.hasExtra(CHAVE_CARTAO)) {
                    atualizaRecyclerViewCadastra(data);
                }
            }
        }
    }

    private void atualizaRecyclerViewCadastra(@NotNull Intent data) {
        Cartao cartao = (Cartao) data.getSerializableExtra(CHAVE_CARTAO);
        adapterRecyclerView.adicionaNovoCartao(cartao);
    }

    private void atualizaRecyclerViewAltera(@NotNull Intent data) {
        Cartao cartao = (Cartao) data.getSerializableExtra(CHAVE_CARTAO);
        int posicao = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
        if (posicao > POSICAO_INVALIDA){
            adapterRecyclerView.editarCartao(cartao, posicao);
        }
    }

    private void desativarModoNoturno() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void atualizarRecyclerView(){

    }

}