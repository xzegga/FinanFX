
package finanfx.models;

/**
 * @author Raúl Escamilla
 */
import java.time.LocalDateTime;

public class Presupuesto {
    private int ID_Presupuesto;
    private int ID_Usuario;   
    private String Periodo;
    private String Inicio;
    private String Categoria;
    private Double Monto_Presupuestado;
    private LocalDateTime Fecha_Creacion;

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public LocalDateTime getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(LocalDateTime Fecha_Creacion) {
        this.Fecha_Creacion = Fecha_Creacion;
    }

    public int getID_Presupuesto() {
        return ID_Presupuesto;
    }

    public void setID_Presupuesto(int ID_Presupuesto) {
        this.ID_Presupuesto = ID_Presupuesto;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    public String getInicio() {
        return Inicio;
    }

    public void setInicio(String Inicio) {
        this.Inicio = Inicio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public Double getMonto_Presupuestado() {
        return Monto_Presupuestado;
    }

    public void setMonto_Presupuestado(Double Monto_Presupuestado) {
        this.Monto_Presupuestado = Monto_Presupuestado;
    }


    // Constructor vacío
    public Presupuesto() {
        this.Fecha_Creacion = LocalDateTime.now(); // Establecer la fecha de creación como la fecha y hora actual
    }

    // Constructor con parámetros
    public Presupuesto(int idPresupuesto, int idUsuario, String periodo, String inicio, String categoria, Double montoPresupuestado) {
        this.ID_Presupuesto = idPresupuesto;
        this.ID_Usuario = idUsuario;
        this.Periodo = periodo;
        this.Inicio = inicio;
        this.Categoria = categoria;
        this.Monto_Presupuestado = montoPresupuestado;
    }

    // Constructor con parámetros excepto ID_Presupuesto y Fecha_Creacion
    public Presupuesto(int idUsuario, String periodo, String inicio, String categoria, Double montoPresupuestado) {
        this.ID_Usuario = idUsuario;
        this.Periodo = periodo;
        this.Inicio = inicio;
        this.Categoria = categoria;
        this.Monto_Presupuestado = montoPresupuestado;
    }

    
}
