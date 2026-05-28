import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;
import java.util.Random;


static Random rand = new Random();
static boolean quit = false;
public static int HitsWithFrame = 0;
public static int HitsWithSquares = 0;

static final String RESET = "\u001B[0m";
static final String RED = "\u001B[31m";
static final String GREEN = "\u001B[32m";
static final String BLUE = "\u001B[34m";
static final String WHITE = "\u001B[37m";

static class TSquareGroup {
    String[] color;
    int n;
    int[] X, Y, size;
    int[] direction;
    int[] velocity;
}

static class TPlayer {
    int Points;
    int X;
    int Y;

}

public static TPlayer createPlayer1() {
    TPlayer p = new TPlayer();
    p.Points = 0;
    p.X = 5;
    p.Y = 15;
    return p;
}

public static TPlayer createPlayer2() {
    TPlayer p = new TPlayer();
    p.Points = 0;
    p.X = 115;
    p.Y = 15;
    return p;
}

public static TSquareGroup createSquares(int count) {
    TSquareGroup g = new TSquareGroup();
    g.n = count;
    g.color = new String[count];
    g.X = new int[count];
    g.Y = new int[count];
    g.size = new int[count];
    g.direction = new int[count];
    g.velocity = new int[count];



    for (int i = 0; i < count; i++) {
        g.X[i] = rand.nextInt(116)+2;
        g.Y[i] = rand.nextInt(26)+2;
        g.size[i] = rand.nextInt(2);
        int c = rand.nextInt(3);
        switch (c) {
            case 0 -> g.color[i] = "red";
            case 1 -> g.color[i] = "green";
            case 2 -> g.color[i] = "blue";
        }
        g.direction[i] = rand.nextInt(4);
        g.velocity[i] =  1;
    }


    return g;
}
public static void DrawPlayertoBuffer(TPlayer p) {
    buffer[p.X][p.Y] = WHITE + "#" + RESET;
}
public static void Draw2x2intoBuffer(TSquareGroup g, int i) {
    String color ="";
    switch (g.color[i]) {
        case "red": color = RED; break;
        case "green": color = GREEN;break;
        case "blue": color = BLUE;break;
    }
    for (int  k= 0; k < 2; k++) {
        for (int j = 0; j<2; j++) {
            buffer[g.X[i]+k][g.Y[i]+j] = color + "*" + RESET;
        }
    }
}

public static void DrawFrameToBuffer() {
    for (int k= 0; k < 2; k++) {
        for (int j  = 0; j<121; j++) {
            buffer[j][k*29+1] = WHITE + "*";
        }
    }
    for (int k= 0; k < 2; k++) {
        for (int j  = 1; j<30; j++) {
            buffer[k*120][j] = WHITE + "*";
        }
    }
}

public static int Timeleft=5;
public static long start = System.currentTimeMillis();


public static void DrawTimeToBuffer() {
    int difference =(int)((System.currentTimeMillis()-start)/1000);
    Timeleft = 60 - difference;
    buffer[40][0] = WHITE + "Timeleft: " + Timeleft;
    if (0 == Timeleft) {
        quit = true;
    }
}

public static void DrawScoreToBuffer1(TPlayer p) {
    buffer[0][0] = WHITE + "Score Player 1: " + p.Points;
}

public static void DrawScoreToBuffer2(TPlayer p) {
    buffer[76][0] = WHITE + "Score Player 2: " + p.Points;
}


public static void DrawSquare(TSquareGroup g, int i) {
    if (g.size[i] == 0 && Objects.equals(g.color[i], "red")) {
        ;
        buffer[g.X[i]][g.Y[i]] = RED + '*';
    }
    else if (g.size[i] == 0 && Objects.equals(g.color[i], "green")) {

        buffer[g.X[i]][g.Y[i]] = GREEN + '*';
    }
    else if (g.size[i] == 0 && Objects.equals(g.color[i], "blue")) {

        buffer[g.X[i]][g.Y[i]] =BLUE + '*';
    }
    else if (g.size[i] == 1 && Objects.equals(g.color[i], "red")) {
        Draw2x2intoBuffer(g, i);
    }
    else if (g.size[i] == 1 && Objects.equals(g.color[i], "green")) {
        Draw2x2intoBuffer(g, i);
    }
    else if (g.size[i] == 1 && Objects.equals(g.color[i], "blue")) {
        Draw2x2intoBuffer(g, i);
    }
    setfgcolor(white);
}
public static int width = 121;
public static int height = 31;
public static String[][] buffer = new String[width][height];

