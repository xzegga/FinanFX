package finanfx.models;

/**
 *
 * @author Ander
 */
public class Nota {

    private int ID_Transaccion;
    private int ID_Nota;
    private String Nota;

    public int getID_Transaccion() {
        return ID_Transaccion;
    }

    public void setID_Transaccion(int transaction) {
        this.ID_Transaccion = transaction;
    }

    public int getID_Nota() {
        return ID_Nota;
    }

    public void setID_Nota(int id) {
        this.ID_Nota = id;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String note) {
        this.Nota = note;
    }

    public Nota(int id, int transaction, String note) {
        this.ID_Nota = id;
        this.ID_Transaccion = transaction;        
        this.Nota = note;
    }
    
    public Nota(int transaction, String note) {
        this.ID_Transaccion = transaction;
        this.Nota = note;
    }

}
