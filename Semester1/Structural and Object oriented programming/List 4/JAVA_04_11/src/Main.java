import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;
import java.util.Random;


    static Random rand = new Random(12320);
    static boolean quit = false;
    public static int HitsWithFrame = 0;
    public static int HitsWithSquares = 0;

    static class TSquareGroup {
        String[] color;
        int n;
        int[] X, Y, size;
        int[] direction;
        int[] velocity;
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

    public static void DrawSquare(TSquareGroup g, int i) {
            if (g.size[i] == 0 && Objects.equals(g.color[i], "red")) {
                setfgcolor(red);
                gotoxy(g.X[i], g.Y[i]);
                print('*');
            }
            else if (g.size[i] == 0 && Objects.equals(g.color[i], "green")) {
                setfgcolor(green);
                gotoxy(g.X[i], g.Y[i]);
                print('*');
            }
            else if (g.size[i] == 0 && Objects.equals(g.color[i], "blue")) {
                setfgcolor(blue);
                gotoxy(g.X[i], g.Y[i]);
                print('*');
            }
            else if (g.size[i] == 1 && Objects.equals(g.color[i], "red")) {
                setfgcolor(red);
                framexy(g.X[i],g.Y[i],g.X[i]+1,g.Y[i]+1);
            }
            else if (g.size[i] == 1 && Objects.equals(g.color[i], "green")) {
                setfgcolor(green);
                framexy(g.X[i],g.Y[i],g.X[i]+1,g.Y[i]+1);
            }
            else if (g.size[i] == 1 && Objects.equals(g.color[i], "blue")) {
                setfgcolor(blue);
                framexy(g.X[i],g.Y[i],g.X[i]+1,g.Y[i]+1);
            }
            setfgcolor(white);
    }

    public static void DeleteSquare(TSquareGroup g, int i) {
        if  (g.size[i] == 0) {
            gotoxy(g.X[i], g.Y[i]);
            print(' ');
        } else {
            framexyc(g.X[i],g.Y[i],g.X[i]+1,g.Y[i]+1, ' ');
        }
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

    public static void move(TSquareGroup g) {

        for (int i = 0; i < g.n; i++) {
            DeleteSquare(g, i);
            int oldX = g.X[i];
            int oldY = g.Y[i];

            switch (g.direction[i]) {
                case 0 -> {
                    if (checkcollisons(g, i)) {
                        g.X[i] = oldX;
                        HitsWithSquares++;
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


    public static void main() {
        cursor_hide();
        clrscr();
        framexy(1, 1, 120, 30);

        TSquareGroup squares = createSquares(20);

        while (!quit) {
            move(squares);
            delay(100);
            checkForQ();
            gotoxy(1,32);
            println("Hits with  squares: " + HitsWithSquares);
            println("Hits with frame: " + HitsWithFrame);
        }

        gotoxy(5, 31);
        setfgcolor(white);
    }
