package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery( name = Torneo.BUSCAR_TODOS,
        query = "SELECT t FROM Torneo t "
)
@NamedQuery( name = Torneo.BUSCAR_TODOS_LOS_JUGADORES_DE_UN_TORNEO,
        query = "SELECT j FROM Torneo t JOIN t.equipos e JOIN e.jugadores j WHERE t.id = ?1"
)

public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    public static final String BUSCAR_TODOS = "Torneo.BUSCAR_TODOS", BUSCAR_TODOS_LOS_JUGADORES_DE_UN_TORNEO = "Torneo.BUSCAR_TODOS_LOS_JUGADORES_DE_UN_TORNEO";

    @Column(name = "clasificados")
    @ManyToMany( fetch = FetchType.LAZY)
    private List<Equipo> equipos;

    public Torneo(String nombre) {
        this.nombre = nombre;
    }
    public Torneo(){
        this.equipos = new ArrayList<>();
    }

    public void addEquipo(Equipo e){
        this.equipos.add(e);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
