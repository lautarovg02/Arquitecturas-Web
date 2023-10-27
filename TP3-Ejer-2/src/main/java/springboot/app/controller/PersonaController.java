package springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.app.dtos.DireccionDTO;
import springboot.app.dtos.PersonaDTO;
import springboot.app.model.Persona;
import springboot.app.services.PersonaServicio;


@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaServicio personaServicio;

    public PersonaController() {}

    @GetMapping("")
    public ResponseEntity<?> getPersonas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personaServicio.getPersonas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Intentelo mas tarde");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            if(personaServicio.existById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(personaServicio.findById(id));
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: esta persona no existe .");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Persona persona) {
        try {
            if (personaServicio.existById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(personaServicio.update(id, persona));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: esta persona no existe .");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @GetMapping("edad/{edad}")
    public ResponseEntity<?> getPersonasByEdad(@PathVariable int edad) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personaServicio.findAllByEdad(edad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }


    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody PersonaDTO personaDTO) {
        try {
            Persona persona = new Persona(personaDTO.getId(), personaDTO.getNombre(), personaDTO.getEdad());

            if(!personaServicio.existByNameAndEdad(persona)) {
                return ResponseEntity.status(HttpStatus.OK).body(personaServicio.save(personaDTO));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: esta persona ya existe .");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (personaServicio.existById(id)) {
                personaServicio.delete(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado con Éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Dirección no encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }
}
