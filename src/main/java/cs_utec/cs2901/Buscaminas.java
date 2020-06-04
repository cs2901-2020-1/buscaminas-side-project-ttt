package cs_utec.cs2901;

public class Buscaminas{
    public String[][] field = new String[10][10];
    public String[][] display = new String[10][10];
    public Boolean isDone = false;
    public Boolean isWin = false;

    private final String unknown = "   ";
    private final String mine = " * ";
    private final String empty = "   ";

    public Buscaminas(){
        int row = 0;
        int column = 0;

        for(int x = 0; x < field.length; x++){
            for(int y = 0; y < field[0].length; y++){
                if((x == 0 || x == field.length - 1)||(y == 0 || y == field[0].length - 1)){
                    field[x][y] = empty;
                    display[x][y] = empty;
                } else{
                    field[x][y] = unknown;
                    display[x][y] = unknown;
                }
            }
        }
    }

    public static void imprimirBuscaminas(String[][] str){
        for(int x = 1; x < str.length - 1; x++){
            for(int y = 0; y < str[0].length ; y++){
                if(y > 0)
                    System.out.print("|");
                else
                    System.out.println("");
                System.out.print(str[x][y]);
            }
        }
    }

    public void actualizarMatriz(){
        imprimirBuscaminas(display);
        System.out.println("");
    }

    public void generarMinas(int n){
        for(int m = 0; m < n; m++){
            while(true){
                int x, y;
                x = (int)((field.length-1)*Math.random());
                y = (int)((field[0].length-1)*Math.random());

                if(x >= 0 && x <= (field.length-1)){
                    if(y >= 0 && y <= (field[0].length-1)){
                        if(!field[x][y].equals(mine)){
                            field[x][y] = mine;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void inicializar(int x, int y){
        for(int i = (x - 1); i <= (x + 1); i++){
            for(int j = (y - 1); j <= (y + 1); j++){
                if(field[i][j].equals(unknown)){
                    display[i][j] = empty;
                    field[i][j] = empty;
                }
            }
        }
    }

    public String obtenerCasilla(int x, int y){
        return field[x][y];
    }

    public void detectarMinas(){
        for(int x = 1; x < display.length - 2; x++){
            for(int y = 1; y < display.length - 2; y++){
                if(field[x][y].equals(empty)){
                    int nums = 0;
                    for(int i = (x - 1); i <= (x + 1); i++){
                        for(int j = (y - 1); j <= (y + 1); j++){
                            if(field[i][j].equals(mine))
                                nums++;
                        }
                    }
                    display[x][y] = " " + nums + " ";
                }
            }
        }
    }

    public void turnoJugador(int x, int y){
        if(field[x][y].equals(unknown)){
            isDone = false;
            display[x][y] = empty;
            field[x][y] = empty;
        }else if(field[x][y].equals(mine)){
            isDone = true;
            isWin = false;
            System.out.println("¡Has perdido! :(");
        }else if(display[x][y].equals(empty) && field[x][y].equals(empty)){
            isDone = false;
            System.out.println("¡Has ganado! :)");
        }
    }

    public void haGanado(){
        int tile = 0;
        for (String[] strings : field) {
            for (int j = 0; j < field[0].length; j++) {
                if (strings[j].equals(unknown))
                    tile++;
            }
        }
        if(tile != 0)
            isWin = false;
        else{
            isWin = true;
            isDone = true;
        }
    }

    public Boolean juegoFinalizado(){
        return isDone;
    }

    public Boolean juegoGanado(){
        return isWin;
    }

    public void mostrarTodasMinas(){
        imprimirBuscaminas(field);
    }

}