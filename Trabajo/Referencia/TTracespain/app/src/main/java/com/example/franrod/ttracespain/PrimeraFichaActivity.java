package com.example.franrod.ttracespain;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PrimeraFichaActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnerTipoInspeccion;
    EditText date;
    DatePickerDialog datePickerDialog;
    Button bTingresar;
    Button bTingresar2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_ficha);
        bTingresar = (Button) findViewById(R.id.bTingresar);
        bTingresar2 = (Button) findViewById(R.id.bTingresar2);
        bTingresar.setOnClickListener(this);
        bTingresar2.setOnClickListener(this);



        spinnerTipoInspeccion=(Spinner)findViewById(R.id.sPtipoInspccion);
        date = (EditText) findViewById(R.id.eTfecha);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        //Probar
        if(month==13)
            month=1;
        if(month==0)
            month=1;


        //Toast.makeText(PrimeraFichaActivity.this,"fecha :"+day+"/"+month+"/"+year,Toast.LENGTH_SHORT).show();
        date.setText(day+"/"+month+"/"+year);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(PrimeraFichaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });




        List ListaTipoInpeccion=new ArrayList();
        ListaTipoInpeccion.add("1|ruben garcia lopez");
        ListaTipoInpeccion.add("2");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,ListaTipoInpeccion);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoInspeccion.setAdapter(arrayAdapter);

        ///select
        spinnerTipoInspeccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PrimeraFichaActivity.this,"posisition"+String.valueOf(spinnerTipoInspeccion.getSelectedItem()),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.bTingresar:
            Toast.makeText(PrimeraFichaActivity.this,"botom",Toast.LENGTH_SHORT).show();
            break;
        case R.id.bTingresar2:
            Toast.makeText(PrimeraFichaActivity.this,"botom2",Toast.LENGTH_SHORT).show();
            break;
        default:break;
    }
    }
}
