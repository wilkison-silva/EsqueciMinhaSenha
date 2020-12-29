package br.com.will.esqueciminhasenha.Stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Environment;
import android.util.Log;

public class ConexaoArquivo {
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    private static boolean checkSdCard() throws Exception {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // SD montado, podemos ler e escrever no disco
            return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            throw new Exception("SDCARD configurado para somente leitura.");
        }
        throw new Exception("Não é possível gravar no SDCARD.");
    }

    /***********************************************************************
     *                      MÉTODOS DE LEITURA                             *
     **********************************************************************/
    public static boolean createBufferedReader(String nomeArquivo)
            throws IOException, Exception {
        if (checkSdCard()) {
            File file = new File(Environment.getExternalStorageDirectory()
                    + "/" + nomeArquivo + ".txt");
            if (file.exists()) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "ISO-8859-1"));
                return true;
            } else {
                throw new Exception("Arquivo não encontrado.");
            }
        }
        return false;
    }

    public static String getText() throws IOException {
        String aux = null;
        String ret = "";
        while ((aux = bufferedReader.readLine()) != null) {
            ret += aux + "\n";
        }

        return ret;
    }

    public static String readLine() throws IOException{
        return bufferedReader.readLine();
    }

    public static boolean closeReader() throws IOException {
        bufferedReader.close();
        return true;
    }

    /***********************************************************************
     *                      MÉTODOS DE ESCRITA                             *
     **********************************************************************/
    public static boolean createBufferedWriter(String nomeArquivo) {

        try {
            if (checkSdCard()) {
                File file = new File(Environment.getExternalStorageDirectory() + "/" + nomeArquivo + ".txt");
                if (file.exists() == false){
                    file.createNewFile();
                }
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), "ISO-8859-1"));
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        finally {
            return false;
        }

    }

    public static void write(String text) throws IOException {
        bufferedWriter.write(text);
    }

    public static boolean appendLine(String line) throws IOException{
        bufferedWriter.append(line+"\n");
        return true;
    }

    public static boolean closeWriter() throws IOException {
        bufferedWriter.close();
        return true;
    }

}