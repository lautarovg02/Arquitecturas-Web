package springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.app.services.PersonaServicio;


@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaServicio personaServicio;

    public PersonaController(){

    }
    @GetMapping("")
    public ResponseEntity<?> getPersonas(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personaServicio.getPersonas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Intentelo mas tarde");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaServicio.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: La persona que busca en la bd no existe");
        }
    }

    @GetMapping("edad/{edad}")
    public ResponseEntity<?>getPersonasByEdad(@PathVariable int edad){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaServicio.findAllByEdad(edad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Intentalo mas tarde ");
        }
    }

}
