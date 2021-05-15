package com.br.apporangestore.model;

public class ProdutoModel {

    String id, nomeProduto, qtdProduto, categoriaProduto, dataCriacao, dataAlteracao;

    public ProdutoModel(String id, String nomeProduto, String qtdProduto,
                        String categoriaProduto, String dataCriacao, String dataAlteracao) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.categoriaProduto = categoriaProduto;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(String qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
