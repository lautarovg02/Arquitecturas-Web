package querys;

import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class Insert {
    private static String pathFilesCsv = "./src/main/java/csv/";
    private static final String ADDRESS_FILE = "domicilios.csv", PEOPLE_FILE = "personas.csv", PARTNERS_FILE = "socio.csv", PERSISTENCE_NAME = "TP2-EJER1";

    private static final int MAX_TURNS = 50, MAX_PLAYERS = 10;
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        // ADDING ADDRESS
//         addAddreses();
//        @SuppressWarnings("unchecked")
        List<Direccion> addreses = em.createQuery("SELECT d FROM Direccion d").getResultList();
        addreses.forEach(System.out::println);

//         ADDING PEOPLE
         addPeople(addreses);
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
        personas.forEach(System.out::println);

//         ADDING PARTNER
         addPartners(personas);
        List<Socio> socios = em.createQuery("SELECT s FROM Socio s ").getResultList();
        socios.forEach(System.out::println);

//         ADDING TURNS
        addTurns(personas);

        // SEND TRANSACTION
        em.getTransaction().commit();

        // CLOSE
        em.close();
        emf.close();
    }

    private static void addPartners(List<Persona> personas) {
        Socio s = null;
        System.out.print("Cargando direcciones ....");
        List<String> partners = getPartners();
        for (Persona p : personas) {
            String type = (String) getRandomObjetc(partners);
            s = new Socio(type, p);
            em.persist(s);
            System.out.println(s);
            System.out.print(".");
        }
        System.out.println("\n ......Proceso terminado /_");
    }

    private static void addTurns(List<Persona> possiblePlayers) {
        Turno t = null;
        int i = 0;
        System.out.print("Cargando Turnos ....");
        while (i < MAX_TURNS) {
            t = new Turno();
            Timestamp fecha = (Timestamp) getRandomFutureDate();
            List<Persona> players = getRandomPeople(possiblePlayers);
            t.setFecha(fecha);
            t.setJugadores(players);
            em.persist(t);
            i++;
            System.out.print(".");
        }
        System.out.println("\n ......Proceso terminado /_");

    }

    private static List<Persona> getRandomPeople(List<Persona> players) {
        List<Persona> people = new ArrayList<Persona>();
        int i = 0;
        while (i < MAX_PLAYERS) {
            Persona p = (Persona) getRandomObjetc(players);
            if (!people.contains(p)) {
                people.add(p);
                i++;
            }
        }
        return people;
    }

    public static Timestamp getRandomFutureDate() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Random random = new Random();
        int randomDays = random.nextInt(365) + 1;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentDate.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, randomDays);

        Timestamp futureDate = new Timestamp(calendar.getTimeInMillis());

        return futureDate;
    }


    private static void addPeople(List<Direccion> listOfAddress) {
        Persona p = null;
        System.out.print("Cargando direcciones ....");
        CSVParser parser = getCSVParser(PEOPLE_FILE);
        for (CSVRecord row : parser) {
            Direccion d = (Direccion) getRandomObjetc(listOfAddress);
            p = new Persona(Integer.parseInt(row.get("id")), row.get("nombre"), Integer.parseInt(row.get(2)));
            p.setDomicilio(d);
            em.persist(p);
            System.out.println(p);
            System.out.print(".");
        }
        System.out.println("\n ......Proceso terminado /_");
    }

    private static List<String> getPartners() {
        List<String> partners = new ArrayList<>();
        CSVParser parser = getCSVParser(PARTNERS_FILE);
        for (CSVRecord row : parser) {
            partners.add(row.get("tipo"));
        }
        return partners;
    }

    private static void addAddreses() {
        Direccion d = null;
        System.out.print("Cargando direcciones ....");
        CSVParser parser = getCSVParser(ADDRESS_FILE);
        for (CSVRecord row : parser) {
            d = new Direccion(row.get("ciudad"), row.get("calle"));
            em.persist(d);
            System.out.print(".");
        }
        System.out.println("\n ......Proceso terminado /_");
    }

    private static Object getRandomObjetc(List<?> l) {
        Random rand = new Random();
        return l.get(rand.nextInt(l.size()));
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
