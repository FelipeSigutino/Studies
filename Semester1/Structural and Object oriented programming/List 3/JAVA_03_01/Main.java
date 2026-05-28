//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
void cTree(int N,int x,int y){
    int p=N/2;
    int h=N-1;
    int n=0;
    for(int i=0;i<N;i++){
        for(int j=0;j<N-h;j++){
            setfgcolor(green);
            gotoxy(x+p-n+j,y+n);
            print('*');
            gotoxy(x+p+j,y+n);
            print('*');
        }
        n++;
        h=h-1;
    }
    if(N<=4){
        setfgcolor(brown);
        gotoxy(x+p,y+N);
        print("|");
    }
    else{
        setfgcolor(brown);
        gotoxy(x+p,y+N);
        print('asd');
    }
}

        void main() {

            cursor_hide();
            clrscr();
            setbgcolor(white);
            setfgcolor(black);
            framexy(1, 1, 120, 30);
            cTree(10,40,10);
            setfgcolor(black);
            gotoxy(1,31);
        }