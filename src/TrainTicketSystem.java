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


    public static void main(String[] args) {

        // Pruebas iniciales del profesor (se conservan; ahora se accede a ellas desde el menú de Usuarios)
        // crearUsuario();
        // consultarUsuarios();
        // actualizarDatos();
        // consultarUsuarioPorId();

        app();

    }

    // Arrays y Matrices

    static String[][] usuarios = new String[5][5];
    static String[][] cajeros = new String[5][5];
    static String[][] trenes = new String[3][3];
    static String[][] tickets = new String[5][7];
    static String[][] ventas = new String[5][5];


    // Métodos Usuario

    static String [] encabezados ={"Id" , "Nombre" , "Apellido" , "Origen" , "Destino"};

    public static void crearUsuario(){

        for(int i = 0 ; i < encabezados.length; i++){
            System.out.println(encabezados);
        }

        for(int i= 0; i < usuarios.length ; i++){
            for(int j = 0 ; j < usuarios[i].length; j++){
                System.out.println("Ingrese dato " + j +": " );
                usuarios[i][j] = sc.nextLine();
            }
        }

    }

    public static void consultarUsuarios(){

        for(int i= 0; i < usuarios.length ; i++){
            for(int j = 0 ; j < usuarios[i].length; j++){
                System.out.println("Usuario: " + usuarios[i][j] + "\n" + "----------------------");
            }
        }
    }

    public static void consultarUsuarioPorId(){
        System.out.println("Indique el id del usuario a consultar");
        id = sc.nextInt();

        if( id >= 0 && id < usuarios.length){
            System.out.println("Datos usuario" + id + ": ");
            for(int j = 0 ; j < usuarios[id].length; j++){
                System.out.println(usuarios[id][j]);
            }
        }
    }

    public static void actualizarDatos(){

        System.out.println("Ingrese el id del usuario a actualizar");

        id = sc.nextInt();

        int i = id -1;

        System.out.println("Seleccione el dato a actualizar 1. Nombre 2. Apellido 3. Origen 4. Destino");

        int opcion= sc.nextInt();
        sc.nextLine();

        switch (opcion){
            case 1:
                System.out.println("Actualizar nombre");
                usuarios[i][1] = sc.nextLine();
                break;
            case 2:
                System.out.println("Actualizar apellido");
                usuarios[i][2] = sc.nextLine();
                break;
            case 3:
                System.out.println("Actualizar origen");
                usuarios[i][3] = sc.nextLine();
                break;
            case 4:
                System.out.println("Actualizar destino");
                usuarios[i][4] = sc.nextLine();
                break;
            default:
        }
    }

    // Eliminar usuario

    public static void eliminarUsuario(){

        System.out.println("Ingrese el id del usuario a eliminar");
        id = sc.nextInt();
        sc.nextLine();

        int pos = -1;
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i][0] != null && usuarios[i][0].equals(String.valueOf(id))){
                pos = i;
            }
        }

        if(pos == -1){
            System.out.println("No se encontró el usuario");
            return;
        }

        // No se elimina un usuario que ya tiene tickets
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][1].equals(String.valueOf(id))){
                System.out.println("No se puede eliminar: el usuario tiene tickets");
                return;
            }
        }

        for(int j = 0; j < usuarios[pos].length; j++){
            usuarios[pos][j] = null;
        }
        System.out.println("Usuario eliminado");
    }


    // Métodos del Tren

    static String [] encabezadosTren = {"# Máquina", "Modelo", "Capacidad"};

    // Registra los 3 trenes de la compañía (misma lógica de crearUsuario: llena toda la matriz)
    public static void crearTren(){

        for(int i = 0; i < trenes.length; i++){
            System.out.println("Tren " + (i + 1));
            for(int j = 0; j < trenes[i].length; j++){
                System.out.println("Ingrese " + encabezadosTren[j] + ": ");
                trenes[i][j] = sc.nextLine();
            }
        }
    }

    public static void consultarTrenes(){

        boolean hay = false;
        for(int i = 0; i < trenes.length; i++){
            if(trenes[i][0] != null){
                for(int j = 0; j < trenes[i].length; j++){
                    System.out.println(encabezadosTren[j] + ": " + trenes[i][j]);
                }
                System.out.println("----------------------");
                hay = true;
            }
        }
        if(!hay){
            System.out.println("No hay trenes registrados");
        }
    }

    public static void consultarTrenPorId(){

        System.out.println("Indique el # de máquina del tren a consultar");
        trenId = sc.nextInt();
        sc.nextLine();

        boolean encontrado = false;
        for(int i = 0; i < trenes.length; i++){
            if(trenes[i][0] != null && trenes[i][0].equals(String.valueOf(trenId))){
                for(int j = 0; j < trenes[i].length; j++){
                    System.out.println(encabezadosTren[j] + ": " + trenes[i][j]);
                }
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No se encontró el tren");
        }
    }

    public static void actualizarTren(){

        System.out.println("Ingrese el # de máquina del tren a actualizar");
        trenId = sc.nextInt();
        sc.nextLine();

        int i = -1;
        for(int k = 0; k < trenes.length; k++){
            if(trenes[k][0] != null && trenes[k][0].equals(String.valueOf(trenId))){
                i = k;
            }
        }

        if(i == -1){
            System.out.println("No se encontró el tren");
            return;
        }

        System.out.println("Seleccione el dato a actualizar 1. Modelo 2. Capacidad");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion){
            case 1:
                System.out.println("Actualizar modelo");
                trenes[i][1] = sc.nextLine();
                break;
            case 2:
                System.out.println("Actualizar capacidad");
                trenes[i][2] = sc.nextLine();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void eliminarTren(){

        System.out.println("Ingrese el # de máquina del tren a eliminar");
        trenId = sc.nextInt();
        sc.nextLine();

        int pos = -1;
        for(int i = 0; i < trenes.length; i++){
            if(trenes[i][0] != null && trenes[i][0].equals(String.valueOf(trenId))){
                pos = i;
            }
        }

        if(pos == -1){
            System.out.println("No se encontró el tren");
            return;
        }

        // No se elimina un tren que ya tiene tickets
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][2].equals(String.valueOf(trenId))){
                System.out.println("No se puede eliminar: el tren tiene tickets");
                return;
            }
        }

        for(int j = 0; j < trenes[pos].length; j++){
            trenes[pos][j] = null;
        }
        System.out.println("Tren eliminado");
    }

    //Metodos registro Cajero

    static String [] encabezadosCajero = {"Id", "Nombre", "Apellido", "Rol", "Contraseña"};

    // Registra un cajero en la primera fila libre de la matriz
    public static void registrarCajero(){

        int pos = -1;
        for(int i = 0; i < cajeros.length; i++){
            if(cajeros[i][0] == null){
                pos = i;
                break;
            }
        }

        if(pos == -1){
            System.out.println("No hay espacio para más cajeros");
            return;
        }

        System.out.println("Ingrese " + encabezadosCajero[0] + ": ");
        id = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < cajeros.length; i++){
            if(cajeros[i][0] != null && cajeros[i][0].equals(String.valueOf(id))){
                System.out.println("Ya existe un cajero con ese id");
                return;
            }
        }

        cajeros[pos][0] = String.valueOf(id);
        for(int j = 1; j < cajeros[pos].length; j++){
            System.out.println("Ingrese " + encabezadosCajero[j] + ": ");
            cajeros[pos][j] = sc.nextLine();
        }
        System.out.println("Cajero registrado");
    }

    // Metodo de Creación del ticket

    static String [] encabezadosTicket = {"Id ticket", "Id usuario", "Id tren", "Asiento", "Origen", "Destino", "Precio"};

    // Destinos de la empresa y su precio (los precios son un supuesto del ejercicio)
    static String [] rutas = {"Santa Fe", "Cisneros", "Puerto Berrio"};
    static double [] precios = {15000, 20000, 35000};

    // Registra un ticket en la primera fila libre de la matriz
    public static void crearTicket(){

        int pos = -1;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] == null){
                pos = i;
                break;
            }
        }

        if(pos == -1){
            return;
        }

        System.out.println("Ingrese el id del ticket");
        ticketId = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][0].equals(String.valueOf(ticketId))){
                System.out.println("Ya existe un ticket con ese id");
                return;
            }
        }

        // El usuario debe existir
        System.out.println("Ingrese el id del usuario");
        usuario = sc.nextInt();
        sc.nextLine();

        int posUsuario = -1;
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i][0] != null && usuarios[i][0].equals(String.valueOf(usuario))){
                posUsuario = i;
            }
        }
        if(posUsuario == -1){
            System.out.println("El usuario no existe");
            return;
        }

        // El tren debe existir
        System.out.println("Ingrese el # del tren");
        tren = sc.nextInt();
        sc.nextLine();

        int posTren = -1;
        for(int i = 0; i < trenes.length; i++){
            if(trenes[i][0] != null && trenes[i][0].equals(String.valueOf(tren))){
                posTren = i;
            }
        }
        if(posTren == -1){
            System.out.println("El tren no existe");
            return;
        }

        // Origen y destino se toman del usuario; el precio depende del destino
        origenRuta = usuarios[posUsuario][3];
        destinoRuta = usuarios[posUsuario][4];

        precio = 0;
        for(int k = 0; k < rutas.length; k++){
            if(rutas[k].equalsIgnoreCase(destinoRuta)){
                destinoRuta = rutas[k];
                precio = precios[k];
            }
        }
        if(precio == 0){
            System.out.println("El destino del usuario no es válido (Santa Fe, Cisneros o Puerto Berrio)");
            return;
        }

        // Control de capacidad: se marcan los asientos ya ocupados en ese tren y destino
        int cap = Integer.parseInt(trenes[posTren][2]);
        boolean[] ocupado = new boolean[cap + 1];
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][2].equals(String.valueOf(tren)) && tickets[i][5].equals(destinoRuta)){
                int s = Integer.parseInt(tickets[i][3]);
                if(s <= cap){
                    ocupado[s] = true;
                }
            }
        }

        boolean hayLibres = false;
        System.out.print("Asientos libres: ");
        for(int s = 1; s <= cap; s++){
            if(!ocupado[s]){
                System.out.print(s + " ");
                hayLibres = true;
            }
        }
        System.out.println();

        if(!hayLibres){
            System.out.println("El tren está lleno para ese destino");
            return;
        }

        System.out.println("Ingrese el asiento");
        int asiento = sc.nextInt();
        sc.nextLine();

        if(asiento < 1 || asiento > cap || ocupado[asiento]){
            System.out.println("Asiento no válido u ocupado");
            return;
        }
        silla = String.valueOf(asiento);

        tickets[pos][0] = String.valueOf(ticketId);
        tickets[pos][1] = String.valueOf(usuario);
        tickets[pos][2] = String.valueOf(tren);
        tickets[pos][3] = silla;
        tickets[pos][4] = origenRuta;
        tickets[pos][5] = destinoRuta;
        tickets[pos][6] = String.valueOf((int) precio);
        System.out.println("Ticket registrado. Precio: $" + (int) precio);
    }

    public static void consultarTickets(){

        boolean hay = false;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null){
                for(int j = 0; j < tickets[i].length; j++){
                    System.out.println(encabezadosTicket[j] + ": " + tickets[i][j]);
                }
                System.out.println("----------------------");
                hay = true;
            }
        }
        if(!hay){
            System.out.println("No hay tickets registrados");
        }
    }

    public static void consultarTicketPorId(){

        System.out.println("Indique el id del ticket a consultar");
        ticketId = sc.nextInt();
        sc.nextLine();

        boolean encontrado = false;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][0].equals(String.valueOf(ticketId))){
                for(int j = 0; j < tickets[i].length; j++){
                    System.out.println(encabezadosTicket[j] + ": " + tickets[i][j]);
                }
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No se encontró el ticket");
        }
    }

    public static void actualizarTicket(){

        System.out.println("Ingrese el id del ticket a actualizar");
        ticketId = sc.nextInt();
        sc.nextLine();

        int i = -1;
        for(int k = 0; k < tickets.length; k++){
            if(tickets[k][0] != null && tickets[k][0].equals(String.valueOf(ticketId))){
                i = k;
            }
        }

        if(i == -1){
            System.out.println("No se encontró el ticket");
            return;
        }

        System.out.println("Seleccione el dato a actualizar 1. Asiento 2. Tren");

        int opcion = sc.nextInt();
        sc.nextLine();

        // Se determina el tren y el asiento a validar según la opción elegida
        String trenNuevo = tickets[i][2];
        String asientoNuevo = tickets[i][3];

        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo asiento");
                asientoNuevo = String.valueOf(sc.nextInt());
                sc.nextLine();
                break;
            case 2:
                System.out.println("Ingrese el nuevo # de tren");
                trenNuevo = String.valueOf(sc.nextInt());
                sc.nextLine();
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        // El tren debe existir
        int posTren = -1;
        for(int k = 0; k < trenes.length; k++){
            if(trenes[k][0] != null && trenes[k][0].equals(trenNuevo)){
                posTren = k;
            }
        }
        if(posTren == -1){
            System.out.println("El tren no existe");
            return;
        }

        int cap = Integer.parseInt(trenes[posTren][2]);
        int asiento = Integer.parseInt(asientoNuevo);
        if(asiento < 1 || asiento > cap){
            System.out.println("El asiento debe estar entre 1 y " + cap);
            return;
        }

        // El asiento no puede estar ocupado por otro ticket del mismo tren y destino
        for(int k = 0; k < tickets.length; k++){
            if(k != i && tickets[k][0] != null
                    && tickets[k][2].equals(trenNuevo)
                    && tickets[k][3].equals(asientoNuevo)
                    && tickets[k][5].equals(tickets[i][5])){
                System.out.println("Ese asiento ya está ocupado en ese tren");
                return;
            }
        }

        tickets[i][2] = trenNuevo;
        tickets[i][3] = asientoNuevo;
        System.out.println("Ticket actualizado");
    }

    public static void eliminarTicket(){

        System.out.println("Ingrese el id del ticket a eliminar");
        ticketId = sc.nextInt();
        sc.nextLine();

        int pos = -1;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][0].equals(String.valueOf(ticketId))){
                pos = i;
            }
        }

        if(pos == -1){
            System.out.println("No se encontró el ticket");
            return;
        }

        // No se elimina un ticket que ya tiene ventas
        for(int i = 0; i < ventas.length; i++){
            if(ventas[i][0] != null && ventas[i][1].equals(String.valueOf(ticketId))){
                System.out.println("No se puede eliminar: el ticket tiene ventas");
                return;
            }
        }

        for(int j = 0; j < tickets[pos].length; j++){
            tickets[pos][j] = null;
        }
        System.out.println("Ticket eliminado");
    }

    // Registro de la venta

    static String [] encabezadosVenta = {"Id venta", "Id ticket", "Precio", "Cantidad", "Total"};

    // Registra una venta en la primera fila libre de la matriz
    public static void registrarVenta(){

        int pos = -1;
        for(int i = 0; i < ventas.length; i++){
            if(ventas[i][0] == null){
                pos = i;
                break;
            }
        }

        if(pos == -1){
            System.out.println("No hay espacio para más ventas");
            return;
        }

        System.out.println("Ingrese el id de la venta");
        ventaId = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < ventas.length; i++){
            if(ventas[i][0] != null && ventas[i][0].equals(String.valueOf(ventaId))){
                System.out.println("Ya existe una venta con ese id");
                return;
            }
        }

        System.out.println("Ingrese el id del ticket");
        ticketIdVenta = sc.nextInt();
        sc.nextLine();

        int posTicket = -1;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] != null && tickets[i][0].equals(String.valueOf(ticketIdVenta))){
                posTicket = i;
            }
        }
        if(posTicket == -1){
            System.out.println("El ticket no existe");
            return;
        }

        precioVentaUnitario = Double.parseDouble(tickets[posTicket][6]);

        System.out.println("Ingrese la cantidad");
        cantidad = sc.nextInt();
        sc.nextLine();

        if(cantidad <= 0){
            System.out.println("La cantidad debe ser mayor que 0");
            return;
        }

        totalVenta = precioVentaUnitario * cantidad;

        ventas[pos][0] = String.valueOf(ventaId);
        ventas[pos][1] = String.valueOf(ticketIdVenta);
        ventas[pos][2] = String.valueOf((int) precioVentaUnitario);
        ventas[pos][3] = String.valueOf(cantidad);
        ventas[pos][4] = String.valueOf((int) totalVenta);
        System.out.println("Venta registrada. Total: $" + (int) totalVenta);
    }

    public static void consultarVentas(){

        boolean hay = false;
        for(int i = 0; i < ventas.length; i++){
            if(ventas[i][0] != null){
                for(int j = 0; j < ventas[i].length; j++){
                    System.out.println(encabezadosVenta[j] + ": " + ventas[i][j]);
                }
                System.out.println("----------------------");
                hay = true;
            }
        }
        if(!hay){
            System.out.println("No hay ventas registradas");
        }
    }

    public static void consultarVentaPorId(){

        System.out.println("Indique el id de la venta a consultar");
        ventaId = sc.nextInt();
        sc.nextLine();

        boolean encontrado = false;
        for(int i = 0; i < ventas.length; i++){
            if(ventas[i][0] != null && ventas[i][0].equals(String.valueOf(ventaId))){
                for(int j = 0; j < ventas[i].length; j++){
                    System.out.println(encabezadosVenta[j] + ": " + ventas[i][j]);
                }
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("No se encontró la venta");
        }
    }

    public static void actualizarVenta(){

        System.out.println("Ingrese el id de la venta a actualizar");
        ventaId = sc.nextInt();
        sc.nextLine();

        int i = -1;
        for(int k = 0; k < ventas.length; k++){
            if(ventas[k][0] != null && ventas[k][0].equals(String.valueOf(ventaId))){
                i = k;
            }
        }

        if(i == -1){
            System.out.println("No se encontró la venta");
            return;
        }

        System.out.println("Ingrese la nueva cantidad");
        cantidad = sc.nextInt();
        sc.nextLine();

        if(cantidad <= 0){
            System.out.println("La cantidad debe ser mayor que 0");
            return;
        }

        // Se recalcula el total con el precio ya guardado
        precioVentaUnitario = Double.parseDouble(ventas[i][2]);
        totalVenta = precioVentaUnitario * cantidad;

        ventas[i][3] = String.valueOf(cantidad);
        ventas[i][4] = String.valueOf((int) totalVenta);
        System.out.println("Venta actualizada. Nuevo total: $" + (int) totalVenta);
    }

    public static void eliminarVenta(){

        System.out.println("Ingrese el id de la venta a eliminar");
        ventaId = sc.nextInt();
        sc.nextLine();

        int pos = -1;
        for(int i = 0; i < ventas.length; i++){
            if(ventas[i][0] != null && ventas[i][0].equals(String.valueOf(ventaId))){
                pos = i;
            }
        }

        if(pos == -1){
            System.out.println("No se encontró la venta");
            return;
        }

        for(int j = 0; j < ventas[pos].length; j++){
            ventas[pos][j] = null;
        }
        System.out.println("Venta eliminada");
    }

    // Metodo de la App

    // Cajero que inició sesión
    static String cajeroActual = "";

    // Menú inicial: registrar cajero, iniciar sesión o salir
    public static void app(){

        int opcion;
        do{
            System.out.println("\n===== FERROCARRILES DE ANTIOQUIA =====");
            System.out.println("1. Registrar cajero");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    registrarCajero();
                    break;
                case 2:
                    if(login()){
                        menuApp();
                    } else {
                        System.out.println("Demasiados intentos fallidos");
                    }
                    break;
                case 0:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    // login

    public static boolean login(){

        for(int intento = 1; intento <= 3; intento++){
            System.out.println("Ingrese el id del cajero");
            id = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese la contraseña");
            password = sc.nextLine();

            for(int i = 0; i < cajeros.length; i++){
                if(cajeros[i][0] != null && cajeros[i][0].equals(String.valueOf(id)) && cajeros[i][4].equals(password)){
                    cajeroActual = cajeros[i][1] + " " + cajeros[i][2];
                    return true;
                }
            }
            System.out.println("Credenciales incorrectas (" + intento + "/3)");
        }
        return false;
    }

    // menu de app (dashboard del cajero después de iniciar sesión)

    public static void menuApp(){

        int opcion;
        do{
            System.out.println("\n===== DASHBOARD - Cajero: " + cajeroActual + " =====");
            System.out.println("1. Usuarios");
            System.out.println("2. Trenes");
            System.out.println("3. Tickets");
            System.out.println("4. Ventas");
            System.out.println("0. Cerrar sesión");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuTren();
                    break;
                case 3:
                    menuTickets();
                    break;
                case 4:
                    menuVenta();
                    break;
                case 0:
                    System.out.println("Sesión cerrada");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    // menu de Usuario

    public static void menuUsuario(){

        int opcion;
        do{
            System.out.println("\n--- MENÚ USUARIOS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    actualizarDatos();
                    break;
                case 3:
                    consultarUsuarioPorId();
                    break;
                case 4:
                    consultarUsuarios();
                    break;
                case 5:
                    eliminarUsuario();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    // menu de Tren

    public static void menuTren(){

        int opcion;
        do{
            System.out.println("\n--- MENÚ TRENES ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    crearTren();
                    break;
                case 2:
                    actualizarTren();
                    break;
                case 3:
                    consultarTrenPorId();
                    break;
                case 4:
                    consultarTrenes();
                    break;
                case 5:
                    eliminarTren();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    // menu tickets

    public static void menuTickets(){

        int opcion;
        do{
            System.out.println("\n--- MENÚ TICKETS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    crearTicket();
                    break;
                case 2:
                    actualizarTicket();
                    break;
                case 3:
                    consultarTicketPorId();
                    break;
                case 4:
                    consultarTickets();
                    break;
                case 5:
                    eliminarTicket();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    // menu venta

    public static void menuVenta(){

        int opcion;
        do{
            System.out.println("\n--- MENÚ VENTAS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    registrarVenta();
                    break;
                case 2:
                    actualizarVenta();
                    break;
                case 3:
                    consultarVentaPorId();
                    break;
                case 4:
                    consultarVentas();
                    break;
                case 5:
                    eliminarVenta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

}