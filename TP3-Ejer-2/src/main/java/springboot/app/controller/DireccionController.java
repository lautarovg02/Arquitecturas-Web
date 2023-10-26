package springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.app.dtos.DireccionDTO;
import springboot.app.model.Direccion;
import springboot.app.services.DireccionServicio;


@RestController
@RequestMapping("/direccion")
public class DireccionController {
    @Autowired
    private DireccionServicio direccionServicio;

    public DireccionController() {
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DireccionDTO direccionDTO) throws Exception {
        try {
            Direccion direccion = new Direccion();
            direccion.setCalle(direccionDTO.getCalle());
            direccion.setCiudad(direccionDTO.getCiudad());
            if(!direccionServicio.existsByCalleAndCiudad(direccion)){
                return ResponseEntity.status(HttpStatus.OK).body(direccionServicio.save(direccion));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Ya existe esta la direccion.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getDirecciones() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(direccionServicio.getDirecciones());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try {
            if(direccionServicio.existById(id)){
                return ResponseEntity.status(HttpStatus.OK).body(direccionServicio.findById(id));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. no existe la direccion que busca");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<?>findAllByCiudad(@PathVariable String ciudad){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(direccionServicio.findAllByCiudad(ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody DireccionDTO direccionDTO){
        try {
            if (!direccionServicio.existById(id)) {
                Direccion direccion = new Direccion();
                direccion.setCalle(direccionDTO.getCalle());
                direccion.setCiudad(direccionDTO.getCiudad());
                return ResponseEntity.status(HttpStatus.OK).body(direccionServicio.update(id, direccion));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Dirección no encontrada.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            if (!direccionServicio.existById(id)) {
                direccionServicio.delete(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado con Éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Dirección no encontrada.");
            }
        } catch (Exception e) {
            // Handle exceptions appropriately, and return an internal server error status.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error. Por favor, inténtelo más tarde.");
        }
    }

}
