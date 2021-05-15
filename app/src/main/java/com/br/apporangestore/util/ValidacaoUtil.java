package com.br.apporangestore.util;

import android.text.TextUtils;
import android.widget.EditText;

public class ValidacaoUtil {

    public static void validarPreenchimentoTexto(EditText texto) {
        if (TextUtils.isEmpty(texto.getText().toString().trim())) {
            texto.setError("!");
            texto.requestFocus();
        }
    }

}
