package querys;

import dao.Persona;
import dao.Socio;
import dao.Turno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Ejercicio2 {
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

        //EJERCICIO 2-B
        String jpql2b = Turno.OBTENER_PERSONAS_DE_UN_TURNO;
        int idTurn2 = 108;
        Query query2b = em.createNamedQuery(jpql2b);
        query2b.setParameter(1, idTurn2);
        List<Persona> players2 = query2b.getResultList();
        for (Persona p: players2) {
            Socio s = em.find(Socio.class, p.getId());
            if(!s.getTipo().equals(Socio.SOCIO_SUSPENDIDO)){
                System.out.println(p + "ES SOCIO -->:" + s.getTipo());
            }
            System.out.println(p);
        }

        //CERRANDO LA CONEXION
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
