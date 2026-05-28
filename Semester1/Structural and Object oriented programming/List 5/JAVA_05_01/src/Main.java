import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;
import java.util.Random;

Random rand =  new Random(12345);

 int x = 60;
 int y = 15;
 int xd = 1;
 int yd = 1;
 int yp1 = 12;
 int yp2 = 12;
 int scoreleft = 0;
 int scoreright = 0;

 int movementX = rand.nextInt(2)*2 -1;
 int movementY = rand.nextInt(2)*2 -1;


 boolean quit = false;
 boolean checkForQ() {
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("q")) quit = true;
    }
    return quit;
}

void DeleteSideFrame() {
     for (int i = 0; i < 2; i++) {
         for (int j = 2; j < 30; j++) {
             gotoxy(1 + i * 119, j );
             print(" ");
         }
     }
}

char[][] CreateBuffer() {
     char[][] buffer = new char[121][28];
     for (int i = 0; i < 120; i++) {
         for (int j = 0; j < 28; j++) {
             buffer[i][j] = ' ';
         }
     }
     return buffer;
}

void DrawBall(char[][] buffer, int x,  int y) {
     buffer[x][y-2] = '*';
}



void DrawBorder() {
     for (int i = 0; i <= 27; i++) {
         gotoxy(60, 2+i);
         print("|");
     }
}

void DrawScoreRight() {

}

void DrawScoreLeft() {

}

void DrawPaddleLeft(char[][] buffer, int yp1) {
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 7; j++) {
                int px = i;
                int py = yp1 + j;
                buffer[px][py] = '█';
             }
         }
 }

void DrawPaddleRight(char[][] buffer, int yp2) {
    for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 7; j++) {
             int px = 117 + i;
             int py = yp2 + j;

             buffer[px][py] = '█';
         }
    }
}

void RenderBuffer(char[][] buffer) {
     gotoxy(1,2);
     StringBuilder sb = new StringBuilder();
     for (int i = 0; i < 28; i++) {
         for (int j = 0; j < 120; j++) {
             sb.append(buffer[j][i]);
         }
         sb.append("\n");
     }
     print(sb.toString());
}

boolean w = false, s = false, arrow_up = false, arrow_dn = false;

void ControlPaddles(){
     if (keypressed()) {
         String key = readkeystr();
         if (key.equals("w"))  w = true;
         if (key.equals("s"))  s = true;
         if (key.equals("arrow_up")) arrow_up = true;
         if (key.equals("arrow_dn")) arrow_dn = true;
     }
     if (w) yp1--;
     if (s) yp1++;
     if (arrow_up) yp2--;
     if (arrow_dn) yp2++;
     if (yp1<=0) yp1 = 0;
     if (yp2<=0) yp2 = 0;
     if (yp1>=21)yp1 = 21;
     if (yp2>=21)yp2 = 21;
     w = s = arrow_up = arrow_dn = false;
}

void waitfor() {
    boolean Q = false;
    while (!Q) {
        if (keypressed()) {
            String key = readkeystr();
            if (key.equals(" ")) Q = true;
        }
    }
}

void resetBall(char buffer[][]) {
    x = 60;
    y = 15;
    yp1 = 12;
    yp2 = 12;
    movementX = rand.nextInt(2) * 2 - 1;
    movementY = rand.nextInt(2) * 2 - 1;
    DrawBall(buffer, x, y);
    DrawPaddleLeft(buffer, yp1);
    DrawPaddleRight(buffer, yp2);
    RenderBuffer(buffer);
    waitfor();

}

void DrawScore() {
    gotoxy(25,1);
    print("Player 1:"+ scoreleft);
    gotoxy(75, 1);
    print("Player 2:"+ scoreright);
        }

void MovementDirection(char buffer[][]) {

    if (y >= 29) {
        movementY = -1;
    }
    if (y <= 2) {
        movementY = 1;
    }

    if (x >= 114) {
        if (y >= yp2 && y <= yp2 + 6) {
            movementX = -1;
        } else {
            scoreleft++;
            resetBall(buffer);

        }
    }

    if (x <= 4) {

        if (y >= yp1 && y <= yp1 + 6) {
            movementX = 1; // bounce back
        } else {

            scoreright++;
            resetBall(buffer);
        }
    }
}



void clearBuffer(char[][] buffer) {
    for (int i = 0; i < 120; i++) {
        for (int j = 0; j < 28; j++) {
            buffer[i][j] = ' ';
        }
    }
}

void main() {
    clrscr();
    cursor_hide();
    framexy(1,1,120,30);

    char[][] buffer = CreateBuffer();
    DrawBall(buffer, x, y);
    DrawPaddleLeft(buffer, yp1);
    DrawPaddleRight(buffer, yp2);
    RenderBuffer(buffer);
    waitfor();
    while (!quit) {
        clearBuffer(buffer);
        for (int i=0; i<5;i++) {
            ControlPaddles();
            delay(6);
        }

        x += movementX*xd;
        y += movementY*yd;
        MovementDirection(buffer);

        DrawBall(buffer, x, y);
        DrawPaddleLeft(buffer, yp1);
        DrawPaddleRight(buffer, yp2);
        DrawScore();
        RenderBuffer(buffer);



        checkForQ();

    }
    gotoxy(1,31);
    print("Thank you for playing :)");
}
