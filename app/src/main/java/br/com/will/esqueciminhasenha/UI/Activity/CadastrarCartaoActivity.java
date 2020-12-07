package br.com.will.esqueciminhasenha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.R;

public class CadastrarCartaoActivity extends AppCompatActivity {


    private Spinner spinnerCategoria;
    private List<String> listCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartao);

        /*listCategoria = new ArrayList<>();
        listCategoria.add("Site de compras");
        listCategoria.add("Rede social");
        listCategoria.add("Streaming");
        listCategoria.add("E-mails");

        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        ArrayAdapter<String> arrayAdapterSpinnerCategoria = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                this.listCategoria);

        spinnerCategoria.setAdapter(arrayAdapterSpinnerCategoria);
*/
    }
}