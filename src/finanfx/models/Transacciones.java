package finanfx.models;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Transacciones {
    private int ID_Transaccion;
    private int ID_Usuario;
    private String Tipo_Transaccion;
    private double Cantidad;
    private Date Fecha;
    private String Categoria;
    private String Descripcion;
    private String Forma_Pago;


    public int getID_Transaccion() {
        return ID_Transaccion;
    }

    public void setID_Transaccion(int ID_Transaccion) {
        this.ID_Transaccion = ID_Transaccion;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getTipo_Transaccion() {
        return Tipo_Transaccion;
    }

    public void setTipo_Transaccion(String Tipo_Transaccion) {
        this.Tipo_Transaccion = Tipo_Transaccion;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getForma_Pago() {
        return Forma_Pago;
    }

    public void setForma_Pago(String Forma_Pago) {
        this.Forma_Pago = Forma_Pago;
    }

    public Transacciones(int ID_Transaccion, int ID_Usuario, String Tipo_Transaccion, double Cantidad, Date Fecha, String Categoria, String Descripcion, String Forma_Pago) {
        this.ID_Transaccion = ID_Transaccion;
        this.ID_Usuario = ID_Usuario;
        this.Tipo_Transaccion = Tipo_Transaccion;
        this.Cantidad = Cantidad;
        this.Fecha = Fecha;
        this.Categoria = Categoria;
        this.Descripcion = Descripcion;
        this.Forma_Pago = Forma_Pago;
    }

    public Transacciones(int ID_Usuario, String Tipo_Transaccion, double Cantidad, Date Fecha, String Categoria, String Descripcion, String Forma_Pago) {
        this.ID_Usuario = ID_Usuario;
        this.Tipo_Transaccion = Tipo_Transaccion;
        this.Cantidad = Cantidad;
        this.Fecha = Fecha;
        this.Categoria = Categoria;
        this.Descripcion = Descripcion;
        this.Forma_Pago = Forma_Pago;
    }
    
    
}
