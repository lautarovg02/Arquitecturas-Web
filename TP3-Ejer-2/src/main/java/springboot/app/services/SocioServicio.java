package springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;
import springboot.app.dtos.SocioDTO;
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
}
