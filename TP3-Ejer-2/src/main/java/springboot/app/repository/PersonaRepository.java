package springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.app.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

     List<Persona> findAll();
     Optional<Persona> findById(Long id);

     List<Persona> findAllByEdad(int edad);


    void deleteById(Long id);
}
