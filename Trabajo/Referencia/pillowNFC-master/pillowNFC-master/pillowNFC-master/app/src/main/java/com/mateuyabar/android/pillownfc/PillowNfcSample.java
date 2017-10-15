package com.mateuyabar.android.pillownfc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mateuyabar.android.pillownfc.util.WriteTagHelper;

import java.util.Date;
import java.util.Locale;

/**
 * Sample activity
 */
public class PillowNfcSample extends ActionBarActivity implements TextToSpeech.OnInitListener {
	PillowNfcManager nfcManager;
	WriteTagHelper writeHelper;
	EditText datos;
	TextView leerDatos;
	TextToSpeech textToSpeech;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		datos=(EditText) findViewById(R.id.txtDatos) ;
		leerDatos= (TextView) findViewById(R.id.txtLeerDatos) ;
		nfcManager = new PillowNfcManager(this);
		nfcManager.onActivityCreate();
		textToSpeech = new TextToSpeech(this,  this);
		nfcManager.setOnTagReadListener(new PillowNfcManager.TagReadListener() {
			@Override
			public void onTagRead(String tagRead) {
				Toast.makeText(PillowNfcSample.this, "La etiqueta contiene:"+tagRead, Toast.LENGTH_LONG).show();
				leerDatos.setText(tagRead.toString());
				textToSpeech.setLanguage( new Locale( "spa", "ESP" ) );
				speak( leerDatos.getText().toString() );
			}
		});


		writeHelper= new WriteTagHelper(this, nfcManager);
		nfcManager.setOnTagWriteErrorListener(writeHelper);
		nfcManager.setOnTagWriteListener(writeHelper);
//		// If don't want to use the Write helper you can use the following code
//		nfcManager.setOnTagWriteListener(new TagWriteListener() {
//			@Override
//			public void onTagWritten() {
//				Toast.makeText(SampleActivity.this, "tag writen", Toast.LENGTH_LONG).show();
//			}
//		});
//		nfcManager.setOnTagWriteErrorListener(new TagWriteErrorListener() {
//			@Override
//			public void onTagWriteError(NFCWriteException exception) {
//				Toast.makeText(SampleActivity.this, exception.getType().toString(), Toast.LENGTH_LONG).show();
//			}
//		});
		
		Button writeButton = (Button) findViewById(R.id.write_button);
		writeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = datos.getText().toString();
				writeHelper.writeText(text);
//				// If don't want to use the Write helper you can use the following code
//				nfcManager.writeText(text);
			}
		});
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
