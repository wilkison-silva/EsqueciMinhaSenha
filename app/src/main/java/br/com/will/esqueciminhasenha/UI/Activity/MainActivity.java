package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.Adapter.AdapterListView;
import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private AdapterListView adapterListView;
    private List<Cartao> cartaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        listView = (ListView) findViewById(R.id.activity_main_listview);
        cartaoList = new ArrayList<>();
        persistirDadosdeExemplos();

        adapterListView = new AdapterListView(cartaoList, this);
        listView.setAdapter(adapterListView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent = new Intent(MainActivity.this, CadastrarCartaoActivity.class);
                startActivity(intent);
            }
        });


    }

    private void persistirDadosdeExemplos() {
        cartaoList.add(new Cartao("Instagram","rede social","wilkisonmartinsdasilva@gmail.com","91952205e72","#00FFFF"));
        cartaoList.add(new Cartao("Spotify","streaming","wilkisonmartinsdasilva@gmail.com","91952205e72will", "#87CEFA"));
        cartaoList.add(new Cartao("Facebook","site de compras","wilkisonmartinsdasilva@gmail.com","91952205e72will", "#00FFFF"));
        cartaoList.add(new Cartao("GitHub","site de educação","wilkisonmartinsdasilva@gmail.com","91952205e72will", "#006400"));
        cartaoList.add(new Cartao("Alura","site de educação","wilkisonmartinsdasilva@gmail.com","91952205e72will", "#87CEFA"));
        cartaoList.add(new Cartao("Aliexpress","site de compras","wilkisonmartinsdasilva@gmail.com","91952205e72will", "#8B4513"));
    }
}