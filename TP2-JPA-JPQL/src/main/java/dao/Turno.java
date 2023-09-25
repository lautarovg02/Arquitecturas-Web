package dao;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = Turno.OBTENER_PERSONAS_DE_UN_TURNO, query = "SELECT t.jugadores FROM Turno t WHERE t.id = ?1")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Timestamp fecha;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Persona> jugadores;

    public static final String OBTENER_PERSONAS_DE_UN_TURNO = "Turno.OBTENER_PERSONAS_DE_UN_TURNO";
    public Turno() {
    }

    public Turno(Timestamp fecha, List<Persona> jugadores) {
        this.fecha = fecha;
        this.jugadores = jugadores;
    }

    public Turno(int id, Timestamp fecha) {
        this.id = id;
        this.fecha = fecha;
        this.jugadores = new ArrayList<Persona>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public List<Persona> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Persona> jugadores) {
        this.jugadores = jugadores;
    }

    public Turno(Timestamp fecha) {
        this.fecha = fecha;
        this.jugadores = new ArrayList<Persona>();
    }

    @Override
    public String toString() {
        return "\nTurno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", jugadores=" + jugadores +
                '}';
    }
}
