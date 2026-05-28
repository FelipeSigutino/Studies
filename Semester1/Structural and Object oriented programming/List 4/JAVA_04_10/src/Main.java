import static java.lang.IO.println;
import static term.term.*;   //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.util.Random;

static Random rand = new Random(69);


static int GreenCount = 0;
static int RedCount = 0;
static int BlueCount = 0;


public static class TGreenXY {
    public int n;
    int [] X;
    int [] Y;
    int[] dir;
    int[] size;
}

public static class TBlueXY {
    int [] X;
    int [] Y;
    int n;
    int[] dir;
    int[] size;
}

public static class TRedXY {
    public int n;
    int [] X;
    int [] Y;
    int[] dir;
    int[] size;
}

public static class TColor {
    TBlueXY blue;
    TGreenXY green;
    TRedXY red;
}



public static TGreenXY greenXY(int GreenCount) {
    TGreenXY g = new TGreenXY();
    g.X = new int[GreenCount];
    g.Y = new int[GreenCount];
    g.dir = new int[GreenCount];
    g.size = new int[GreenCount];
    g.n = GreenCount;

    for (int i = 0; i < GreenCount; i++) {
        g.X[i] = rand.nextInt(116) + 3;
        g.Y[i] = rand.nextInt(26) + 3;
    }
    return g;
}

public static TRedXY redXY(int RedCount) {
    TRedXY r = new TRedXY();
    r.X = new int[RedCount];
    r.Y = new int[RedCount];
    r.dir = new int[RedCount];
    r.size = new int[RedCount];
    r.n = RedCount;

    for (int i = 0; i < RedCount; i++) {
        r.X[i] = rand.nextInt(116) + 3;
        r.Y[i] = rand.nextInt(26) + 3;
    }
    return r;
}

public static TBlueXY blueXY(int BlueCount) {
    TBlueXY b = new TBlueXY();
    b.X = new int[BlueCount];
    b.Y = new int[BlueCount];
    b.dir = new int[BlueCount];
    b.size = new int[BlueCount];
    b.n = BlueCount;

    for (int i = 0; i < BlueCount; i++) {
        b.X[i] = rand.nextInt(116) + 3;
        b.Y[i] = rand.nextInt(26) + 3;
    }
    return b;
}

public static TColor color(int n) {
    TColor c = new TColor();
    switch  (n) {
        case 0: {
            c.green = greenXY(GreenCount);
            break;
        }
        case 1: {
            c.red = redXY(RedCount);
            break;
        }
        case 2: {
            c.blue = blueXY(BlueCount);
            break;
        }
    }
    return c;
}

public static void DrawColor(TColor c, int i) {
    if (c.blue != null) {
        setfgcolor(blue);
        if (c.blue.size[i] == 1) {
            framexy(c.blue.X[i], c.blue.Y[i], c.blue.X[i], c.blue.Y[i]);
        }
        else{
            framexy(c.blue.X[i]-1, c.blue.Y[i]-1, c.blue.X[i], c.blue.Y[i]);
        }
    }
    if (c.green != null) {
        setfgcolor(green);
        if (c.green.size[i] == 1) {
            framexy(c.green.X[i], c.green.Y[i], c.green.X[i], c.green.Y[i]);
        }else {
            framexy(c.green.X[i] - 1, c.green.Y[i] - 1, c.green.X[i], c.green.Y[i]);
        }

    }
    if (c.red != null) {
        setfgcolor(red);
        if (c.red.size[i] == 1) {
            framexy(c.red.X[i], c.red.Y[i], c.red.X[i], c.red.Y[i]);
        }else {
            framexy(c.red.X[i] - 1, c.red.Y[i] - 1, c.red.X[i], c.red.Y[i]);
        }
    }
}

public static void DeleteColor(TColor c, int i) {
    if (c.blue != null) {
        if (c.blue.size[i] == 1) {
            framexyc(c.blue.X[i], c.blue.Y[i], c.blue.X[i], c.blue.Y[i], ' ');
        }
        else{
            framexyc(c.blue.X[i]-1, c.blue.Y[i]-1, c.blue.X[i], c.blue.Y[i], ' ');
        }
    }
    if (c.green != null) {
        if (c.green.size[i] == 1) {
            framexyc(c.green.X[i], c.green.Y[i], c.green.X[i], c.green.Y[i], ' ');
        }else {
            framexyc(c.green.X[i] - 1, c.green.Y[i] - 1, c.green.X[i], c.green.Y[i], ' ');
        }
    }
    if (c.red != null) {
        if (c.red.size[i] == 1) {
            framexyc(c.red.X[i], c.red.Y[i], c.red.X[i], c.red.Y[i], ' ');
        }else {
            framexyc(c.red.X[i] - 1, c.red.Y[i] - 1, c.red.X[i], c.red.Y[i], ' ');
        }
    }
}

public static boolean checkCollision(int x1, int y1, int size1, int x2, int y2, int size2) {
    // Axis-aligned bounding box collision
    return (x1 < x2 + size2) && (x1 + size1 > x2) &&
            (y1 < y2 + size2) && (y1 + size1 > y2);
}

