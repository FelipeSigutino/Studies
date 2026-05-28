//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)


int factorialloop(int x) {
    int n = 1;
    for (int i = 1; i <= x; i++) {
        n=n*i;
    }
    return n;
}

int factorialrecursion(int x) {
    if  (x==0) {
        return x;
    } else if (x==1) {
        return x;
    }
    else {
        return x * factorialrecursion(x-1);
    }
}

int factorialtail(int n, int m){
    if (n == 0 || n == 1){
        return m;
    }
    return factorialtail(n - 1, n * m);
}

long stime = 0;
long etime = 0;
long duration = 0;
long totaltime = 0;

long measureloop (int N) {
    for(int j=1; j<=N;j++) {
        stime = System.nanoTime();
        factorialloop(45);
        etime = System.nanoTime();
        duration = etime - stime;
        totaltime += duration;
    }
    return totaltime;
}

long measurerecursion (int N) {
    for(int j=1; j<=N;j++) {
        stime = System.nanoTime();
        factorialrecursion(45);
        etime = System.nanoTime();
        duration = etime - stime;
        totaltime += duration;
    }
    return totaltime/N;
}

long measuretailrecursion (int N) {
    for(int j=1; j<=N;j++) {
        stime = System.nanoTime();
        factorialtail(45,1);
        etime = System.nanoTime();
        duration = etime - stime;
        totaltime += duration;
    }
    return totaltime/N;
}


int nb_of_repeats =10;


void main() {



    cursor_hide();
    clrscr();
    setfgcolor(white);
    framexy(1,1,120,30);
    gotoxy(3,2);
    print("10 tries");
    gotoxy(3,3);
    print("average working time for loop: "+ measureloop(nb_of_repeats)/nb_of_repeats + " ns");
    gotoxy(3,4);
    print("average working time for recursion: "+measurerecursion(nb_of_repeats)+" ns");
    gotoxy(3,5);
    print("average working time for tailrecursion: "+measuretailrecursion(nb_of_repeats)+" ns");
    gotoxy(3,6);
    print("10000 tries");
    nb_of_repeats= 10000;
    gotoxy(3,7);
    print("average working time for loop: "+ measureloop(nb_of_repeats)/nb_of_repeats + " ns");
    gotoxy(3,8);
    print("average working time for recursion: "+measurerecursion(nb_of_repeats)+" ns");
    gotoxy(3,9);
    print("average working time for tailrecursion: "+measuretailrecursion(nb_of_repeats)+" ns");

    gotoxy(1,31);
}