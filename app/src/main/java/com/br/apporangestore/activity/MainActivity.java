package com.br.apporangestore.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.br.apporangestore.dao.ParametrosTabelaProduto;
import com.br.apporangestore.R;
import com.br.apporangestore.dao.Banco;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.abMainAct);

        mRecyclerView = findViewById(R.id.recyclerView);
        banco = new Banco(this);

        mostrarRegistro();

        floatingActionButton = findViewById(R.id.addFabButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity( new Intent(MainActivity.this, AdicionarRegistroActivity.class));
                Intent intent = new Intent(MainActivity.this, EditarRegistroActivity.class);
                intent.putExtra("modoEdicao", false);
                startActivity(intent);
            }
        });
    }

    private void mostrarRegistro() {
        AdapterProduto adapterProduto = new AdapterProduto(MainActivity.this,
                banco.buscarTodosProdutos(ParametrosTabelaProduto.PRODUTO_DT_CRIACAO + " DESC"));
        mRecyclerView.setAdapter(adapterProduto);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarRegistro();
    }
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }

 */
}