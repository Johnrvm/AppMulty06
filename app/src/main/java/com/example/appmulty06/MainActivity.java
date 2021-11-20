package com.example.appmulty06;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText Usua;
    EditText Contra;
    TextView Text;
    String Acces="Access";
    String Incc= "Incc";
    String cliente="cliente";
    String proveedor= "proveedor";
    String llave="sesion";
    CheckBox Check;
    String tipoOn;

    TextView Textfull;

    //cuenta
    EditText id;
    EditText user;
    EditText tip;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();


        //cuenta
        id = (EditText) findViewById(R.id.ID);
        user = (EditText) findViewById(R.id.User);
        tip = (EditText) findViewById(R.id.CuentaD);

        //main
        Usua = (EditText) findViewById(R.id.User);
        Contra = (EditText) findViewById(R.id.Pass);
        Text =(TextView) findViewById(R.id.textView);
        tipoOn = revisarTipo();


        if(revisar()){
            if(revisarusuario()!=""){Usua.setText(revisarusuario());}
            if(revisarcontraseña()!=""){Contra.setText(revisarcontraseña());}
            inicio();
        }
       /* if(revisar() && tipoOn.equals(cliente)){
            startActivity(  new Intent(this,Cliente.class));
        }else if(revisar() && tipoOn.equals(proveedor)) {
            startActivity(  new Intent(this,Proovedor.class));
        }else{
            String mensaje = "iniciar sesion";
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }
*/
    }


    public void onClickcc(View v) {
        id.setText("a");
        user.setText("a");
        tip.setText("a");

    }
    private void inicio(){
        String usua = Usua.getText().toString();
        String cont = Contra.getText().toString();


        String sql = "https://coreappmulti.conveyor.cloud/WeatherForecast/usuario?username="+usua+"&pass="+cont;

        Intent loginc = new Intent(this, com.example.appmulty06.Cliente.class);
        Intent loginp = new Intent(this, com.example.appmulty06.Proovedor.class);
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


            String datajalada = jsonObj.getString("data");


            if (jsonObj.getString("data").equals(Acces)){


                if (jsonObj.getString("tipo").equals(cliente)){
                    startActivity(loginc);
                }else if (jsonObj.getString("tipo").equals(proveedor)){
                    startActivity(loginp);
                }

            }else if(jsonObj.getString("data").equals(Incc)){

            }else {
                Text.setText("Ha ocurrido un error "+jsonObj.getString("mensaje"));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }






    }
    public void onClick(View v) {
        String usua = Usua.getText().toString();
        String cont = Contra.getText().toString();


        String sql = "https://coreappmulti.conveyor.cloud/WeatherForecast/usuario?username="+usua+"&pass="+cont;

        Intent loginc = new Intent(this, com.example.appmulty06.Cliente.class);
        Intent loginp = new Intent(this, com.example.appmulty06.Proovedor.class);
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


            String datajalada = jsonObj.getString("data");


            if (jsonObj.getString("data").equals(Acces)){
                guardar(Check.isChecked());
                editor.putString("id", jsonObj.getString("exito"));
                editor.putString("tipo", jsonObj.getString("tipo"));
                editor.putString("nombre", jsonObj.getString("nombre"));
                editor.putString("apellido", jsonObj.getString("apellido"));
                editor.putString("apellidoSeg", jsonObj.getString("apellidoSeg"));
                editor.putString("direccion", jsonObj.getString("direccion"));
                editor.apply();

                if (jsonObj.getString("tipo").equals(cliente)){
                    startActivity(loginc);
                }else if (jsonObj.getString("tipo").equals(proveedor)){
                    startActivity(loginp);
                }

            }else if(jsonObj.getString("data").equals(Incc)){
                Text.setText("Verifica usuario o contraseña");
            }else {
                Text.setText("Ha ocurrido un error "+jsonObj.getString("mensaje"));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }






    }
    private void inicializar(){

        preferences= this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        Check = findViewById(R.id.checkBox);

    }
    private boolean revisar(){
        return  this.preferences.getBoolean(llave, false);

    }

    private String revisarTipo(){
        return  this.preferences.getString("tipo", "false");
    }

    private void  guardar(boolean checked){
        editor.putBoolean(llave,checked);
        editor.putString("usuario", Usua.getText().toString());
        editor.putString("contra", Contra.getText().toString());
        editor.apply();
    }

    private String revisarusuario(){
        return  this.preferences.getString("usuario", "");
    }
    private String revisarcontraseña(){
        return  this.preferences.getString("contra", "");
    }

    public void oss(View v) {
        Intent loginc = new Intent(this, com.example.appmulty06.Cliente.class);
        startActivity(loginc);
    }
    public void oss2(View v) {
        Intent loginc = new Intent(this, com.example.appmulty06.Proovedor.class);
        startActivity(loginc);
    }
    public void oss3(View v) {
        Intent loginc = new Intent(this, com.example.appmulty06.Registro.class);
        startActivity(loginc);
    }

}