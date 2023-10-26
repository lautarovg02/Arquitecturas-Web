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

    public boolean existById(Long id) {
        return personaRepository.existsById(id);
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
    public PersonaDTO save(Persona entity) throws Exception {
        try {
                Persona persona = personaRepository.save(entity);
                PersonaDTO personaDTO = new PersonaDTO();
                personaDTO.setEdad(persona.getEdad());
                personaDTO.setId(persona.getId());
                personaDTO.setNombre(persona.getNombre());
                return personaDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void delete(Long id) {
        personaRepository.deleteById(id);
    }

    @Transactional
    public PersonaDTO update(Long id, Persona nuevaPersona) throws Exception {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        try {
            Persona personaActual = optionalPersona.get();
            personaActual.setEdad(nuevaPersona.getEdad());
            personaActual.setId(nuevaPersona.getId());
            personaActual.setNombre(nuevaPersona.getNombre());

            Persona persona = personaRepository.save(personaActual);

            PersonaDTO personaDTO = new PersonaDTO();
            personaDTO.setEdad(persona.getEdad());
            personaDTO.setId(persona.getId());
            personaDTO.setNombre(persona.getNombre());

            return personaDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public boolean existByNameAndEdad(Persona persona){
        return personaRepository.existsByNombreAndEdad(persona.getNombre(), persona.getEdad());
    }
}
