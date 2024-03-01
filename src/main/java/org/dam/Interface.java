package org.dam;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class Interface {

    public static void menu() throws InterruptedException {

        System.out.println("|============================={ Fútbol Freak Manager }==============================|");
        System.out.println("|");
        System.out.println("|        1. Gestionar Ligas");
        System.out.println("|        2. Gestionar Equipos");
        System.out.println("|        3. Gestionar Partidos");
        System.out.println("|        4. Comprobar conexión a la base de datos");
        System.out.println("|");
        System.out.println("|   <0> Salir");
        System.out.println("|");
        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.print("|> "); Scanner input = new Scanner(System.in); int option = input.nextInt();

        switch(option){
            case 1:

                menu_ligas();

                break;

            case 2:

                menu_equipos();

                break;

            case 3:

                menu_partidos();

                break;

            case 4:

                check_connection();

                break;

            case 0:

                System.out.println("|---------------------------------------------------------------------------------|");

                exit(0);

                break;
            default:

                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.println("| Opción no válida.");

                menu();

                break;
        }

    }

    public static void menu_ligas() throws InterruptedException {

        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.println("|  Gestión Ligas  |");
        System.out.println("|******************");
        System.out.println("|");
        System.out.println("|        1. Crear Liga");
        System.out.println("|        2. Modificar Liga");
        System.out.println("|        3. Eliminar Liga");
        System.out.println("|        4. Listar Ligas");
        System.out.println("|");
        System.out.println("|   <0> Volver");
        System.out.println("|");
        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.print("|> "); Scanner input = new Scanner(System.in); int option = input.nextInt();

        switch(option){
            case 1:

                System.out.println("|---------------------------------------------------------------------------------|");

                Scanner teclado = new Scanner(System.in);
                System.out.print("|Nombre de la Liga                      |> ");
                String nombre = teclado.nextLine();
                System.out.print("|Fecha de inicio de liga (yyyy-mm-dd)   |> ");
                Date fechaInicio = Date.valueOf(teclado.nextLine());
                System.out.print("|Fecha final de la liga (yyyy-mm-dd)    |> ");
                Date fechaFin = Date.valueOf(teclado.nextLine());

                LigaService ligaService = new LigaService();
                ligaService.crearLiga(nombre, fechaInicio, fechaFin);

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| Creando liga");

                for(int i=0; i<3; i++){
                    System.out.print(".");
                    sleep(1000);
                }

                System.out.print("| OK\n");

                menu_ligas();

                break;

            case 2:

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| ID de liga a modificar: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("| Nuevo nombre de liga: ");
                nombre = input.nextLine();
                System.out.print("| Fecha de inicio (yyyy-mm-dd): ");
                fechaInicio = Date.valueOf(input.nextLine());
                System.out.print("| Fecha de finalización Liga (yyyy-mm-dd): ");
                fechaFin = Date.valueOf(input.nextLine());

                ligaService = new LigaService();
                ligaService.modificarLiga(id, nombre, fechaInicio, fechaFin);

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| Modificando liga");

                for(int i=0; i<3; i++){
                    System.out.print(".");
                    sleep(1000);
                }

                System.out.print("| OK\n");

                menu_ligas();

                break;

            case 3:

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| ID de liga a eliminar: ");
                id = input.nextInt();

                ligaService = new LigaService();
                ligaService.eliminarLiga(id);

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| Eliminando liga");

                for(int i=0; i<3; i++){
                    System.out.print(".");
                    sleep(1000);
                }

                System.out.print("| OK\n");

                menu_ligas();

                break;

            case 4:

                List<Liga> ligas = LigaService.listarLigas();
                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.printf("| %-4s| %-20s| %-25s| %-25s|\n", "ID", "Nombre", "Fecha Inicio", "Fecha Fin");
                System.out.println("|---------------------------------------------------------------------------------|");
                for (Liga liga : ligas) {
                    System.out.printf("| %-4d| %-20s| %-25s| %-25s|\n", liga.getId(), liga.getNombre(), liga.getFechaInicio(), liga.getFechaFin());
                }

                menu_ligas();

                break;

            case 0:

                menu();

                break;
            default:

                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.println("| Opción no válida.");

                menu_ligas();

                break;
        }

    }

    public static void menu_equipos() throws InterruptedException {

        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.println("|  Gestión Equipos  |");
        System.out.println("|******************");
        System.out.println("|");
        System.out.println("|        1. Crear Equipo");
        System.out.println("|        2. Modificar Equipo");
        System.out.println("|        3. Eliminar Equipo");
        System.out.println("|        4. Listar Equipos");
        System.out.println("|");
        System.out.println("|   <0> Volver");
        System.out.println("|");
        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.print("|> "); Scanner input = new Scanner(System.in); int option = input.nextInt();

        switch(option){
            case 1:

                System.out.println("|---------------------------------------------------------------------------------|");

                Scanner teclado = new Scanner(System.in);
                System.out.print("|Nombre del equipo   |> ");
                String nombre = teclado.nextLine();
                System.out.print("|Ciudad del equipo   |> ");
                String ciudad = teclado.nextLine();
                System.out.print("|ID de la liga       |> ");
                int idLiga = teclado.nextInt();

                EquipoService equipoService = new EquipoService();
                equipoService.crearEquipo(nombre, ciudad, idLiga);

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| Creando equipo");

                for(int i=0; i<3; i++){
                    System.out.print(".");
                    sleep(1000);
                }

                System.out.print("| OK\n");

                menu_equipos();

                break;

            case 2:

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| ID de equipo a modificar: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("| Nuevo nombre del equipo: ");
                nombre = input.nextLine();
                System.out.print("| Nuevo ID de la liga: ");
                idLiga = input.nextInt();

                equipoService = new EquipoService();
                equipoService.modificarEquipo(id, nombre, idLiga);

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| Modificando equipo");

                for(int i=0; i<3; i++){
                    System.out.print(".");
                    sleep(1000);
                }

                System.out.print("| OK\n");

                menu_equipos();

                break;

            case 3:

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| ID de equipo a eliminar: ");
                id = input.nextInt();

                equipoService = new EquipoService();
                equipoService.eliminarEquipo(id);

                System.out.println("|---------------------------------------------------------------------------------|");

                System.out.print("| Eliminando equipo");

                for(int i=0; i<3; i++){
                    System.out.print(".");
                    sleep(1000);
                }

                System.out.print("| OK\n");

                menu_equipos();

                break;

            case 4:

                List<Equipo> equipos = EquipoService.listarEquipos();
                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.printf("| %-4s| %-15s| %-10s| %-23s| %-20s|\n", "ID", "Nombre", "Ciudad", "Fecha Creación", "Liga");
                System.out.println("|---------------------------------------------------------------------------------|");
                for (Equipo equipo : equipos) {
                    Liga liga = equipo.getLiga();
                    String ligaNombre = liga != null ? liga.getNombre() : "N/A";
                    System.out.printf("| %-4d| %-15s| %-10s| %-23s| %-20s|\n", equipo.getId(), equipo.getNombre(), equipo.getCiudad(), equipo.getFechaCreacion(), ligaNombre);
                }

                menu_equipos();

                break;

            case 0:

                menu();

                break;
            default:

                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.println("| Opción no válida.");

                menu_equipos();

                break;
        }

    }

    public static void menu_partidos() throws InterruptedException {

        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.println("|  Gestión Partidos  |");
        System.out.println("|******************");
        System.out.println("|");
        System.out.println("|        1. Crear Partido");
        System.out.println("|        2. Modificar Partido (No Funciona)");
        System.out.println("|        3. Anular Partido");
        System.out.println("|        4. Consultar Partidos");
        System.out.println("|        5. Consultar Partidos por Liga");
        System.out.println("|");
        System.out.println("|   <0> Volver");
        System.out.println("|");
        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.print("|> "); Scanner input = new Scanner(System.in); int option = input.nextInt();
        Scanner teclado = new Scanner(System.in);

        switch(option){
            case 1:
                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.print("| Fecha del partido (yyyy-mm-dd) |> ");
                Date fechaPartido = Date.valueOf(teclado.nextLine());
                System.out.print("| Goles del equipo local         |> ");
                int golesEquipoLocal = teclado.nextInt();
                System.out.print("| Goles del equipo visitante     |> ");
                int golesEquipoVisitante = teclado.nextInt();
                System.out.print("| ID del equipo local            |> ");
                int idEquipoLocal = teclado.nextInt();
                System.out.print("| ID del equipo visitante        |> ");
                int idEquipoVisitante = teclado.nextInt();
                System.out.print("| ID de la liga                  |> ");
                int idLiga = teclado.nextInt();

                PartidoService partidoService = new PartidoService();
                partidoService.crearPartido(fechaPartido, golesEquipoLocal, golesEquipoVisitante, idEquipoLocal, idEquipoVisitante, idLiga);

                System.out.println("| Creando partido...");

                menu_partidos();

                break;

            case 2:
                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.print("| ID del partido a modificar      |> ");
                int id = teclado.nextInt();
                System.out.print("| Nueva fecha del partido (yyyy-mm-dd)   |> ");
                fechaPartido = Date.valueOf(teclado.nextLine());
                System.out.print("| Nuevos goles del equipo local         |> ");
                golesEquipoLocal = teclado.nextInt();
                System.out.print("| Nuevos goles del equipo visitante     |> ");
                golesEquipoVisitante = teclado.nextInt();
                System.out.print("| Nuevo ID del equipo local            |> ");
                idEquipoLocal = teclado.nextInt();
                System.out.print("| Nuevo ID del equipo visitante        |> ");
                idEquipoVisitante = teclado.nextInt();
                System.out.print("| Nuevo ID de la liga                  |> ");
                idLiga = teclado.nextInt();

                partidoService = new PartidoService();
                partidoService.modificarPartido(id, fechaPartido, golesEquipoLocal, golesEquipoVisitante, idEquipoLocal, idEquipoVisitante, idLiga);

                System.out.println("| Modificando partido...");

                menu_partidos();

                break;

            case 3:
                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.print("| ID del partido a eliminar      |> "); id = teclado.nextInt();
                System.out.println("|---------------------------------------------------------------------------------|");

                partidoService = new PartidoService();
                partidoService.eliminarPartido(id);

                System.out.println("| Anulando partido...");

                menu_partidos();

                break;

            case 4:

                partidoService = new PartidoService();
                List<Partido> partidos = partidoService.listarPartidos();

                System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
                System.out.printf("| %-4s| %-25s| %-25s| %-25s| %-25s| %-25s| %-25s|\n", "ID", "Fecha Partido", "Goles Equipo Local", "Goles Equipo Visitante", "ID Equipo Local", "ID Equipo Visitante", "ID Liga");
                System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

                for (Partido partido : partidos) {
                    Equipo equipoLocal = partido.getIdEquipoLocal();
                    Equipo equipoVisitante = partido.getIdEquipoVisitante();
                    Liga liga = partido.getIdLiga();
                    String equipoLocalNombre = equipoLocal != null ? equipoLocal.getNombre() : "N/A";
                    String equipoVisitanteNombre = equipoVisitante != null ? equipoVisitante.getNombre() : "N/A";
                    String ligaNombre = liga != null ? liga.getNombre() : "N/A";
                    System.out.printf("| %-4d| %-25s| %-25d| %-25d| %-25s| %-25s| %-25s|\n", partido.getId(), partido.getFechaPartido(), partido.getGolesEquipoLocal(), partido.getGolesEquipoVisitante(), equipoLocalNombre, equipoVisitanteNombre, ligaNombre);
                }

                System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

                menu_partidos();

                break;

            case 5:

                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.print("| ID de la liga a consultar      |> "); id = teclado.nextInt();
                System.out.println("|---------------------------------------------------------------------------------|");

                partidoService = new PartidoService();
                partidos = partidoService.listarPartidosPorLiga(id);

                System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
                System.out.printf("| %-4s| %-25s| %-25s| %-25s| %-25s| %-25s| %-25s|\n", "ID", "Fecha Partido", "Goles Equipo Local", "Goles Equipo Visitante", "ID Equipo Local", "ID Equipo Visitante", "ID Liga");
                System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

                for (Partido partido : partidos) {
                    Equipo equipoLocal = partido.getIdEquipoLocal();
                    Equipo equipoVisitante = partido.getIdEquipoVisitante();
                    Liga liga = partido.getIdLiga();
                    int equipoLocalId = equipoLocal != null ? equipoLocal.getId() : -1;
                    int equipoVisitanteId = equipoVisitante != null ? equipoVisitante.getId() : -1;
                    int ligaId = liga != null ? liga.getId() : -1;
                    System.out.printf("| %-4d| %-25s| %-25d| %-25d| %-25d| %-25d| %-25d|\n", partido.getId(), partido.getFechaPartido(), partido.getGolesEquipoLocal(), partido.getGolesEquipoVisitante(), equipoLocalId, equipoVisitanteId, ligaId);
                }

                System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

                menu_partidos();

                break;

            case 0:

                menu();

                break;

            default:

                System.out.println("|---------------------------------------------------------------------------------|");
                System.out.println("| Opción no válida.");

                menu_partidos();

                break;
        }

    }

    public static void check_connection(){

        System.out.println("|---------------------------------------------------------------------------------|");
        System.out.println("|");
        System.out.println("| Comprobando conexión a la base de datos...");
        System.out.println("|");

    }

}
