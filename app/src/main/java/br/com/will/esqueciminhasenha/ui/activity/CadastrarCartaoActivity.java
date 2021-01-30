package br.com.will.esqueciminhasenha.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import br.com.will.esqueciminhasenha.controller.CartaoController;
import br.com.will.esqueciminhasenha.model.Cartao;
import br.com.will.esqueciminhasenha.R;

import static br.com.will.esqueciminhasenha.interfaces.Constantes.CHAVE_CARTAO;
import static br.com.will.esqueciminhasenha.interfaces.Constantes.CHAVE_POSICAO;
import static br.com.will.esqueciminhasenha.interfaces.Constantes.POSICAO_INVALIDA;


public class CadastrarCartaoActivity extends AppCompatActivity {

    public static final String COR_TEXTVIEWS_FUNDO_CLARO = "#FF000000";
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
    @SuppressWarnings("FieldCanBeLocal")
    private Button buttonCadastrarNovoCartao;

    private String corCartao = null;
    private String corTexto = null;
    private boolean novoCartao = true;
    private int posicao_adapterRecyclerView;
    CartaoController cartaoController;
    Cartao cartaoRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartao);
        cardView = findViewById(R.id.cardview_simulacao);
        cartaoController = new CartaoController(this);

        configuraComponentesDeEntrada();
        checaSeExisteAlteracao();

    }

    private void configuraComponentesDeEntrada() {
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

    private void checaSeExisteAlteracao() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_CARTAO)) {
            cartaoRecebido = (Cartao) intent.getSerializableExtra(CHAVE_CARTAO);
            carregarInformacoesCartao(cartaoRecebido);
            novoCartao = false;
            posicao_adapterRecyclerView = intent.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            setTitle(getString(R.string.titulo_alterando_cartao));
        } else {
            setTitle(getString(R.string.adicionar_novo_cartao));
        }
    }

    private void carregarInformacoesCartao(Cartao cartao) {

        configuraCamposDescricaoAlterar(cartao);
        configurarCamposCategoriaAlterar(cartao);
        configuraCamposLoginAlterar(cartao);
        configuraCamposSenhaAlterar(cartao);
        verificaCorCardView(cartao.getCorCartao());
    }

    private void configuraCamposSenhaAlterar(Cartao cartao) {
        cardviewSenha = findViewById(R.id.cardview_textview_senha);
        editTextSenha = findViewById(R.id.edittext_senha);
        cardviewSenha.setText(cartao.getSenha());
        editTextSenha.setText(cartao.getSenha());
    }

    private void configuraCamposLoginAlterar(Cartao cartao) {
        cardviewLogin = findViewById(R.id.cardview_textview_login);
        editTextLogin = findViewById(R.id.edittext_login);
        cardviewLogin.setText(cartao.getLogin());
        editTextLogin.setText(cartao.getLogin());
    }

    private void configurarCamposCategoriaAlterar(Cartao cartao) {
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        cardviewCategoria = findViewById(R.id.cardview_textview_categoria);
        setSpinnerText(spinnerCategoria, cartao.getCategoria());
        cardviewCategoria.setText(cartao.getCategoria());
    }

    private void configuraCamposDescricaoAlterar(Cartao cartao) {
        cardviewDescricao = findViewById(R.id.cardview_textview_descricao);
        editTextDescricao = findViewById(R.id.edittext_descricao);
        cardviewDescricao.setText(cartao.getDescricao());
        editTextDescricao.setText(cartao.getDescricao());
    }

    public void setSpinnerText(Spinner spin, String text) {
        for (int i = 0; i < spin.getAdapter().getCount(); i++) {
            if (spin.getAdapter().getItem(i).toString().contains(text)) {
                spin.setSelection(i);
            }
        }
    }

    private void configuraButtonSalvar() {
        buttonCadastrarNovoCartao = findViewById(R.id.button_cadastrar_novo_cartao);
        buttonCadastrarNovoCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarPreenchimentoDosCampos()) {
                    Cartao cartao = getDadosFormulario();
                    if (novoCartao) {
                        cadastraNovoCartao(cartao);
                    } else {
                        cartao.setId(cartaoRecebido.getId());
                        alteraCartaoExistente(cartao);
                    }
                }
            }

            private void alteraCartaoExistente(Cartao cartao) {
                cartaoController.editar(cartao);
                Toast.makeText(CadastrarCartaoActivity.this, R.string.cartao_alterado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra(CHAVE_CARTAO, cartao);
                intent.putExtra(CHAVE_POSICAO, posicao_adapterRecyclerView);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

            private void cadastraNovoCartao(@NotNull Cartao cartao) {
                cartaoController.cadastrar(cartao);
                Toast.makeText(CadastrarCartaoActivity.this, R.string.mensagem_cartao_salvo, Toast.LENGTH_LONG).show();
                cartao = cartaoController.ultimoRegistro();
                Intent intent = new Intent();
                intent.putExtra(CHAVE_CARTAO, cartao);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }


    private boolean verificarPreenchimentoDosCampos() {

        if (editTextDescricao.getText().toString().equals("")) {
            Toast.makeText(CadastrarCartaoActivity.this, "Descrição não pode ser vazia", Toast.LENGTH_LONG).show();
            return false;
        } else if (spinnerCategoria.getSelectedItem().toString().equals("")) {
            Toast.makeText(CadastrarCartaoActivity.this, "Selecione uma categoria", Toast.LENGTH_LONG).show();
            return false;
        } else if (editTextLogin.getText().toString().equals("")) {
            Toast.makeText(CadastrarCartaoActivity.this, "Preencha seu login de acesso", Toast.LENGTH_LONG).show();
            return false;
        } else if (editTextSenha.getText().toString().equals("")) {
            Toast.makeText(CadastrarCartaoActivity.this, "Informe a senha de acesso", Toast.LENGTH_LONG).show();
            return false;
        } else if ((corCartao == null) && (corTexto == null)) {
            Toast.makeText(CadastrarCartaoActivity.this, "Selecione uma cor na paleta", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Cartao getDadosFormulario() {
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
        imageButtonCorIndigo.setOnClickListener(v -> configurarCorCardView(7));
    }

    private void configuraImageButtonCorRosa() {
        imageButtonCorRosa = findViewById(R.id.imagebutton_cor_rosa);
        imageButtonCorRosa.setOnClickListener(v -> configurarCorCardView(6));
    }

    private void configuraImageButtonCorAmarelo() {
        imageButtonCorAmarelo = findViewById(R.id.imagebutton_cor_amarelo);
        imageButtonCorAmarelo.setOnClickListener(v -> configurarCorCardView(5));
    }

    private void configuraImageButtonCorCiano() {
        imageButtonCorCiano = findViewById(R.id.imagebutton_cor_ciano);
        imageButtonCorCiano.setOnClickListener(v -> configurarCorCardView(4));
    }

    private void configuraImageButtonCorVerdeClaro() {
        imageButtonCorVerdeClaro = findViewById(R.id.imagebutton_Cor_verde_claro);
        imageButtonCorVerdeClaro.setOnClickListener(v -> configurarCorCardView(3));
    }

    private void configuraImageButtonCorVermelho() {
        imageButtonCorVermelho = findViewById(R.id.imagebutton_cor_vermelho);
        imageButtonCorVermelho.setOnClickListener(v -> configurarCorCardView(2));
    }

    private void configuraImageButtonCorLaranja() {
        imageButtonCorLaranja = findViewById(R.id.imagebutton_cor_laranja);
        imageButtonCorLaranja.setOnClickListener(v -> configurarCorCardView(1));
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

    private void verificaCorCardView(String corCardView) {

        switch (corCardView) {
            case "#FF8C00":
                configurarCorCardView(1);
                break;
            case "#8B0000":
                configurarCorCardView(2);
                break;
            case "#1DE3AA":
                configurarCorCardView(3);
                break;
            case "#70FFFF":
                configurarCorCardView(4);
                break;
            case "#FFDE16":
                configurarCorCardView(5);
                break;
            case "#FF1493":
                configurarCorCardView(6);
                break;
            case "#4B0082":
                configurarCorCardView(7);
                break;
        }
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