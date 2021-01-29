package br.com.will.esqueciminhasenha.DAO;

import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.will.esqueciminhasenha.Model.Cartao;
import br.com.will.esqueciminhasenha.Stream.ConexaoArquivo;

import static br.com.will.esqueciminhasenha.Interfaces.Constantes.NOME_ARQUIVO;

public class CartaoDAO {



    public List<Cartao> getListaCartoesSalvos() {

        List<Cartao> cartaoList = new ArrayList<>();

        try {
            String texto = "";
            ConexaoArquivo.createBufferedReader(NOME_ARQUIVO);
            while ((texto = ConexaoArquivo.readLine()) != null) {
                if (texto.equals("") == false) {
                    Cartao cartao = new Cartao();
                    cartao.setDescricao(texto.split(",")[0]);
                    cartao.setCategoria(texto.split(",")[1]);
                    cartao.setLogin(texto.split(",")[2]);
                    cartao.setSenha(texto.split(",")[3]);
                    cartao.setCorCartao(texto.split(",")[4]);
                    cartao.setCorTexto(texto.split(",")[5]);
                    cartaoList.add(cartao);
                }
            }
            ConexaoArquivo.closeReader();
            return cartaoList;
        } catch (Exception e) {
            return cartaoList;
        }
    }

    public boolean cadastrar(Cartao cartao){
        boolean resultado;
        try {
            ConexaoArquivo.createBufferedWriter(NOME_ARQUIVO, false);
            resultado = ConexaoArquivo.appendLine(getLinhaFormatada(cartao));
            ConexaoArquivo.closeWriter();
            return resultado;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean excluir(Cartao cartao){
        return false;
    }

    public boolean editar(Cartao cartao, int posicao){
        List<Cartao> cartaoList = getListaCartoesSalvos();
        cartaoList.set(posicao, cartao);
        try {
            ConexaoArquivo.createBufferedWriter(NOME_ARQUIVO, true);
            ConexaoArquivo.appendLine(getLinhaFormatada(cartaoList.get(0)));
            ConexaoArquivo.closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try{
            for(int i = 1; i < cartaoList.size(); i++){
                cadastrar(cartaoList.get(i));
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private String getLinhaFormatada(Cartao cartao){
        String linhaFormatada = cartao.getDescricao() + ","
                +cartao.getCategoria() + ","
                +cartao.getLogin() + ","
                +cartao.getSenha() + ","
                +cartao.getCorCartao() + ","
                +cartao.getCorTexto();

        return  linhaFormatada;
    }
}
