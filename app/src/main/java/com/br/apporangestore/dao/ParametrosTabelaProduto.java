package com.br.apporangestore.dao;

public class ParametrosTabelaProduto {

    public static final String NOME_BANCO = "ORANGE_SHOP_DB";
    public static final int VERSAO_BANCO = 1;
    public static final String TABELA_PRODUTO = "PRODUTO";

    public static final String PRODUTO_ID = "ID";
    public static final String PRODUTO_NOME = "NOME";
    public static final String PRODUTO_QTD = "QUANTIDADE";
    public static final String PRODUTO_CATEGORIA = "CATEGORIA";
    public static final String PRODUTO_DT_CRIACAO = "DT_CRIACAO";
    public static final String PRODUTO_DT_ALTERACAO = "DT_ALTERACAO";

    public static final String CRIACAO_TABELA =
            "CREATE TABLE IF NOT EXISTS " + TABELA_PRODUTO + "("
                    + PRODUTO_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + PRODUTO_NOME + " TEXT NOT NULL,"
                    + PRODUTO_QTD + " INTEGER NOT NULL,"
                    + PRODUTO_CATEGORIA + " TEXT NOT NULL,"
                    + PRODUTO_DT_CRIACAO + " TEXT NOT NULL,"
                    + PRODUTO_DT_ALTERACAO + " TEXT"
                    + ")";
}
