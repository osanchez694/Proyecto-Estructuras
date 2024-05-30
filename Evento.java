/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionhoraslibres;

import java.io.Serializable;
import java.util.Date;

class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private Date fecha;
    private String lugar;
    private int horasLibresOtorgadas;

    public Evento(String nombre, Date fecha, String lugar, int horasLibresOtorgadas) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.horasLibresOtorgadas = horasLibresOtorgadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getHorasLibresOtorgadas() {
        return horasLibresOtorgadas;
    }

    public void setHorasLibresOtorgadas(int horasLibresOtorgadas) {
        this.horasLibresOtorgadas = horasLibresOtorgadas;
    }
}