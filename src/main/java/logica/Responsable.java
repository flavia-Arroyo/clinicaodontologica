/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author LENOVO
 */
@Entity
public class Responsable extends Persona{
    //private int id_responsable;
    private String tipo_res;

    public Responsable() {
    }

    public Responsable(String tipo_res, int id, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(id, nombre, apellido, telefono, direccion, fecha_nac);
        this.tipo_res = tipo_res;
    }

   

    

    public String getTipo_res() {
        return tipo_res;
    }

    public void setTipo_res(String tipo_res) {
        this.tipo_res = tipo_res;
    }
    
    
    
    
}
