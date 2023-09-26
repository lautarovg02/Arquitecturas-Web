package dao;

import javax.persistence.*;
import java.util.List;

@Entity
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;

    @Column(name = "clasificados")
    @OneToMany(mappedBy = "torneo", fetch = FetchType.LAZY)
    private List<Equipo> equipos;


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
                ", equipos=" + equipos +
                '}';
    }
}
