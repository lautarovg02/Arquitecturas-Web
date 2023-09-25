package querys;

import dao.Persona;
import dao.Turno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Delete {
    protected static String PERSISTENCE_NAME = "TP2-EJER1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        //CONEXION A LA BD
        em.getTransaction().begin();

        Turno t = searchTurnById(114);
        deleteTurn(t);

        //CERRANDO LA CONEXION
        em.close();
        emf.close();
    }

    private static void deleteTurn(Turno t) {
        System.out.print("Eliminando  Turno ... ");
        if (t != null){
            System.out.println(t);
            System.out.print(".");
            em.remove(t);
            em.getTransaction().commit();
            System.out.print("Proceso terminado con exito ... ");
        }else
            System.out.println("......... La persona " + t + " que quiere borrar de la BD no existe");
    }

    private static Turno searchTurnById(int i) {
        return em.find(Turno.class, i);
    }
}
