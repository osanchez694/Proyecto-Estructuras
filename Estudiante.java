/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionhoraslibres;

import java.io.Serializable;
import java.util.ArrayList;

class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String numeroEstudiante;
    private String correoElectronico;
    private ArrayList<Integer> horasLibres;
    private ArrayList<Evento> eventosInscritos;

    public Estudiante(String nombre, String numeroEstudiante, String correoElectronico, int horasLibres) {
        this.nombre = nombre;
        this.numeroEstudiante = numeroEstudiante;
        this.correoElectronico = correoElectronico;
        this.horasLibres = new ArrayList<>();
        this.horasLibres.add(horasLibres);
        this.eventosInscritos = new ArrayList<>(); // Initialize eventosInscritos
    }

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

    public void agregarHorasLibres(int horas) {
        horasLibres.add(horas);
    }

    public double calcularPromedioHorasSemana() {
        if (horasLibres.isEmpty()) return 0.0;
        int totalHoras = 0;
        for (int horas : horasLibres) {
            totalHoras += horas;
        }
        return (double) totalHoras / horasLibres.size();
    }

    public int obtenerTotalHorasLibres() {
        int totalHoras = 0;
        for (int horas : horasLibres) {
            totalHoras += horas;
        }
        return totalHoras;
    }

    public void inscribirEnEvento(Evento evento) {
        eventosInscritos.add(evento);
        agregarHorasLibres(evento.getHorasLibresOtorgadas());
    }

    public void desinscribirDeEvento(Evento evento) {
        if (eventosInscritos.remove(evento)) {
            agregarHorasLibres(-evento.getHorasLibresOtorgadas());
        }
    }

    public ArrayList<Evento> getEventosInscritos() {
        return eventosInscritos;
    }
}

    