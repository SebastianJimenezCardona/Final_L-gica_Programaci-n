package ejemploproyectofinal;

import java.util.Scanner;

public class TrainTicketSystem {

    // Crear las variables para las entidades
    /* Ferrocarriles de Antioquia requiere un sistema que permita asignar
     * un ticket en un tren a un usuario a través de una venta.
     * Del Usuario requiere: id, nombre , apellido, origen , destino.
     * Del cajero requiere conocer los mismos datos (id, nombre, apellido) y adicional
     * El rol y la contraseña.
     * Del tren requiere conocer: # de máquina , módelo, capacidad.
     * Del Ticket requiere conocer: Id del ticket , usuario, tren , asiento , origen , destino, precio.
     * De la venta requiere conocer: id de la venta , id del ticket, precio, cantidad , total
     * El sistema debe permitir registrar, actualizar , consultar por id , consultar todos y eliminar, para cada
     * entidad del sistema (Usuarios, trenes, tickets , ventas)
     * El sistema debe permitir navegar las opciones a través de menús
     * El sistema debe permitir registrar un cajero e iniciar sesión
     * El sistema debe permitir al cajero contar con un dashboard con los menus a las diferentes opciones.
     * después de iniciar sesión.
     * La compañia cuenta con tres trenes cada uno con capacidad de 4 pasajeros.
     * Cada tren hace un viaje de ida y vuelta, en un día , a tres destinos: Santa Fe , Cisneros y Puerto Berrio  */

    // Scanner para capturar los datos

    static Scanner sc = new Scanner(System.in);

    // Usuario tren

    static int id;
    static String nombre;
    static String apellido;
    static String origen;
    static String destino;

    //Usuario cajero

    static String rol;
    static String password;


    // Tren

    static int trenId;
    static String modelo;
    static int capacidad;

    // Ticket

    static int ticketId;
    static int usuario;
    static String origenRuta;
    static String destinoRuta;
    static String silla;
    static int tren;
    static String fecha;
    static String hora;
    static double precio;

    // Venta

    static int ventaId;
    static int ticketIdVenta;
    static int cantidad;
    static double precioVentaUnitario;
    static double totalVenta;

    // Destinos de la empresa y su precio (los precios son un supuesto del ejercicio)
    static final String[] RUTAS = {"Santa Fe", "Cisneros", "Puerto Berrio"};
    static final double[] PRECIOS = {15000, 20000, 35000};

    // Cajero que inició sesión
    static String cajeroActual = "";


    public static void main(String[] args) {

        cargarTrenesIniciales();
        menuApp();

    }

    // Arrays y Matrices
    // Cada fila es un registro. Una fila está libre cuando su primera casilla (el id) es null.

    static String[][] usuarios = new String[5][5];
    static String[][] cajeros = new String[5][5];
    static String[][] trenes = new String[3][3];
    static String[][] tickets = new String[5][7];
    static String[][] ventas = new String[5][5];

    static String [] encabezados ={"Id" , "Nombre" , "Apellido" , "Origen" , "Destino"};
    static String [] encCajeros = {"Id", "Nombre", "Apellido", "Rol", "Contraseña"};
    static String [] encTrenes = {"# Máquina", "Modelo", "Capacidad"};
    static String [] encTickets = {"Id ticket", "Id usuario", "Id tren", "Asiento", "Origen", "Destino", "Precio"};
    static String [] encVentas = {"Id venta", "Id ticket", "Precio", "Cantidad", "Total"};


    // Métodos de apoyo (lectura segura y búsqueda)

