package frgp.utn.edu.ar.tp6_grupo_8;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import OpenHelper.OpenHelper;
import entidad.Contacto;

public class ListadoContactos extends AppCompatActivity {

    private ListView lvContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_contactos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvContactos = findViewById(R.id.lvContactos);

        // datos de ejemplo, reemplazar dsp por la consulta a sqlite
        OpenHelper helper = new OpenHelper(this);
        ArrayList<Contacto> lista = helper.obtenerContactos();

        ArrayList<String> contactos = new ArrayList<>();

        for (Contacto c : lista) {
            contactos.add(
                    c.getNombre() + " "+ c.getApellido() + " - "+ c.getEmail()
            );
        }


        // verifica si existen contactos para mostrar
        if (contactos.isEmpty()) {

            Toast.makeText(
                    this,
                    "No existen contactos cargados",
                    Toast.LENGTH_LONG
            ).show();

        } else {

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, contactos);

            lvContactos.setAdapter(adapter);
        }

//muestra cantidad de contactos
        Toast.makeText(
                this,
                "Cantidad de contactos: " + contactos.size(),
                Toast.LENGTH_SHORT
        ).show();



        lvContactos.setOnItemClickListener((parent, view, position, id) -> {


            Contacto contactoSeleccionado = lista.get(position);


            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Detalle del contacto")
                    .setMessage(

                            "Nombre: " + contactoSeleccionado.getNombre()
                                    + "\nApellido: " + contactoSeleccionado.getApellido()
                                    + "\nEmail: " + contactoSeleccionado.getEmail()+ " (" + contactoSeleccionado.getTipoEmail() + ")"
                                    + "\nTeléfono: " + contactoSeleccionado.getTelefono()+" (" + contactoSeleccionado.getTipoTelefono() + ")"
                                    + "\nDirección: " + contactoSeleccionado.getDireccion()
                                    + "\nFecha de Nacimiento: " + contactoSeleccionado.getFechaNacimiento()
                                    + "\nNivel de Estudios: " + contactoSeleccionado.getNivelEstudios()
                                    + "\nIntereses: " + contactoSeleccionado.getIntereses()
                                    + "\n¿Recibe Información?: " + (contactoSeleccionado.getRecibeInformacion() == 1 ? "SI" : "NO")

                    )
                    .setPositiveButton("Aceptar", null)
                    .show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //redirige
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();

        if (id == R.id.opcion1) {
            Intent intent = new Intent(this, AgregarContactosMainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.opcion2) {
            Intent intent = new Intent(this, ListadoContactos.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
