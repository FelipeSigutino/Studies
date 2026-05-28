
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

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



    //1. Preparation of the scene
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
    int posy = 15;
    int last_posx;
    int last_posy;
    int ball_color = 10;
    int time_color = red;

    gotoxy(posx,posy);   //draw controlled object
    setfgcolor(ball_color);
    draw_frame_c(posx-1,posy+1,posx+1,posy-1,'*');

    boolean hit_left = false;            //flags to control which wall was hit last time
    boolean hit_right = false;

    long time_start = System.nanoTime();

    //2. main loop for listening and processing keyboard events
    while (true) {
        if ( keypressed()) {              //check if any key was pressed
            String keystr = readkeystr(); //something was pressed so read what key it was

            last_posx=posx;
            last_posy=posy;
            if (keystr.equals("a")) {     //perform something if 'a' was pressed
                if (posx>minx+1) posx = posx - 1;

            };
            if (keystr.equals("d")) {     //perform something if 'd' was pressed
                if (posx<maxx-1) posx = posx + 1;

            };
            if (keystr.equals("w")) {
                if (posy>miny+1) posy = posy - 1;
            }
            if (keystr.equals("s")) {
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

        delay(20);        //additional delay to limit the frequency of the event processing loop
    }
    //5. clear the terminal before exit
    clrscr();
    gotoxy(1,1);
}