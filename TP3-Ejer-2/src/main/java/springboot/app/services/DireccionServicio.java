package springboot.app.services;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.app.dtos.DireccionDTO;
import springboot.app.model.Direccion;
import springboot.app.repository.DireccionRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("DireccionServicio")
public class DireccionServicio {
    @Autowired
    private DireccionRepository direccionRepository;

    @Transactional
    public List<DireccionDTO> getDirecciones() throws Exception {

        var resultado = direccionRepository.findAll();
        try {
            return resultado.stream().map(direccion -> new DireccionDTO(direccion.getId(), direccion.getCiudad(), direccion.getCalle())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Direccion> findAll() throws Exception {
        return direccionRepository.findAll();
    }

    @Transactional
    public List<DireccionDTO> findAllByCiudad(String ciudad) throws Exception {
        var resultado = direccionRepository.findAllByCiudad(ciudad);
        try {
            return resultado.stream().map(direccion -> new DireccionDTO(direccion.getId(), direccion.getCiudad(), direccion.getCalle())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DireccionDTO save(Direccion entity) throws Exception {
        try {
                Direccion direccion = direccionRepository.save(entity);
                DireccionDTO direccionDTO = new DireccionDTO();
                direccionDTO.setCalle(direccion.getCalle());
                direccionDTO.setCiudad(direccion.getCiudad());
                return direccionDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DireccionDTO findById(Long id) throws Exception {
        try {
            Optional<Direccion> optionalDireccion = direccionRepository.findById(id);
            Direccion direccion = optionalDireccion.get();
            DireccionDTO direccionDTO = new DireccionDTO();

            direccionDTO.setCalle(direccion.getCalle());
            direccionDTO.setCiudad(direccion.getCiudad());

            return direccionDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public DireccionDTO update(Long id, Direccion nuevaDireccion) throws Exception {
        Optional<Direccion> direccionExistente = direccionRepository.findById(id);
        try {
            if (direccionExistente.isPresent()) {
                Direccion direccionActual = direccionExistente.get();

                direccionActual.setCiudad(nuevaDireccion.getCiudad());
                direccionActual.setCalle(nuevaDireccion.getCalle());

                DireccionDTO direccionDTO = new DireccionDTO();
                direccionDTO.setCiudad(direccionActual.getCiudad());
                direccionDTO.setCalle(direccionActual.getCalle());

                return direccionDTO;

            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void delete(Long id) throws Exception {
        direccionRepository.deleteById(id);
    }

    public boolean existsByCalleAndCiudad(Direccion entity) {
        return  direccionRepository.existsByCalleAndCiudad(entity.getCalle(),entity.getCiudad());
    }

    public boolean existById(Long id) {
        return direccionRepository.existsById(id);
    }
}
