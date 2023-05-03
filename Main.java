import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {

        String distritos[] = new String[43];    // en lima existen 43 distritos
        String horas[] = {"00:00 - 08:00 AM", "08:00 - 16:00 PM", "16:00 - 00:00 AM"};

        String militares[][] = new String[100][5];

        // DATOS DE EJEMPLO
        distritos[0] = "MIRAFLORES";
        distritos[1] = "SAN ISIDRO";

        militares[0][0] = "juan";
        militares[0][1] = "19/20/1222";
        militares[0][2] = "999666333";

        militares[1][0] = "libree";
        militares[1][1] = "19/20/1222";
        militares[1][2] = "999666333";

        militares[2][0] = "andres";
        militares[2][1] = "19/20/1222";
        militares[2][2] = "999666333";

        militares[3][0] = "jean";
        militares[3][1] = "19/20/1222";
        militares[3][2] = "999666333";

        militares[4][0] = "carlos";
        militares[4][1] = "19/20/1222";
        militares[4][2] = "999666333";

        militares[5][0] = "carlos";
        militares[5][1] = "19/20/1222";
        militares[5][2] = "999666333";

        militares[6][0] = "carlos";
        militares[6][1] = "19/20/1222";
        militares[6][2] = "999666333";

        militares[7][0] = "carlos";
        militares[7][1] = "19/20/1222";
        militares[7][2] = "999666333";

        /////////

        Scanner in = new Scanner(System.in);

        int retorno = 0;

        do {

            switch (menu(in)){
                case 1:
                    retorno = nuevoDistrito(in,distritos); break;
                case 2:
                    retorno = nuevoMilitar(in,militares); break;
                case 3:
                    retorno = listaMilitares(militares); break;
                case 4:
                    retorno = listaDistritos(distritos); break;
                case 5:
                    retorno = listaMilitaresTurnos(militares,distritos,horas); break;
                default:
                    break;
            }
        }
        while (retorno == 1);

        System.out.println("----- GRACIAS POR USAR NUESTRO SISTEMA-------");

    }

    public static void limpiarConsola(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int menu(Scanner in){
        limpiarConsola();
        System.out.println("");
        System.out.println(" ---- BIENVENIDO AL SISTEMA DE COMANDOS MILITAR -----");
        System.out.println("");
        System.out.println("---- MENU ------");
        System.out.println("--------------------------------------------");
        System.out.println("  1 - Nuevo Distrito");
        System.out.println("  2 - Nuevo Militar");
        //System.out.println("3 - Consulta militar por código");
        System.out.println("  3 - Lista de militares");
        System.out.println("  4 - Lista de distritos");
        //System.out.println("5 - TURNOS aleatorio");
        System.out.println("  5 - Ver Turno de Militares");
        System.out.println("  0 - SALIR");
        System.out.println("--------------------------------------------");
        System.out.print("Ingrese el número: ");

        int valorMenu = in.nextInt();

        while (valorMenu < 0 ||  valorMenu > 7){
            System.out.println("Ese número no corresponde al menu");
            System.out.print("Ingrese el número: ");
            valorMenu = in.nextInt();
        }

        limpiarConsola();
        System.out.println("");
        return valorMenu;
    }

    public static int nuevoDistrito(Scanner in, String[] distritos) {

        int index = cantDistritosRegist(distritos);

        String rpt;
        Scanner input2 = new Scanner(System.in);

        System.out.println("---- INGRESE UN NUEVO DISTRITO -----");
        System.out.println("");

        // agregar validación si se repite
        do {
            System.out.println("Nombre del distrito: " );
            String valor  = input2.nextLine();
            distritos[index] = valor;

            System.out.println("Desea ingresar otro SI[S] o NO[N]: ");
            rpt = in.next();

            index++;
        }
        while (rpt.equalsIgnoreCase("S")); // este metodo ignora si es mayuscula o minuscula

        return 1;
    }
    public static int nuevoMilitar(Scanner in, String[][] militares){
        int index = cantMilitaresRegist(militares);

        String rpt;
        Scanner inputData = new Scanner(System.in);

        System.out.println("---- INGRESE LOS DATOS DEL NUEVO MILITAR -----");

        // agregar validación si se repite
        do {
            System.out.print("Nombre completo: ");
            militares[index][0]  = inputData.nextLine();
            System.out.print("Fecha de nacimiento: ");
            militares[index][1]  = inputData.nextLine();
            System.out.print("Teléfono: ");
            militares[index][2]  = inputData.nextLine();

            System.out.println("");
            System.out.println("Desea ingresar otro SI[S] o NO[N]: ");
            rpt = in.next();
            System.out.println("");

            index++;
        }
        while (rpt.equalsIgnoreCase("S")); // este metodo ignora si es mayuscula o minuscula

        return 1;
    }
    public static int listaDistritos(String distritos[]){
        System.out.println("");
        System.out.println("---- LISTA DE TODOS LOS PUNTOS REGISTROS ----");
        System.out.println("");
        int index = 0;
        for (int i = 0; i < cantDistritosRegist(distritos); i++){
           System.out.println( (i+1)+" - "+distritos[i]);
            index = i;
        }

        if( index == 0) System.out.println("\033[3mNo hay ningún regristro agregado\033[0m"); //  \033[3m convierte en texto en italica

        System.out.println("------------------------------");
        System.out.println("Desea regresar al Menú SI[S] o NO [N] (para salir)");

        Scanner in = new Scanner(System.in);
        String rpt = in.next();
        if (rpt.equalsIgnoreCase("S")) return 1;
        else return 0;
    }
    public static int cantDistritosRegist(String distritos[]){
        int cantidad = 0;
        for (int i = 0; i < distritos.length; i++) {
            if (distritos[i] == null) { cantidad = i; break; }
        }
        return cantidad;
    }
    public static int listaMilitares(String[][] militares){
        System.out.println("---- LISTA DE TODOS LOS MILITARES REGISTROS ----");
        System.out.println("");
        int index = 0;

        for (int i = 0; i < cantMilitaresRegist(militares); i++){

            System.out.println(" - " + militares[i][0]);
            System.out.println("\t * Nac.: " + militares[i][1]);
            System.out.println("\t * Telef.: " + militares[i][2]);
            System.out.println("\t * Punto Asig.: " + militares[i][3]);
            System.out.println("\t * Hora Asig.: " + militares[i][4]);
            System.out.println("--------------------");

            index = i;
        }
        if( index == 0) System.out.println("\033[3mNo hay ningún regristro agregado\033[0m"); //  \033[3m convierte en texto en italica

        System.out.println("------------------------------");
        System.out.println("");
        System.out.println("Desea regresar al Menú SI[S] o NO [N] (para salir)");

        Scanner in = new Scanner(System.in);
        String rpt = in.next();
        if (rpt.equalsIgnoreCase("S")) return 1;
        else return 0;

    }
    public static int cantMilitaresRegist(String militares[][]){
        int cantidad = 0;
        for (int i = 0; i < militares.length; i++) {
            if (militares[i][0] == null) { cantidad = i; break; }
        }
        return cantidad;
    }
    public static void asignacionTurnos(String[][] militares, String[] distritos, String[]horas){
        int cantMilitares   = cantMilitaresRegist(militares);

        int cantDistritos  = cantDistritosRegist(distritos);
        int catHoras    = horas.length;

        /*System.out.println("---- ASIGNACIÓN DE LUGARES ----");
        System.out.println("");*/

        int index = 0;
        int[] numAleatorio = numerosAleatorios(militares);

        outerloop:  //etiqueta
        while (index < cantMilitares) {
            for (int i = 0; i < cantDistritos; i++) {
                for (int j = 0; j < catHoras; j++) {

                    if (index == cantMilitares) { break outerloop; }

                    int index2 = numAleatorio[index];
                    militares[index2][3] = distritos[i];
                    militares[index2][4] = horas[j];
                    index++;

                }
            }
        }

        /*System.out.println("Desea regresar al Menú SI[S] o NO [N] (para salir)");

        Scanner in = new Scanner(System.in);
        String rpt = in.next();
        if (rpt.equalsIgnoreCase("S")) return 1;
        else return 0;*/
    }
    public static int[] numerosAleatorios(String[][] militares){

        int x = cantMilitaresRegist(militares);
        int[] numerosPosibles = new int[x];
        int[] numerosAleatorios = new int[x];
        Random rand = new Random();

        //  array de números posibles
        for (int i = 0; i < x; i++) {
            numerosPosibles[i] = i;
        }

        // genera números aleatorios sin repetir
        for (int i = 0; i < x; i++) {
            int index = rand.nextInt(x-i);
            numerosAleatorios[i] = numerosPosibles[index]; // selecciona número aleatorio y se agrega al array
            numerosPosibles[index] = numerosPosibles[x-i-1]; // se reemplaza el número seleccionado con el último número posible restante
        }

        return  numerosAleatorios;
    }
    public static int listaMilitaresTurnos(String[][] militares, String[] distritos, String[]horas){
        asignacionTurnos(militares,distritos,horas);

        System.out.println("");
        System.out.println("---- Listado Militares y sus turnos ----");
        System.out.println("");
        int index = 0;

        for (int i = 0; i < cantMilitaresRegist(militares); i++){

            System.out.println(" - " + militares[i][0]);
            System.out.println("\t * Punto Asig.: " + militares[i][3]);
            System.out.println("\t * Hora Asig.: " + militares[i][4]);
            System.out.println("--------------------");

            index = i;
        }
        if( index == 0) System.out.println("\033[3mNo hay ningún regristro agregado\033[0m"); //  \033[3m convierte en texto en italica

        System.out.println("------------------------------");
        System.out.println("");
        System.out.println("Desea regresar al Menú SI[S] o NO [N] (para salir)");

        Scanner in = new Scanner(System.in);
        String rpt = in.next();
        if (rpt.equalsIgnoreCase("S")) return 1;
        else return 0;

    }

}