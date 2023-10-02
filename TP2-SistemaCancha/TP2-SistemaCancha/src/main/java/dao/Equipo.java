package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//SELECT p FROM Equipo p
@NamedQuery(name = Equipo.BUSCAR_TODOS,
            query = "SELECT p FROM Equipo p"
)
@NamedQuery(
        name = Equipo.BUSCAR_JUGADORES_DE_UN_EQUIPO,
        query = "SELECT j.nombre FROM Equipo e JOIN Jugador j ON e.id = j.equipo.id WHERE e.nombre = ?1"
)

public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = true)
    private String publicidad;
    @ManyToOne
    private DirectorTecnico directorTecnico;
    public static final String BUSCAR_JUGADORES_DE_UN_EQUIPO = "Equipo.BUSCAR_JUGADORES_DE_UN_EQUIPO";
    public static final String BUSCAR_TODOS = "Equipo.BUSCAR_TODOS";

    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    private List<Jugador> jugadores;
    @OneToMany(mappedBy = "equipo",fetch = FetchType.LAZY)
    private List<Jugador> suplentes;

    public Equipo(String nombre, String publicidad, DirectorTecnico directorTecnico) {
        this.nombre = nombre;
        this.publicidad = publicidad;
        this.directorTecnico = directorTecnico;
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
                ", directorTecnico=" + directorTecnico.getNombre() +
//                ", jugadores=" + listaToString(jugadores) +
//                ", suplentes=" + listaToString(suplentes) +
                '}';
    }

    private String listaToString(List<Jugador> lista) {
        if (lista == null) {
            return "null";
        }

        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            result.append(lista.get(i).getNombre());
            if (i < lista.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");

        return result.toString();
    }
}
