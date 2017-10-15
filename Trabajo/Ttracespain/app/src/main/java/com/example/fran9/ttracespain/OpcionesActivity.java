package com.example.fran9.ttracespain;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fran9.ttracespain.util.WriteTagHelper;

import java.util.Locale;

public class OpcionesActivity extends AppCompatActivity implements View.OnClickListener,TextToSpeech.OnInitListener {
    TextView leerDatos,leerDatos2,leerEtiqueta;
    String RecuperarDatos,RecuperarDatos2;
    EditText txtDatos;
    Button btnAplicacion;
    ImageButton btnSonido;
    Button writeButton;
    String NumeroSerie,Usuario,Contrasena;
    String [] arrayString;
    NfcManager nfcManager;
    WriteTagHelper writeHelper;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        leerDatos= (TextView) findViewById(R.id.txtLeerDatos) ;
        txtDatos= (EditText) findViewById(R.id.txtDatos) ;
        leerDatos2= (TextView) findViewById(R.id.txtLeerDatos2) ;
        leerEtiqueta= (TextView) findViewById(R.id.todo) ;
        RecuperarDatos = getIntent().getExtras().getString("parametro1");
        RecuperarDatos2 = getIntent().getExtras().getString("parametro2");
        textToSpeech = new TextToSpeech(this,  this);


        arrayString = RecuperarDatos2.split(" ");
        Usuario=arrayString[0];
        Contrasena=arrayString[1];
        NumeroSerie=arrayString[2];

        leerDatos.setText(RecuperarDatos);
        leerDatos2.setText(RecuperarDatos2);

        txtDatos.setText("Numero de Serie: \nFecha: \n-"+Usuario+"-"+Contrasena+"-"+NumeroSerie+"");
        writeButton = (Button) findViewById(R.id.write_button);
        btnAplicacion =(Button) findViewById(R.id.btnAplicacion) ;
        btnSonido = (ImageButton) findViewById(R.id.btnSonido) ;
        btnSonido.setOnClickListener(this);
        writeButton.setOnClickListener(this);
        btnAplicacion.setOnClickListener(this);

        nfcManager = new NfcManager(this);
        nfcManager.onActivityCreate();

        writeHelper= new WriteTagHelper(this, nfcManager);
        nfcManager.setOnTagWriteErrorListener(writeHelper);
        nfcManager.setOnTagWriteListener(writeHelper);


        nfcManager.setOnTagReadListener(new NfcManager.TagReadListener() {
            @Override
            public void onTagRead(String tagRead) {
                leerEtiqueta.setText(tagRead);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAplicacion:
                Intent intent = new Intent(OpcionesActivity.this, PrimeraFichaActivity.class);
                startActivity(intent);
                break;
            case R.id.write_button:
                writeHelper.writeText(txtDatos.getText().toString());
                break;
            case R.id.btnSonido:
                textToSpeech.setLanguage( new Locale( "spa", "ESP" ) );
                speak( RecuperarDatos   );
                break;
            default:
                break;
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

    @Override
    public void onInit(int status) {
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast.makeText( this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT ).show();
        }
    }

    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }
}
