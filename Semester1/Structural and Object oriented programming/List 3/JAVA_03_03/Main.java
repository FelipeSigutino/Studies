//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

int n=0;
int c=0;
int[] digits = null;
public void fromftob (int x){
    int t=x;
    while(t>0){
        t=t/10;
        c++;
    }
    digits = new int[c];
    while (x>0){
        digits[n]=x%10;
        x=x/10;
        n=n+1;
    }
        gotoxy(3,3);
        for(int j=0;j<n;j++){
            print(digits[j]+",");
        }
}

public void DigitsCount(){
    gotoxy(3,4);
    print(c);
}

public void DigitAtPosition(int h){
    gotoxy(3,5);
    print(digits[h-1]);
}

public void main() {

    cursor_hide();
    clrscr();
    setbgcolor(0);
    setfgcolor(white);
    framexy(1,1,120,30);
    fromftob(123211);
    DigitsCount();
    DigitAtPosition(3);
    gotoxy(1,31);

}