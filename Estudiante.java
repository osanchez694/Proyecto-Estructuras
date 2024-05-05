/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionhoraslibres;

import java.io.*;
import java.util.*;

class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String numeroEstudiante;
    private String correoElectronico;
    private ArrayList<Integer> horasLibres; // Lista de horas libres por semana

    // Constructor
    public Estudiante(String nombre, String numeroEstudiante, String correoElectronico, int horasLibres) {
        this.nombre = nombre;
        this.numeroEstudiante = numeroEstudiante;
        this.correoElectronico = correoElectronico;
        this.horasLibres = new ArrayList<>(); // Inicializar la lista
        this.horasLibres.add(horasLibres); // Agregar horas libres iniciales
    }
    

    // Getters y setters según sea necesario
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroEstudiante() {
        return numeroEstudiante;
    }

    public void setNumeroEstudiante(String numeroEstudiante) {
        this.numeroEstudiante = numeroEstudiante;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public ArrayList<Integer> getHorasLibres() {
        return horasLibres;
    }
    // Método para agregar horas libres por semana
    public void agregarHorasLibres(int horas) {
        horasLibres.add(horas);
    }

    // Método para calcular el promedio de horas libres por semana
    public double calcularPromedioHorasSemana() {
        if (horasLibres.isEmpty()) return 0.0;
        int totalHoras = 0;
        for (int horas : horasLibres) {
            totalHoras += horas;
        }
        return (double) totalHoras / horasLibres.size();
    }

    // Método para obtener la cantidad total de horas libres
    public int obtenerTotalHorasLibres() {
        int totalHoras = 0;
        for (int horas : horasLibres) {
            totalHoras += horas;
        }
        return totalHoras;
    }


    // Otros métodos según sea necesario
}

