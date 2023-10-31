package springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;
import springboot.app.dtos.SocioDTO;
import springboot.app.exception.SocioException;
import springboot.app.model.Socio;
import springboot.app.repository.SocioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service("SocioServicio")
public class SocioServicio {

    @Autowired
    private SocioRepository socioRepository;

    public List<SocioDTO> getSocios() throws Exception {
        var resultado = socioRepository.findAll();
        try {
            return resultado.stream().map(socio -> new SocioDTO(socio.getPersona().getId(),socio.getTipo(),socio.getPersona().getNombre(),socio.getPersona().getEdad())).collect(Collectors.toList());
        }catch (Exception e){
             throw new Exception(e.getMessage());
        }
    }


    public List<SocioDTO> getSociosByTipo(String tipo) throws Exception {
        var resultado = socioRepository.findAllByTipo(tipo);
        try {
            return resultado.stream().map(socio -> new SocioDTO(socio.getPersona().getId(),socio.getTipo(),socio.getPersona().getNombre(),socio.getPersona().getEdad())).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean deleteById(Long id) throws Exception {
        try {
         if(socioRepository.existsById(id)){

             socioRepository.deleteById(id);
             return !socioRepository.existsById(id);
         }else
            throw new SocioException("Error: El socio que quiere borrar no existe en la bd");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public SocioDTO getSocioById(Long id) throws SocioException {
        try {
            if(socioRepository.existsById(id)){
                Socio socio = socioRepository.findById(id).get();
                SocioDTO socioDTO = new SocioDTO(socio.getId(),socio.getTipo(),socio.getPersona().getNombre(),socio.getPersona().getEdad());
                return socioDTO;
            }else
                throw new SocioException("Error: El socio que quiere traer no existe en la bd");
        }catch (SocioException e){
              throw  new SocioException(e.getMessage());
        }
    }
}
