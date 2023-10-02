package query;

import com.sun.xml.bind.v2.model.core.ID;
import dao.DirectorTecnico;
import dao.Equipo;
import dao.Jugador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Delete {
    private static String pathFilesCsv = "./src/main/java/csv/";
    private static final String PERSISTENCE_NAME = "TP2-EJER3";
    private static final int MAX_TITULARS_PLAYERS = 7, MAX_SUBSTITUTE_PLAYERS = 2;
    private static final String ARCHER_REQUIREMENT = "Arquero";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        //DELETE TEAM
        deleteTeamById(32);

        //DELETE JUGADOR
        deletePlayerById(32);

        //DELETE
        deletePlayerById(34);

        // CLOSE
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    /*** Método para eliminar un equipo por ID*/
    private static void deleteTeamById(int equipoId) {
        Equipo equipo = em.find(Equipo.class, equipoId);
        if (equipo != null) {
            em.remove(equipo);
            System.out.println("Equipo eliminado correctamente.");
        } else {
            System.out.println("Equipo no encontrado con el ID: " + equipoId);
        }
    }

    /*** Método para eliminar un jugador por ID*/
    private static void deletePlayerById(int jugadorId) {
        Jugador j = em.find(Jugador.class, jugadorId);
        if (j != null) {
            em.remove(j);
            System.out.println("Jugador eliminado correctamente.");
        } else {
            System.out.println("Jugador no encontrado con el ID: " + jugadorId);
        }
    }

    /*** Método para eliminar un dt por ID*/
    private static void deleteDtById(int dtId) {
        DirectorTecnico dt = em.find(DirectorTecnico.class, dtId);
        if (dt != null) {
            em.remove(dt);
            System.out.println("dt eliminado correctamente.");
        } else {
            System.out.println("dt no encontrado con el ID: " + dtId);
        }
    }
}
