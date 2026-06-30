package frgp.utn.edu.ar.tp6_grupo_8;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgregarContactosMainActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etTelefono, etEmail, etDireccion, etFechaNacimiento;
    private Spinner spTipoTelefono, spTipoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_contactos_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
//evita que se escriba la fecha manualmente
        etFechaNacimiento.setFocusable(false);
        etFechaNacimiento.setClickable(true);

        etFechaNacimiento.setOnClickListener(v -> {

            Calendar calendario = Calendar.getInstance();

            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
            int año = calendario.get(Calendar.YEAR);

            DatePickerDialog selectorFecha =
                    new DatePickerDialog(
                            this,
                            (view, year, month, dayOfMonth) -> {

                                String fecha = String.format(
                                        Locale.getDefault(),
                                        "%02d/%02d/%04d",
                                        dayOfMonth,
                                        month + 1,
                                        year
                                );

                                etFechaNacimiento.setText(fecha);

                            },
                            año,
                            mes,
                            dia
                    );

//no permite seleccionar una fechas posterior a hoy
            selectorFecha.getDatePicker().setMaxDate(System.currentTimeMillis());

            selectorFecha.show();
        });

        spTipoTelefono = findViewById(R.id.spTipoTelefono);
        spTipoEmail = findViewById(R.id.spTipoEmail);

        String[] tipos = {"Seleccione una opcion","Casa","Trabajo","movil"};

        ArrayAdapter<String> adapt=new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,tipos);

        adapt.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spTipoTelefono.setAdapter(adapt);
        spTipoEmail.setAdapter(adapt);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);

        Button btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(v -> {
            if (validarFormulario()) {
                Toast.makeText(this, "Formulario válido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MasDatosDeContacto.class);


                intent.putExtra("txt_nombre", etNombre.getText().toString());
                intent.putExtra("txt_apellido", etApellido.getText().toString());
                intent.putExtra("txt_email", etEmail.getText().toString());
                intent.putExtra("txt_telefono", etTelefono.getText().toString());
                intent.putExtra("txt_direccion", etDireccion.getText().toString());
                intent.putExtra("txt_fecha", etFechaNacimiento.getText().toString());

                intent.putExtra("txt_tipo_tel", spTipoTelefono.getSelectedItem().toString());
                intent.putExtra("txt_tipo_email", spTipoEmail.getSelectedItem().toString());

                startActivity(intent);
            }
        });
    }

    private boolean validarFormulario() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("El nombre es obligatorio");
            return false;
        }
        if (!nombre.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            etNombre.setError("El nombre no puede contener números ni símbolos");
            return false;
        }

        if (TextUtils.isEmpty(apellido)) {
            etApellido.setError("El apellido es obligatorio");
            return false;
        }
        if (!apellido.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            etApellido.setError("El apellido no puede contener números ni símbolos");
            return false;
        }

        if (TextUtils.isEmpty(telefono)) {
            etTelefono.setError("El teléfono es obligatorio");
            return false;
        }
        if (!telefono.matches("[0-9-]+")) {
            etTelefono.setError("El teléfono solo admite números y guiones");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("El email es obligatorio");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("El email no es válido");
            return false;
        }

        if (TextUtils.isEmpty(direccion)) {
            etDireccion.setError("La dirección es obligatoria");
            return false;
        }

        if (TextUtils.isEmpty(fechaNacimiento)) {
            etFechaNacimiento.setError("La fecha de nacimiento es obligatoria");
            return false;
        }
        if (!esFechaValida(fechaNacimiento)) {
            etFechaNacimiento.setError("La fecha de nacimiento no es válida");
           return false;
       }

        if (spTipoEmail.getSelectedItemPosition()==0){
            Toast.makeText(this,"Selecionar tipo de Email", Toast.LENGTH_SHORT).show();
        return false;
        }
        if (spTipoTelefono.getSelectedItemPosition()==0){
            Toast.makeText(this,"Selecionar tipo de telefono", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean esFechaValida(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        formato.setLenient(false);

        try {
            Date fechaIngresada = formato.parse(fecha);
            return !fechaIngresada.after(new Date());

        } catch (ParseException e) {
            return false;
        }
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
