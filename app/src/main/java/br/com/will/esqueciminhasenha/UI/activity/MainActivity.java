package br.com.will.esqueciminhasenha.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.adapter.listener.OnItemClickListener;
import br.com.will.esqueciminhasenha.controller.CartaoController;
import br.com.will.esqueciminhasenha.itemHelpers.CartaoItemTouchHelperCallback;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.R;

import static br.com.will.esqueciminhasenha.interfaces.Constantes.*;



public class MainActivity extends AppCompatActivity {



    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private List<Cartao> cartaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desativarModoNoturno();
        configuraListaDeCartoes();
        configuraAdapterRecyclerView();
        configuraRecyclerView();
        configurarFlotActionButtonAdicionar();
    }

    private void configuraRecyclerView() {
        recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setAdapter(adapterRecyclerView);
        ItemTouchHelper itemTouchHelper = new  ItemTouchHelper(new CartaoItemTouchHelperCallback(adapterRecyclerView, this));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void configuraListaDeCartoes() {
        CartaoController cartaoController = new CartaoController(this);
        cartaoList = new ArrayList<>();
        cartaoList = cartaoController.getListaDeCartoesSalvos();
    }

    private void configuraAdapterRecyclerView() {
        adapterRecyclerView = new AdapterRecyclerView(cartaoList, this);
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

}