//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

public int factorialtail(int n, int m){
    if (n == 0 || n == 1){
        return m;
    }
    return factorialtail(n - 1, n * m);
}

void main() {

    cursor_hide();
    clrscr();
    setfgcolor(white);
    setbgcolor(black);
    gotoxy(3,3);
    print(factorialtail(5,1));
    framexy(1, 1, 120, 30);
    gotoxy(1,31);
}