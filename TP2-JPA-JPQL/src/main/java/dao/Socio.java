package dao;

import javax.persistence.*;

@Entity
public class Socio {
    @Id
    private int id;

    /*
     * Relacion Socio-Persona
     * Con el maps id indiciamos que es PK y FK */
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Persona persona;
    @Column
    private String tipo;

    public Socio() {
    }

    public Socio(int id, Persona persona, String tipo) {
        this.id = id;
        this.persona = persona;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\nSocio{" +
                "id=" + id +
                ", persona=" + persona +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
