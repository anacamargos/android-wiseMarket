package com.example.anacamargos.wisemarket;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */



public class SettingsFragment extends Fragment implements View.OnClickListener {


    private EditText settingsNome;
    private EditText settingsEmail;
    private EditText settingsTelefone;
    private EditText settingsCpf;
    private EditText settingsCep;
    private EditText settingsSenha;
    private EditText settingsNumCredito;
    private EditText settingsDatExpira;
    private EditText settingsCodigoSeguranca;
    private Button settingsButton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

        settingsNome = (EditText) getView().findViewById(R.id.settings_nome);
        settingsEmail = (EditText) getView().findViewById(R.id.settings_email);
        settingsTelefone = (EditText) getView().findViewById(R.id.settings_telefone);
        settingsCpf = (EditText) getView().findViewById(R.id.settings_cpf);
        settingsCep = (EditText) getView().findViewById(R.id.settings_cep);
        settingsSenha = (EditText) getView().findViewById(R.id.settings_password);
        settingsNumCredito = (EditText) getView().findViewById(R.id.settings_numcredito);
        settingsDatExpira = (EditText) getView().findViewById(R.id.settings_datexpira);
        settingsCodigoSeguranca = (EditText) getView().findViewById(R.id.settings_codigoseguranca);
        settingsButton = (Button) getView().findViewById(R.id.settings_button);

        // TODO pegar instacia do usuario
        // TODO setar valores com dados do usuario

        settingsNome.setText("OIOI");
        settingsEmail.setText("");
        settingsTelefone.setText("");
        settingsCpf.setText("");
        settingsCep.setText("");
        settingsSenha.setText("");
        settingsNumCredito.setText("");
        settingsDatExpira.setText("");
        settingsCodigoSeguranca.setText("");

        settingsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //registrar usuario
        String email = settingsEmail.getText().toString().trim();
        String nome = settingsNome.getText().toString().trim();
        String telefone = settingsTelefone.getText().toString().trim();
        String cpf = settingsCpf.getText().toString().trim();
        String cep = settingsCep.getText().toString().trim();
        String senha = settingsSenha.getText().toString().trim();
        String numCredito = settingsNumCredito.getText().toString().trim();
        String datExpira = settingsDatExpira.getText().toString().trim();
        String codigoSeguranca = settingsCodigoSeguranca.getText().toString().trim();


        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(nome) ||
                TextUtils.isEmpty(telefone) || TextUtils.isEmpty(cpf) || TextUtils.isEmpty(cep) ||
                TextUtils.isEmpty(numCredito) || TextUtils.isEmpty(datExpira) || TextUtils.isEmpty(codigoSeguranca) ) {
            //email is empty
            Toast.makeText(getContext(), "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok
        //Usuario usuario = new Usuario(nome, cpf, cep, telefone, email, numCredito, datExpira, codigoSeguranca, senha);
        // TODO alterar dados do Usuario


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
