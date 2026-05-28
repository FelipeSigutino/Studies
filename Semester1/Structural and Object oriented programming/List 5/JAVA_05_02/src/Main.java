import static java.lang.IO.print;

import static term.term.*;
import java.util.Random;

Random rand =  new Random(12345);

class TBall{
    int x, y;
    int dx, dy;
}

TBall CreateBall(int x, int y, int dx, int dy){
    TBall b = new TBall();
    b.x = x;
    b.y = y;
    b.dx = dx;
    b.dy = dy;
    return b;
}

class TPaddle{
    int x;
    int y;
    final int height = 7;
    final int width = 3;
}

TPaddle CreatePaddle(int x, int y){
    TPaddle p = new TPaddle();
    p.x = x;
    p.y = y;
    return p;
}

class TPlayer {
    String name;
    TPaddle paddle;
    int score = 0;
}

TPlayer CreatePlayer(String name, TPaddle paddle){
    TPlayer p = new TPlayer();
    p.name = name;
    p.paddle = paddle;
    return p;
}

class TFrame {
    final int width = 120;
    final int height = 28;
    char[][] buffer = new char[width][height];

    void clear(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                buffer[i][j] = ' ';
            }
        }
    }
    void render(){
        gotoxy(1,2);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                sb.append(buffer[j][i]);
            }
            sb.append("\n");
        }
        print(sb.toString());
    }
}


class TGame{
    Random rand =  new Random(12345);
    boolean quit = false;

    TFrame frame;
    TBall ball;
    TPlayer player1, player2;
}

boolean w = false, s = false, arrow_up = false, arrow_dn = false;

void drawBall(TBall ball, TFrame frame){
    frame.buffer[ball.x][ball.y] = '*';
}

void drawPaddle(TPaddle p, TFrame frame){
    for(int j = 0; j < p.width; j++){
        for(int k = 0; k < p.height; k++){
            int px = p.x + j;
            int py = p.y + k;
            frame.buffer[px][py] = '█';
        }
    }
}


void drawScore(TPlayer player1, TPlayer player2){
    gotoxy(25,1);
    print(player1.name + ": "+ player1.score);
    gotoxy(75, 1);
    print(player2.name + ": "+ player2.score);
}

void MovePaddles(TPlayer player1, TPlayer player2){
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("w"))  w = true;
        if (key.equals("s"))  s = true;
        if (key.equals("arrow_up")) arrow_up = true;
        if (key.equals("arrow_dn")) arrow_dn = true;
    }

    if (w) player1.paddle.y --;
    if (s) player1.paddle.y ++;
    if (arrow_up) player2.paddle.y --;
    if (arrow_dn) player2.paddle.y ++;

    player1.paddle.y = Math.max(player1.paddle.y, 0);
    player1.paddle.y = Math.min(player1.paddle.y, 21);
    player2.paddle.y = Math.max(player2.paddle.y, 0);
    player2.paddle.y = Math.min(player2.paddle.y, 21);

    w = s = arrow_up = arrow_dn = false;
}

void resetBall(TBall ball, TFrame frame, TPlayer player1, TPlayer player2) {
    ball.x = 60;
    ball.y = 15;
    ball.dx = rand.nextInt(2)*2-1;
    ball.dy = rand.nextInt(2)*2-1;
    frame.clear();
    drawEverything(player1,player2,frame,ball);
    waitForSpace();
}

void moveBall(TBall ball, TFrame frame, TPlayer player1, TPlayer player2){
    ball.x += ball.dx;
    ball.y += ball.dy;

    if (ball.y <= 1) {
        ball.dy *= -1;
    }
    if (ball.y >= frame.height - 1) {
        ball.dy *= -1;
    }

    if (ball.x >= player2.paddle.x - 1) {

        if (ball.y >= player2.paddle.y && ball.y <= player2.paddle.y + player2.paddle.height) {
            ball.dx *= -1;
        } else {
            player1.score += 1;
            resetBall(ball,frame, player1, player2);
        }
    }
    if (ball.x <= player1.paddle.x + player1.paddle.width) {
        if (ball.y >= player1.paddle.y && ball.y <= player1.paddle.y + player1.paddle.height) {
            ball.dx *= -1;
        } else {
            player2.score += 1;
            resetBall(ball,frame,  player1, player2);
        }
    }
}

void drawEverything(TPlayer player1, TPlayer player2, TFrame frame, TBall ball){
    drawBall(ball, frame);
    drawPaddle(player1.paddle, frame);
    drawPaddle(player2.paddle, frame);
    drawScore(player1, player2);
}

void waitForSpace(){
    boolean space = false;
    while (!space) {
        if (keypressed()) {
            String key = readkeystr();
            if (key.equals(" "))  space = true;
        }
    }
}

boolean quit = false;

void checkq() {
    quit = false;
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("q"))  quit = true;
    }
}

TGame CreateGame(){
    TGame g = new TGame();
    g.frame = new TFrame();
    g.rand = new Random();
    g.ball = CreateBall(60, 15, g.rand.nextInt(2)*2 - 1, g.rand.nextInt(2)*2 - 1);
    g.player1 = CreatePlayer("Filip", CreatePaddle(1, 12));
    g.player2 = CreatePlayer("Olegzander", CreatePaddle(117, 12));
    g.quit = false;
    return g;
}

void main() {
    TGame g = CreateGame();

        clrscr();
        cursor_hide();
        framexy(1,1,120,30);


        drawEverything(g.player1, g.player2, g.frame, g.ball);
        waitForSpace();

        while (!quit) {
            g.frame.clear();

            moveBall(g.ball,g.frame, g.player1, g.player2);
            for (int i = 0; i < 5; i++) {
                MovePaddles(g.player1,g.player2);
            }

            drawEverything(g.player1, g.player2, g.frame, g.ball);

            g.frame.render();
            delay(30);
            checkq();
        }

        gotoxy(1,31);
        print("Thanks for playing :)");


}
