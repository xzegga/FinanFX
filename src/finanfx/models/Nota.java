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
    private String Nota;
    
    public Nota (int ID_Transaccion, String Nota)
    {
        this.ID_Transaccion=ID_Transaccion;
        this.Nota = Nota;
    }
    
    public int getID_Transaccion()
    {
        return ID_Transaccion;
    }
    
    public String getNota()
    {
        return Nota;
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
