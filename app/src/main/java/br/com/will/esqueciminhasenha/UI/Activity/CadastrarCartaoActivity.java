package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class CadastrarCartaoActivity extends AppCompatActivity {

    private TextView cardviewDescricao;
    private EditText editTextDescricao;
    private TextView cardviewCategoria;
    private Spinner spinnerCategoria;
    private TextView cardviewLogin;
    private EditText editTextLogin;
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

        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        cardviewCategoria = findViewById(R.id.cardview_textview_categoria);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardviewCategoria.setText(spinnerCategoria.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cardviewLogin = findViewById(R.id.cardview_textview_login);
        editTextLogin = findViewById(R.id.edittext_login);

        editTextLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                cardviewLogin.setText(editTextLogin.getText().toString());
                return false;
            }
        });
    }
}