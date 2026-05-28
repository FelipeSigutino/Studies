import static java.lang.IO.println;
import static term.term.*;   //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.util.Random;

static Random rand = new Random();


static int GreenCount = 0;
static int RedCount = 0;
static int BlueCount = 0;

public static class TPoint {
    int x;
    int y;
}

public static class TGreenXY {
    public int n;
    int [] X;
    int [] Y;
}

public static class TBlueXY {
    int [] X;
    int [] Y;
    int n;
}

public static class TRedXY {
    public int n;
    int [] X;
    int [] Y;
}

public static class TColor {
    TBlueXY blue;
    TGreenXY green;
    TRedXY red;
}

public TPoint point(int x, int y) {
    TPoint p = new TPoint();
    p.x = x;
    p.y = y;
    return p;
}

public static TGreenXY greenXY(int GreenCount) {
    TGreenXY g = new TGreenXY();
    g.X = new int[GreenCount];
    g.Y = new int[GreenCount];
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
        framexy(c.blue.X[i] - 1, c.blue.Y[i] - 1, c.blue.X[i] + 1, c.blue.Y[i] + 1);
    }
    if (c.green != null) {
        setfgcolor(green);
        framexy(c.green.X[i] - 1, c.green.Y[i] - 1, c.green.X[i] + 1, c.green.Y[i] + 1);
    }
    if (c.red != null) {
        setfgcolor(red);
        framexy(c.red.X[i] - 1, c.red.Y[i] - 1, c.red.X[i] + 1, c.red.Y[i] + 1);
    }
}

public static void DeleteColor(TColor c, int i) {
    if (c.blue != null) {
        framexyc(c.blue.X[i] - 1, c.blue.Y[i] - 1, c.blue.X[i] + 1, c.blue.Y[i] + 1, ' ');
    }
    if (c.green != null) {
        framexyc(c.green.X[i]-1, c.green.Y[i]-1, c.green.X[i] + 1, c.green.Y[i] + 1, ' ');
    }
    if (c.red != null) {
        framexyc(c.red.X[i]-1,c.red.Y[i]-1, c.red.X[i] + 1, c.red.Y[i] + 1, ' ');
    }
}

public static void MoveSquare (TColor c, int i, int N) {

    int direction = rand.nextInt(4);

    if (c.red != null) {
        switch (direction) {
            case 0 -> c.red.X[i] += N;
            case 1 -> c.red.Y[i] += N;
            case 2 -> c.red.X[i] -= N;
            case 3 -> c.red.Y[i] -= N;
        }
        CheckBoundry(c.red.X, c.red.Y, i);
    }
    if (c.green != null) {
        switch (direction) {
            case 0 -> c.green.X[i] += N;
            case 1 -> c.green.Y[i] += N;
            case 2 -> c.green.X[i] -= N;
            case 3 -> c.green.Y[i] -= N;
        }
        CheckBoundry(c.green.X, c.green.Y, i);
    }
    if (c.blue != null) {
        switch (direction) {
            case 0 -> c.blue.X[i] += N;
            case 1 -> c.blue.Y[i] += N;
            case 2 -> c.blue.X[i] -= N;
            case 3 -> c.blue.Y[i] -= N;
        }
        CheckBoundry(c.blue.X, c.blue.Y, i);
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
            MoveSquare(c, i, N);
            DrawColor(c,i);
        }
    }
    if (c.green != null) {
        for (int i = 0; i < c.green.n; i++) {
            DeleteColor(c, i);
            MoveSquare(c, i, N);
            DrawColor(c,i);
        }
    }
    if (c.red != null) {
        for (int i = 0; i < c.red.n; i++) {
            DeleteColor(c, i);
            MoveSquare(c, i, N);
            DrawColor(c,i);
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

// n w kodzie jest odpowiednikiem tego ile wartosci xy jest przypisanych do kwadratu jakiegos koloru

public static void main() {
    cursor_hide();
    clrscr();
    framexy(1,1,120,30);

    GenerateRandomColourSquares(50);

    TColor Green = color(0);
    TColor Red = color(1);
    TColor Blue = color(2);

    for (int i = 0; i < Green.green.n; i++) DrawColor(Green,i);
    for (int i = 0; i < Red.red.n; i++) DrawColor(Red,i);
    for (int i = 0; i < Blue.blue.n; i++) DrawColor(Blue,i);


    while (true) {
        for (int frame = 0; frame < 20; frame++) {
            MoveAllSquares(Green, 2);
            MoveAllSquares(Red, 2);
            MoveAllSquares(Blue, 2);
            delay(100);
        }
    }



}