
package frgp.utn.edu.ar.tp6_grupo_8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MasDatosDeContacto extends AppCompatActivity {
    private String nombre, apellido,email, telefono, direccion, fechaNacimiento, tipoTelefono, tipoEmail;

    // Variables
    private RadioGroup rgEstudios;
    private CheckBox cbDeporte;
    private CheckBox cbMusica;
    private CheckBox cbArte;
    private CheckBox cbTecnologia;
    private Button btnGuardar;

    private Switch switchSiNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // vinculacion con XML
        setContentView(R.layout.activity_mas_datos_de_contacto);

        //
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nombre = extras.getString("txt_nombre");
            apellido = extras.getString("txt_apellido");
            email = extras.getString("txt_email");
            telefono = extras.getString("txt_telefono");
            direccion = extras.getString("txt_direccion");
            fechaNacimiento = extras.getString("txt_fecha");
            tipoTelefono = extras.getString("txt_tipo_tel");
            tipoEmail = extras.getString("txt_tipo_email");
        }


        rgEstudios = findViewById(R.id.rgEstudios);
        cbDeporte = findViewById(R.id.cbDeporte);
        cbMusica = findViewById(R.id.cbMusica);
        cbArte = findViewById(R.id.cbArte);
        cbTecnologia = findViewById(R.id.cbTecnologia);


        // ejemplo para probar
        //Toast.makeText(this, "Bienvenido: " + nombre + " " + apellido, Toast.LENGTH_LONG).show();
                Toast.makeText(
                        this,
                        "Intereses: " + obtenerIntereses(),
                        Toast.LENGTH_LONG
                ).show();

        switchSiNo =findViewById(R.id.switchSiNo);
        switchSiNo.setChecked(true);
        switchSiNo.setText("SI");
        switchSiNo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                switchSiNo.setText("SI");
            }
            else
            {
                switchSiNo.setText("NO");
            }
        });
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            if (Validaciones()) {
               // luego agregar un metodo guardarContactoBD();
            }
        });

    }

    private String obtenerIntereses(){

        String intereses = "";


        if(cbDeporte.isChecked()){
            intereses += "Deporte ";
        }

        if(cbMusica.isChecked()){
            intereses += "Musica ";
        }

        if(cbArte.isChecked()){
            intereses += "Arte ";
        }

        if(cbTecnologia.isChecked()){
            intereses += "Tecnologia ";
        }

        return intereses;
    }
    private String obtenerNivelEstudio() {

        int seleccionado = rgEstudios.getCheckedRadioButtonId();

        if (seleccionado == R.id.rbPrimarioIncompleto) {
            return "Primario incompleto";
        }

        if (seleccionado == R.id.rbPrimarioCompleto) {
            return "Primario completo";
        }

        if (seleccionado == R.id.rbSecundarioIncompleto) {
            return "Secundario incompleto";
        }
        if (seleccionado == R.id.rbSecundarioCompleto) {
            return "Secundario completo";
        }
        if (seleccionado == R.id.rbOtros) {
            return "Otros";
        }

        return "";
    }
    private boolean Validaciones(){

            int seleccionadoRG = rgEstudios.getCheckedRadioButtonId();

            if(seleccionadoRG == -1){
                Toast.makeText(this,"Debe seleccionar un nivel de estudio",Toast.LENGTH_SHORT).show();
                return false;
            }

            if(cbDeporte.isChecked()){
                return true;
            }else if(cbMusica.isChecked()){
                return true;
            }else if (cbArte.isChecked()){
                return true;
            }else if(cbTecnologia.isChecked()){
                return true;
            }else{
                Toast.makeText(this,"Debe selecionar al menos un interes",Toast.LENGTH_SHORT).show();
                return false;
            }

    }
}