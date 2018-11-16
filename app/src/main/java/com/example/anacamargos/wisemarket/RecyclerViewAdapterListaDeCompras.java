package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterListaDeCompras extends RecyclerView.Adapter<RecyclerViewAdapterListaDeCompras.ViewHolder> {

    Context context;
    ArrayList<ListaDeCompras> listaDeCompras;
    View view;
    RecyclerViewAdapterListaDeCompras.ViewHolder viewHolder;

    public RecyclerViewAdapterListaDeCompras (Context context, ArrayList<ListaDeCompras> listaDeCompras) {
        this.context = context;
        this.listaDeCompras = listaDeCompras;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_card_layout, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ListaDeCompras listaDeComprasNovo = listaDeCompras.get(position);
        holder.listName.setText(listaDeComprasNovo.getNomeLista());
        //holder.productValue.setText("R$"+String.valueOf(produto.getValor()));
    }

    @Override
    public int getItemCount() {
        return listaDeCompras.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView listName;

        public ViewHolder (View intemView) {
            super(intemView);
            listName = (TextView) itemView.findViewById(R.id.nomeLista);

        }


    }

}
