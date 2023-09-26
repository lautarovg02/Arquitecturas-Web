package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = true)
    private String publicidad;
    @OneToOne
    private DirectorTecnico directorTecnico;
    @ManyToOne
    private Torneo torneo;
    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    private List<Jugador> jugadores;
    @OneToMany(mappedBy = "equipo",fetch = FetchType.LAZY)
    private List<Jugador> suplentes;

    public Equipo(String nombre, String publicidad, DirectorTecnico directorTecnico, Torneo torneo,List<Jugador> t , List<Jugador> suplentes) {
        this.nombre = nombre;
        this.publicidad = publicidad;
        this.directorTecnico = directorTecnico;
        this.torneo = torneo;
        this.jugadores = t;
        this.suplentes = suplentes;

    }
    public Equipo(){
        this.jugadores = new ArrayList<>();
        this.suplentes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPublicidad() {
        return publicidad;
    }

    public void setPublicidad(String publicidad) {
        this.publicidad = publicidad;
    }

    public Persona getDirectorTecnico() {
        return directorTecnico;
    }

    public void setDirectorTecnico(DirectorTecnico directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void setSuplentes(List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", publicidad='" + publicidad + '\'' +
                ", torneo='" + torneo + '\'' +
                ", directorTecnico=" + directorTecnico +
                ", jugadores=" + jugadores +
                ", suplentes=" + suplentes +
                '}';
    }
}
