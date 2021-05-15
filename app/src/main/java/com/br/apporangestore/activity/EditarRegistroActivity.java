package com.br.apporangestore.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.apporangestore.R;
import com.br.apporangestore.dao.Banco;
import com.br.apporangestore.util.ValidacaoUtil;

public class EditarRegistroActivity extends AppCompatActivity {

    private static final String LOG_TAG = EditarRegistroActivity.class.getName();
    private EditText nomeProdutoEt, qtdProdutoEt, categoriaProdutoEt;

    Button salvarBtn;
    ActionBar actionBar;

    private String id;
    private String nomeProduto;
    private String categoriaProduto;
    private String dataCriacao;
    private String qtdProduto;
    private boolean modoEdicao = false;
    private Banco dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_registro);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        nomeProdutoEt = findViewById(R.id.nomeProduto);
        qtdProdutoEt = findViewById(R.id.qtdProduto);
        categoriaProdutoEt = findViewById(R.id.categoriaProduto);

        salvarBtn = findViewById(R.id.AddBtn);

        Intent intent = getIntent();
        modoEdicao = intent.getBooleanExtra("modoEdicao", modoEdicao);
        id = intent.getStringExtra("ID");
        nomeProduto = intent.getStringExtra("NOME_PRODUTO");
        qtdProduto = intent.getStringExtra("QTD_PRODUTO");
        categoriaProduto = intent.getStringExtra("CATEGORIA_PRODUTO");
        dataCriacao = intent.getStringExtra("DATA_CRIACAO");
        String dataAlteracao = intent.getStringExtra("DATA_ALTERACAO");

        if (modoEdicao) {

            actionBar.setTitle(R.string.abEditRegAct);

            nomeProdutoEt.setText(nomeProduto);
            qtdProdutoEt.setText(qtdProduto);
            categoriaProdutoEt.setText(categoriaProduto);
        } else {
            actionBar.setTitle(R.string.abAddRegAct);
        }

        dbHelper = new Banco(this);

        salvarBtn.setOnClickListener(v -> {

            boolean validacao;

            validacao = ValidacaoUtil.validarPreenchimentoTexto(nomeProdutoEt, qtdProdutoEt, categoriaProdutoEt);

            if (validacao) {
                if (!modoEdicao) {

                    validacao = dbHelper.validarProdutoDuplicado(nomeProdutoEt.getText().toString().trim(),
                            categoriaProdutoEt.getText().toString().trim());

                    if (validacao) {
                        Toast.makeText(EditarRegistroActivity.this,
                                R.string.errorDuplicateReg,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(LOG_TAG, "Atualização de registro: " + id);
                        gravarDados();
                    }
                } else {
                    Log.d(LOG_TAG, "Gravação de registro: " + id);
                    gravarDados();
                }
            } else {
                Log.e(LOG_TAG, "Tudo está vazio!");
            }
        });

    }

    private void gravarDados() {
        getDados();
        startActivity(new Intent(EditarRegistroActivity.this, MainActivity.class));
        Toast.makeText(EditarRegistroActivity.this, R.string.sucessReg, Toast.LENGTH_SHORT).show();
    }

    private void getDados() {
        nomeProduto = "" + nomeProdutoEt.getText().toString().trim();
        qtdProduto = "" + qtdProdutoEt.getText().toString().trim();
        categoriaProduto = "" + categoriaProdutoEt.getText().toString().trim();
        int auxQtdProduto = Integer.parseInt(qtdProduto);

        if (modoEdicao) {
            String novaAtualizacaoHorario = "" + System.currentTimeMillis();

            dbHelper.atualizarInformacao(
                    "" + id,
                    "" + nomeProduto,
                    auxQtdProduto,
                    "" + categoriaProduto,
                    "" + dataCriacao,
                    "" + novaAtualizacaoHorario

            );
        } else {

            dataCriacao = "" + System.currentTimeMillis();

            dbHelper.adicionarInformacao(
                    "" + nomeProduto,
                    auxQtdProduto,
                    "" + categoriaProduto,
                    "" + dataCriacao,
                    null
            );
        }
        startActivity(new Intent(EditarRegistroActivity.this, MainActivity.class));
    }


    @Override
    public boolean onSupportNavigateUp() {
        // Função para voltar pra essa tela quando o botão de voltar é pressionado.
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}