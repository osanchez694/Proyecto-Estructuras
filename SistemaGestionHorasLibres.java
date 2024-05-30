/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagestionhoraslibres;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class SistemaGestionHorasLibres {
    private static final String ARCHIVO_ESTUDIANTES = "estudiantes.dat";
    private static final String ARCHIVO_EVENTOS = "eventos.dat";
    private static final int MAX_HORAS_LIBRES = 100; // Constante para las horas libres máximas
    private static ArrayList<Estudiante> estudiantes;
    private static ArrayList<Evento> eventos;

    public static void main(String[] args) {
        cargarDatos();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Inscribirse en Evento");
            System.out.println("4. Desinscribirse de Evento");
            System.out.println("5. Consultar Horas Libres");
            System.out.println("6. Darse de Baja");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    registrarUsuario(scanner);
                    break;
                case 3:
                    inscribirEnEvento(scanner);
                    break;
                case 4:
                    desinscribirDeEvento(scanner);
                    break;
                case 5:
                    consultarHorasLibres(scanner);
                    break;
                case 6:
                    darseDeBaja(scanner);
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    guardarDatos();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su número de estudiante: ");
        String numeroEstudiante = scanner.nextLine();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equals(nombreUsuario) && estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                System.out.println("Inicio de sesión exitoso.");
                return;
            }
        }
        System.out.println("Inicio de sesión fallido. Usuario no encontrado.");
    }

    private static void registrarUsuario(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su número de estudiante: ");
        String numeroEstudiante = scanner.nextLine();
        System.out.print("Ingrese su correo electrónico institucional: ");
        String correo = scanner.nextLine();

        if (buscarEstudiantePorNumero(numeroEstudiante) != null) {
            System.out.println("Ya existe un usuario registrado con este número de estudiante.");
            return;
        }

        System.out.print("Ingrese la cantidad de horas libres del estudiante: ");
        int horasLibres = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        registrarEstudiante(nombre, numeroEstudiante, correo, horasLibres);
        System.out.println("Usuario registrado correctamente.");
    }

    private static void registrarEstudiante(String nombre, String numeroEstudiante, String correoElectronico, int horasLibres) {
        Estudiante nuevoEstudiante = new Estudiante(nombre, numeroEstudiante, correoElectronico, horasLibres);
        estudiantes.add(nuevoEstudiante);
        guardarDatos();
        System.out.println("Estudiante registrado correctamente.");
    }

    private static void inscribirEnEvento(Scanner scanner) {
        System.out.print("Ingrese su número de estudiante: ");
        String numeroEstudiante = scanner.nextLine();

        Estudiante estudiante = buscarEstudiantePorNumero(numeroEstudiante);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("=== Eventos Disponibles ===");
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            System.out.println((i + 1) + ". " + evento.getNombre() + " - Fecha: " + evento.getFecha() + " - Lugar: " + evento.getLugar() + " - Horas libres: " + evento.getHorasLibresOtorgadas());
        }

        System.out.print("Seleccione el número del evento al que desea inscribirse: ");
        int numeroEvento = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numeroEvento < 1 || numeroEvento > eventos.size()) {
            System.out.println("Número de evento no válido.");
            return;
        }

        Evento eventoSeleccionado = eventos.get(numeroEvento - 1);
        estudiante.inscribirEnEvento(eventoSeleccionado);
        guardarDatos();
        System.out.println("Inscripción al evento " + eventoSeleccionado.getNombre() + " realizada con éxito.");
    }

    private static void desinscribirDeEvento(Scanner scanner) {
        System.out.print("Ingrese su número de estudiante: ");
        String numeroEstudiante = scanner.nextLine();

        Estudiante estudiante = buscarEstudiantePorNumero(numeroEstudiante);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("=== Eventos Inscritos ===");
        ArrayList<Evento> eventosInscritos = estudiante.getEventosInscritos();
        for (int i = 0; i < eventosInscritos.size(); i++) {
            Evento evento = eventosInscritos.get(i);
            System.out.println((i + 1) + ". " + evento.getNombre() + " - Fecha: " + evento.getFecha() + " - Lugar: " + evento.getLugar());
        }

        System.out.print("Seleccione el número del evento del que desea desinscribirse: ");
        int numeroEvento = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numeroEvento < 1 || numeroEvento > eventosInscritos.size()) {
            System.out.println("Número de evento no válido.");
            return;
        }

        Evento eventoSeleccionado = eventosInscritos.get(numeroEvento - 1);
        estudiante.desinscribirDeEvento(eventoSeleccionado);
        guardarDatos();
        System.out.println("Desinscripción del evento " + eventoSeleccionado.getNombre() + " realizada con éxito.");
    }

    private static void consultarHorasLibres(Scanner scanner) {
        System.out.print("Ingrese su número de estudiante: ");
        String numeroEstudiante = scanner.nextLine();

        Estudiante estudiante = buscarEstudiantePorNumero(numeroEstudiante);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        double promedioHorasLibres = estudiante.calcularPromedioHorasSemana();
        int totalHorasLibres = estudiante.obtenerTotalHorasLibres();
        int horasFaltantes = MAX_HORAS_LIBRES - totalHorasLibres;

        System.out.println("Promedio de horas libres por semana: " + promedioHorasLibres);
        System.out.println("Total de horas libres: " + totalHorasLibres);
        System.out.println("Horas libres faltantes para el máximo de " + MAX_HORAS_LIBRES + ": " + (horasFaltantes > 0 ? horasFaltantes : 0));
    }

    private static void darseDeBaja(Scanner scanner) {
        System.out.print("Ingrese su número de estudiante: ");
        String numeroEstudiante = scanner.nextLine();

        Estudiante estudiante = buscarEstudiantePorNumero(numeroEstudiante);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        estudiantes.remove(estudiante);
        guardarDatos();
        System.out.println("Estudiante dado de baja correctamente.");
    }

    private static Estudiante buscarEstudiantePorNumero(String numeroEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                return estudiante;
            }
        }
        return null;
    }

    private static void cargarDatos() {
        estudiantes = (ArrayList<Estudiante>) cargarObjetoDesdeArchivo(ARCHIVO_ESTUDIANTES);
        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
        eventos = (ArrayList<Evento>) cargarObjetoDesdeArchivo(ARCHIVO_EVENTOS);
        if (eventos == null) {
            eventos = new ArrayList<>();
        }
    }

    private static void guardarDatos() {
        guardarObjetoEnArchivo(estudiantes, ARCHIVO_ESTUDIANTES);
        guardarObjetoEnArchivo(eventos, ARCHIVO_EVENTOS);
    }

    private static void guardarObjetoEnArchivo(Object objeto, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object cargarObjetoDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
