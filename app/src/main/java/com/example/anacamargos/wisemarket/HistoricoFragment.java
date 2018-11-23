package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoricoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoricoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoricoFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Pedido> listaDePedidos;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HistoricoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoricoFragment.
     */
    public static HistoricoFragment newInstance(String param1, String param2) {
        HistoricoFragment fragment = new HistoricoFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText etDataInicio = (EditText) getView().findViewById(R.id.historico_dataInicio);
        final EditText etDataFim = (EditText) getView().findViewById(R.id.historico_dataFim);

        Button btn = (Button) getView().findViewById(R.id.btn);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);

        recyclerView.setVisibility(View.INVISIBLE);

        //TODO recuperar pedidos existentes no banco e trocar por esse array list
        listaDePedidos = new ArrayList<Pedido>();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.MARCH);
        c.set(Calendar.DAY_OF_MONTH, 1 );
        Pedido pedido = new Pedido(123.45, "Festa de Aniversário", c);

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 2018);
        c1.set(Calendar.MONTH, Calendar.MARCH);
        c1.set(Calendar.DAY_OF_MONTH, 28 );
        Pedido pedido2 = new Pedido(45.80, "Churrasco ", c1);

        listaDePedidos.add(pedido);
        listaDePedidos.add(pedido2);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerViewAdapterPedido recyclerViewAdapter = new RecyclerViewAdapterPedido(getContext(), listaDePedidos);
        recyclerView.setAdapter(recyclerViewAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dataInicio = etDataInicio.getText().toString().trim();
                String dataFim = etDataFim.getText().toString().trim();

                if(TextUtils.isEmpty(dataInicio) || TextUtils.isEmpty(dataFim) || datasNaoValidas(dataInicio, dataFim)) {
                    //email is empty
                    Toast.makeText(getContext(), "Por favor informe datas válidas", Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<Pedido> novaLista = retornaListasCompativeis(listaDePedidos, dataInicio, dataFim);

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                final RecyclerViewAdapterPedido recyclerViewAdapter = new RecyclerViewAdapterPedido(getContext(), novaLista);
                recyclerView.setAdapter(recyclerViewAdapter);


                recyclerView.setVisibility(View.VISIBLE);
            }
        });



    }

    public ArrayList<Pedido> retornaListasCompativeis(ArrayList<Pedido> listaDePedidos, String dataInicio, String dataFim) {

        ArrayList<Pedido> retorno = new ArrayList<Pedido>();

        Calendar inicio = retornaCalendar(dataInicio);
        Calendar fim = retornaCalendar(dataFim);

        for (int i = 0; i < listaDePedidos.size(); i ++) {

            Pedido pedidoAtual = listaDePedidos.get(i);

            Calendar dataDoPedido = pedidoAtual.getDataPedido();

            if ( ( dataDoPedido.after(inicio) && dataDoPedido.before(fim) )  || dataDoPedido.equals(inicio) ||
                    dataDoPedido.equals(fim)) {
                retorno.add(pedidoAtual);
            }
        }

        return retorno;

    }

    public boolean datasNaoValidas (String dataInicio, String dataFim) {
        //retorna true se datas forem invalidas

        boolean retorno = true;

        if (dataInicio.contains("/") && dataFim.contains("/") && seForMenor (dataInicio, dataFim)) {
            //return false;
            retorno = false;
        } else {
            //return true;
            retorno = true;
        }
        return retorno;

    }


    public Calendar retornaCalendar (String data) {
        String [] vetor = data.split("/");

        int dia = Integer.parseInt(vetor[0]);
        int mes = Integer.parseInt(vetor[1]);
        int ano = Integer.parseInt(vetor[2]);


        int mesReal = retornaMesReal(mes);

        Calendar completo = new GregorianCalendar(ano, mesReal, dia);
        return completo;
    }

    public boolean seForMenor (String dataInicio, String dataFim) {

        boolean retorno = false;

        Calendar inicio = retornaCalendar(dataInicio);
        Calendar fim = retornaCalendar(dataFim);

        if ( inicio.before(fim)) {
            retorno = true;
        }
        return retorno;

    }

    public int retornaMesReal (int mes) {
        int mesReal = mes - 1;
        return mesReal;
//        int mesReal = 0;
//        if(mes == 1) {
//            mesReal = 0;
//        } else if(mes == 2) {
//
//        } else if(mes == 3) {
//
//        } else if(mes == 4) {
//
//        } else if(mes == 5) {
//
//        } else if(mes == 6) {
//
//        } else if(mes == 7) {
//
//        } else if(mes == 8) {
//
//        } else if(mes == 9) {
//
//        } else if(mes == 10) {
//
//        } else if(mes == 11) {
//
//        } else if(mes == 12) {
//
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico, container, false);
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
