package br.com.will.esqueciminhasenha.UI.Activity;

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

import br.com.will.esqueciminhasenha.Adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.Adapter.Listener.OnItemClickListener;
import br.com.will.esqueciminhasenha.Controller.CartaoController;
import br.com.will.esqueciminhasenha.ItemHelpers.CartaoItemTouchHelperCallback;
import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;
import br.com.will.esqueciminhasenha.Stream.ConexaoArquivo;
import br.com.will.esqueciminhasenha.Stream.Permissions;

import static br.com.will.esqueciminhasenha.Interfaces.Constantes.*;


public class MainActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private List<Cartao> cartaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificarPermissoes();
        criarArquivoNoCelular();
        desativarModoNoturno();
        configuraListaDeCartoes();
        configuraAdapterRecyclerView();
        configuraRecyclerView();
        configurarFlotActionButtonAdicionar();
    }

    private void configuraRecyclerView() {
        recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setAdapter(adapterRecyclerView);
        ItemTouchHelper itemTouchHelper = new  ItemTouchHelper(new CartaoItemTouchHelperCallback(adapterRecyclerView));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void configuraListaDeCartoes() {
        CartaoController cartaoController = new CartaoController();
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
                intent.putExtra(getString(R.string.alterar),cartao);
                intent.putExtra(getString(R.string.posicao), posicao);
                startActivityForResult(intent, CHAVE_ALTERA);
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
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODIGO_CADASTRAR){
            if(resultCode == RESULT_OK && data.hasExtra(CHAVE_CARTAO)){
                atualizaRecyclerViewCadastra(data);
            }
        }
        if(requestCode == CODIGO_ALTERAR) {
            if(resultCode == Activity.RESULT_OK && data.hasExtra(CHAVE_CARTAO)) {
                atualizaRecyclerViewAltera(data);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void desativarModoNoturno() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void verificarPermissoes() {
        Permissions permissions = new Permissions(MainActivity.this);
        permissions.checkPermissionForExternalStorage();
        permissions.requestPermissionForExternalStorage();
    }

    private void criarArquivoNoCelular(){
        try {
            ConexaoArquivo.createBufferedReader(NOME_ARQUIVO);
            ConexaoArquivo.closeReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}