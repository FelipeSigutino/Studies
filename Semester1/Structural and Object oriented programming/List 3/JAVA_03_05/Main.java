//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)
int factorialrecursion(int x) {
    if  (x==0) {
        gotoxy(3,3);
        return x;
    } else if (x==1) {
        gotoxy(3,3);
        return x;
    }
    else {
        gotoxy(3,3);
        return x * factorialrecursion(x-1);
    }
}

void main() {
    cursor_hide();
    clrscr();
    setfgcolor(white);
    setbgcolor(0);
    print(factorialrecursion(5));
    framexy(1,1,120,30);
    gotoxy(1,31);
}