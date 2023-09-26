package query;

import dao.DirectorTecnico;
import dao.Jugador;
import dao.Persona;
import dao.Posicion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Insert {
    private static String pathFilesCsv = "./src/main/java/csv/";
    private static final String COACH_FILE = "directorTecnico.csv", TEAMS_FILE = "equipo.csv", PLAYERS_FILE = "jugador.csv", POSITION_FILE = "posicion.csv", PERSISTENCE_NAME = "TP2-EJER3";

    private static final int MIN_PLAYERS = 7, MAX_PLAYERS = 10;
    private static final String ARCHER_REQUIREMENT = "Arquero";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        //ADDING COACH
//        addCoach();
        List<DirectorTecnico> coachs = em.createQuery("SELECT d FROM DirectorTecnico d").getResultList();
        coachs.forEach(System.out::println);

//        ADDING POSITIONS
//        addPositions();

        List<Posicion> positions = em.createQuery("SELECT p FROM Posicion p").getResultList();
        positions.forEach(System.out::println);


        //ADDING PLAYERS
//        addPlayers();
        List<Jugador> players = em.createQuery("SELECT j FROM Jugador j").getResultList();
        players.forEach(System.out::println);


        // SEND TRANSACTION
        em.getTransaction().commit();

        // CLOSE
        em.close();
        emf.close();
    }

    private static void addPositions() {
        Posicion p = null;
        System.out.print("Cargando posiciones .....................");
        CSVParser parser = getCSVParser(POSITION_FILE);
        for (CSVRecord row : parser){
            p = new Posicion(row.get("tipo"));
            em.persist(p);
            System.out.print(".");
        }
        System.out.print(" .....................PROCESO FINALIZADO ");

    }

    private static Posicion getPositionById(int i){
        return (Posicion) em.find(Posicion.class,i);
    }
    private static void addPlayers() {
        Jugador j = null;
        Posicion p = null;
        System.out.print("Cargando jugadores .....................");
        CSVParser parser = getCSVParser(PLAYERS_FILE);
        int i = 20;
        for (CSVRecord row : parser){
             p = getPositionById(Integer.parseInt(row.get("posicion_id")));
            j = new Jugador(i, row.get("nombre"),Integer.parseInt(row.get("edad")),p);
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
        for (CSVRecord row : parser){
            dt = new DirectorTecnico(i,row.get("nombre"),Integer.parseInt(row.get("edad")),row.get("estrategia"));
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
