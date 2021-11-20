package com.example.appmulty06.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmulty06.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Saldo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Saldo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Saldo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Saldo.
     */
    // TODO: Rename and change types and number of parameters
    public static Saldo newInstance(String param1, String param2) {
        Saldo fragment = new Saldo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private TextView a;
    private  TextView paranaga;

    private String elID;
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
        View root = inflater.inflate(R.layout.fragment_saldo, container, false);


        preferences = getActivity().getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor= preferences.edit();
        a = (TextView) root.findViewById(R.id.Saldo);
        paranaga = (TextView) root.findViewById(R.id.textView12);
       // elID = revisarusuario();
        inicio();
        return root;

    }

    public void rata (View v){
        Toast toast3 =
                Toast.makeText(getContext(),
                        revisarSaldo(), Toast.LENGTH_SHORT);
        toast3.show();

    }

    private void inicio(){
        //int elID = Integer.valueOf(revisarusuario());
        //
paranaga.setText(revisarSaldo());
        String hhh = revisarSaldo();
        String sql = "https://coreappmulti.conveyor.cloud/WeatherForecast/consultarSaldo?idusuario="+hhh;


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url=null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            String json = "";

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            json = response.toString();

            JSONObject jsonObj = null;

            jsonObj = new JSONObject(json);



            a.setText(jsonObj.getString("mensaje")+"$");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }






    }
    public void AñadirSaldo(View v){
        //int elID = Integer.valueOf(revisarusuario());
        //
        Toast toast2 =
                Toast.makeText(getContext(),
                        "Registro en cuenta exitoso", Toast.LENGTH_SHORT);

        String sql = "https://coreappmulti.conveyor.cloud/WeatherForecast/cambiosSaldo?idUsua=1&tipo=aporte&xx=52";


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url=null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            String json = "";

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            json = response.toString();

            JSONObject jsonObj = null;

            jsonObj = new JSONObject(json);



            a.setText(jsonObj.getString("saldo")+"$");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String revisarSaldo(){

       return  this.preferences.getString("id", "1");

    }
}