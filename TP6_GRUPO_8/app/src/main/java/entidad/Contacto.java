package entidad;

import java.util.ArrayList;

public class Contacto {

    // datos del contacto
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String tipoTelefono;          // Guardara lo elegido en el Spinner ("Casa", "Trabajo", "Móvil")
    private String email;
    private String tipoEmail;             // Guardara lo elegido en el segundo Spinner Email
    private String direccion;
    private String fechaNacimiento;

    // segundo formulario (mas datos)
    private String nivelEstudios;         // Guardara el RadioButton seleccionado
    private int recibeInformacion;        // Guardara el estado del Switch (Sí, No)
    private ArrayList<String> intereses;  // Guardara los CheckBoxes seleccionados ("Deporte", "Musica", ..)

    public Contacto() {
        this.intereses = new ArrayList<>();
    }

    public Contacto(int id, String nombre, String apellido, String telefono, String tipoTelefono, String email, String tipoEmail, String direccion, String fechaNacimiento, String nivelEstudios, int recibeInformacion, ArrayList<String> intereses) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoTelefono = tipoTelefono;
        this.email = email;
        this.tipoEmail = tipoEmail;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelEstudios = nivelEstudios;
        this.recibeInformacion = recibeInformacion;
        this.intereses = intereses;
    }


    public int getId() {
        return id;
    }

        public void setId(int id) {
        this.id = id;
}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public int getRecibeInformacion() {
        return recibeInformacion;
    }

    public void setRecibeInformacion(int recibeInformacion) {
        this.recibeInformacion = recibeInformacion;
    }

    public ArrayList<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(ArrayList<String> intereses) {
        this.intereses = intereses;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}

