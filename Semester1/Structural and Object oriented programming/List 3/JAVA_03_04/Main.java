//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)
int n=1;
void factorialloop(int x) {
    for (int i = 1; i <= x; i++) {
        n=n*i;
    }
    gotoxy(3,3);
    print(n);
}

void main() {
    cursor_hide();
    clrscr();
    setfgcolor(white);
    setbgcolor(0);
    factorialloop(5);
    framexy(1,1,120,30);
    gotoxy(1,31);
}