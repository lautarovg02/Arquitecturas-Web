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
    private static final String TOURNAMENT_FILE = "Torneo.csv" ,COACH_FILE = "directorTecnico.csv", TEAMS_FILE = "equipo.csv", PLAYERS_FILE = "jugador.csv", POSITION_FILE = "posicion.csv", PERSISTENCE_NAME = "TP2-EJER3";

    private static final int MAX_TITULARS_PLAYERS = 7, MAX_SUBSTITUTE_PLAYERS = 2;
    private static final String ARCHER_REQUIREMENT = "Arquero";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        //ADDING COACH
//        addCoach();
//        List<DirectorTecnico> coachs = em.createQuery("SELECT d FROM DirectorTecnico d").getResultList();
//        coachs.forEach(System.out::println);
//
////        ADDING POSITIONS
////        addPositions();
//        List<Posicion> positions = em.createQuery("SELECT p FROM Posicion p").getResultList();
//        positions.forEach(System.out::println);
//
//
//        //ADDING PLAYERS
////        addPlayers();
//        List<Jugador> players = em.createQuery("SELECT j FROM Jugador j").getResultList();
//        players.forEach(System.out::println);

        //ADDING TOURNAMENT
//        addTournaments();
        List<Torneo> tournaments = em.createQuery("SELECT t FROM Torneo t").getResultList();
        tournaments.forEach(System.out::println);
        //ADDING TEAMS

        // SEND TRANSACTION
        em.getTransaction().commit();

        // CLOSE
        em.close();
        emf.close();
    }

    private static void addTournaments() {
        Torneo t = null;
        System.out.print("Cargando Torneos .....................");
        CSVParser parser = getCSVParser(TOURNAMENT_FILE);
        for (CSVRecord row : parser) {
            t = new Torneo(row.get("nombre"));
            em.persist(t);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO ");

    }

    private static Torneo getRandomTournament(){
        String query = Torneo.BUSCAR_TODOS;
        Query query1 = em.createNamedQuery(query);
        List<Torneo> tournaments = (List<Torneo>) query1.getResultList();
        return (Torneo) getRandomObjetc(tournaments);
    }

    public static DirectorTecnico getCoachById(int id){
        return em.find(DirectorTecnico.class, id);
    }
    public static Torneo getTournamentById(int id){
        return em.find(Torneo.class, id);
    }

    private static void addTeams() {
        Equipo e = null;
        List<Jugador> titulares, suplentes = new ArrayList<>();
        Torneo t = null;
        DirectorTecnico dt = null;
        CSVParser parser = getCSVParser(TEAMS_FILE);
        for (CSVRecord row : parser) {
            dt = getCoachById(Integer.parseInt(row.get("directorTecnico_id")));
            t = getTournamentById(Integer.parseInt(row.get("torneo_id")));
            titulares = getTitularPlayers();
            suplentes = getSubstitutePlayers();
            e = new Equipo(row.get("nombre"),row.get("publicidad"),dt,t,titulares,suplentes);
            em.persist(e);

        }
    }

    private static List<Jugador> getTitularPlayers(){
        List<Jugador> players, titulars = new ArrayList<>();
        String query = Jugador.BUSCAR_TODOS;
        Query query1 = em.createNamedQuery(query);
        players = query1.getResultList();
        Jugador goalkeeper = getFirstPlayerWithoutAJob();
        titulars.add(goalkeeper);
        Iterator<Jugador> itPlayers = players.iterator();
        while (itPlayers.hasNext() && titulars.size() < MAX_TITULARS_PLAYERS){
            Jugador j = itPlayers.next();
            if(!j.getPosicion().getTipo().equals(Posicion.TIPO_ARQUERO)){
                titulars.add(j);
            }
        }
        return titulars;
    }

    private static List<Jugador> getSubstitutePlayers(){
        List<Jugador> players, substitute = new ArrayList<>();
        String query = Jugador.BUSCAR_TODOS;
        Query query1 = em.createNamedQuery(query);
        players = query1.getResultList();
        Iterator<Jugador> itPlayers = players.iterator();
        while (itPlayers.hasNext() && substitute.size() < MAX_SUBSTITUTE_PLAYERS){
            Jugador j = itPlayers.next();
            if(!j.getPosicion().getTipo().equals(Posicion.TIPO_ARQUERO)){
                substitute.add(j);
            }
        }
        return substitute;
    }

    private static Object getRandomObjetc(List<?> l) {
        Random rand = new Random();
        return l.get(rand.nextInt(l.size()));
    }

    private static Jugador getFirstPlayerWithoutAJob() {
        String queryString = Jugador.OBTENER_PRIMER_ARQUERO_SIN_CLUB;
        Query query = em.createQuery(queryString);
        query.setMaxResults(1); // Establece el límite en 1
        Jugador player = (Jugador) query.getSingleResult();
        return player;
    }

    private static DirectorTecnico getFirstCoachWithoutAJob() {
        String queryString = DirectorTecnico.OBTENER_PRIMER_TECNICO_SIN_CLUB;
        Query query = em.createQuery(queryString);
        query.setMaxResults(1); // Establece el límite en 1
        DirectorTecnico coach = (DirectorTecnico) query.getSingleResult();
        return coach;
    }

    private static void addPositions() {
        Posicion p = null;
        System.out.print("Cargando posiciones .....................");
        CSVParser parser = getCSVParser(POSITION_FILE);
        for (CSVRecord row : parser) {
            p = new Posicion(row.get("tipo"));
            em.persist(p);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO ");

    }

    private static Posicion getPositionById(int i) {
        return (Posicion) em.find(Posicion.class, i);
    }

    private static void addPlayers() {
        Jugador j = null;
        Posicion p = null;
        System.out.print("Cargando jugadores .....................");
        CSVParser parser = getCSVParser(PLAYERS_FILE);
        int i = 20;
        for (CSVRecord row : parser) {
            p = getPositionById(Integer.parseInt(row.get("posicion_id")));
            j = new Jugador(i, row.get("nombre"), Integer.parseInt(row.get("edad")), p);
            i++;
            System.out.println(j);
            em.persist(j);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO ");

    }

    private static void addCoach() {
        DirectorTecnico dt = null;
        System.out.print("Cargando DTs .....................");
        CSVParser parser = getCSVParser(COACH_FILE);
        int i = 0;
        for (CSVRecord row : parser) {
            dt = new DirectorTecnico(i, row.get("nombre"), Integer.parseInt(row.get("edad")), row.get("estrategia"));
            i++;
            em.persist(dt);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO ");

    }

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
