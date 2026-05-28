
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)


Random rand = new Random();
int xb = (int)(Math.random() * 119) + 1;
int yb = (int)(Math.random() * 29) + 1;
int xr = (int)(Math.random() * 119) + 1;
int yr = (int)(Math.random() * 29) + 1;
int max_x = 117;
int min_x = 2;
int max_y = 27;
int min_y = 2;

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

void crazy_squaresd(int n) {
    setfgcolor(4);
    crazy_squareb(n);
    setfgcolor(9);
    crazy_squarer(n);
}
void crazy_squaresde() {
        delete_square3x3(xb,yb);
        delete_square3x3(xr,yr);
}


void draw_frame_c(int x1, int y1, int x2, int y2,char c) {
    for (int x = x1; x <= x2; x++) {
        gotoxy(x,y1);
        print(c);
        gotoxy(x,y2);
        print(c);
    }
    for (int y = y1; y <= y2; y++) {
        gotoxy(x1,y);
        print(c);
        gotoxy(x2,y);
        print(c);
    }
}


void main() {

    cursor_hide();
    clrscr();
    setfgcolor(white);
    framexy(1,1,120,30);
    gotoxy(32,30);
    write("   Use 'wsad' to move a square. Use 'q' to exit   ");
    gotoxy(28,1);
    write("   Java Lab 3, initial example (keyboard control, delay)  ");


    int minx = 2;
    int maxx = 119;
    int miny = 2;
    int maxy = 29;
    setbgcolor(black);

    cursor_hide();
    int posx = 65;
    int posy = 14;
    int last_posx;
    int last_posy;
    int ball_color = 10;
    int time_color = red;

    gotoxy(posx,posy);
    setfgcolor(ball_color);
    draw_frame_c(posx-1,posy-1,posx+1,posy+1,'*');
int g=0;
    while (true) {
        while (true) {
            crazy_squaresd(2);
            delay(20);
            crazy_squaresde();
        }
        if ( keypressed()) {              //check if any key was pressed
            String keystr = readkeystr(); //something was pressed so read what key it was

            last_posx=posx;
            last_posy=posy;
            if (keystr.equals("arrow_lt")) {     //perform something if 'a' was pressed
                if (posx>minx+1) posx = posx - 1;

            };
            if (keystr.equals("arrow_rt")) {     //perform something if 'd' was pressed
                if (posx<maxx-1) posx = posx + 1;

            };
            if (keystr.equals("arrow_up")) {
                if (posy>miny+1) posy = posy - 1;
            }
            if (keystr.equals("arrow_dn")) {
                if (posy<maxy-1) posy = posy + 1;
            }
            if (keystr.equals("c")) {
                if (ball_color==10) {
                    ball_color=4;
                }
                else if (ball_color==4) {
                    ball_color=9;
                }
                else if (ball_color==9) {
                    ball_color=10;
                }
            }
            if (keystr.equals("q")) { break; };


            //3. refresh moving objects
            gotoxy(last_posx, posy);     //clear last position of the controlled object
            draw_frame_c(last_posx-1,last_posy-1,last_posx+1 ,last_posy+1,' ');
            gotoxy(posx, posy);          //draw controlled object
            setfgcolor(ball_color);
            draw_frame_c(posx-1,posy-1,posx+1 , posy+1,'*');;
        }


    }
    //5. clear the terminal before exit
    clrscr();
    gotoxy(1,1);
}