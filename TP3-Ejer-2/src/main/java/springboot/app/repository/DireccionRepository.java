package springboot.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.model.Direccion;

import java.util.List;
import java.util.Optional;

@Repository("DireccionRepository")
public interface DireccionRepository extends JpaRepository<Direccion,Long> {

     List<Direccion> findAll();

     Optional<Direccion> findById(Long id);
     List<Direccion> findAllByCiudad(String ciudad);

     void deleteById(Long id);

     boolean existsByCalleAndCiudad(String calle, String ciudad);
}
