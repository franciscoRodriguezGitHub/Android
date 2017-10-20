package com.example.fjrodriguez.ejercicio4;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fjrodriguez.ejercicio4.Clases.Alumno;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    //Declaro las Variables
    IconicAdapter adapter;
    EditText nombre, edad;
    Button btnAnadir, btnBorrar;
    Boolean encontrado = false;
    int textlength = 0;
    ArrayList<Alumno> ListaTotal = new ArrayList<Alumno>();
    ArrayList<Alumno> array_sort = new ArrayList<Alumno>();
    ArrayList<Alumno> listaAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creo el arrayList y creo alumnos alumno por defecto
        listaAlumno = new ArrayList<Alumno>();
        listaAlumno.add(new Alumno("Pepe", 10, R.drawable.delete));
        listaAlumno.add(new Alumno("Paco", 10, R.drawable.delete));
        listaAlumno.add(new Alumno("Fran", 22, R.drawable.alarm));
        listaAlumno.add(new Alumno("juan", 24, R.drawable.delete));
        listaAlumno.add(new Alumno("Carmen", 24, R.drawable.delete));

        // Asigno las variables con la vista
        nombre = (EditText) findViewById(R.id.txtNombre);
        edad = (EditText) findViewById(R.id.txtEdad);
        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        ListaTotal=listaAlumno;
        //creo el IconicAdapter y lo muestrio
        adapter = new IconicAdapter<String>(this, R.layout.row, R.id.nombreList, ListaTotal);
        setListAdapter(adapter);

        // Añade un alumno al arrayList
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validacion
                if (nombre.getText().toString().isEmpty() || edad.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Debe Introducir los datos adecuados",Toast.LENGTH_SHORT).show();
                } else {
                    int edadInt = Integer.parseInt(edad.getText().toString());
                    listaAlumno.add(new Alumno(nombre.getText().toString(), edadInt, R.drawable.calendar));
                    adapter.notifyDataSetChanged();
                    nombre.setText("");
                }
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este Alumno ?");
                dialogo1.setCancelable(false);
                // Cuando Pulse El button Confirma
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        //bucle para averiguar si ese Alumno existe
                        for (int i = 0; i < listaAlumno.size(); i++) {
                            if (nombre.getText().toString().equalsIgnoreCase(listaAlumno.get(i).getNombre())) {
                                //y en caso de encontralo borrarlo
                                listaAlumno.remove(i);
                                adapter.notifyDataSetChanged();
                                encontrado = true;
                            }
                        }
                        //si no,muestro un mensaje
                        if (encontrado == Boolean.FALSE) {
                            Toast.makeText(getApplicationContext(), "No Existe ese Alumno", Toast.LENGTH_SHORT).show();
                        }
                        encontrado = false;
                    }
                });
                // Cuando Pulse El button Cancelar
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                //Muestro el Dialogo
                dialogo1.show();
            }
        });
        // Capturo el evento de  cuando se esta modificando el EdiText
        nombre.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = nombre.getText().length();
                array_sort.clear();
                int edadInt;
                for (int i = 0; i < listaAlumno.size(); i++) {
                    if (textlength <= listaAlumno.get(i).getNombre().length()) {
                        if (nombre.getText().toString().equalsIgnoreCase((String) listaAlumno.get(i).getNombre().subSequence(0, textlength))) {
                            //Toast.makeText(getApplicationContext(), listaAlumno.get(i).getNombre().toString(), Toast.LENGTH_SHORT).show();
                            array_sort.add(new Alumno(listaAlumno.get(i).getNombre().toString(),
                                    listaAlumno.get(i).getEdad(), listaAlumno.get(i).getImagen()));
                        }
                    }
                }
                ListaTotal=array_sort;
                adapter= (IconicAdapter) new IconicAdapter<String>(MainActivity.this, R.layout.row,R.id.nombreList, ListaTotal);
                setListAdapter(adapter);
            }
        });

    }
    // Esto es para personalizar el ListView
    class IconicAdapter<T> extends ArrayAdapter<T> {
        IconicAdapter(Context c, int resourceId, int textId, ArrayList<Alumno> objects) {
            super(c, resourceId, textId, (List<T>) objects);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            //consigo las filas de la lista
            View row = super.getView(position, convertView, parent);

            //Declaro la variable y lo enlanzo con sus vista correspondiente
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            TextView nombreList = (TextView) row.findViewById(R.id.nombreList);
            TextView edadList = (TextView) row.findViewById(R.id.edadList);

            // Le asigno valores
            icon.setImageResource(Integer.parseInt(String.valueOf(listaAlumno.get(position).getImagen())));
            nombreList.setText("Nombre : "+ListaTotal.get(position).getNombre().toString());
            edadList.setText(String.valueOf("   Edad: "+ListaTotal.get(position).getEdad()));

            // Una validacion
            if (ListaTotal.get(position).getEdad() > 18) {
                edadList.setTextColor(Color.BLUE);
            }
            return (row);
        }
    }
}