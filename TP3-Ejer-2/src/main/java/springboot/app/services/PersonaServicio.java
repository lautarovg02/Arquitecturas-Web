package springboot.app.services;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import springboot.app.dtos.PersonaDTO;
import springboot.app.model.Persona;
import springboot.app.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("PersonaServicio")
public class PersonaServicio {
    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    public List<PersonaDTO> getPersonas() throws Exception {
        var resultado = personaRepository.findAll();
        try {
            return resultado.stream().map(persona -> new PersonaDTO(persona.getId(), persona.getNombre(), persona.getEdad(), persona.getDomicilio().getCalle(), persona.getDomicilio().getCiudad())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public PersonaDTO findById(Long id) throws Exception {
        try {
            Optional<Persona> optionalPersona = personaRepository.findById(id);
            if (optionalPersona.isPresent()) {
                Persona persona = optionalPersona.get();
                PersonaDTO personaDTO = new PersonaDTO();
                personaDTO.setEdad(persona.getEdad());
                personaDTO.setId(persona.getId());
                personaDTO.setNombre(persona.getNombre());
                personaDTO.setCiudad(persona.getDomicilio().getCiudad());
                personaDTO.setDomicilio(persona.getDomicilio().getCalle());
                return personaDTO;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<PersonaDTO> findAllByEdad(int edad) throws Exception {
        var resultado = personaRepository.findAllByEdad(edad);
        try {
            return resultado.stream().map(persona -> new PersonaDTO(persona.getId(), persona.getNombre(), persona.getEdad(), persona.getDomicilio().getCalle(), persona.getDomicilio().getCiudad())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Persona save(Persona entity) throws Exception {
        try {
            return personaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void delete(Long id) {
        personaRepository.deleteById(id);
    }
}