public static void clearBuffer(){
    for(int i = 0; i < width; i++){
        for(int j = 0; j < height; j++) buffer[i][j] = " ";
    }

}

public static void render( int width, int height){
    gotoxy(1,1);
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < height; j++){
        for (int i = 0; i < width; i++) {
            sb.append(buffer[i][j]);
        }
        sb.append(RESET).append("\n");
    }
    print(sb.toString());
}



public static boolean IsOutBound(TSquareGroup g, int i) {
    boolean squareoutofbound = false;
    if (g.size[i] == 0) {
        if (g.X[i] < 2) {g.X[i] = 2; squareoutofbound = true;}
        if (g.X[i] > 119) {g.X[i] = 119; squareoutofbound = true;}
        if (g.Y[i] < 2) {g.Y[i] = 2; squareoutofbound = true;}
        if (g.Y[i] > 29) {g.Y[i] = 29; squareoutofbound = true;}

    }
    if (g.size[i] == 1) {
        if (g.X[i] < 2) {g.X[i] = 2; squareoutofbound = true;}
        if (g.X[i] > 118) {g.X[i] = 118; squareoutofbound = true;}
        if (g.Y[i] < 2) {g.Y[i] = 2; squareoutofbound = true;}
        if (g.Y[i] > 28) {g.Y[i] = 28; squareoutofbound = true;}

    }
    return squareoutofbound;
}

