package dao;

import javax.persistence.*;

@Entity
public class DirectorTecnico extends Persona {

    @Column
    private String estrategia;

    @ManyToOne
    private Equipo equipo;

    public static final String OBTENER_PRIMER_TECNICO_SIN_CLUB = "SELECT d FROM DirectorTecnico d WHERE d.equipo IS NULL ORDER BY d.id";
    public DirectorTecnico(int id,String nombre, int edad, String estrategia) {
        super(id,nombre, edad);
        this.estrategia = estrategia;
    }

    public DirectorTecnico() {

    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "DirectorTecnico{" +
                "id='" + super.getId() + '\'' +
                "nombre='" + super.getNombre() + '\'' +
                "edad='" + super.getEdad() + '\'' +
                "estrategia='" + estrategia + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
