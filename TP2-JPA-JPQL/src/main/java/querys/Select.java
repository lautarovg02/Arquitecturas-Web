package querys;

import dao.Persona;
import dao.Turno;

import javax.persistence.*;
import java.util.List;

public class Select {
    protected static String PERSISTENCE_NAME = "TP2-EJER1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        //CONEXION A LA BD
        em.getTransaction().begin();



        //CERRANDO LA CONEXION
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
