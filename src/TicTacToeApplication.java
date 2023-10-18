
import java.util.Scanner;

public class TicTacToeApplication {

    static Scanner scanner;
    static char[][]map;
    static final int MAP_SIZE=3;
    static final char EMPTY_FIELD='*';
    static final char X_FIELD='X';
    static final char O_FIELD='O';
    static final int AI_LEVEL =2;

    public static void main(String[] args) {

        init();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(checkWin(X_FIELD)){
                System.out.println("Game over. Gamer win!");
                break;
            }
            if(checkDraft()){
                System.out.println("Game draw.");
                break;
            }
            aiTurn();
            printMap();
            if(checkWin(O_FIELD)){
                System.out.println("Game over. Computer win!");
                break;
            }
            if(checkDraft()){
                System.out.println("Game draw.");
                break;
            }
        }
    }

    public static boolean checkWin(char playerField){
//        if(map[0][0]==playerField&&map[0][1]==playerField&&map[0][2]==playerField)return true;
//        if(map[1][0]==playerField&&map[1][1]==playerField&&map[1][2]==playerField)return true;
//        if(map[2][0]==playerField&&map[2][1]==playerField&&map[2][2]==playerField)return true;
//
//        if(map[0][0]==playerField&&map[1][0]==playerField&&map[2][0]==playerField)return true;
//        if(map[0][1]==playerField&&map[1][1]==playerField&&map[2][1]==playerField)return true;
//        if(map[0][2]==playerField&&map[1][2]==playerField&&map[2][2]==playerField)return true;
//
//        if(map[0][0]==playerField&&map[1][1]==playerField&&map[2][2]==playerField)return true;
//        if(map[0][2]==playerField&&map[1][1]==playerField&&map[2][0]==playerField)return true;
        for (int i = 0; i < MAP_SIZE; i++) {
            if((map[i][0]==playerField && map[i][1]==playerField &&
                    map[i][2]==playerField)||
            (map[0][i]==playerField && map[1][i]==playerField &&
                    map[2][i]==playerField))
            return true;
            if((map[0][0]==playerField && map[1][1]==playerField &&
                    map[2][2]==playerField)||
            (map[2][0]==playerField && map[1][1]==playerField &&
                    map[0][2]==playerField))
            return true;
        }
        return false;
    }
    public static boolean checkDraft(){
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if(map[i][j]==EMPTY_FIELD){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCellValid(int x, int y){
        if(x<0||y<0||x>=MAP_SIZE||y>=MAP_SIZE){
            return false;
        }
        if(map[x][y]!=EMPTY_FIELD){
            return false;
        }
        return true;
    }


    public static void aiTurn(){

        int x =-1;
        int y =-1;

        System.out.println("Computer turn");
        boolean ai_win = false;
        boolean user_win = false;
        // aiLevel = 2

        if(AI_LEVEL ==2){
            for (int i = 0; i < MAP_SIZE; i++)
            {
                for (int j = 0; j < MAP_SIZE; j++)
                {
                    if (isCellValid(i, j))
                    {
                        map[i][j] = O_FIELD;
                        if (checkWin(O_FIELD))
                        {
                            x = i;
                            y = j;
                            ai_win = true;
                        }
                        map[i][j] = EMPTY_FIELD;
                    }
                }
            }

        }

        // aiLevel = 1

        if(AI_LEVEL >0){
            if (!ai_win)
            {
                for (int i = 0; i < MAP_SIZE; i++)
                {
                    for (int j = 0; j < MAP_SIZE; j++)
                    {
                        if (isCellValid(i, j))
                        {
                            map[i][j] = X_FIELD;
                            if (checkWin(X_FIELD))
                            {
                                x = i;
                                y = j;
                                user_win = true;
                            }
                            map[i][j] = EMPTY_FIELD;
                        }
                    }
                }
            }
        }


        // aiLevel = 0
        if (!ai_win && !user_win)
        {
            do{
                if(map[1][1]==EMPTY_FIELD) {
                x=1;
                y=1;
                break;
                }

                x = (int)(Math.random() * MAP_SIZE);
                y = (int)(Math.random() * MAP_SIZE);
            }while (!isCellValid(x, y));
        }
        map[x][y] = O_FIELD;
    }
    public static void humanTurn(){
        int x,y;
        do {
            System.out.println("Gamer turn. Enter the coordinates of your move (row, column):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isCellValid(x,y));
        map[x][y]=X_FIELD;
    }


    public static void printMap(){
        for (int i = 0; i <=MAP_SIZE; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void init(){

        map=new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j]=EMPTY_FIELD;
            }
        }
        scanner=new Scanner(System.in);
    }

}
