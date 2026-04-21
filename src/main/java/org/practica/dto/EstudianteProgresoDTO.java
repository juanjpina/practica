package org.practica.dto;

public class EstudianteProgresoDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private String cursoTitulo;
    private int total;
    private int completados;

    public EstudianteProgresoDTO(String nombre, String apellidos, String email, String cursoTitulo, int total, int completados) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.cursoTitulo = cursoTitulo;
        this.total = total;
        this.completados = completados;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public String getCursoTitulo() { return cursoTitulo; }
    public int getTotal() { return total; }
    public int getCompletados() { return completados; }
    public int getPorcentaje() { return total > 0 ? (completados * 100) / total : 0; }
}
