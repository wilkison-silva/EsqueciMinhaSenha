package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class CadastrarCartaoActivity extends AppCompatActivity {


    private Spinner spinnerCategoria;
    private List<String> listCategoria;
    private TextView cardviewDescricao;
    private EditText editTextDescricao;
    private TextView cardviewCategoria;
    private TextView cardviewLogin;
    private TextView cardviewSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartao);

        cardviewDescricao = findViewById(R.id.cardview_textview_descricao);
        editTextDescricao = findViewById(R.id.edittext_descricao);

        editTextDescricao.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                cardviewDescricao.setText(editTextDescricao.getText().toString());
                return false;
            }
        });

    }
}