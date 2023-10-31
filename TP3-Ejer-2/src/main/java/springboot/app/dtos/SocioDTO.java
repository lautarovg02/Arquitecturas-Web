package springboot.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SocioDTO {
    private Long id;
    private String tipo, nombre;
    private int edad;

}
