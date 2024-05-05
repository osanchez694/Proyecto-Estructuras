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
/**
 *
 * 
 */
public class SistemaGestionHorasLibres {
    // Ruta del archivo donde se guardarán los datos de los estudiantes
    private static final String ARCHIVO_ESTUDIANTES = "estudiantes.dat";
    private static final int HORAS_DESEADAS = 10; // Horas deseadas por semana

    // ArrayList para almacenar los estudiantes registrados
    private static ArrayList<Estudiante> estudiantes;

    // Método para registrar un estudiante
    public static void registrarEstudiante(String nombre, String numeroEstudiante, String correoElectronico, int horasLibres) {
        cargarDatos(); // Cargar los datos existentes
        Estudiante nuevoEstudiante = new Estudiante(nombre, numeroEstudiante, correoElectronico, horasLibres);
        estudiantes.add(nuevoEstudiante);
        guardarDatos(); // Guardar los datos actualizados
        System.out.println("Estudiante registrado correctamente.");
    }
    // Método para inscribir a un estudiante
    public static void inscribirEstudiante() {
        // Solicitar al usuario los detalles del estudiante
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el número de estudiante:");
        String numeroEstudiante = scanner.nextLine();
        System.out.println("Ingrese el correo electrónico del estudiante:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese la cantidad de horas libres del estudiante:");
        int horasLibres = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        // Registrar al estudiante con los detalles proporcionados por el usuario
        registrarEstudiante(nombre, numeroEstudiante, correo, horasLibres);
    }

    // Método para obtener las horas libres de un estudiante por ID
    public static void obtenerHorasLibresPorId(String numeroEstudiante) {
        cargarDatos(); // Cargar los datos existentes

        // Buscar al estudiante por ID y mostrar sus horas libres
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                System.out.println("El estudiante con ID " + numeroEstudiante + " tiene " + estudiante.obtenerTotalHorasLibres() + " horas libres en total.");
                return;
            }
        }
        System.out.println("No se encontró ningún estudiante con el ID proporcionado.");
    }
    // Método para darse de baja como estudiante
    public static void darseDeBaja(String numeroEstudiante) {
        cargarDatos(); // Cargar los datos existentes
    
        // Buscar al estudiante por ID y eliminarlo de la lista
        Iterator<Estudiante> iterator = estudiantes.iterator();
        while (iterator.hasNext()) {
            Estudiante estudiante = iterator.next();
            if (estudiante.getNumeroEstudiante().equals(numeroEstudiante)) {
                iterator.remove();
                guardarDatos(); // Guardar los datos actualizados
                System.out.println("El estudiante con ID " + numeroEstudiante + " se dio de baja exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún estudiante con el ID proporcionado.");
    }

    // Método para guardar los datos de los estudiantes en el archivo
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

    // Método para cargar los datos de los estudiantes desde el archivo
    @SuppressWarnings("unchecked")
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
                estudiantes = new ArrayList<>(); // Si el archivo no existe, crear una nueva lista
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Cargar los datos de los estudiantes desde el archivo
        cargarDatos();

        // Crear un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Menú principal
        while (true) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Inscribirse");
            System.out.println("2. Eventos - donde puedo obtener HL");
            System.out.println("3. Eventos (CRUD)");
            System.out.println("4. Cuantas horas libres tengo?");
            System.out.println("5. Darse de baja");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada
            
            switch (opcion) {
                case 1:
                     inscribirEstudiante();
                    break;
                case 2:
                    // Implementar la funcionalidad de eventos
                    System.out.println("Funcionalidad de eventos aún no implementada.");
                    break;
                case 3:
                    // Implementar la funcionalidad de eventos (CRUD)
                    System.out.println("Funcionalidad de eventos (CRUD) aún no implementada.");
                    break;
                case 4:
                    System.out.println("Ingrese su número de estudiante:");
                    String numeroEstudiante4 = scanner.nextLine();
                    obtenerHorasLibresPorId(numeroEstudiante4);
                    break;
                case 5:
                    // Solicitar al usuaGrio el ID del estudiante para darse de baja
                    System.out.println("Ingrese el número de estudiante para darse de baja:");
                    String numeroEstudiante = scanner.nextLine();
                    darseDeBaja(numeroEstudiante);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    // Cerrar el Scanner
                    scanner.close();
                    System.exit(0); // Salir del programa
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}