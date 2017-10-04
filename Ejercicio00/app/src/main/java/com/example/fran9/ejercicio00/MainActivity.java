package com.example.fran9.ejercicio00;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    /*  Variables   */
    Button botonSaludo,btnSumar,btnRojo,btnAzul,btnDerecha,btnIzquierda,btnMenos,btnMas;
    Button btnSiguiente,btnAtras,btnSiguiente2,btnAtras2;
    ImageButton ImgBtnAnt,ImgBtnSgn;
    EditText txtNombre,txtNumero1,txtNumero2;
    TextView Resultado;
    ImageView img,ImagenTag;
    ViewFlipper simpleViewFlipper;
    float fontsize;
    int posicion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*  Inicializo las variables   */
        txtNombre=(EditText) findViewById(R.id.txtNombre);
        txtNumero1=(EditText) findViewById(R.id.txtNumero1);
        txtNumero2=(EditText) findViewById(R.id.txtNumero2);
        Resultado=(TextView)  findViewById(R.id.labelResultado);
        botonSaludo=(Button) findViewById(R.id.btnPulsado2);
        btnSumar=(Button) findViewById(R.id.btnSumar);
        btnRojo=(Button) findViewById(R.id.btnColorRojo);
        btnAzul=(Button) findViewById(R.id.btnColorAzul);
        btnDerecha=(Button) findViewById(R.id.btnDerecha);
        btnIzquierda=(Button) findViewById(R.id.btnIzquierda);
        btnSiguiente=(Button) findViewById(R.id.btnSiguiente);
        btnAtras=(Button) findViewById(R.id.btnAtras);
        btnSiguiente2=(Button) findViewById(R.id.btnSiguiente2);
        btnAtras2=(Button) findViewById(R.id.btnAtras2);
        btnMas=(Button) findViewById(R.id.btnMas);
        btnMenos=(Button) findViewById(R.id.btnMenos);
        ImgBtnAnt=(ImageButton) findViewById(R.id.ImgBtnAnt);
        ImgBtnSgn=(ImageButton) findViewById(R.id.ImgBtnSgn);
        img=(ImageView)  findViewById(R.id.imageView);
        ImagenTag=(ImageView)  findViewById(R.id.ImagenTag);
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper);
        fontsize=Resultado.getTextSize();
        posicion =1;
         /*  le mando el evento setOnClickListener     */
        btnAtras2.setOnClickListener(this);
        btnSiguiente2.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);
        btnMas.setOnClickListener(this);
        btnMenos.setOnClickListener(this);
        btnDerecha.setOnClickListener(this);
        btnIzquierda.setOnClickListener(this);
        btnAzul.setOnClickListener(this);
        btnRojo.setOnClickListener(this);
        ImgBtnAnt.setOnClickListener(this);
        ImgBtnSgn.setOnClickListener(this);


        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /* esto es para controlar si esta pulsado y cuando lo suelta
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img.setImageResource(R.drawable.imagen2);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    img.setImageResource(R.drawable.imagen3);
                }
                */
                posicion++;
                if(posicion==2)
                    img.setImageResource(R.drawable.imagen2);
                else if (posicion==3)
                    img.setImageResource(R.drawable.imagen3);
                else if (posicion==4){
                    posicion=1;
                    img.setImageResource(R.drawable.imagen1);
                }
                return true;
            }
        });
         /*  Esto es una forma de llamar al evento setOnClickListener   */
        botonSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(MainActivity.this,"hola "+txtNombre.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        /*  Suma   */
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aux1 = Integer.valueOf(txtNumero1.getText().toString());
                int aux2 = Integer.valueOf(txtNumero2.getText().toString());
                int suma = aux1 + aux2;
                Resultado.setText(String.valueOf(suma));
            }
        });

    }
    /*  Esto es una otra forma de llamar al evento
    setOnClickListener pero dentro del xml  */
    public void Saludo(View v){

        Toast.makeText(MainActivity.this,"holaaaa",Toast.LENGTH_SHORT).show();

    }

    @Override
     /* la ultima forma es controlandolo mediante un switch  */
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnColorRojo: Resultado.setTextColor(Color.RED);//Cambiar color
                break;
            case R.id.btnColorAzul: Resultado.setTextColor(Color.BLUE);
                break;
            case R.id.btnIzquierda: Resultado.setGravity(Gravity.LEFT);//alinear
                break;
            case R.id.btnDerecha: Resultado.setGravity(Gravity.RIGHT);
                break;
            case R.id.btnMenos: Resultado.setTextSize(--fontsize);//tamaño
                break;
            case R.id.btnMas: fontsize++;Resultado.setTextSize(fontsize+1);
                break;
            case R.id.btnAtras:
                posicion--;
                if(posicion==2)
                    img.setImageResource(R.drawable.imagen2);//imagen
                else if (posicion==1)
                    img.setImageResource(R.drawable.imagen1);
                else if (posicion==0){
                    posicion=3;
                    img.setImageResource(R.drawable.imagen3);
                }
                break;
            case R.id.btnSiguiente:
                posicion++;
                       if(posicion==2)
                             img.setImageResource(R.drawable.imagen2);
                       else if (posicion==3)
                           img.setImageResource(R.drawable.imagen3);
                       else if (posicion==4){
                           posicion=1;
                           img.setImageResource(R.drawable.imagen1);
                       }
                break;
            case R.id.btnSiguiente2: simpleViewFlipper.showNext();
                break;
            case R.id.btnAtras2: simpleViewFlipper.showPrevious();
                break;
            case R.id.ImgBtnAnt:
                if(ImagenTag.getTag().toString().equals("imagen1")) {
                    ImagenTag.setImageResource(R.drawable.imagen1);//Imagen con el tag
                    ImagenTag.setTag("imagen3");
                }
                else if(ImagenTag.getTag().toString().equals("imagen2")) {
                    ImagenTag.setImageResource(R.drawable.imagen2);
                    ImagenTag.setTag("imagen1");
                }
                else if(ImagenTag.getTag().toString().equals("imagen3")) {
                    ImagenTag.setImageResource(R.drawable.imagen3);
                    ImagenTag.setTag("imagen2");
                }
                break;
            case R.id.ImgBtnSgn:
                if(ImagenTag.getTag().toString().equals("imagen1")) {
                    ImagenTag.setImageResource(R.drawable.imagen1);
                    ImagenTag.setTag("imagen2");
                }
                else if(ImagenTag.getTag().toString().equals("imagen2")) {
                    ImagenTag.setImageResource(R.drawable.imagen2);
                    ImagenTag.setTag("imagen3");
                }
                else if(ImagenTag.getTag().toString().equals("imagen3")) {
                    ImagenTag.setImageResource(R.drawable.imagen3);
                    ImagenTag.setTag("imagen1");
                }
                break;
            default:
                break;
        }

    }
}