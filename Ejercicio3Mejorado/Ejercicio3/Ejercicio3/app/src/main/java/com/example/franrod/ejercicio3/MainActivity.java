package com.example.franrod.ejercicio3;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {
    //Declaro variables
    Button btnAnadir, btnBorrar;
    EditText txtAlumno;
    ArrayAdapter adapter;
    Boolean encontrado = false;
    int textlength = 0;
    private ArrayList<String> array_sort = new ArrayList<String>();
    //Le introduzco datos de pruebas
    ArrayList<String> listaClase = new ArrayList<String>(
            Arrays.asList("Fran", "Alvaro", "Ivan"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Le asigno esta variables con sus vistas correspondiente
        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        txtAlumno = (EditText) findViewById(R.id.txtAlumno);

        // Le asigno un adaptador a la lista y la muestro
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaClase);
        setListAdapter(adapter);

        //Captturo el evento de cuando de este modificando este EdiText
        txtAlumno.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Consigo la longitud de txtAlumno
                textlength = txtAlumno.getText().length();
                //vacio el array Secundario
                array_sort.clear();
                //compruebo en mi arrayList si ese Alumno  existe
                for (int i = 0; i < listaClase.size(); i++) {
                    if (textlength <= listaClase.get(i).length()) {
                        if (txtAlumno.getText().toString().equalsIgnoreCase((String) listaClase.get(i).subSequence(0, textlength))) {
                            //si lo encuentra lo añado
                            array_sort.add(listaClase.get(i));
                        }
                    }
                }
                //introduzo ese array al adaptador
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, array_sort);
                setListAdapter(adapter);
            }
        });
        //Añade un alumno
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaClase.add(txtAlumno.getText().toString());
                adapter.notifyDataSetChanged();
                txtAlumno.setText("");
            }
        });
        //borra un alumno
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creo el objeto AlertDialog
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este Alumno ?");
                dialogo1.setCancelable(false);
                //cuando pulson el button Confirmar
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    //Compruebo si ese Alumno existe
                    public void onClick(DialogInterface dialogo1, int id) {
                        for (int i = 0; i < listaClase.size(); i++) {
                            if (txtAlumno.getText().toString().equalsIgnoreCase(listaClase.get(i).toString())) {
                                listaClase.remove(i);
                                array_sort.remove(i);
                                adapter.notifyDataSetChanged();
                                encontrado = true;
                            }
                            if (encontrado == Boolean.FALSE) {
                                Toast.makeText(getApplicationContext(), "No encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                        encontrado = false;
                    }
                });
                //cuando pulson el button Cancelar
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                //Muestro el Dialogo
                dialogo1.show();
            }
        });
    }
    //evento al pulsar unos de los item del ListViews
    public void onListItemClick(ListView parent, View v, int position,
                                long id) {
        Toast.makeText(getApplicationContext(), listaClase.get(position), Toast.LENGTH_SHORT).show();
    }

}