public static void MoveSquare (TColor c, int i, int N, TColor allSquares) {
    int oldX = 0;
    int oldY = 0;
    int size = 1;


    if (c.red != null) {
        oldX = c.red.X[i];
        oldY = c.red.Y[i];
        size = c.red.size[i];

        switch (c.red.dir[i]) {
            case 0 -> c.red.X[i] += N;
            case 1 -> c.red.Y[i] += N;
            case 2 -> c.red.X[i] -= N;
            case 3 -> c.red.Y[i] -= N;
        }
        if (c.red.size[i] == 2) {
            if (c.red.X[i] <= 3) c.red.dir[i] = 0;
            if (c.red.X[i] >= 118) c.red.dir[i] = 2;
            if (c.red.Y[i] <= 3) c.red.dir[i] = 1;
            if (c.red.Y[i] >= 29) c.red.dir[i] = 3;
        } else{
            if (c.red.X[i] <= 3) c.red.dir[i] = 0;
            if (c.red.X[i] >= 118) c.red.dir[i] = 2;
            if (c.red.Y[i] <= 2) c.red.dir[i] = 1;
            if (c.red.Y[i] >= 30) c.red.dir[i] = 3;
        }


    }
    if (c.green != null) {
        switch (c.green.dir[i]) {
            case 0 -> c.green.X[i] += N;
            case 1 -> c.green.Y[i] += N;
            case 2 -> c.green.X[i] -= N;
            case 3 -> c.green.Y[i] -= N;
        }
        if (c.green.size[i] == 2) {
            if (c.green.X[i] <= 3) c.green.dir[i] = 0;
            if (c.green.X[i] >= 118) c.green.dir[i] = 2;
            if (c.green.Y[i] <= 3) c.green.dir[i] = 1;
            if (c.green.Y[i] >= 29) c.green.dir[i] = 3;
        } else {
            if (c.green.X[i] <= 3) c.green.dir[i] = 0;
            if (c.green.X[i] >= 118) c.green.dir[i] = 2;
            if (c.green.Y[i] <= 2) c.green.dir[i] = 1;
            if (c.green.Y[i] >= 29) c.green.dir[i] = 3;
        }

    }
    if (c.blue != null) {
        switch (c.blue.dir[i]) {
            case 0 -> c.blue.X[i] += N;
            case 1 -> c.blue.Y[i] += N;
            case 2 -> c.blue.X[i] -= N;
            case 3 -> c.blue.Y[i] -= N;
        }
        if (c.blue.size[i] == 2) {
            if (c.blue.X[i] <= 3) c.blue.dir[i] = 0;
            if (c.blue.X[i] >= 118) c.blue.dir[i] = 2;
            if (c.blue.Y[i] <= 3) c.blue.dir[i] = 1;
            if (c.blue.Y[i] >= 29) c.blue.dir[i] = 3;
        } else  {
            if (c.blue.X[i] <= 3) c.blue.dir[i] = 0;
            if (c.blue.X[i] >= 118) c.blue.dir[i] = 2;
            if (c.blue.Y[i] <= 2) c.blue.dir[i] = 1;
            if (c.blue.Y[i] >= 29) c.blue.dir[i] = 3;
        }
    }
}

public static void CheckBoundry (int[] X, int[] Y, int i){
    if (X[i] > 118) X[i] = 118;
    if (X[i] < 3) X[i] = 3;
    if (Y[i] > 28) Y[i] = 28;
    if (Y[i] < 3) Y[i] = 3;
}

public static void MoveAllSquares (TColor c, int N) {
    if  (c.blue != null) {
        for (int i = 0; i < c.blue.n; i++) {
            DeleteColor(c, i);
            MoveSquare(c, i, N, c);
            DrawColor(c,i);
        }
    }
    if (c.green != null) {
        for (int i = 0; i < c.green.n; i++) {
            DeleteColor(c, i);
            MoveSquare(c, i, N, c);
            DrawColor(c,i);
        }
    }
    if (c.red != null) {
        for (int i = 0; i < c.red.n; i++) {
            DeleteColor(c, i);
            MoveSquare(c, i, N, c);
            DrawColor(c,i);
        }
    }
}

public static void initMotion(TColor c) {
    if (c.blue != null) {
        c.blue.dir = new int[c.blue.n];
        c.blue.size = new int[c.blue.n];
        for (int i = 0; i< c.blue.n; i++) {
            c.blue.dir[i] = rand.nextInt(4);
            c.blue.size[i] = rand.nextInt(2) + 1;
        }
    }
    if (c.green != null) {
        c.green.dir = new int[c.green.n];
        c.green.size = new int[c.green.n];
        for (int k = 0; k < c.green.n; k++) {
            c.green.dir[k] = rand.nextInt(4);
            c.green.size[k] = rand.nextInt(2) + 1;
        }
    }
    if (c.red != null) {
        c.red.dir = new int[c.red.n];
        c.red.size = new int[c.red.n];
        for (int j = 0; j < c.red.n; j++) {
            c.red.dir[j] = rand.nextInt(4);
            c.red.size[j] = rand.nextInt(2) + 1;
        }
    }
}



public static void GenerateRandomColourSquares (int N) {

    for (int i = 0; i < N; i++) {
        switch(rand.nextInt(3)) {
            case 0 -> GreenCount++;
            case 1 -> RedCount++;
            case 2 -> BlueCount++;
        }

    }
}



static boolean quit = false;

public static boolean checkforq() {
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("q")) {
            quit = true;
        }
    }
    return quit;
}

// n w kodzie jest odpowiednikiem tego ile wartosci xy jest przypisanych do kwadratu jakiegos koloru

public static void main() {
    cursor_hide();
    clrscr();
    framexy(1,1,120,30);

    GenerateRandomColourSquares(20);

    TColor Green = color(0);
    TColor Red = color(1);
    TColor Blue = color(2);


    initMotion(Green);
    initMotion(Red);
    initMotion(Blue);


    while (true) {
        if (quit) {
            break;
        }
        MoveAllSquares(Green, 1);
        MoveAllSquares(Red, 1);
        MoveAllSquares(Blue, 1);


        delay(100);

        checkforq();
    }





    gotoxy(5,31);
    setfgcolor(white);
}