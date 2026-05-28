
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;
import java.util.Scanner;

boolean quit = false;

boolean CheckForS() {
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("s")) {
            quit = true;
        }
    }
    return quit;
}


void main() {
    Scanner sc = new Scanner(System.in);

    clrscr();
    cursor_hide();
    println("Please input the color of tha D");
    int color  = sc.nextInt();
    setfgcolor(color);
    print("8");
    while (!quit){
        print("=");
        CheckForS();
    }
    println("D");
    setfgcolor(15);
    gotoxy(1,10);

}