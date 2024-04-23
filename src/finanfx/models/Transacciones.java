package finanfx.models;

import java.sql.Date;

public class Transacciones {
    private int ID_Usuario;
    private String Tipo_Transaccion;
    private double Cantidad;
    private Date Fecha;
    private String Categoria;
    private String Descripcion;
    private String Forma_Pago;

    public Transacciones(int ID_Usuario, String Tipo_Transaccion, double Cantidad, Date Fecha, String Categoria, String Descripcion, String Forma_Pago) {
        this.ID_Usuario = ID_Usuario;
        this.Tipo_Transaccion = Tipo_Transaccion;
        this.Cantidad = Cantidad;
        this.Fecha = Fecha;
        this.Categoria = Categoria;
        this.Descripcion = Descripcion;
        this.Forma_Pago = Forma_Pago;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public String getTipo_Transaccion() {
        return Tipo_Transaccion;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public Date getFecha() {
        return Fecha;
    }

    public String getCategoria() {
        return Categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getForma_Pago() {
        return Forma_Pago;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public void setTipo_Transaccion(String Tipo_Transaccion) {
        this.Tipo_Transaccion = Tipo_Transaccion;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setForma_Pago(String Forma_Pago) {
        this.Forma_Pago = Forma_Pago;
    }
    
    
}
