/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finanfx.models;

/**
 *
 * @author Ander
 */


public class Nota {
    private int ID_Transaccion;
    private int ID_Nota;
    private String Nota;
    
    
    
    public Nota (int ID_Transaccion, String Nota, int ID_Nota)
    {
        this.ID_Transaccion=ID_Transaccion;
        this.Nota = Nota;
        this.ID_Nota=ID_Nota;
    }
    
    public int getID_Nota()
    {
        return ID_Nota;
    }
    
    public int getID_Transaccion()
    {
        return ID_Transaccion;
    }
    
    public String getNota()
    {
        return Nota;
    }
    
    public void setID_Nota(int ID_Nota)
    {
        this.ID_Nota = ID_Nota;
    }
    
    public void setID_Transaccion(int ID_Transaccion)
    {
        this.ID_Transaccion = ID_Transaccion;
    }
    
    public void setNota(String Nota)
    {
        this.Nota=Nota;
    }
}
