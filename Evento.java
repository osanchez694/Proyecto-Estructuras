/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionhoraslibres;

import java.io.*;
import java.util.*;

// Clase Evento
class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String lugar;
    private Date fecha;
    private int horasLibresOtorgadas;

    public Evento(String nombre, String lugar, Date fecha, int horasLibresOtorgadas) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha = fecha;
        this.horasLibresOtorgadas = horasLibresOtorgadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getHorasLibresOtorgadas() {
        return horasLibresOtorgadas;
    }
}
