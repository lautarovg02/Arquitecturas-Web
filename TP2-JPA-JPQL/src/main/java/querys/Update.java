package querys;

import dao.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Update {

    protected static String PERSISTENCE_NAME = "TP2-EJER1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        updatePersonById(searchPersonById(4),"pepe");

        //CERRANDO LA CONEXION
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void updatePersonById(Persona p, String newName) {
        System.out.print(p);
        System.out.print("Actualizando Persona ... ");

        p.setNombre(newName);
        em.merge(p);
        System.out.println("... proceso finalizado");
        System.out.println("Resultado : " + p);
    }

    private static Persona searchPersonById(int i) {
        return em.find(Persona.class, 1);
    }
}