package springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.model.Socio;

import java.util.List;

@Repository("SocioRepository")
public interface SocioRepository extends JpaRepository<Socio,Long> {

    List<Socio> findAll();
    boolean existsById(Long id);
    List<Socio> findAllByTipo(String tipo);
}