public static boolean checkcollisons(TSquareGroup g, int i) {
    boolean squarecollison = false;

    for (int j = 0; j < g.n; j++) {
        if (j != i) {
            if (g.size[i] == 0) {
                switch (g.direction[i]) {

                    case 0 -> {
                        int newX = g.X[i] + g.velocity[i];
                        if (newX == g.X[j] && g.Y[i] == g.Y[j]) {
                            squarecollison = true;
                            g.direction[i] = rand.nextInt(3)+1;
                        }
                    }
                    case 1 -> {
                        int[] possible = {0,2,3};
                        int newX = g.X[i] - g.velocity[i];
                        if (newX == g.X[j] && g.Y[i] == g.Y[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                    }
                    case 2 -> {
                        int[] possible = {0,1,3};
                        int newY = g.Y[i] - g.velocity[i];
                        if (newY == g.Y[j] && g.X[i] == g.X[j]) {
                            squarecollison = true;

                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                    }
                    case 3 -> {
                        int newY = g.Y[i] + g.velocity[i];
                        if (newY == g.Y[j] && g.X[i] == g.X[j]) {
                            squarecollison = true;
                            g.direction[i] = rand.nextInt(3);
                        }
                    }
                }
            }
            if (g.size[i] == 1) {
                switch (g.direction[i]) {
                    case 0 -> {
                        int newX1 = g.X[i] + g.velocity[i];
                        if (newX1 == g.X[j] && g.Y[i] == g.Y[j]) {
                            squarecollison = true;

                            g.direction[i] = rand.nextInt(3)+1;
                        } else if (newX1 == g.X[j] && g.Y[i] + 1 == g.Y[j]) {
                            squarecollison = true;

                            g.direction[i] = rand.nextInt(3)+1;
                        }
                        if (newX1 == g.X[j] - 1 && g.Y[i] == g.Y[j]) {
                            squarecollison = true;

                            g.direction[i] = rand.nextInt(3)+1;
                        } else if (newX1 == g.X[j] - 1 && g.Y[i] + 1 == g.Y[j]) {
                            squarecollison = true;

                            g.direction[i] = rand.nextInt(3)+1;
                        }
                    }
                    case 1 -> {
                        int[] possible = {0,2,3};
                        int newX1 = g.X[i] - g.velocity[i];
                        if (newX1 == g.X[j] && g.Y[i] == g.Y[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        } else if (newX1 == g.X[j] && g.Y[i] + 1 == g.Y[j]) {
                            squarecollison = true;

                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                        if (newX1 == g.X[j] - 1 && g.Y[i] == g.Y[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        } else if (newX1 == g.X[j] - 1 && g.Y[i] + 1 == g.Y[j]) {
                            squarecollison = true;

                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                    }
                    case 2 -> {
                        int[] possible = {0,1,3};
                        int newY1 = g.Y[i] - g.velocity[i];
                        if (newY1 == g.Y[j] && g.X[i] == g.X[j]) {
                            squarecollison = true;

                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        } else if (newY1 == g.Y[j] && g.X[i] + 1 == g.X[j]) {
                            squarecollison = true;

                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }

                        else if (newY1 == g.Y[j] - 1 && g.X[i] == g.X[j]) {
                            squarecollison = true;

                            g.direction[i] = rand.nextInt(3);
                        } else if (newY1 == g.Y[j] - 1 && g.X[i] + 1 == g.X[j]) {
                            squarecollison = true;

                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                    }
                    case 3 -> {
                        int[] possible = {0,1,2};
                        int newY1 = g.Y[i] + g.velocity[i];
                        if (newY1 == g.Y[j] && g.X[i] == g.X[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        } else if (newY1 == g.Y[j] && g.X[i] + 1 == g.X[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                        else if (newY1 == g.Y[j] - 1 && g.X[i] == g.X[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        } else if (newY1 == g.Y[j] - 1 && g.X[i] + 1 == g.X[j]) {
                            squarecollison = true;
                            g.direction[i] = possible[rand.nextInt(possible.length)];
                        }
                    }
                }
            }
        }
    }

    return squarecollison;
}

public static void removeSquare(TSquareGroup g, int index) {
    for (int i = index; i < g.n - 1; i++) {
        g.X[i] = g.X[i+1];
        g.Y[i] = g.Y[i+1];
        g.size[i] = g.size[i+1];
        g.color[i] = g.color[i+1];
        g.direction[i] = g.direction[i+1];
        g.velocity[i] = g.velocity[i+1];
    }
    g.n--;
}

public static void movePlayer1(TPlayer p,TSquareGroup g) {
    if (keypressed()) {
        String key = readkeystr();
        switch (key) {
            case "w": p.Y--;
                break;
            case "s": p.Y++;
                break;
            case "a": p.X--;
                break;
            case "d": p.X++;
                break;
        }
    }

    for (int i = 0; i < g.n; i++) {

        boolean hit = false;

        if (g.size[i] == 0) {
            if (p.X == g.X[i] && p.Y == g.Y[i]) {
                p.Points = p.Points + 4;
                hit = true;
            }
        }

        else if (g.size[i] == 1) {
            if (
                    (p.X == g.X[i]     && p.Y == g.Y[i])     ||
                            (p.X == g.X[i] + 1 && p.Y == g.Y[i])     ||
                            (p.X == g.X[i]     && p.Y == g.Y[i] + 1) ||
                            (p.X == g.X[i] + 1 && p.Y == g.Y[i] + 1)
            ) {
                p.Points = p.Points + 1;
                hit = true;
            }
        }

        if (hit) {
            sound(600,1);
            removeSquare(g, i);
            i--;
        }
    }
}

public static void movePlayer2(TPlayer p,TSquareGroup g) {
    if (keypressed()) {
        String key = readkeystr();
        switch (key) {
            case "arrow_up": p.Y--;
            break;
            case "arrow_dn": p.Y++;
            break;
            case "arrow_lt": p.X--;
            break;
            case "arrow_rt": p.X++;
        }
    }

    for (int i = 0; i < g.n; i++) {

        boolean hit = false;

        if (g.size[i] == 0) {
            if (p.X == g.X[i] && p.Y == g.Y[i]) {
                p.Points = p.Points + 4;
                hit = true;
            }
        }

        else if (g.size[i] == 1) {
            if (
                    (p.X == g.X[i]     && p.Y == g.Y[i])     ||
                            (p.X == g.X[i] + 1 && p.Y == g.Y[i])     ||
                            (p.X == g.X[i]     && p.Y == g.Y[i] + 1) ||
                            (p.X == g.X[i] + 1 && p.Y == g.Y[i] + 1)
            ) {
                p.Points = p.Points + 1;
                hit = true;
            }
        }

        if (hit) {
            sound(800,1);
            removeSquare(g, i);
            i--;
        }
    }
}

public static void move(TSquareGroup g) {

    for (int i = 0; i < g.n; i++) {
        int oldX = g.X[i];
        int oldY = g.Y[i];

        switch (g.direction[i]) {
            case 0 -> {
                if (checkcollisons(g, i)) {
                    g.X[i] = oldX;
                    HitsWithSquares++;
                    sound(400,1);
                } else {
                    g.X[i] += g.velocity[i];
                }
                if (IsOutBound(g, i)) {
                    g.direction[i] = 1;
                    HitsWithFrame = HitsWithFrame + 1;
                }

            }
            case 1 -> {
                if (checkcollisons(g, i)) {
                    g.X[i] = oldX;
                    HitsWithSquares++;
                    sound(400,1);
                } else {
                    g.X[i] -= g.velocity[i];
                }

                if (IsOutBound(g, i)) {
                    g.direction[i] = 0;
                    HitsWithFrame = HitsWithFrame + 1;
                }
            }
            case 2 -> {
                if (checkcollisons(g, i)) {
                    g.Y[i] = oldY;
                    HitsWithSquares++;
                    sound(400,1);
                } else {
                    g.Y[i] += g.velocity[i];
                }


                if (IsOutBound(g, i)) {
                    g.direction[i] = 3;
                    HitsWithFrame = HitsWithFrame + 1;
                }
            }
            case 3 -> {
                if (checkcollisons(g, i)) {
                    g.Y[i] = oldY;
                    HitsWithSquares++;
                    sound(400,1);
                } else {
                    g.Y[i] -= g.velocity[i];
                }

                if (IsOutBound(g, i)) {
                    g.direction[i] = 2;
                    HitsWithFrame = HitsWithFrame + 1;
                }
            }
        }
        IsOutBound(g, i);
        DrawSquare(g, i);
    }
}


public static boolean checkForQ() {
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("q")) quit = true;
    }
    return quit;
}


public static boolean WinCond = false;
public static boolean areThereSquares(TSquareGroup g) {
    if (g.n == 0) {
        quit = true;
        WinCond = true;
    }

    return quit;
}


public static void main() {
    cursor_hide();
    clrscr();
    framexy(1, 1, 120, 30);

    TSquareGroup squares = createSquares(1);
    TPlayer Player1 = createPlayer1();
    TPlayer Player2 = createPlayer2();

    while (!quit) {
        clearBuffer();
        move(squares);
        for (int i = 0; i < 5; i++) {
            movePlayer1(Player1, squares);
            movePlayer2(Player2, squares);
            delay(7);
        }

        DrawTimeToBuffer();
        DrawFrameToBuffer();

        DrawPlayertoBuffer(Player2);
        DrawPlayertoBuffer(Player1);
        DrawScoreToBuffer1(Player1);
        DrawScoreToBuffer2(Player2);
        render(121,31);

        checkForQ();
        gotoxy(1,32);
        println("Hits with  squares: " + HitsWithSquares);
        println("Hits with frame: " + HitsWithFrame);

        areThereSquares(squares);

    }
    clrscr();
    framexy(1, 1, 120, 30);

    if (WinCond) {
        if (Player1.Points>Player2.Points) {
            framexy(56, 13, 77, 17);
            gotoxy(60,15);
            print("Player 1 Wins");
        } else {
            framexy(56, 13, 77, 17);
            gotoxy(60,15);
            print("Player 2 Wins");
        }

    } else {
        framexy(56, 13, 75, 17);
        gotoxy(60,15);
        print("YOU LOOSE :(");
    }
    gotoxy(5, 31);
    setfgcolor(white);
}