package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = Posicion.BUSCAR_TODOS,
        query = "SELECT p FROM Posicion p"
)
@NamedQuery(name = Posicion.BUSCAR_POSICION_POR_TIPO,
        query = "SELECT p FROM Posicion p WHERE p.id = ?1"
)
public class Posicion {
    @Id
    private int id;
    @Column
    private String tipo;
    @OneToMany(fetch = FetchType.LAZY)// Esta es la relación inversa desde Jugador
    private List<Jugador> jugadores;// Relation con el jugador que ocupa esta posición
    public static final String BUSCAR_TODOS = "Posicion.BUSCAR_TODOS";
    public static final String BUSCAR_POSICION_POR_TIPO = "Posicion.BUSCAR_POSICION_POR_TIPO";
    public static final String TIPO_ARQUERO = "arquero";
    public Posicion(String tipo) {
        this.tipo = tipo;
    }

    public Posicion() {
        this.jugadores = new ArrayList<>();
    }

    public Posicion(int i, String tipo) {
        this.id = i;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Jugador> getJugador() {
        return jugadores;
    }

    public void setJugador(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", jugadores=" + jugadores +
                '}';
    }
}






