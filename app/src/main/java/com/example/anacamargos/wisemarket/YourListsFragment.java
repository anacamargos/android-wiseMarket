package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YourListsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YourListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourListsFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ListaDeCompras> listaDeCompras;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public YourListsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YourListsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YourListsFragment newInstance(String param1, String param2) {
        YourListsFragment fragment = new YourListsFragment();
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
        return inflater.inflate(R.layout.fragment_your_lists, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_list);
        listaDeCompras = new ArrayList<ListaDeCompras>();

        ListaDeCompras lista1 = new ListaDeCompras("Churrasco");
        ListaDeCompras lista2 = new ListaDeCompras("Supermercado da semana");
        listaDeCompras.add(lista1);
        listaDeCompras.add(lista2);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerViewAdapterListaDeCompras recyclerViewAdapter = new RecyclerViewAdapterListaDeCompras(getContext(), listaDeCompras);
        recyclerView.setAdapter(recyclerViewAdapter);

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = getLayoutInflater();

                View view1 = inflater.inflate(R.layout.dialog_new_list, null);
                mBuilder.setView(view1);

                final EditText nomeLista = (EditText) view1.findViewById(R.id.nomeLista_dialog);

                Button btn = (Button) view1.findViewById(R.id.btn);

                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!nomeLista.getText().toString().isEmpty() ) {
                            ListaDeCompras novaLista = new ListaDeCompras (nomeLista.getText().toString());
                            listaDeCompras.add(novaLista);
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                            recyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(view.getContext(), "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListenerListaDeCompras(this, recyclerView, new RecyclerItemClickListenerListaDeCompras.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ListaDeCompras novaLista = listaDeCompras.get(position);
                Toast.makeText(getContext(), "Cliquei na lista " + novaLista.getNomeLista(), Toast.LENGTH_SHORT ).show();


                //Fragment fragment = new tasks();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ListDetailsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


                /*FragmentManager fm;
                fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout, new ListDetailsFragment());
                //barra = "Listas de Compras";
                //toolbar.setTitle(barra);
                ft.commit(); */
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));




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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
