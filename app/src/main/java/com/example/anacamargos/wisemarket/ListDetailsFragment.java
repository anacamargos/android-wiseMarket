package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDetailsFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Produto> listaDeProdutos;

    int idLista;
    String nomeLista;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListDetailsFragment.
     */


    public static ListDetailsFragment newInstance(String param1, String param2) {
        ListDetailsFragment fragment = new ListDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        nomeLista = "";

        Bundle mBundle = new Bundle();
        if(mBundle != null){
            mBundle = getArguments();
            idLista = Integer.parseInt(mBundle.getString("idLista"));
            nomeLista = mBundle.getString("nomeLista");
        }

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Produtos da Lista " + nomeLista);


        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_listDetail);
        TextView totalCompra = (TextView) getView().findViewById(R.id.totalCompra);
        Button btn = (Button) getView().findViewById(R.id.btn);


        //TODO recuperar produtos que pertencem a essa lista e substituir por esse array
        listaDeProdutos = new ArrayList<Produto>();

        Produto produto = new Produto ("Coca-Cola", 5.30);
        Produto produto2 = new Produto ("Picanha 1kg", 45.90);
        listaDeProdutos.add(produto);
        listaDeProdutos.add(produto2);

        DecimalFormat df = new DecimalFormat("###,##0.00");

        final double valorDaCompra = recuperaTotalDaCompra(listaDeProdutos);
        totalCompra.setText("Total: R$" + df.format(valorDaCompra));

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerViewAdapterProduct recyclerViewAdapter = new RecyclerViewAdapterProduct(getContext(), listaDeProdutos);
        recyclerView.setAdapter(recyclerViewAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getView().getContext());
                LayoutInflater inflater = getLayoutInflater();

                View view1 = inflater.inflate(R.layout.dialog_confirma_pedido, null);
                mBuilder.setView(view1);

                Button btnSim = (Button) view1.findViewById(R.id.btnSim);
                Button btnNao = (Button) view1.findViewById(R.id.btnNao);

                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnSim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getContext(),"Pedido efetuado com sucesso", Toast.LENGTH_LONG).show();

                        Calendar calendar = Calendar.getInstance();

                        Pedido novo = new Pedido(valorDaCompra, nomeLista, calendar );
                        //TODO criar pedido no banco de dados
                    }
                });

                btnNao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getContext(),"Pedido cancelado com sucesso", Toast.LENGTH_LONG).show();
                    }
                });




            }
        });
    }

    public double recuperaTotalDaCompra ( ArrayList<Produto> listaDeProdutos) {

        double totalDaCompra = 0;

        for (int i = 0; i < listaDeProdutos.size(); i ++) {
            Produto produto = listaDeProdutos.get(i);
            totalDaCompra += produto.getValor();
        }
        return totalDaCompra;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
