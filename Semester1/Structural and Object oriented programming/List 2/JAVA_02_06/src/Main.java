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
    print(String.format(SET_BG_COLOR, n));
}

        Random rand = new Random();



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

        int max_x = 120;
        int min_x = 1;
        int max_y = 30;
        int min_y = 1;


        void crazy_squareg(int n) {
                int c = rand.nextInt(4);
                switch (c) {
                    case 0:
                        xg = xg + n;
                        if(xg > max_x){
                            xg = max_x;
                        }

                        break;
                    case 1:
                        yg = yg - n;
                        if(yg < min_y){
                            yg = min_y;
                        }

                        break;
                    case 2:
                        xg = xg - n;
                        if(xg < min_x){
                            xg = min_x;
                        }
                        break;
                    case 3:
                        yg = yg + n;
                        if(yg > max_y){
                            yg = max_y;
                        }
                        break;
            }
            draw_square(xg,yg);
        }
        void crazy_squareb( int n) {
            int c = rand.nextInt(4);
            switch (c) {
                case 0:
                    xb = xb + n;
                    if(xb > max_x){
                        xb = max_x;
                    }

                    break;
                case 1:
                    yb = yb - n;
                    if(yb < min_y){
                        yb = min_y;
                    }

                    break;
                case 2:
                    xb = xb - n;
                    if(xb < min_x){
                        xb = min_x;
                    }

                    break;
                case 3:
                    yb = yb + n;
                    if(yb > max_y){
                        yb = max_y;
                    }

                    break;
            }
            draw_square(xb,yb);
        }
        void crazy_squarer(int n) {
            int c = rand.nextInt(4);
            switch (c) {
                case 0:
                    xr = xr + n;
                    if(xr > max_x){
                        xr = max_x;
                    }
                    break;
                case 1:
                    yr = yr - n;
                    if(yr < min_y){
                        yr = min_y;
                    }

                    break;
                case 2:
                    xr = xr - n;
                    if(xr < min_x){
                        xr = min_x;
                    }

                    break;
                case 3:
                    yr = yr + n;
                    if(yr > max_y){
                        yr = max_y;
                    }

                    break;
            }
            draw_square(xr,yr);
        }

        int xb = (int)(Math.random() * 119) + 1;
        int yb = (int)(Math.random() * 29) + 1;
        int xg = (int)(Math.random() * 119) + 1;
        int yg = (int)(Math.random() * 29) + 1;
        int xr = (int)(Math.random() * 119) + 1;
        int yr = (int)(Math.random() * 29) + 1;
void crazy_squares(int n) {

            for(int i=1;i<=30;i++){
                setfgcolor(4);
                crazy_squareb(n);
                setfgcolor(2);
                crazy_squareg(n);
                setfgcolor(9);
                crazy_squarer(n);
                delay(200);
                delete_square3x3(xb,yb);
                delete_square3x3(xg,yg);
                delete_square3x3(xr,yr);
            }
}

void main() {

    cursor_hide();
    clrscr();
    setbgcolor(black);
    gotoxy(4,3);
    setfgcolor(white);
    delay(800);

    crazy_squares(3);

    gotoxy(2,25);
    setfgcolor(white);    
    print("Press Enter to end the program...");
    readln();
    
    cursor_show();
    clrscr();
    gotoxy(1,1);
}