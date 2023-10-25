package springboot.app.dtos;

public class PersonaDTO {

    private Long id;
    private String nombre;
    private int edad;

    private String domicilio, ciudad;

    public PersonaDTO(Long id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public PersonaDTO(Long id, String nombre, int edad, String domicilio, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public PersonaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
