package com.example.appmulty06;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentaGeneral#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentaGeneral extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CuentaGeneral() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaGeneral.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaGeneral newInstance(String param1, String param2) {
        CuentaGeneral fragment = new CuentaGeneral();
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
    private TextView id;
    private TextView user;
    private TextView tip;
    private EditText Direc;
    private TextView Sal;
    private Button XD;


    private TextView full;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=  inflater.inflate(R.layout.fragment_cuenta_general, container, false);

        preferences = getActivity().getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor= preferences.edit();

        id = (TextView) root.findViewById(R.id.ID2);
        user = (TextView) root.findViewById(R.id.User2);
        tip = (TextView) root.findViewById(R.id.Tipo2);
        full = (TextView)root.findViewById(R.id.textView6);
        Direc = (EditText)root.findViewById(R.id.Dir);
        Sal = (TextView)root.findViewById(R.id.Saldo);

        XD = (Button) root.findViewById(R.id.button5);


        id.setText(revisarid());
        user.setText(revisarusuario());
        tip.setText(revisartipo());
        full.setText(revisarnombre()+ "\n"+revisarapellido1()+ "\n"+revisarapellido2());
        Direc.setHint(revisardireccion());
        Sal.setText(revisarSaldo());
        XD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginc = new Intent(getContext(), com.example.appmulty06.MainActivity.class);
                zzz();
                editor.putString("id",null);
                editor.putString("usuario",null);
                editor.putString("contra",null);
                editor.putString("tipo",null);

                editor.apply();
                startActivity(loginc);
            }
            private void zzz() {
                editor.putBoolean("llave",false);
                editor.apply();

            }
        });

        return root;
    }


    private String revisarid(){
        return  this.preferences.getString("id", "");
    }
    private String revisarusuario(){
        return  this.preferences.getString("usuario", "");
    }
    private String revisartipo(){
        return  this.preferences.getString("tipo", "");
    }
    private String revisarnombre(){
        return  this.preferences.getString("nombre", "");
    }
    private String revisarapellido1(){
        return  this.preferences.getString("apellido", "");
    }
    private String revisarapellido2(){
        return  this.preferences.getString("apellidoSeg", "");
    }
    private String revisardireccion(){
        return  this.preferences.getString("direccion", "");
    }
    private String revisarSaldo(){
        return  this.preferences.getString("saldo", "");
    }
}