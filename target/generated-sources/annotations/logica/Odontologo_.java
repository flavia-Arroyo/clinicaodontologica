package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Horario;
import logica.Turno;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-08-27T11:21:09")
@StaticMetamodel(Odontologo.class)
public class Odontologo_ extends Persona_ {

    public static volatile SingularAttribute<Odontologo, Usuario> unUsuario;
    public static volatile ListAttribute<Odontologo, Turno> listaTurno;
    public static volatile SingularAttribute<Odontologo, String> especialidad;
    public static volatile SingularAttribute<Odontologo, Horario> unHorario;

}