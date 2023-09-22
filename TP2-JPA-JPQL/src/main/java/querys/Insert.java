import dao.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class Insert {
    public static void main(String[] args) {
        /*
        * Cuando trabajemos con hibernate lo que vamos a usar es EntityManagerFacotry*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        // le decimso a emf que ahora si nso cree un EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //CREANDO DIRECCIONES
        Direccion d1 = new Direccion("Olavarria","Rufino Fal 2214");
        Direccion d2 = new Direccion("Olavarria","Manuel Leal 1214");
        Direccion d3 = new Direccion("Olavarria","Espana 4231");
        Direccion d4 = new Direccion("Olavarria","Celestino munos 1314");
        //LE DECIMOS QUE PERSISTA LAS DIRECCIONES PARA QUE LAS GUARDE EN LA BD
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(d4);

        //CREANDO PERSONAS
        Persona p1 = new Persona(1,"Lautaro", 20,d4);
        Persona p2 = new Persona(2,"Felipe", 13,d2);
        Persona p3 = new Persona(3,"Josefina", 19,d4);
        Persona p4 = new Persona(4,"Pepe", 20,d3);
        //LE DECIMOS QUE PERSISTA LAS PERSONAS PARA QUE LAS GUARDE EN LA BD
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);

        //FINALMENTE COMITEAMOS LA TRANSACCION
        em.getTransaction().commit();
        //CERRAMOS EL "EntityManager" QUE ESTABAMOS USANDO
        em.close();
        emf.close();


    }
}
