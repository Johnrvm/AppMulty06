package com.example.appmulty06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Registro extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    RadioButton r1,r2;
    EditText Usua;
    EditText Contra;
    TextView Text;
    EditText Nombre;
    EditText Apellido1;
    EditText Apellido2;
    EditText Direccion;
    String Not="Not";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getApplicationContext().getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor= preferences.edit();
        setContentView(R.layout.activity_registro);
        Usua = (EditText) findViewById(R.id.name);
        Contra = (EditText) findViewById(R.id.pass);
        Text =(TextView) findViewById(R.id.textView2);
        r1= (RadioButton) findViewById(R.id.radioButton4);
        r2= (RadioButton) findViewById(R.id.radioButton);
        Nombre = (EditText) findViewById(R.id.Nombre);
        Apellido1 = (EditText) findViewById(R.id.apellido1);
        Apellido2 = (EditText) findViewById(R.id.apellido2);
        Direccion = (EditText) findViewById(R.id.editTextTextMultiLine);



    }


    public void onClick(View v) {
        String usua = Usua.getText().toString();
        String cont = Contra.getText().toString();
        String tipo="";
        String nombre= Nombre.getText().toString();
        String apellido1 = Apellido1.getText().toString();
        String apellido2 = Apellido2.getText().toString();
        String direccion = Direccion.getText().toString();

        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "No se ha seleccionado el tipo de cuenta", Toast.LENGTH_SHORT);
        Toast toast2 =
                Toast.makeText(getApplicationContext(),
                        "Registro exitoso", Toast.LENGTH_SHORT);
        Toast toast3 =
                Toast.makeText(getApplicationContext(),
                        "informacion no valida", Toast.LENGTH_SHORT);


        if(r1.isChecked()==true){
            tipo="cliente";
        }else if (r2.isChecked()==true){
            tipo="proveedor";
        }

        String sql = "https://coreappmulti.conveyor.cloud/WeatherForecast/registro?usuario="+usua+"&contrasena="+cont+"&tipo="+tipo+"&nombre="+nombre+"&ape="+apellido1+"&ape2="+apellido1+"&dir="+direccion;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url=null;
        HttpURLConnection conn;



        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

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


            if (jsonObj.getString("tipo").equals(Not)){
                toast3.show();
            }else if(jsonObj.getString("tipo") != Not){

                editor.putString("id", jsonObj.getString("id"));
                editor.putString("tipo", jsonObj.getString("tipo"));
                editor.putString("nombre", jsonObj.getString("nombre"));
                editor.putString("apellido", jsonObj.getString("apellido"));
                editor.putString("apellidoSeg", jsonObj.getString("apellidoSeg"));
                editor.putString("direccion", jsonObj.getString("direccion"));
                editor.apply();
                Usua.setText(revisarid());
                toast2.show();
            zz();
            }else {
                Text.setText("Ha ocurrido un error "+jsonObj.getString("tipo"));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String revisarid(){
        return  this.preferences.getString("id", "1");
    }

    private void zz (){


        Toast toast2 =
                Toast.makeText(getApplicationContext(),
                        "Registro exitoso", Toast.LENGTH_SHORT);
        Toast toast3 =
                Toast.makeText(getApplicationContext(),
                        "informacion no valida", Toast.LENGTH_SHORT);


        String sql = "https://coreappmulti.conveyor.cloud/WeatherForecast/registro_en_cuenta?Idusuario="+revisarid()+"&SoloSaldo=0";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url=null;
        HttpURLConnection conn;



        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

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


            if (jsonObj.getString("idUsuarioS").equals("null")){
                toast3.show();
            }else if(jsonObj.getString("idUsuarioS") != "null"){
                toast2.show();



            }else {
                Text.setText("Ha ocurrido un error "+jsonObj.getString("tipo"));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}