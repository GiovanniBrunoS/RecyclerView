package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ComprasAdapter extends RecyclerView.Adapter {

    private List<Compras> listaCompras;
    private int posicaoRemovidoRecentemente;
    private Compras comprasRemovidoRecentemente;
    private AppCompatActivity activity;

    ComprasAdapter(AppCompatActivity activity) {
        this.listaCompras = new ArrayList<Compras>();
        listaCompras.add(new Compras("Bolacha","Oreo",5));
        listaCompras.add(new Compras("Maçãs",3));
        listaCompras.add(new Compras("Bananas",5));
        listaCompras.add(new Compras("Carne de Frango",1));
        listaCompras.add(new Compras("Arroz",1));
        listaCompras.add(new Compras("Água mineral", "Ouro fino",1));
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ComprasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ComprasViewHolder viewHolder = (ComprasViewHolder)holder;
        viewHolder.nomeTextView.setText(listaCompras.get(position).getNome());
        viewHolder.marcaTextView.setText(listaCompras.get(position).getMarca());
        viewHolder.quantidadeTextView.setText(String.valueOf(listaCompras.get(position).getQuantidade()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getBaseContext(), EditarComprasActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("compras", listaCompras.get(holder.getAdapterPosition()));
                bundle.putInt("position",holder.getAdapterPosition());
                bundle.putInt("request_code", MainActivity.REQUEST_EDITAR_COMPRAS);
                intent.putExtras(bundle);
                activity.startActivityForResult(intent, MainActivity.REQUEST_EDITAR_COMPRAS);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCompras.size();
    }

    void remover(int position){
        posicaoRemovidoRecentemente = position;
        comprasRemovidoRecentemente = listaCompras.get(position);

        listaCompras.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,this.getItemCount());

        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.constraintLayout), "Item deletado",Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaCompras.add(posicaoRemovidoRecentemente, comprasRemovidoRecentemente);
                notifyItemInserted(posicaoRemovidoRecentemente);
            }
        });
        snackbar.show();
    }

    void inserir(Compras compras){
        listaCompras.add(compras);
        notifyItemInserted(getItemCount());
    }

    void mover(int fromPosition, int toPosition){
        if (fromPosition < toPosition)
            for (int i = fromPosition; i < toPosition; i++)
                Collections.swap(listaCompras, i, i+1);
        else
            for (int i = fromPosition; i > toPosition; i--)
                Collections.swap(listaCompras, i, i-1);
        notifyItemMoved(fromPosition,toPosition);
    }

    void editar(Compras compras, int position){
        listaCompras.get(position).setNome(compras.getNome());
        listaCompras.get(position).setMarca(compras.getMarca());
        listaCompras.get(position).setQuantidade(compras.getQuantidade());
        notifyItemChanged(position);
    }

    public static class ComprasViewHolder extends RecyclerView.ViewHolder{

        TextView nomeTextView;
        TextView marcaTextView;
        TextView quantidadeTextView;

        ComprasViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            nomeTextView = (TextView)itemView.findViewById(R.id.nomeTextView);
            marcaTextView = (TextView)itemView.findViewById(R.id.marcaTextView);
            quantidadeTextView = (TextView)itemView.findViewById(R.id.quantidadeTextView);
        }
    }
}
