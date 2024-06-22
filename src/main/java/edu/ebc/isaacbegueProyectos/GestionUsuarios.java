package edu.ebc.isaacbegueProyectos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    int id;
    String nombreUsuario;
    String correoElectronico;

    Usuario(int id, String nombreUsuario, String correoElectronico) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
    }
}

public class GestionUsuarios {

    private static final String NOMBRE_ARCHIVO = "usuarios.json";
    private static List<Usuario> usuarios = new ArrayList<>();
    private static int siguienteId = 1;

    public static void main(String[] args) {
        cargarUsuarios();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int eleccion = obtenerEntradaInt(scanner);

            switch (eleccion) {
                case 1:
                    agregarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios(scanner);
                    break;
                case 3:
                    guardarUsuarios();
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private static void agregarUsuario(Scanner scanner) {
        System.out.print("Introduce el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Introduce el correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        usuarios.add(new Usuario(siguienteId++, nombreUsuario, correoElectronico));
        System.out.println("Usuario agregado exitosamente.");
    }

    private static void listarUsuarios(Scanner scanner) {
        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println(usuario.id + ". " + usuario.nombreUsuario + " (" + usuario.correoElectronico + ")");
        }

        System.out.println("1. Modificar Usuario");
        System.out.println("2. Eliminar Usuario");
        System.out.println("3. Volver");
        System.out.print("Elige una opción: ");
        int eleccion = obtenerEntradaInt(scanner);

        switch (eleccion) {
            case 1:
                modificarUsuario(scanner);
                break;
            case 2:
                eliminarUsuario(scanner);
                break;
            case 3:
                return;
            default:
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
        }
    }

    private static void modificarUsuario(Scanner scanner) {
        System.out.print("Introduce el ID del usuario a modificar: ");
        int id = obtenerEntradaInt(scanner);

        Usuario usuario = encontrarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Introduce el nuevo nombre de usuario: ");
        usuario.nombreUsuario = scanner.nextLine();
        System.out.print("Introduce el nuevo correo electrónico: ");
        usuario.correoElectronico = scanner.nextLine();
        System.out.println("Usuario actualizado exitosamente.");
    }

    private static void eliminarUsuario(Scanner scanner) {
        System.out.print("Introduce el ID del usuario a eliminar: ");
        int id = obtenerEntradaInt(scanner);

        Usuario usuario = encontrarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        usuarios.remove(usuario);
        System.out.println("Usuario eliminado exitosamente.");
    }

    private static Usuario encontrarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.id == id) {
                return usuario;
            }
        }
        return null;
    }

    private static void cargarUsuarios() {
        try (FileReader reader = new FileReader(NOMBRE_ARCHIVO)) {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            usuarios = new Gson().fromJson(reader, listType);
            if (usuarios == null) {
                usuarios = new ArrayList<>();
            } else {
                for (Usuario usuario : usuarios) {
                    if (usuario.id >= siguienteId) {
                        siguienteId = usuario.id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontraron datos de usuarios existentes. Iniciando nuevo.");
        }
    }

    private static void guardarUsuarios() {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO)) {
            new Gson().toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los usuarios.");
        }
    }

    private static int obtenerEntradaInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Por favor, introduce un número: ");
            }
        }
    }
}
