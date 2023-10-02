package dao;

import javax.persistence.*;


@Entity
@NamedQuery(name = Jugador.BUSCAR_TODOS,
        query = "SELECT j FROM Jugador j"
)
@NamedQuery(name = Jugador.BUSCAR_TODOS_SIN_CLUB,
        query = "SELECT j FROM Jugador j WHERE j.equipo IS NULL"
)
@DiscriminatorValue("Jugador")
public class Jugador extends Persona {
    @ManyToOne
    private Posicion posicion;
    @ManyToOne
    private Equipo equipo;
    public static final String OBTENER_PRIMER_ARQUERO_SIN_CLUB = "SELECT j FROM Jugador j WHERE j.equipo IS NULL AND j.posicion = 4 ORDER BY j.id";
    public static final String BUSCAR_TODOS = "Jugador.BUSCAR_TODOS", BUSCAR_TODOS_SIN_CLUB = "Jugador.BUSCAR_TODOS_SIN_CLUB";

    public Jugador(String nombre, int edad, Posicion posicion) {
        super(nombre, edad);
        this.posicion = posicion;
    }

    public Jugador() {

    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + super.getNombre() + '\'' +
                ", edad='" + super.getEdad() + '\'' +
                ", posicion=" + this.getTipo() +
//                ", equipo=" + this.getNombreEquipo() +
                "} ";
    }

    private String getNombreEquipo() {
        if (this.equipo != null)
            return this.equipo.getNombre();
        else
            return "null";
    }

    private String getTipo() {
        if (this.posicion != null)
            return this.posicion.getTipo();
        else
            return "null";
    }


}
