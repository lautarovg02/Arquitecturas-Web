package query;


import dao.DirectorTecnico;
import dao.Equipo;
import dao.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Update {
    private static String pathFilesCsv = "./src/main/java/csv/";
    private static final String  PERSISTENCE_NAME = "TP2-EJER3";
    private static final int MAX_TITULARS_PLAYERS = 7, MAX_SUBSTITUTE_PLAYERS = 2;
    private static final String ARCHER_REQUIREMENT = "Arquero";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // CONEXION
        em.getTransaction().begin();

        //UPDATE TEAM
        String name = "Boca juniors";
        updateTeamById(206,name);

        //UPDATE DT
        String estrategia = "433";
        updateDtById(206,estrategia);
        
        // CLOSE
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void updateTeamById(int id, String name) {
        Equipo e =  em.find(Equipo.class, id);

        System.out.print(e);
        System.out.print("Actualizando Equipo ... ");
        e.setNombre(name);
        em.merge(e);
        System.out.println("... proceso finalizado");

        System.out.println("Resultado : " + e);
    }


    private static void updateDtById(int id, String estraegia) {
        DirectorTecnico dt =  em.find(DirectorTecnico.class, id);

        System.out.print(dt);
        System.out.print("Actualizando DirectorTecnico ... ");
        dt.setEstrategia(estraegia);
        em.merge(dt);
        System.out.println("... proceso finalizado");

        System.out.println("Resultado : " + dt);
    }


}
