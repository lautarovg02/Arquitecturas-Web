package springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.app.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    public List<Persona> findAll();
    public Optional<Persona> findById(Long id);

    public List<Persona> findAllByEdad(int edad);


}
