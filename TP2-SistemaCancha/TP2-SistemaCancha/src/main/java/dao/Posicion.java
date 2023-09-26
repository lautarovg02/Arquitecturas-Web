package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = Posicion.BUSCAR_POSICION_POR_TIPO, query = "SELECT p FROM Posicion p WHERE p.id = ?1")
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String tipo;
    @OneToMany(mappedBy = "posicion",fetch = FetchType.LAZY)// Esta es la relación inversa desde Jugador
    private List<Jugador> jugadores;// Relation con el jugador que ocupa esta posición
    public static final String BUSCAR_POSICION_POR_TIPO = "Posicion.BUSCAR_POSICION_POR_TIPO";

    public Posicion(String tipo) {
        this.tipo = tipo;
    }

    public Posicion() {
        this.jugadores = new ArrayList<>();
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






