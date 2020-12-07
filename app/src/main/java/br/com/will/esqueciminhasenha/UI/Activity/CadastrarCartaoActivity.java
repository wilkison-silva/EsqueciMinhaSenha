package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class CadastrarCartaoActivity extends AppCompatActivity {

    private CardView cardView;
    private TextView cardviewDescricao;
    private EditText editTextDescricao;
    private TextView cardviewCategoria;
    private Spinner spinnerCategoria;
    private TextView cardviewLogin;
    private EditText editTextLogin;
    private TextView cardviewSenha;
    private EditText editTextSenha;
    private ImageButton imageButtonCorLaranja;
    private ImageButton imageButtonCorVermelho;
    private ImageButton imageButtonCorVerdeClaro;
    private ImageButton imageButtonCorCiano;
    private ImageButton imageButtonCorAmarelo;


    private Cartao cartao;

    private boolean corLaranja = false;
    private boolean corVermelha = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartao);

        setTitle(getString(R.string.adicionar_novo_cartao));
        cardView = findViewById(R.id.cardview_simulacao);

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

        cardviewSenha = findViewById(R.id.cardview_textview_senha);
        editTextSenha = findViewById(R.id.edittext_senha);

        editTextSenha.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                cardviewSenha.setText(editTextSenha.getText().toString());
                return false;
            }
        });

        imageButtonCorLaranja = findViewById(R.id.imagebutton_cor_laranja);
        imageButtonCorLaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (corLaranja == false) {
                    corLaranja = true;
                    corVermelha = false;
                    imageButtonCorLaranja.setImageResource(R.drawable.botao_selecionado);
                    imageButtonCorVermelho.setImageResource(R.drawable.botao_nao_selecionado);
                    cardView.setCardBackgroundColor(Color.parseColor("#FF8C00"));
                }
            }
        });

        imageButtonCorVermelho = findViewById(R.id.imagebutton_cor_vermelho);
        imageButtonCorVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (corVermelha == false){
                    corLaranja = false;
                    corVermelha = true;
                    imageButtonCorLaranja.setImageResource(R.drawable.botao_nao_selecionado);
                    imageButtonCorVermelho.setImageResource(R.drawable.botao_selecionado);
                    cardView.setCardBackgroundColor(Color.parseColor("#8B0000"));
                }

            }
        });



    }


}