package com.br.apporangestore.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.br.apporangestore.R;

public class ValidacaoUtil {

    private static final String LOG_TAG = ValidacaoUtil.class.getName();

    public static boolean validarPreenchimentoTexto(EditText nomeProdutoEt,
                                                    EditText qtdProdutoEt,
                                                    EditText categoriaProduto) {
        boolean dadosValidos = true;

        Log.d(LOG_TAG, "Validando o preenchimento dos campos.");

        if (TextUtils.isEmpty(nomeProdutoEt.getText().toString().trim())) {
            Log.d(LOG_TAG, "Campo vazio: " + nomeProdutoEt);
            nomeProdutoEt.setError("!");
            nomeProdutoEt.requestFocus();
            dadosValidos = false;
        }

        if (TextUtils.isEmpty(qtdProdutoEt.getText().toString().trim())) {
            Log.d(LOG_TAG, "Campo vazio: " + qtdProdutoEt);
            qtdProdutoEt.setError("!");
            qtdProdutoEt.requestFocus();
            dadosValidos = false;
        }

        if (TextUtils.isEmpty(categoriaProduto.getText().toString().trim())) {
            Log.d(LOG_TAG, "Campo vazio: " + categoriaProduto);
            categoriaProduto.setError("!");
            categoriaProduto.requestFocus();
            dadosValidos = false;
        }
        return dadosValidos;
    }

    public static void validarValorNegativo(EditText qtdProdutoEt) {
        int valor;
        if(!TextUtils.isEmpty(qtdProdutoEt.getText().toString().trim())){
        valor = Integer.parseInt(qtdProdutoEt.getText().toString());
        if(valor <= 0) {
            qtdProdutoEt.setError("!");
            qtdProdutoEt.requestFocus();
            }
        } else {
            qtdProdutoEt.requestFocus();
        }
    }
}
