import static java.lang.IO.*;  //including package to be able to use simple print()

//Planned Squares 2

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

//add your procedures here
        int[] sq_greenx = {40, 55, 63, 17};
        int[] sq_greeny = {7, 12, 10, 17};
        int[] sq_redx = {7, 82, 53, 110, 38, 24, 115, 68};
        int[] sq_redy = {12, 28, 6, 1, 14, 9, 24, 18};
        int[] sq_bluex = {15, 66, 95, 2, 117, 56, 44, 81, 35, 72, 5, 108, 63, 23, 91};
        int[] sq_bluey = {10, 4, 22, 8, 13, 19, 27, 1, 23, 30, 6, 25, 2, 18, 11};

        void draw_square(int x,  int y) {
            int i = 1;
            for (i = 1; i <= 3; i++) {
                gotoxy(x, y - 1 + i);
                print("x");
                gotoxy(x - 1 + i, y);
                print("x");
                gotoxy(x + 2, y - 1 + i);
                print("x");
                gotoxy(x - 1 + i, y + 2);
                print("x");
            }
        }
        void delete_square3x3(int x, int y) {
            for(int i=1;i<=3;i++){
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







        void planned_squares2() {
            int j=0;
            int k=0;
            for(int i=0;i<=14;i++){
                setfgcolor(4);
                draw_square(sq_bluex[i],sq_bluey[i]);
                setfgcolor(2);
                draw_square(sq_greenx[j],sq_greeny[j]);
                setfgcolor(9);
                draw_square(sq_redx[k],sq_redy[k]);
                delay(200);
                delete_square3x3(sq_bluex[i],sq_bluey[i]);
                delete_square3x3(sq_greenx[j],sq_greeny[j]);
                delete_square3x3(sq_redx[k],sq_redy[k]);
                k=k+1;
                j=j+1;
                if (i==14){
                    i=0;
                }
                if (j==4){
                    j=0;
                }
                if (k==8){
                    k=0;
                }
            }
        }


void main() {
     
    //example of the usage of procedures defined above
    //to be modified by usage of your procedures

    cursor_hide();
    clrscr();
    setbgcolor(black);
    gotoxy(4,3);
    setfgcolor(white);

    delay(800);

    planned_squares2();


    gotoxy(2,25);
    setfgcolor(white);    
    print("Press Enter to end the program...");
    readln();
    
    cursor_show();
    clrscr();
    gotoxy(1,1);
}