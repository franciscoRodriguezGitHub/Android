package com.example.franrod.ejercicio01;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener{
    /* Variables  */
    TextView texto;
    CheckBox chb1,chb2,chb3,chb4,chb5;
    RadioGroup rbgroup ;
    EditText txtNumero1,txtNumero2;
    TextView Resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Inicializo las Variables  */
        texto=(TextView) findViewById(R.id.txtNombre);
        chb1=(CheckBox)findViewById(R.id.checkBox1);
        chb2=(CheckBox)findViewById(R.id.checkBox2);
        chb3=(CheckBox)findViewById(R.id.checkBox3);
        chb4=(CheckBox)findViewById(R.id.checkBox4);
        chb5=(CheckBox)findViewById(R.id.checkBox5);
        txtNumero1=(EditText) findViewById(R.id.txtNumero1);
        txtNumero2=(EditText) findViewById(R.id.txtNumero2);
        Resultado=(TextView)  findViewById(R.id.txtResultado);
        rbgroup = (RadioGroup) findViewById(R.id.radioGroup1);
        /* Le añado el evento setOnCheckedChangeListener */
        chb1.setOnCheckedChangeListener(this);
        chb2.setOnCheckedChangeListener(this);
        chb3.setOnCheckedChangeListener(this);
        chb4.setOnCheckedChangeListener(this);
        /* Es otra forma de añadir el evento setOnCheckedChangeListener */
        chb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chb5.setText("Activo");
                } else {
                    chb5.setText("no Activo");
                }
            }
        });
        //Borra la selección. Cuando se borra la selección,
        // se selecciona ningún botón de opción en este grupo
        // y getCheckedRadioButtonId()devuelve un valor nulo.
       // rbgroup.clearCheck();
        /* Le añado el evento setOnCheckedChangeListener */
        rbgroup.setOnCheckedChangeListener(this);

    }
    @Override
    /*Aqui Recoge el onCheckedChanged Del CompoundButton */
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.checkBox1:
                if (b) {
                    texto.setTextColor(Color.RED);
                } else {
                    texto.setTextColor(Color.BLACK);
                }
                break;
            case R.id.checkBox2:
                if (b) {
                    texto.setTextSize(3);
                } else {
                    texto.setTextSize(13);
                }
                break;
            case R.id.checkBox3:
                if (b) {
                    texto.setTextSize(20);
                } else {
                    texto.setTextSize(13);
                }
                break;
            case R.id.checkBox4:
                if (b) {
                    texto.setTypeface(null, Typeface.BOLD);//negrita
                } else {
                    texto.setTypeface(null, Typeface.NORMAL);
                }
                break;
            default:
                break;
        }
    }
    /*Aqui Recoge el CheckedChanged del RadioGroup*/
    @Override
    public void onCheckedChanged(RadioGroup rg, int checkedId) {
        //variables para las operaciones
        int aux1 = Integer.valueOf(txtNumero1.getText().toString());
        int aux2 = Integer.valueOf(txtNumero2.getText().toString());
        int operacion;
       // switch (checkedId){
            switch (rg.getCheckedRadioButtonId()){
            case R.id.radio0:
                 operacion = aux1 + aux2;
                 Resultado.setText(String.valueOf(operacion));//suma
                break;
            case R.id.radio1:
                operacion = aux1 - aux2;
                Resultado.setText(String.valueOf(operacion));//resta
                break;
            case R.id.radio2:
                operacion = aux1 * aux2;
                Resultado.setText(String.valueOf(operacion));//multiplicar
                break;
            case R.id.radio3:
                operacion = aux1 / aux2;
                Resultado.setText(String.valueOf(operacion));//dividir
                break;
            default:
                break;
        }

    }
}
