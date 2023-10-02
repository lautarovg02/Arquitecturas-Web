package query;

import dao.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Insert {
    private static String pathFilesCsv = "./src/main/java/csv/";
    private static final String TOURNAMENT_FILE = "Torneo.csv", COACH_FILE = "directorTecnico.csv", TEAMS_FILE = "equipo.csv", PLAYERS_FILE = "jugador.csv", POSITION_FILE = "posicion.csv", PERSISTENCE_NAME = "TP2-EJER3";

    private static final int MAX_TITULARS_PLAYERS = 7, MAX_SUBSTITUTE_PLAYERS = 2;
    private static final String ARCHER_REQUIREMENT = "Arquero";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();
        /*
         * AGREGAR ENTIDADES:
         * A la hora de ejecutar los metodos, priorize ejecutar de a uno cada metodo si no se rompera */

        //ADDING POSITIONS
        addPositions();

        //ADDING COACH
//        addCoach();

        //ADDING PLAYERS
//        addPlayers();

        //ADDING TOURNAMENT
//        addTournaments();

        //ADDING TEAMS
//        addTeams();

        // CLOSE
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static List<DirectorTecnico> getTecnico() {
        return em.createQuery("SELECT j FROM Jugador j").getResultList();
    }

    /***Metodo para agregar torneos a la BD*/
    private static void addTournaments() {
        Torneo t = null;
        System.out.print("Cargando Torneos .....................");
        CSVParser parser = getCSVParser(TOURNAMENT_FILE);
        for (CSVRecord row : parser) {
            t = new Torneo(row.get("nombre"));
            em.persist(t);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO \n");

    }

    /***Metodo para retornar un torneo random de la BD*/
    private static Torneo getRandomTorneo() {
        String query = Torneo.BUSCAR_TODOS;
        Query query1 = em.createNamedQuery(query);
        List<Torneo> tournaments = (List<Torneo>) query1.getResultList();
        return (Torneo) getRandomObjetc(tournaments);
    }

    /***Metodo para retornar un DirectorTecnico segun un id*/
    public static DirectorTecnico getCoachById(int id) {
        return em.find(DirectorTecnico.class, id);
    }

    /***Metodo para retornar un torneo segun un id*/
    public static Torneo getTournamentById(int id) {
        return em.find(Torneo.class, id);
    }

    /***Metodo para agregar equipos a la BD*/
    private static void addTeams() {
        Equipo e = null;
        List<Jugador> titulares, suplentes = new ArrayList<>();
        Torneo t = null;
        DirectorTecnico dt = null;
        CSVParser parser = getCSVParser(TEAMS_FILE);
        for (CSVRecord row : parser) {
            dt = getFirstAvailableDT();
            t = getRandomTorneo();
            titulares = getTitularPlayers();
            suplentes = getSubstitutePlayers();
            dt.setEquipo(e);
            e = new Equipo(row.get("nombre"), row.get("publicidad"), dt);
            e.setSuplentes(suplentes);
            e.setJugadores(titulares);
            t.addEquipo(e);
            em.merge(e);
            for (Jugador j : titulares) {
                j.setEquipo(e);
                em.merge(j);
            }
            for (Jugador j : suplentes) {
                j.setEquipo(e);
                em.merge(j);
            }
            em.persist(e);
        }
    }

    private static DirectorTecnico getFirstAvailableDT() {
        String queryString = "SELECT j FROM DirectorTecnico j WHERE j.equipo IS NULL";
        Query query = em.createQuery(queryString);
        query.setMaxResults(1); // Establece el límite en 1
        DirectorTecnico dt = (DirectorTecnico) query.getSingleResult();
        return dt;
    }


    /***Metodo para retornar una lista de jugadores titulares random segun las reglas del torneo*/
    private static List<Jugador> getTitularPlayers() {
        List<Jugador> players, titulars = new ArrayList<>();
        String query = Jugador.BUSCAR_TODOS_SIN_CLUB;
        Query query1 = em.createNamedQuery(query);
        players = query1.getResultList();
        Jugador goalkeeper = getFirstPlayerWithoutAJob();
        titulars.add(goalkeeper);
        Iterator<Jugador> itPlayers = players.iterator();
        while (itPlayers.hasNext() && titulars.size() < MAX_TITULARS_PLAYERS) {
            Jugador j = itPlayers.next();
            if (!j.getPosicion().getTipo().equals(Posicion.TIPO_ARQUERO)) {
                titulars.add(j);
            }
        }
        return titulars;
    }

    /***Metodo para retornar una lista de jugadores suplentes random segun las reglas del torneo*/
    private static List<Jugador> getSubstitutePlayers() {
        List<Jugador> players, substitute = new ArrayList<>();
        String query = Jugador.BUSCAR_TODOS_SIN_CLUB;
        Query query1 = em.createNamedQuery(query);
        players = query1.getResultList();
        Iterator<Jugador> itPlayers = players.iterator();
        while (itPlayers.hasNext() && substitute.size() < MAX_SUBSTITUTE_PLAYERS) {
            Jugador j = itPlayers.next();
            if (!j.getPosicion().getTipo().equals(Posicion.TIPO_ARQUERO)) {
                substitute.add(j);
            }
        }
        return substitute;
    }

    /***Metodo para retornar un objeto random de una coleccion que recibe por parametro */
    private static Object getRandomObjetc(List<?> l) {
        Random rand = new Random();
        return l.get(rand.nextInt(l.size()));
    }

    /***Metodo para retornar el primer jugador sin club*/
    private static Jugador getFirstPlayerWithoutAJob() {
        String queryString = Jugador.OBTENER_PRIMER_ARQUERO_SIN_CLUB;
        Query query = em.createQuery(queryString);
        query.setMaxResults(1); // Establece el límite en 1
        Jugador player = (Jugador) query.getSingleResult();
        return player;
    }

    /***Metodo para retornar el primer tecnico sin club*/
    private static DirectorTecnico getFirstCoachWithoutAJob() {
        String queryString = DirectorTecnico.OBTENER_PRIMER_TECNICO_SIN_CLUB;
        Query query = em.createQuery(queryString);
        query.setMaxResults(1); // Establece el límite en 1
        DirectorTecnico coach = (DirectorTecnico) query.getSingleResult();
        return coach;
    }

    /***Metodo para agregar posiciones disponibles para los jugadores */
    private static void addPositions() {
        Posicion p = null;
        System.out.print("Cargando posiciones .....................");
        CSVParser parser = getCSVParser(POSITION_FILE);
        int i = 0;
        for (CSVRecord row : parser) {
            p = new Posicion(i, row.get("tipo"));
            i++;
            em.persist(p);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO \n ");

    }

    /***Metodo para retornar una posicion de la BD segun el id*/
    private static Posicion getPositionById(int i) {
        return (Posicion) em.find(Posicion.class, i);
    }

    /***Metodo para agregar jugadores a la BD*/
    private static void addPlayers() {
        Jugador j = null;
        Posicion p = null;
        System.out.print("Cargando jugadores .....................");
        CSVParser parser = getCSVParser(PLAYERS_FILE);
        List<DirectorTecnico> dt = getTecnico();
        for (CSVRecord row : parser) {
            p = getPositionById(Integer.parseInt(row.get("posicion_id")));
            j = new Jugador(row.get("nombre"), Integer.parseInt(row.get("edad")), p);
            System.out.println(j);
            em.persist(j);
            p.addJugador(j);
            em.merge(p);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO \n");

    }

    /***Metodo para agregar Tecnicos a la BD*/
    private static void addCoach() {
        DirectorTecnico dt = null;
        System.out.print("Cargando DTs .....................");
        CSVParser parser = getCSVParser(COACH_FILE);
        for (CSVRecord row : parser) {
            dt = new DirectorTecnico(row.get("nombre"), Integer.parseInt(row.get("edad")), row.get("estrategia"));
            em.persist(dt);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO\n ");

    }

    /***Metodo para retornar un CSVPARSER segun un archivo recibido por parametro*/
    private static CSVParser getCSVParser(String file) {
        CSVParser p = null;
        try {
            p = CSVFormat.DEFAULT.withHeader().parse(new FileReader(pathFilesCsv + file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}
