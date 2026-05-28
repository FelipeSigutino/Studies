//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

int fibonacciloop(int N) {
    int a=0;
    int b=1;
    int c=0;
    if (N==0){
        return 0;
    }
    for(int i = 2; i < N; i++) {
        c=a+b;
        a=b;
        b=c;
    }
    return c;
}

int fibonaccirecursion(int N) {
    int a=0;
    int b=1;
    int c=0;
    if (N==0){
        return 0;
    }
    else if (N==1){
        return 1;
    }
    else if (N==2){
        return 1;
    }
    else {
        return fibonaccirecursion(N-1)+fibonaccirecursion(N-2);
    }
}

int fibonaccitailrecursion(int N,int a, int b) {
    if (N==0){
        return a;
    }
    else if (N==1){
        return b;
    }
    else {
        return fibonaccitailrecursion(N-1,b,a+b);
    }
}

//gotoxy(13,3);
//    print(fibonaccirecursion(10-1));
//    gotoxy(23,3);
//    print(fibonaccitailrecursion(10-1,0,1));
//
long stime = 0;
long etime = 0;
long duration = 0;
long totaltime = 0;
long measureloop (int N) {
    for(int j=1; j<=N;j++) {
        stime = System.nanoTime();
        fibonacciloop(45);
        etime = System.nanoTime();
        duration = etime - stime;
        totaltime += duration;
    }
    return totaltime;
}

long measurerecursion (int N) {
    for(int j=1; j<=N;j++) {
        stime = System.nanoTime();
        fibonaccirecursion(45-1);
        etime = System.nanoTime();
        duration = etime - stime;
        totaltime += duration;
    }
    return totaltime/N;
}

long measuretailrecursion (int N) {
    for(int j=1; j<=N;j++) {
        stime = System.nanoTime();
        fibonaccitailrecursion(45-1,0,1);
        etime = System.nanoTime();
        duration = etime - stime;
        totaltime += duration;
    }
    return totaltime/N;
}
int nb_of_repeats =1000;

void main() {


    //1. Preparation of the scene
    cursor_hide();
    clrscr();
    setfgcolor(white);
    framexy(1,1,120,30);
    gotoxy(3,3);
    print("average working time for loop: "+measureloop(nb_of_repeats)/nb_of_repeats+" ns");
    gotoxy(3,4);
    print("average working time for recursion: "+measurerecursion(nb_of_repeats)+" ns");
    gotoxy(3,5);
    print("average working time for tailrecursion: "+measuretailrecursion(nb_of_repeats)+" ns");
    gotoxy(1,31);
}