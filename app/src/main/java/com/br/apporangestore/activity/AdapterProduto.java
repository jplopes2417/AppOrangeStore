package com.br.apporangestore.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.apporangestore.dao.Banco;
import com.br.apporangestore.model.ProdutoModel;
import com.br.apporangestore.R;

import java.util.ArrayList;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.Holder> {

    private final Context context;
    private final ArrayList<ProdutoModel> arrayList;
    private final Banco banco;


    public AdapterProduto(Context context, ArrayList<ProdutoModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        banco = new Banco(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.linha, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        ProdutoModel produtoModel = arrayList.get(position);

        final String nomeProduto = produtoModel.getNomeProduto();
        final String quantidadeProduto = produtoModel.getQtdProduto();
        final String categoriaProduto = produtoModel.getCategoriaProduto();
        final String id = produtoModel.getId();
        final String dataCriacao = produtoModel.getDataCriacao();
        final String dataAlteracao = produtoModel.getDataAlteracao();

        holder.nomeProduto.setText(nomeProduto);
        holder.quantidadeProduto.setText(quantidadeProduto);
        holder.categoriaProduto.setText(categoriaProduto);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edicaoProduto(
                        "" + id,
                        "" + nomeProduto,
                        "" + quantidadeProduto,
                        "" + categoriaProduto,
                        "" + dataCriacao,
                        "" + dataAlteracao
                );
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deletarDialog(
                        "" + id
                );
                return false;
            }
        });

    }

    private void deletarDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.delReg);
        builder.setMessage(R.string.msgDelReg);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_iconfinder_trash_can);

        builder.setPositiveButton(R.string.msgPositiveBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                banco.deletarInformacao(id);
                ((MainActivity) context).onResume();
                Toast.makeText(context, R.string.msgConfirmDelReg, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(R.string.msgNegativeBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    private void edicaoProduto(String id, String nomeProduto,
                               String quantidadeProduto, String categoriaProduto,
                               String dataCriacao, String dataAlteracao) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.abEditRegAct);
        builder.setMessage(R.string.msgConfirmUpdateReg);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_pencil_edit);

        builder.setPositiveButton(R.string.msgPositiveBtn, (dialog, which) -> {
            Intent intent = new Intent(context, EditarRegistroActivity.class);
            intent.putExtra("ID", id);
            intent.putExtra("NOME_PRODUTO", nomeProduto);
            intent.putExtra("QTD_PRODUTO", quantidadeProduto);
            intent.putExtra("CATEGORIA_PRODUTO", categoriaProduto);
            intent.putExtra("DATA_CRIACAO", dataCriacao);
            intent.putExtra("DATA_ALTERACAO", dataAlteracao);
            intent.putExtra("modoEdicao", true);
            context.startActivity(intent);
        });

        builder.setNegativeButton(R.string.msgNegativeBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView nomeProduto, categoriaProduto, quantidadeProduto;
        ImageButton editButton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            nomeProduto = itemView.findViewById(R.id.nomeProdutoTv);
            categoriaProduto = itemView.findViewById(R.id.categoriaProdutoTv);
            quantidadeProduto = itemView.findViewById(R.id.qtdProdutoTv);
            editButton = itemView.findViewById(R.id.editBtn);
        }
    }

}
