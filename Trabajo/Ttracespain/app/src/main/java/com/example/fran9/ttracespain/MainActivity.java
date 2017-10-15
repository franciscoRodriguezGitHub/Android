package com.example.fran9.ttracespain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    NfcManager nfcManager;
    String Usuario,Contrasena;
    private EditText txtUsuario;
    private EditText txtContrasena;
    private Button bTingresar;
    String cadena, NumeroSerie,DatosRecogido,DatosActividad;
    String [] arrayString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText) findViewById(R.id.eTusuario);
        txtContrasena = (EditText) findViewById(R.id.eTcontrasena);
        bTingresar = (Button) findViewById(R.id.bTingresar);

        nfcManager = new NfcManager(this);
        nfcManager.onActivityCreate();

        nfcManager.setOnTagReadListener(new NfcManager.TagReadListener() {
            @Override
            public void onTagRead(String tagRead) {
                Toast.makeText(MainActivity.this, "La etiqueta contiene:"+tagRead, Toast.LENGTH_LONG).show();
                cadena = tagRead.toString();
                arrayString = cadena.split("-");
                txtUsuario.setText(arrayString[1]);
                txtContrasena.setText(arrayString[2]);
                NumeroSerie=arrayString[3];

                DatosRecogido=txtUsuario.getText().toString()+" "+txtContrasena.getText().toString()+" "+NumeroSerie;
                DatosActividad= arrayString[0];
                //Texto.setText(tagRead);
            }
        });



        bTingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificarLogin(txtUsuario.getText().toString().toLowerCase(),txtContrasena.getText().toString().toLowerCase());
            }
        });

    }
    public void VerificarLogin(String user, String password){
        Usuario = user;
        Contrasena = password;

        if((Usuario.equals("fran")) && (Contrasena.equals("123"))){
            Toast.makeText(MainActivity.this,"Te has Logueado correctamente",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, OpcionesActivity.class);
            intent.putExtra("parametro1", DatosActividad);
            intent.putExtra("parametro2", DatosRecogido);

            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"Incorrecto",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcManager.onActivityResume();
    }

    @Override
    protected void onPause() {
        nfcManager.onActivityPause();
        super.onPause();
    }

    @Override
    public void onNewIntent(Intent intent){
        nfcManager.onActivityNewIntent(intent);
    }

}
