import static java.lang.IO.*;  //including package to be able to use simple print()

//move cursor position to column x, row y
void gotoxy(int x, int y) {
    String GOTO_XY = "\u001b[%d;%dH";
    print(String.format(GOTO_XY, y, x));
}

//clear the terminal window
void clrscr() {
    String CLEAR_SCREEN = "\u001b[2J";
    print(String.format(CLEAR_SCREEN));
}

void cursor_hide() {
    String HIDE_CURSOR = "\u001b[?25l";
    print(String.format(HIDE_CURSOR));
}

void cursor_show() {
    String SHOW_CURSOR = "\u001b[?25h";
    print(String.format(SHOW_CURSOR));
}

void delay(int msec) {
    try {
        Thread.sleep(msec);
    } catch (InterruptedException e) {}
}

//constants with identifiers of basic colors 
//see: https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
final int black = 0;
final int brown = 1;
final int green = 2;
final int yellow = 3;
final int blue = 4;
final int magenta = 5;
final int cyan = 6;
final int ltgrey = 7;
final int grey = 8;
final int red = 9;
final int ltgreen = 10;
final int ltyellow = 11;
final int ltblue = 12;
final int white = 15;

//set text foreground color
void setfgcolor(int n) {
    String SET_FG_COLOR = "\u001b[38;5;%dm";
    print(String.format(SET_FG_COLOR,n));
}

//set text background color
void setbgcolor(int n) {
    String SET_BG_COLOR = "\u001b[48;5;%dm";
    print(String.format(SET_BG_COLOR,n));
}

        int x=20;
        int y=20;
void draw_square3x3() {
    int i=1;
    for( i=1;i<=3;i++){
        gotoxy(x,y-1+i);
        print("x");
        gotoxy(x-1+i,y);
        print("x");
        gotoxy(x+2,y-1+i);
        print("x");
        gotoxy(x-1+i,y+2);
        print("x");
    }
    delay(200);
    for( i=1;i<=3;i++){
        gotoxy(x,y-1+i);
        print(" ");
        gotoxy(x-1+i,y);
        print(" ");
        gotoxy(x+2,y-1+i);
        print(" ");
        gotoxy(x-1+i,y+2);
        print(" ");
    }
}

int max_x = 120;
int min_x = 1;
int max_y = 30;
int min_y = 1;

Random rand = new Random();
void crazy_square(int n) {
    for ( int i = 1; i <= 30; i++ ) {
        int c = rand.nextInt(4);
        switch (c) {
            case 0:
                x = x + n;
                if(x > max_x){
                    x = max_x;
                }
                draw_square3x3();
                break;
            case 1:
                y = y - n;
                if(y < min_y){
                    y = min_y;
                }
                draw_square3x3();
                break;
            case 2:
                x = x - n;
                if(x < min_x){
                    x = min_x;
                }
                draw_square3x3();
                break;
            case 3:
                y = y + n;
                if(y > max_y){
                    y = max_y;
                }
                draw_square3x3();
                break;
        }
    }
}


    void main() {
        cursor_hide();
        clrscr();
        setbgcolor(black);
        gotoxy(4,3);
        setfgcolor(white);
        delay(800);

        crazy_square(3);

        gotoxy(2,25);
        setfgcolor(white);
        print("Press Enter to end the program...");
        readln();

        cursor_show();
        clrscr();
        gotoxy(1,1);
    }