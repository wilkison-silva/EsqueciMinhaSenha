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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartao);

        setTitle(getString(R.string.adicionar_novo_cartao));
        cardView = findViewById(R.id.cardview_simulacao);

        configuraEditTextDescricao();
        configurarSpinnerCategoria();
        configuraEditTextLogin();
        configuraEditTextSenha();

        configuraImageButtonCorLaranja();
        configuraImageButtonCorVermelho();
        configuraImageButtonCorVerdeClaro();
        configuraImageButtonCorCiano();
        configuraImageButtonCorAmarelo();


    }

    private void configuraImageButtonCorAmarelo() {
        imageButtonCorAmarelo = findViewById(R.id.imagebutton_cor_amarelo);
        imageButtonCorAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(5);
            }
        });
    }

    private void configuraImageButtonCorCiano() {
        imageButtonCorCiano = findViewById(R.id.imagebutton_cor_ciano);
        imageButtonCorCiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(4);
            }
        });
    }

    private void configuraImageButtonCorVerdeClaro() {
        imageButtonCorVerdeClaro = findViewById(R.id.imagebutton_Cor_verde_claro);
        imageButtonCorVerdeClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(3);
            }
        });
    }

    private void configuraImageButtonCorVermelho() {
        imageButtonCorVermelho = findViewById(R.id.imagebutton_cor_vermelho);
        imageButtonCorVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(2);
            }
        });
    }

    private void configuraImageButtonCorLaranja() {
        imageButtonCorLaranja = findViewById(R.id.imagebutton_cor_laranja);
        imageButtonCorLaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(1);
            }
        });
    }

    private void configuraEditTextSenha() {
        cardviewSenha = findViewById(R.id.cardview_textview_senha);
        editTextSenha = findViewById(R.id.edittext_senha);

        editTextSenha.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                cardviewSenha.setText(editTextSenha.getText().toString());
                return false;
            }
        });
    }

    private void configuraEditTextLogin() {
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

    private void configurarSpinnerCategoria() {
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
    }

    private void configuraEditTextDescricao() {
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

    private void configurarCorCardView(int idImageButton) {
        ResetarImageButtons();
        if (idImageButton == 1) {
            imageButtonCorLaranja.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#FF8C00"));
            configurarCorTextViews("#000000");
        } else if (idImageButton == 2) {
            imageButtonCorVermelho.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#8B0000"));
            configurarCorTextViews("#FFFFFF");
        } else if (idImageButton == 3) {
            imageButtonCorVerdeClaro.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#1DE3AA"));
            configurarCorTextViews("#000000");
        } else if (idImageButton == 4) {
            imageButtonCorCiano.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#70FFFF"));
            configurarCorTextViews("#000000");
        } else if (idImageButton == 5) {
            imageButtonCorAmarelo.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#FFDE16"));
            configurarCorTextViews("#000000");
        }
    }

    private void configurarCorTextViews(String cor) {
        cardviewDescricao.setTextColor(Color.parseColor(cor));
        cardviewCategoria.setTextColor(Color.parseColor(cor));
        cardviewSenha.setTextColor(Color.parseColor(cor));
        cardviewLogin.setTextColor(Color.parseColor(cor));
    }

    private void ResetarImageButtons() {
        imageButtonCorLaranja.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorVermelho.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorVerdeClaro.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorCiano.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorAmarelo.setImageResource(R.drawable.botao_nao_selecionado);
    }


}