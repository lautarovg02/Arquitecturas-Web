package springboot.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.app.exception.SocioException;
import springboot.app.services.SocioServicio;


@RestController
@RequestMapping("/socio")
public class SocioController {
    @Autowired
    private SocioServicio socioServicio;

    @GetMapping("")
    public ResponseEntity<?> getSocios() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(socioServicio.getSocios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Intente mas tarde");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSocioById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(socioServicio.getSocioById(id));
        } catch (SocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("tipo/{tipo}")
    public ResponseEntity<?> getSociosByTipo(@PathVariable String tipo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(socioServicio.getSociosByTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSocioById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(socioServicio.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Intente mas tarde");
        }
    }
}
