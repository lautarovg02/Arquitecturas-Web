package springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.model.Persona;

import java.util.List;
import java.util.Optional;

@Repository("PersonaRepository")
public interface PersonaRepository extends JpaRepository<Persona,Long> {

     List<Persona> findAll();
     Optional<Persona> findById(Long id);
     List<Persona> findByEdadLessThan(int edad);

     List<Persona> findAllByEdad(int edad);

    void existsByNombreAndAndEdad(String nombre, int edad);
    void deleteById(Long id);
    boolean existsById(Long id);

    boolean existsByNombreAndEdad(String nombre, int edad);
}
