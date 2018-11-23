package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class RecyclerViewAdapterPedido extends RecyclerView.Adapter<RecyclerViewAdapterPedido.ViewHolder>{


    Context context;
    ArrayList<Pedido> listaDePedidos;
    View view;
    ViewHolder viewHolder;

    public RecyclerViewAdapterPedido (Context context, ArrayList<Pedido> listaDePedidos) {
        this.context = context;
        this.listaDePedidos = listaDePedidos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.historico_card_layout, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Pedido pedido = listaDePedidos.get(position);
        holder.pedidoName.setText(pedido.getNomePedido());
        holder.pedidoValue.setText("R$"+String.valueOf(pedido.getValorPedido()));

        Calendar c = pedido.getDataPedido();
        int dataDia = c.get(Calendar.DAY_OF_MONTH);
        int dataMes = c.get(Calendar.MONTH);
        int dataAno = c.get(Calendar.YEAR);

        String mesDoAno = retornaMes(dataMes);

        holder.pedidoDate.setText(dataDia + "/" + mesDoAno + "/" + dataAno);

        /*System.out.println("Ano: "+c.get(Calendar.YEAR));
        System.out.println("Mês: "+c.get(Calendar.MONTH));
        System.out.println("Dia do Mês: "+c.get(Calendar.DAY_OF_MONTH));*/
    }

    public String retornaMes (int dataMes) {

        String mesDoAno = "";

        if(dataMes == 0) {
            mesDoAno = "Jan";
        } else if (dataMes == 1) {
            mesDoAno = "Fev";
        } else if (dataMes == 2) {
            mesDoAno = "Mar";
        } else if ( dataMes == 3) {
            mesDoAno = "Abr";
        } else if ( dataMes == 4 ) {
            mesDoAno = "Mai";
        } else if (dataMes == 5) {
            mesDoAno = "Jun";
        } else if (dataMes == 6) {
            mesDoAno = "Jul";
        } else if (dataMes == 7) {
            mesDoAno = "Ago";
        } else if (dataMes == 8) {
            mesDoAno = "Set";
        } else if (dataMes == 9) {
            mesDoAno = "Out";
        } else if (dataMes == 10) {
            mesDoAno = "Nov";
        } else if (dataMes == 11) {
            mesDoAno = "Dez";
        }
        return mesDoAno;
    }

    @Override
    public int getItemCount() {
        return listaDePedidos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView pedidoName;
        TextView pedidoValue;
        TextView pedidoDate;

        public ViewHolder (View intemView) {
            super(intemView);
            pedidoName = (TextView) itemView.findViewById(R.id.nomePedido);
            pedidoValue = (TextView) itemView.findViewById(R.id.valorPedido);
            pedidoDate = (TextView) itemView.findViewById(R.id.dataPedido);
        }


    }


}
