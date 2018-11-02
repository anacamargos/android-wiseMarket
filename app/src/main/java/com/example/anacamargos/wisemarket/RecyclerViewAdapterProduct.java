package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class RecyclerViewAdapterProduct extends RecyclerView.Adapter<RecyclerViewAdapterProduct.ViewHolder> {

        Context context;
        ArrayList<Produto> listaDeProdutos;
        View view;
        ViewHolder viewHolder;

        public RecyclerViewAdapterProduct (Context context, ArrayList<Produto> listaDeProdutos) {
            this.context = context;
            this.listaDeProdutos = listaDeProdutos;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.product_card_layout, parent, false);
            viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Produto produto = listaDeProdutos.get(position);
            holder.productName.setText(produto.getNome());
            holder.productValue.setText("R$"+String.valueOf(produto.getValor()));
        }

        @Override
        public int getItemCount() {
            return listaDeProdutos.size();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView productName;
            TextView productValue;

            public ViewHolder (View intemView) {
                super(intemView);
                productName = (TextView) itemView.findViewById(R.id.nomeProduto);
                productValue = (TextView) itemView.findViewById(R.id.valorProduto);
            }


        }
}

