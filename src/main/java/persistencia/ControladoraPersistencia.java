/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author LENOVO
 */
public class ControladoraPersistencia {
    HorarioJpaController horaJpa = new HorarioJpaController();
    
       OdontologoJpaController odontoJpa = new OdontologoJpaController();
       PacienteJpaController pacJpa = new PacienteJpaController();
       PersonaJpaController persJpa = new PersonaJpaController();
       ResponsableJpaController respJpa = new ResponsableJpaController();
       SecretarioJpaController secreJpa = new SecretarioJpaController();
       TurnoJpaController turnJpa = new TurnoJpaController();
       UsuarioJpaController usuJpa = new UsuarioJpaController();
   
       
       
       
    public void crearUsuario(Usuario usu) {
        usuJpa.create(usu);
        }

    public List<Usuario> getUsuarios() {
        return usuJpa.findUsuarioEntities();
         }

    public void borrarUsuario(int id) {
        try {
            usuJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    public Usuario traerUsuario(int id) {
        
        return usuJpa.findUsuario(id);
        }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
       
       
}
