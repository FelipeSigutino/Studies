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

        void draw_horizontal_line(int x1,int x2,int y, char c) {
            for(int x=x1; x<=x2; x++) {
                gotoxy(x,y);
                IO.print(c);
                delay(50);
            };
        };
        void draw_vertical_line(int x,int y1,int y2, char c){
            for(int y=y1; y<=y2; y++) {
                gotoxy(x,y);
                IO.print(c);
                delay(50);
            }
        }
        void draw_vertical_linedtu(int x,int y1,int y2, char c){
            for(int y=y2; y>=y1; y--) {
                gotoxy(x,y);
                IO.print(c);
                delay(50);
            }
        }
        void draw_horizontal_linertl(int x1,int x2,int y, char c){
            for(int x=x2; x>=x1; x--) {
                gotoxy(x,y);
                IO.print(c);
                delay(50);
            }
        }


int z=0;
int zy=0;
int zx=0;
int n =1;

int xmax=120;
int xmin=1;
int ymax=30;
int ymin=1;
int s=0;

int checkvalues(int x,int y){
        if (x>xmax){
            s=1;
        }
        if (x<xmin){
            s=1;
        }
        if (y>ymax){
            s=1;
        }
        if (y<ymin){
            s=1;
        }
        return s;
}

void spiral(int x, int y,char c, int s){
    gotoxy(x,y);
    for(int i = 1; i <= s; i++){
        switch (z) {
            case 0:
                gotoxy(x,y);
                zy=zy+2;
                //draw_vertical_line(x,y-zy,y,c);
                draw_vertical_linedtu(x, y-zy, y, c);
                y=y-zy;
                //draw_vertical_line(x,y-zy,y,c);
                break;
            case 1:
                //draw_horizontal_line(x,x+zx,y-zy,c);
                zx=zx+2;
                draw_horizontal_line(x,x+zx,y,c);
                x=x+zx;
                break;
            case 2:
                zy=zy+2;
                //draw_vertical_line(x+zx,y-zy,y+zy,c);
                draw_vertical_line(x,y,y+zy,c);
                y=y+zy;
                break;
            case 3:
                zx=zx+2;
                //draw_horizontal_line(x-zx,x+zx,y+zy,c);
                //draw_horizontal_line(x-zx,x,y,c);
                draw_horizontal_linertl(x-zx,x,y,c);
                x=x-zx;
                //x=x-zx;
                //y=y+zx;
                //zx=zx+4;
                //zy=zy+4;
                break;
        }
        if (x>xmax){
            s=1;
        }
        if (x<xmin){
            s=1;
        }
        if (y>ymax){
            s=1;
        }
        if (y<ymin){
            s=1;
        }
        z=z+1;
        if(z==4){
            n = n + 1;
            z=0;
        }
    }

}

        //draw_vertical_line(60,13,15,'x'); up
        //draw_vertical_line(60,15,17,'x'); down

void main() {

    cursor_hide();
    clrscr();
    setbgcolor(black);
    gotoxy(4,3);
    setfgcolor(white);
    delay(800);



    spiral(60,15,'X',3100);

    gotoxy(2,25);
    setfgcolor(white);
    readln();
    
    cursor_show();
    clrscr();
    gotoxy(1,1);
}