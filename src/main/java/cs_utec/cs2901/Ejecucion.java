package cs_utec.cs2901;

import java.util.Scanner;

public class Ejecucion{

    public static void correrJuego(){
        Buscaminas juego = new Buscaminas();
        juego.generarMinas(25);
        juego.actualizarMatriz();
        Scanner scan = new Scanner(System.in);

        int x, y;
        System.out.print("Ingresar coordenada en X: ");
        x = scan.nextInt();
        System.out.print("Ingresar coordenada en Y: ");
        y = scan.nextInt();

        if(juego.obtenerCasilla(x, y).equals("   ")){
            juego.generarMinas(1);
            juego.field[x][y] = "   ";
        }

        juego.inicializar(x,y);
        juego.detectarMinas();
        juego.actualizarMatriz();

        while(true){
            if(juego.juegoFinalizado() && juego.juegoGanado()){
                System.out.println("¡Has ganado!");
                juego.mostrarTodasMinas();
                break;
            }else if(juego.juegoFinalizado()){
                juego.mostrarTodasMinas();
                break;
            }else if(!juego.juegoFinalizado()){
                System.out.print("Ingresar coordenada en X: ");
                y = scan.nextInt();
                System.out.print("Ingresar coordenada en Y: ");
                x = scan.nextInt();
                juego.turnoJugador(x,y);
                juego.haGanado();
                juego.detectarMinas();
                juego.actualizarMatriz();
            }

        }
    }
}