
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)

int h0=21;
int m0=50;
int s0=1;

int hexit=0;
int mexit=0;
double sexit=0;

double change = 0.0;
int N=1;            //seconds may be more accurate with bigger N
double[] intersection= new double[11];
double time0;
double closestBigger = Double.POSITIVE_INFINITY;
double difference;

int hfin = h0 + hexit ;
int mfin = m0 + mexit ;
double sfin = s0 + sexit;

void MinuteEqualHour() {
    int i;
    for (i = 0; i <= 10; i++) {
        intersection[i] = change;
        change = change + (12.0 / 11.0) * 3600 * N;
    }

    if (h0<12 && 0<=h0){
        time0=3600 * h0 + 60 * m0 + s0;
    }
    else if (h0<0){
        print("time cannot be negative");
    }
    else {
        int ht=h0%12;
        time0=3600 * ht + 60 * m0 + s0;
    }

    for (double value : intersection) {
        if (value >= time0 && value < closestBigger) {
            closestBigger = value;
        }
    }

    difference = closestBigger-time0;

    if (difference==0) {
        hexit = 0;
        mexit = 0;
        sexit = 0;
        return;
    }

    if (difference >= 3600) {
        hexit = (int) difference / 3600;
        difference = difference % 3600;
    }

    if (difference >= 60) {
        mexit = (int) difference/60;
        difference = difference % 60.0;
    }

    sexit =difference;


    hfin = h0 + hexit;
    mfin = m0 + mexit;
    if (mfin>=60){
        hfin = hfin +1;
        mfin = mfin -60;
    }
    sfin = s0 + sexit;
    if (sfin>=60){
        mfin = mfin +1;
        sfin = sfin -60;
    }



}


void main() {

    clrscr();



    MinuteEqualHour();

    for (int i = 0; i <= 10; i++){
        println(intersection[i]);
    }


    println(closestBigger);
    println(time0);
    println(difference);

    println("Next overlap occurs in: "+hexit+"hours "+mexit+"minutes "+sexit+"seconds");





    println("at hour: "+hfin+":"+mfin+":"+sfin);

}
