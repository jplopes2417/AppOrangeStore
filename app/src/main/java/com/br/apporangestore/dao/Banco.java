package com.br.apporangestore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.br.apporangestore.model.ProdutoModel;

import java.util.ArrayList;

public class Banco extends SQLiteOpenHelper {

    private static final String LOG_TAG = "BANCO DE DADOS: ";

    public Banco(@Nullable Context context) {
        super(context, ParametrosTabelaProduto.NOME_BANCO, null, ParametrosTabelaProduto.VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ParametrosTabelaProduto.CRIACAO_TABELA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ParametrosTabelaProduto.TABELA_PRODUTO);
        onCreate(db);
    }

    public long adicionarInformacao(String nomeProduto, int quantidadeProduto, String categoriaProduto,
                                    String dataCriacao, String dataAlteracao) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ParametrosTabelaProduto.PRODUTO_NOME, nomeProduto);
        values.put(ParametrosTabelaProduto.PRODUTO_QTD, quantidadeProduto);
        values.put(ParametrosTabelaProduto.PRODUTO_CATEGORIA, categoriaProduto);
        values.put(ParametrosTabelaProduto.PRODUTO_DT_CRIACAO, dataCriacao);
        values.put(ParametrosTabelaProduto.PRODUTO_DT_ALTERACAO, dataAlteracao);

        long id = db.insert(ParametrosTabelaProduto.TABELA_PRODUTO, null, values);
        db.close();
        return id;
    }

    public void atualizarInformacao(String id, String nomeProduto, int quantidadeProduto, String categoriaProduto,
                                    String dataCriacao, String dataAlteracao) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ParametrosTabelaProduto.PRODUTO_NOME, nomeProduto);
        values.put(ParametrosTabelaProduto.PRODUTO_QTD, quantidadeProduto);
        values.put(ParametrosTabelaProduto.PRODUTO_CATEGORIA, categoriaProduto);
        values.put(ParametrosTabelaProduto.PRODUTO_DT_CRIACAO, dataCriacao);
        values.put(ParametrosTabelaProduto.PRODUTO_DT_ALTERACAO, dataAlteracao);

        db.update(ParametrosTabelaProduto.TABELA_PRODUTO, values,
                ParametrosTabelaProduto.PRODUTO_ID + " = ?", new String[]{id});
        db.close();
    }

    public void deletarInformacao(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(ParametrosTabelaProduto.TABELA_PRODUTO, ParametrosTabelaProduto.PRODUTO_ID + " = ?", new String[]{id});
        db.close();
    }

    public ArrayList<ProdutoModel> buscarTodosProdutos(String orderBy) {

        ArrayList<ProdutoModel> arrayList = new ArrayList<>();

        String consultaProduto = "SELECT * FROM "
                + ParametrosTabelaProduto.TABELA_PRODUTO + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(consultaProduto, null);

        if (cursor.moveToNext()) {

            do {
                ProdutoModel produtoModel = new ProdutoModel(
                        "" + cursor.getInt(cursor.getColumnIndex(ParametrosTabelaProduto.PRODUTO_ID)),
                        "" + cursor.getString(cursor.getColumnIndex(ParametrosTabelaProduto.PRODUTO_NOME)),
                        "" + cursor.getInt(cursor.getColumnIndex(ParametrosTabelaProduto.PRODUTO_QTD)),
                        "" + cursor.getString(cursor.getColumnIndex(ParametrosTabelaProduto.PRODUTO_CATEGORIA)),
                        "" + cursor.getString(cursor.getColumnIndex(ParametrosTabelaProduto.PRODUTO_DT_CRIACAO)),
                        "" + cursor.getString(cursor.getColumnIndex(ParametrosTabelaProduto.PRODUTO_DT_ALTERACAO)));
                arrayList.add(produtoModel);

            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public boolean validarProdutoDuplicado(String nomeProduto, String categoriaProduto) {

        String consultaProduto = "SELECT * FROM " + ParametrosTabelaProduto.TABELA_PRODUTO +
                "\n WHERE 1=1" +
                "\n AND UPPER(" + ParametrosTabelaProduto.PRODUTO_NOME + ") = " + "'" + nomeProduto.toUpperCase() + "'" +
                "\n AND UPPER(" + ParametrosTabelaProduto.PRODUTO_CATEGORIA + ") = " + "'" + categoriaProduto.toUpperCase() + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(consultaProduto, null);
        Log.d(Banco.LOG_TAG, consultaProduto);

        if (cursor.moveToNext()) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }
}
