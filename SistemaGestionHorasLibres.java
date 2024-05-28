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
    private static final int HORAS_DESEADAS = 10;
    private static ArrayList<Estudiante> estudiantes;
    private static ArrayList<Evento> eventos;

    public static void registrarEstudiante(String nombre, String numeroEstudiante, String correoElectronico, int horasLibres) {
        cargarDatos();
        Estudiante nuevoEstudiante = new Estudiante(nombre, numeroEstudiante, correoElectronico, horasLibres);
        estudiantes.add(nuevoEstudiante);
        guardarDatos();
        System.out.println("Estudiante registrado correctamente.");
    }

    public static void inscribirEstudiante() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el número de estudiante:");
        String numeroEstudiante = scanner.nextLine();
        System.out.println("Ingrese el correo electrónico del estudiante:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese la cantidad de horas libres del estudiante:");
        int horasLibres = scanner.nextInt();
        scanner.nextLine();
        registrarEstudiante(nombre, numeroEstudiante, correo, horasLibres);
    }

    public static void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario:");
        String nombreUsuario = scanner.nextLine();
        System.out.println("Ingrese su número de estudiante:");
        String numeroEstudiante = scanner.nextLine();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equals(nombreUsuario) && estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                System.out.println("Inicio de sesión exitoso.");
                return;
            }
        }
        System.out.println("Inicio de sesión fallido. Usuario no encontrado.");
    }

    public static void registrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su número de estudiante:");
        String numeroEstudiante = scanner.nextLine();
        System.out.println("Ingrese su correo electrónico institucional:");
        String correo = scanner.nextLine();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                System.out.println("Ya existe un usuario registrado con este número de estudiante.");
                return;
            }
        }

        System.out.println("Ingrese la cantidad de horas libres del estudiante:");
        int horasLibres = scanner.nextInt();
        scanner.nextLine();
        registrarEstudiante(nombre, numeroEstudiante, correo, horasLibres);
        System.out.println("Usuario registrado correctamente.");
    }

    public static void obtenerHorasLibresPorId(String numeroEstudiante) {
        cargarDatos();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                System.out.println("El estudiante con ID " + numeroEstudiante + " tiene " + estudiante.obtenerTotalHorasLibres() + " horas libres en total.");
                return;
            }
        }
        System.out.println("No se encontró ningún estudiante con el ID proporcionado.");
    }

    public static void darseDeBaja(String numeroEstudiante) {
        cargarDatos();

        Iterator<Estudiante> iterator = estudiantes.iterator();
        while (iterator.hasNext()) {
            Estudiante estudiante = iterator.next();
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                iterator.remove();
                guardarDatos();
                System.out.println("El estudiante con ID " + numeroEstudiante + " se dio de baja exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún estudiante con el ID proporcionado.");
    }

    public static void inscribirEnEvento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su número de estudiante:");
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

        System.out.println("Seleccione el número del evento al que desea inscribirse:");
        int numeroEvento = scanner.nextInt();
        scanner.nextLine();

        if (numeroEvento < 1 || numeroEvento > eventos.size()) {
            System.out.println("Número de evento no válido.");
            return;
        }

        Evento eventoSeleccionado = eventos.get(numeroEvento - 1);
        estudiante.inscribirEnEvento(eventoSeleccionado);
        guardarDatos();
        System.out.println("Inscripción al evento " + eventoSeleccionado.getNombre() + " realizada con éxito.");
    }

    public static void desinscribirDeEvento() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Ingrese su número de estudiante:");
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
        System.out.println((i + 1) + ". " + evento.getNombre() + " - Fecha: " + evento.getFecha() + " - Lugar: " + evento.getLugar() + " - Horas libres: " + evento.getHorasLibresOtorgadas());
    }

    System.out.println("Seleccione el número del evento del que desea desinscribirse:");
    int numeroEvento = scanner.nextInt();
    scanner.nextLine();

    if (numeroEvento < 1 || numeroEvento > eventosInscritos.size()) {
        System.out.println("Número de evento no válido.");
        return;
    }

    Evento eventoSeleccionado = eventosInscritos.get(numeroEvento - 1);
    estudiante.desinscribirDeEvento(eventoSeleccionado);
    guardarDatos();
    System.out.println("Desinscripción del evento " + eventoSeleccionado.getNombre() + " realizada con éxito.");
}

private static void cargarDatos() {
    try {
        File file = new File(ARCHIVO_ESTUDIANTES);
        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            estudiantes = (ArrayList<Estudiante>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } else {
            estudiantes = new ArrayList<>();
        }
        
        // Inicializar la lista de eventos
        mostrarEventos();  // Para cargar los eventos desde el archivo 'eventos.dat'

    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

private static void mostrarEventos() {
    try {
        File file = new File("eventos.dat");
        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            eventos = (ArrayList<Evento>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } else {
            eventos = new ArrayList<>();
        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    if (eventos.isEmpty()) {
        String[] nombresEventos = {"Cine en 4k", "Taller de Lectura", "Teatro de animales", "Charla de los Videojuegos", "Presentación de Petro"};
        String[] lugares = {"Salon L5-2", "Biblioteca", "Auditorio Mayor", "Aula 102", "Salon D1-1"};
        Random random = new Random();

        eventos = new ArrayList<>();

        System.out.println("=== Eventos ===");
        for (int i = 1; i <= 5; i++) {
            String nombreEvento = nombresEventos[random.nextInt(nombresEventos.length)];
            String lugar = lugares[random.nextInt(lugares.length)];
            int hora = random.nextInt(24);
            int minuto = random.nextInt(60);
            int dia = random.nextInt(28) + 1;
            int mes = random.nextInt(12) + 1;
            int anio = Calendar.getInstance().get(Calendar.YEAR);
            Date fecha = new GregorianCalendar(anio, mes - 1, dia, hora, minuto).getTime();
            Evento evento = new Evento(nombreEvento, lugar, fecha, 2);
            eventos.add(evento);
            System.out.println("Evento " + i + ": " + nombreEvento + " - Fecha: " + dia + "/" + mes + "/" + anio + " - Hora: " + hora + ":" + minuto + " - Lugar: " + lugar);
        }

        guardarEventos();  // Guardar los eventos recién creados en el archivo 'eventos.dat'
    }
}

    private static void guardarDatos() {
        try {
            FileOutputStream fileOut = new FileOutputStream(ARCHIVO_ESTUDIANTES);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(estudiantes);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void guardarEventos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("eventos.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(eventos);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Estudiante buscarEstudiantePorNumero(String numeroEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                return estudiante;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        cargarDatos();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Inscribirse en Evento");
            System.out.println("4. Desinscribirse de Evento");
            System.out.println("5. Cuantas horas libres tengo?");
            System.out.println("6. Darse de baja");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    inscribirEnEvento();
                    break;
                case 4:
                    desinscribirDeEvento();
                    break;
                case 5:
                    System.out.println("Ingrese su número de estudiante:");
                    String numeroEstudiante4 = scanner.nextLine();
                    obtenerHorasLibresPorId(numeroEstudiante4);
                    break;
                case 6:
                    System.out.println("Ingrese el número de estudiante para darse de baja:");
                    String numeroEstudiante = scanner.nextLine();
                    darseDeBaja(numeroEstudiante);
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}