package querys;

import dao.Persona;

import javax.persistence.*;
import java.util.List;

public class Select {
    public static void main(String[] args) {
        //CONEXION A LA BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        // le decimso a emf que ahora si nso cree un EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //Le pedimos que busque una persona atraves de su clave primaria
        Persona j = em.find(Persona.class, 1);// el 1 seria el id
        System.out.println(j);
        //Consulta para traer todas las personas
        @SuppressWarnings("unchecked")
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
        personas.forEach(p-> System.out.println(p));
        //CERRANDO LA CONEXION
        em.getTransaction().commit();
        //CERRAMOS EL "EntityManager" QUE ESTABAMOS USANDO
        em.close();
        emf.close();
    }
}
