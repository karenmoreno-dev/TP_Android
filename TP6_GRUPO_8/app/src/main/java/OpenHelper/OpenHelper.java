package OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.database.DatabaseUtils;
import java.util.ArrayList;

import entidad.Contacto;

public class OpenHelper extends SQLiteOpenHelper {
    // nombre de la BD y version del archivo
    private static final String DATABASE_NAME = "Contactos.db";
    private static final int DATABASE_VERSION = 1;

    // nombre de la tabla
    public static final String TABLA_CONTACTOS = "contactos";

    // columnas
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_APELLIDO = "apellido";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_TIPO_TELEFONO = "tipoTelefono";
    public static final String COL_EMAIL = "email";
    public static final String COL_TIPO_EMAIL = "tipoEmail";
    public static final String COL_DIRECCION = "direccion";
    public static final String COL_FECHA_NACIMIENTO = "fechaNacimiento";
    public static final String COL_NIVEL_ESTUDIOS = "nivelEstudios";
    public static final String COL_RECIBE_INFORMACION = "recibeInformacion";
    public static final String COL_INTERESES = "intereses";

    // constructor estandar
    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLA_CONTACTOS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOMBRE + " TEXT, "
                + COL_APELLIDO + " TEXT, "
                + COL_TELEFONO + " TEXT, "
                + COL_TIPO_TELEFONO + " TEXT, "
                + COL_EMAIL + " TEXT, "
                + COL_TIPO_EMAIL + " TEXT, "
                + COL_DIRECCION + " TEXT, "
                + COL_FECHA_NACIMIENTO + " TEXT, "
                + COL_NIVEL_ESTUDIOS + " TEXT, "
                + COL_RECIBE_INFORMACION + " INTEGER, "
                + COL_INTERESES + " TEXT)";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTACTOS);
        onCreate(db);
    }

    public ArrayList<Contacto> obtenerContactos() {
        SQLiteDatabase bd = this.getReadableDatabase();
        ArrayList<Contacto> lista = new ArrayList<>();

        Cursor cursor = bd.rawQuery(
                "SELECT * FROM " + TABLA_CONTACTOS, null
        );
        if (cursor.moveToFirst()) {
            do {
                Contacto contacto = new Contacto();

                contacto.setId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
                contacto.setNombre(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)));
                contacto.setApellido(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_APELLIDO)));
                contacto.setEmail(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)));
                contacto.setTelefono(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TELEFONO)));
                contacto.setTipoTelefono(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TIPO_TELEFONO)));
                contacto.setTipoEmail(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TIPO_EMAIL)));
                contacto.setDireccion(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_DIRECCION)));
                contacto.setFechaNacimiento(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_NACIMIENTO)));
                contacto.setNivelEstudios(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NIVEL_ESTUDIOS)));
                contacto.setRecibeInformacion(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_RECIBE_INFORMACION)));
                String interesesTexto =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_INTERESES)
                        );


                ArrayList<String> intereses =
                        new ArrayList<>();


                if(interesesTexto != null && !interesesTexto.isEmpty()){

                    interesesTexto =
                            interesesTexto.replace("[","")
                                    .replace("]","");


                    String[] separados =
                            interesesTexto.split(",");


                    for(String i : separados){

                        intereses.add(i.trim());

                    }

                }


                contacto.setIntereses(intereses);

                lista.add(contacto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return lista;
    }

    public int cantidadContactos(){


        SQLiteDatabase bd =
                this.getReadableDatabase();



        int cantidad = (int) DatabaseUtils.queryNumEntries(
                bd,
                TABLA_CONTACTOS
        );



        bd.close();



        return cantidad;

    }

}