    static String leerTexto(String mensaje){
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El dato no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    static int leerEntero(String mensaje){
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    static int leerEnteroPositivo(String mensaje){
        int n;
        do {
            n = leerEntero(mensaje);
            if (n <= 0) {
                System.out.println("El número debe ser mayor que 0.");
            }
        } while (n <= 0);
        return n;
    }

    // Devuelve la posición de la fila cuyo id (columna 0) coincide, o -1 si no existe
    static int buscar(String[][] m, String idBuscado){
        for (int i = 0; i < m.length; i++) {
            if (m[i][0] != null && m[i][0].equals(idBuscado)) {
                return i;
            }
        }
        return -1;
    }

    // Devuelve la primera fila libre (null), o -1 si la matriz está llena
    static int espacioLibre(String[][] m){
        for (int i = 0; i < m.length; i++) {
            if (m[i][0] == null) {
                return i;
            }
        }
        return -1;
    }

    // Revisa si algún registro de la matriz m tiene ese valor en la columna indicada
    static boolean existeReferencia(String[][] m, int columna, String valor){
        for (int i = 0; i < m.length; i++) {
            if (m[i][0] != null && m[i][columna].equals(valor)) {
                return true;
            }
        }
        return false;
    }

    static void imprimirFila(String[] enc, String[] fila){
        for (int j = 0; j < fila.length; j++) {
            System.out.println(enc[j] + ": " + fila[j]);
        }
        System.out.println("----------------------");
    }

    static void listarTodos(String[][] m, String[] enc){
        boolean hay = false;
        System.out.println("----------------------");
        for (int i = 0; i < m.length; i++) {
            if (m[i][0] != null) {
                imprimirFila(enc, m[i]);
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay registros.");
        }
    }

    static void consultarPorId(String[][] m, String[] enc){
        id = leerEntero("Indique el id a consultar: ");
        int pos = buscar(m, String.valueOf(id));
        if (pos == -1) {
            System.out.println("No se encontró el registro.");
        } else {
            System.out.println("----------------------");
            imprimirFila(enc, m[pos]);
        }
    }

    // Elimina un registro. Si "dependiente" no es null, impide borrar
    // cuando otro registro de esa matriz usa este id en la "columna" indicada.
    static void eliminarRegistro(String[][] m, String[][] dependiente, int columna){
        id = leerEntero("Indique el id a eliminar: ");
        int pos = buscar(m, String.valueOf(id));
        if (pos == -1) {
            System.out.println("No se encontró el registro.");
            return;
        }
        if (dependiente != null && existeReferencia(dependiente, columna, String.valueOf(id))) {
            System.out.println("No se puede eliminar: hay registros que dependen de este.");
            return;
        }
        m[pos] = new String[m[pos].length];   // fila vacía: queda libre para reutilizarse
        System.out.println("Registro eliminado.");
    }


    // Métodos Usuario

    static String elegirDestino(){
        int op;
        do {
            System.out.println("Destinos: 1. Santa Fe  2. Cisneros  3. Puerto Berrio");
            op = leerEntero("Seleccione destino: ");
        } while (op < 1 || op > 3);
        return RUTAS[op - 1];
    }

    public static void crearUsuario(){

        int pos = espacioLibre(usuarios);
        if (pos == -1) {
            System.out.println("No hay espacio para más usuarios.");
            return;
        }

        id = leerEntero("Id: ");
        if (buscar(usuarios, String.valueOf(id)) != -1) {
            System.out.println("Ya existe un usuario con ese id.");
            return;
        }

        nombre = leerTexto("Nombre: ");
        apellido = leerTexto("Apellido: ");
        origen = leerTexto("Origen: ");
        destino = elegirDestino();

        usuarios[pos] = new String[]{String.valueOf(id), nombre, apellido, origen, destino};
        System.out.println("Usuario registrado.");
    }

    public static void consultarUsuarios(){
        listarTodos(usuarios, encabezados);
    }

    public static void consultarUsuarioPorId(){
        consultarPorId(usuarios, encabezados);
    }

    public static void actualizarDatos(){

        id = leerEntero("Ingrese el id del usuario a actualizar: ");
        int i = buscar(usuarios, String.valueOf(id));

        if (i == -1) {
            System.out.println("No se encontró el usuario.");
            return;
        }

        System.out.println("Seleccione el dato a actualizar 1. Nombre 2. Apellido 3. Origen 4. Destino");

        int opcion = leerEntero("Opción: ");

        switch (opcion){
            case 1:
                usuarios[i][1] = leerTexto("Actualizar nombre: ");
                break;
            case 2:
                usuarios[i][2] = leerTexto("Actualizar apellido: ");
                break;
            case 3:
                usuarios[i][3] = leerTexto("Actualizar origen: ");
                break;
            case 4:
                usuarios[i][4] = elegirDestino();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        System.out.println("Usuario actualizado.");
    }

    public static void eliminarUsuario(){
        // No se borra un usuario que ya tiene tickets (columna 1 de tickets)
        eliminarRegistro(usuarios, tickets, 1);
    }


    // Métodos del Tren
    // >>> DESARROLLADO AQUÍ: CRUD del Tren.
    //     cargarTrenesIniciales() -> carga los 3 trenes de capacidad 4 (se llama desde main)
    //     crearTren(), consultarTrenes(), consultarTrenPorId(), actualizarTren(), eliminarTren()

    // La compañía cuenta con tres trenes de capacidad 4
    static void cargarTrenesIniciales(){
        trenes[0] = new String[]{"1", "Expreso Nare", "4"};
        trenes[1] = new String[]{"2", "Expreso Cauca", "4"};
        trenes[2] = new String[]{"3", "Expreso Magdalena", "4"};
    }

    public static void crearTren(){

        int pos = espacioLibre(trenes);
        if (pos == -1) {
            System.out.println("No hay espacio para más trenes.");
            return;
        }

        trenId = leerEntero("# de máquina: ");
        if (buscar(trenes, String.valueOf(trenId)) != -1) {
            System.out.println("Ya existe un tren con ese número.");
            return;
        }

        modelo = leerTexto("Modelo: ");
        capacidad = leerEnteroPositivo("Capacidad: ");

        trenes[pos] = new String[]{String.valueOf(trenId), modelo, String.valueOf(capacidad)};
        System.out.println("Tren registrado.");
    }

    public static void consultarTrenes(){
        listarTodos(trenes, encTrenes);
    }

    public static void consultarTrenPorId(){
        consultarPorId(trenes, encTrenes);
    }

    public static void actualizarTren(){

        trenId = leerEntero("# de máquina a actualizar: ");
        int i = buscar(trenes, String.valueOf(trenId));

        if (i == -1) {
            System.out.println("No se encontró el tren.");
            return;
        }

        System.out.println("Seleccione el dato a actualizar 1. Modelo 2. Capacidad");
        int opcion = leerEntero("Opción: ");

        switch (opcion){
            case 1:
                trenes[i][1] = leerTexto("Nuevo modelo: ");
                break;
            case 2:
                trenes[i][2] = String.valueOf(leerEnteroPositivo("Nueva capacidad: "));
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        System.out.println("Tren actualizado.");
    }

    public static void eliminarTren(){
        // No se borra un tren que ya tiene tickets (columna 2 de tickets)
        eliminarRegistro(trenes, tickets, 2);
    }


    //Metodos registro Cajero
    // >>> DESARROLLADO AQUÍ: registro del cajero.
    //     registrarCajero() -> pide id, nombre, apellido, rol y contraseña; valida id repetido

    public static void registrarCajero(){

        int pos = espacioLibre(cajeros);
        if (pos == -1) {
            System.out.println("No hay espacio para más cajeros.");
            return;
        }

        id = leerEntero("Id: ");
        if (buscar(cajeros, String.valueOf(id)) != -1) {
            System.out.println("Ya existe un cajero con ese id.");
            return;
        }

        nombre = leerTexto("Nombre: ");
        apellido = leerTexto("Apellido: ");
        rol = leerTexto("Rol (ej. Vendedor, Administrador): ");
        password = leerTexto("Contraseña: ");

        cajeros[pos] = new String[]{String.valueOf(id), nombre, apellido, rol, password};
        System.out.println("Cajero registrado.");
    }


    // Metodo de Creación del ticket
    // >>> DESARROLLADO AQUÍ: creación y CRUD del ticket.
    //     crearTicket() -> valida usuario y tren, toma origen/destino del usuario y calcula el precio
    //     Apoyo: asientoOcupado(), pedirAsiento() (control de capacidad) y precioDe()
    //     consultarTickets(), consultarTicketPorId(), actualizarTicket(), eliminarTicket()

    // Un asiento está ocupado si ya existe un ticket con el mismo tren, asiento y destino


    // Muestra los asientos libres y pide uno. Devuelve null si el tren está lleno.


    // Registro de la venta


    // Metodo de la App


    // login




    // menu de app
    // >>> DESARROLLADO AQUÍ: menuApp() (registrar cajero / iniciar sesión / salir)
    //     y dashboard() (menú del cajero con Usuarios, Trenes, Tickets y Ventas)

    public static void menuApp(){
        int opcion;
        do {
            System.out.println("\n===== FERROCARRILES DE ANTIOQUIA =====");
            System.out.println("1. Registrar cajero");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion){
                case 1:
                    registrarCajero();
                    break;
                case 2:
                    if (login()) {
                        dashboard();
                    } else {
                        System.out.println("Demasiados intentos fallidos.");
                    }
                    break;
                case 0:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // Dashboard del cajero (después de iniciar sesión)

    public static void dashboard(){
        int opcion;
        do {
            System.out.println("\n===== DASHBOARD - Cajero: " + cajeroActual + " =====");
            System.out.println("1. Usuarios");
            System.out.println("2. Trenes");
            System.out.println("3. Tickets");
            System.out.println("4. Ventas");
            System.out.println("0. Cerrar sesión");
            opcion = leerEntero("Opción: ");

            switch (opcion){
                case 1: menuUsuario(); break;
                case 2: menuTren(); break;
                case 3: menuTickets(); break;
                case 4: menuVenta(); break;
                case 0: System.out.println("Sesión cerrada."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // menu de Usuario
    // >>> DESARROLLADO AQUÍ: menuUsuario() -> llama a crearUsuario, actualizarDatos,
    //     consultarUsuarioPorId, consultarUsuarios y eliminarUsuario

    public static void menuUsuario(){
        int opcion;
        do {
            System.out.println("\n--- MENÚ USUARIOS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            switch (opcion){
                case 1: crearUsuario(); break;
                case 2: actualizarDatos(); break;
                case 3: consultarUsuarioPorId(); break;
                case 4: consultarUsuarios(); break;
                case 5: eliminarUsuario(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // menu de Tren
    // >>> DESARROLLADO AQUÍ: menuTren() -> llama a crearTren, actualizarTren,
    //     consultarTrenPorId, consultarTrenes y eliminarTren

    public static void menuTren(){
        int opcion;
        do {
            System.out.println("\n--- MENÚ TRENES ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            switch (opcion){
                case 1: crearTren(); break;
                case 2: actualizarTren(); break;
                case 3: consultarTrenPorId(); break;
                case 4: consultarTrenes(); break;
                case 5: eliminarTren(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // menu tickets
    // >>> DESARROLLADO AQUÍ: menuTickets() -> llama a crearTicket, actualizarTicket,
    //     consultarTicketPorId, consultarTickets y eliminarTicket

    public static void menuTickets(){
        int opcion;
        do {
            System.out.println("\n--- MENÚ TICKETS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            switch (opcion){
                case 1: crearTicket(); break;
                case 2: actualizarTicket(); break;
                case 3: consultarTicketPorId(); break;
                case 4: consultarTickets(); break;
                case 5: eliminarTicket(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // menu venta
    // >>> DESARROLLADO AQUÍ: menuVenta() -> llama a registrarVenta, actualizarVenta,
    //     consultarVentaPorId, consultarVentas y eliminarVenta

    public static void menuVenta(){
        int opcion;
        do {
            System.out.println("\n--- MENÚ VENTAS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            switch (opcion){
                case 1: registrarVenta(); break;
                case 2: actualizarVenta(); break;
                case 3: consultarVentaPorId(); break;
                case 4: consultarVentas(); break;
                case 5: eliminarVenta(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

}