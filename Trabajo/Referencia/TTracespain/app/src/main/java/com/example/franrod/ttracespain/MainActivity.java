package com.example.franrod.ttracespain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText eTusuario;

    private EditText eTcontraseña;
    private Button bTingresar;
    private Button registro;
    private RequestQueue mRequest;


    private VolleyRP volley;
    private String USER = "";
    private String PASSWORD = "";
    //private static final String IP = "http://kevinandroidkap.pe.hu/ArchivosPHP/Login_GETID.php?id=";
    //private static final String IP_TOKEN = "http://kevinandroidkap.pe.hu/ArchivosPHP/Token_INSERTandUPDATE.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTusuario = (EditText) findViewById(R.id.eTusuario);
        eTcontraseña = (EditText) findViewById(R.id.eTcontrasena);
        bTingresar = (Button) findViewById(R.id.bTingresar);
        bTingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificarLogin(eTusuario.getText().toString().toLowerCase(),eTcontraseña.getText().toString().toLowerCase());
            }
        });
    }

    public void VerificarLogin(String user, String password){
        USER = user;
        PASSWORD = password;
        Toast.makeText(MainActivity.this,"user y contraseña"+USER+"  --"+PASSWORD,Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,PrimeraFichaActivity.class);
        startActivity(intent);
        //SolicitudJSON(IP+user);
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                VerificarPassword(datos);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Ocurrio un error, por favor contactese con el administrador",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volley);
    }

    public void VerificarPassword(JSONObject datos){
        //aqui hacer la validacion
    }

}
