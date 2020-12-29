package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import br.com.will.esqueciminhasenha.Controller.CartaoController;
import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;
import br.com.will.esqueciminhasenha.Stream.ConexaoArquivo;

import static android.service.autofill.Validators.or;

public class CadastrarCartaoActivity extends AppCompatActivity {

    public static final String COR_TEXTVIEWS_FUNDO_CLARO = "#A1000000";
    public static final String COR_TEXTVIEWS_FUNDO_ESCURO = "#EFE9E9";
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
    private ImageButton imageButtonCorRosa;
    private ImageButton imageButtonCorIndigo;
    private Button buttonCadastrarNovoCartao;

    private Cartao cartao;
    private String corCartao = null;
    private String corTexto = null;

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
        configuraImageButtonCorRosa();
        configuraImageButtonCorIndigo();

        configuraButtonSalvar();

    }

    private void configuraButtonSalvar() {
        buttonCadastrarNovoCartao = findViewById(R.id.button_cadastrar_novo_cartao);
        buttonCadastrarNovoCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(verificarPreenchimentoDosCampos() == true) {
                        Cartao cartao = getDadosFormulario();

                        CartaoController cartaoController = new CartaoController();
                        if (cartaoController.cadastrar(cartao)) {
                            Toast.makeText(CadastrarCartaoActivity.this, R.string.mensagem_cartao_salvo, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(CadastrarCartaoActivity.this, R.string.erro_ao_salvar, Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(CadastrarCartaoActivity.this, R.string.erro_ao_salvar, Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private boolean verificarPreenchimentoDosCampos(){

        if (editTextDescricao.getText().toString().equals("")){
            Toast.makeText(CadastrarCartaoActivity.this, "Descrição não pode ser vazia",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (spinnerCategoria.getSelectedItem().toString().equals("")){
            Toast.makeText(CadastrarCartaoActivity.this, "Selecione uma categoria",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (editTextLogin.getText().toString().equals("")){
            Toast.makeText(CadastrarCartaoActivity.this, "Preencha seu login de acesso",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (editTextSenha.getText().toString().equals("")){
            Toast.makeText(CadastrarCartaoActivity.this, "Informe a senha de acesso",Toast.LENGTH_LONG).show();
            return false;
        }
        else if ((corCartao == null) && (corTexto == null)){
            Toast.makeText(CadastrarCartaoActivity.this, "Selecione uma cor na paleta",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Cartao getDadosFormulario(){
        Cartao cartao = new Cartao();

        cartao.setDescricao(editTextDescricao.getText().toString());
        cartao.setCategoria(spinnerCategoria.getSelectedItem().toString());
        cartao.setLogin(editTextLogin.getText().toString());
        cartao.setSenha(editTextSenha.getText().toString());
        cartao.setCorCartao(corCartao);
        cartao.setCorTexto(corTexto);

        return cartao;
    }

    private void configuraImageButtonCorIndigo() {
        imageButtonCorIndigo = findViewById(R.id.imagebutton_cor_indigo);
        imageButtonCorIndigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(7);
            }
        });
    }

    private void configuraImageButtonCorRosa() {
        imageButtonCorRosa = findViewById(R.id.imagebutton_cor_rosa);
        imageButtonCorRosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurarCorCardView(6);
            }
        });
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

        editTextSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardviewSenha.setText(editTextSenha.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void configuraEditTextLogin() {
        cardviewLogin = findViewById(R.id.cardview_textview_login);
        editTextLogin = findViewById(R.id.edittext_login);

        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardviewLogin.setText(editTextLogin.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        editTextDescricao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardviewDescricao.setText(editTextDescricao.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void configurarCorCardView(int idImageButton) {
        ResetarImageButtons();
        if (idImageButton == 1) {
            imageButtonCorLaranja.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#FF8C00"));
            corCartao = "#FF8C00";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_CLARO);
        } else if (idImageButton == 2) {
            imageButtonCorVermelho.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#8B0000"));
            corCartao = "#8B0000";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_ESCURO);
        } else if (idImageButton == 3) {
            imageButtonCorVerdeClaro.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#1DE3AA"));
            corCartao = "#1DE3AA";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_CLARO);
        } else if (idImageButton == 4) {
            imageButtonCorCiano.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#70FFFF"));
            corCartao = "#70FFFF";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_CLARO);
        } else if (idImageButton == 5) {
            imageButtonCorAmarelo.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#FFDE16"));
            corCartao = "#FFDE16";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_CLARO);
        } else if (idImageButton == 6) {
            imageButtonCorRosa.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#FF1493"));
            corCartao = "#FF1493";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_CLARO);
        } else if (idImageButton == 7) {
            imageButtonCorIndigo.setImageResource(R.drawable.botao_selecionado);
            cardView.setCardBackgroundColor(Color.parseColor("#4B0082"));
            corCartao = "#4B0082";
            configurarCorTextViews(COR_TEXTVIEWS_FUNDO_ESCURO);
        }


    }

    private void configurarCorTextViews(String cor) {
        cardviewDescricao.setTextColor(Color.parseColor(cor));
        cardviewCategoria.setTextColor(Color.parseColor(cor));
        cardviewSenha.setTextColor(Color.parseColor(cor));
        cardviewLogin.setTextColor(Color.parseColor(cor));
        corTexto = cor;
    }

    private void ResetarImageButtons() {
        imageButtonCorLaranja.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorVermelho.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorVerdeClaro.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorCiano.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorAmarelo.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorRosa.setImageResource(R.drawable.botao_nao_selecionado);
        imageButtonCorIndigo.setImageResource(R.drawable.botao_nao_selecionado);
    }


}