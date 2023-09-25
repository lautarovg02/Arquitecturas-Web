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

        //EJERCICIO 2-A
        String jpql2A = Turno.OBTENER_PERSONAS_DE_UN_TURNO;
        int idTurn = 107;
        Query query2a = em.createNamedQuery(jpql2A);
        query2a.setParameter(1, idTurn);
        List<Persona> players = query2a.getResultList();
        players.forEach(System.out::println);



        //CERRANDO LA CONEXION
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
