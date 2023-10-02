package query;

import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Select {
    private static String pathFilesCsv = "./src/main/java/csv/";
    private static final String  PERSISTENCE_NAME = "TP2-EJER3";
    private static final int MAX_TITULARS_PLAYERS = 7, MAX_SUBSTITUTE_PLAYERS = 2;
    private static final String ARCHER_REQUIREMENT = "Arquero";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        //SHOW PLAYERS
        showPlayers();

        //SHOW TECNICOS
        showDts();

        //SHOW POSICIONES
        showPositions();

        //SHOW TOURNAMENT
        showTournaments();

        //SHOW TEAMS
        showTeams();

        //SHOW PLAYERS OF TEAM
        String name = "Boca juniors";
        showPlayersOfTeamByName(name);

        // CLOSE
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void showPlayers() {
        String queryString = Jugador.BUSCAR_TODOS ;
        Query query = em.createNamedQuery(queryString);
        List<Jugador> pos = query.getResultList();
        pos.forEach(System.out::println);
    }

    private static void showTournaments() {
        String queryString = Torneo.BUSCAR_TODOS ;
        Query query = em.createNamedQuery(queryString);
        List<Torneo> pos = query.getResultList();
        pos.forEach(System.out::println);
    }

    private static void showPositions() {
        String queryString = Posicion.BUSCAR_TODOS ;
        Query query = em.createNamedQuery(queryString);
        List<Posicion> pos = query.getResultList();
        pos.forEach(System.out::println);
    }

    private static void showPlayersOfTeamByName(String name) {
        String queryString = Equipo.BUSCAR_JUGADORES_DE_UN_EQUIPO;
        Query query = em.createNamedQuery(queryString);
        query.setParameter(1, name);

        List results = query.getResultList();
        results.forEach(System.out::println);
    }

    private static void showTeams(){
        String queryString = Equipo.BUSCAR_TODOS;
        Query query = em.createNamedQuery(queryString);
        List<Equipo> equipos = query.getResultList();
        equipos.forEach(System.out::println);
    }

    private static void showDts(){
        String queryString = DirectorTecnico.BUSCAR_TODOS;
        Query query = em.createNamedQuery(queryString);
        List<DirectorTecnico> dts = query.getResultList();
        dts.forEach(System.out::println);
    }
}
