//Amilkar Jhair De Luna Palacios
//Ejercicio 4- Curso de Java
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
    private Map<String, String> contacts;
    private static final String FILE_NAME = "addressBook.txt";

    public AddressBook() {
        this.contacts = new HashMap<>();
        load();
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                contacts.put(parts[0], parts[1]);
            }
        } catch (IOException e) {

            System.err.println("Error al cargar la agenda telefónica: " + e.getMessage());
        }
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar la agenda telefónica: " + e.getMessage());
        }
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número telefónico: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Ingrese el nombre del contacto: ");
        String contactName = scanner.nextLine();
        contacts.put(phoneNumber, contactName);
        save();
        System.out.println("Contacto creado exitosamente.");
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número telefónico a eliminar: ");
        String phoneNumber = scanner.nextLine();
        if (contacts.containsKey(phoneNumber)) {
            contacts.remove(phoneNumber);
            save();
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("El número telefónico no existe en la agenda.");
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Crear nuevo contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    addressBook.create();
                    break;
                case 3:
                    addressBook.delete();
                    break;
                case 4:
                    addressBook.save(); // Guardar antes de salir.
                    System.out.println("Gracias, nos vemos");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}