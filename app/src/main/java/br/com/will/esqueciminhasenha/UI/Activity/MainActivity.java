package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.UI.Activity.Adapter.AdapterRecyclerView;
import br.com.will.esqueciminhasenha.Controller.CartaoController;
import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;
import br.com.will.esqueciminhasenha.Stream.ConexaoArquivo;
import br.com.will.esqueciminhasenha.Stream.Permissions;

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

        recyclerView = findViewById(R.id.activity_main_recyclerview);
        cartaoList = new ArrayList<>();


        CartaoController cartaoController = new CartaoController();
        cartaoList = cartaoController.getListaDeCartoesSalvos();

        adapterRecyclerView = new AdapterRecyclerView(cartaoList, this);
        recyclerView.setAdapter(adapterRecyclerView);


        configurarFlotActionButtonAdicionar();
    }

    private void configurarFlotActionButtonAdicionar() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastrarCartaoActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 2 && data.hasExtra(getString(R.string.cartao))){
            Cartao cartao = (Cartao) data.getSerializableExtra(getString(R.string.cartao));
            adapterRecyclerView.adicionaNovoCartao(cartao);
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
            ConexaoArquivo.createBufferedReader("EsqueciMinhaSenha");
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