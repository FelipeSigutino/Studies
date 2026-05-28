
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;
import java.util.concurrent.ThreadLocalRandom;

double cosine = 0;

double degToRad(double a) {
    return a*Math.PI/180.0;

}

long factorial(int n) {
    long f = 1;
    for (int i = 2; i <= n; i++) {
        f *= i;
    }
    return f;
}


 double cosTaylor(double a, double eps) {
    if (eps==0.0) {
        return 0;
    }
    double cosine = 0.0;
    double term = 1.0;
    int n=0;
    int m=0;
    do {
        term= Math.pow(-1, n) * Math.pow(a,m) / factorial(m);
        cosine += term;
        n++;
        m=m+2;
    } while (Math.abs(term)>eps);
    return cosine;
}
int Nt=10000;
 double TestCos() {
    double difference=0.0;
    for (int i = 1; i <= Nt; i++) {
        double random = ThreadLocalRandom.current().nextDouble(-10.0,10.0);
        difference = (Math.abs(Math.cos(degToRad(random)) - cosTaylor(degToRad(random), 0.0001))) +difference;
   }
    return difference/Nt;
}
long averageTime;
long averageTimeT;
long checkruntimeTaylor(int Nttime, double eps) {
    double random = ThreadLocalRandom.current().nextDouble(-10.0, 10.0);
    for (int i = 1; i <= Nttime; i++) {
    long startT = System.nanoTime();
    cosTaylor(degToRad(random), eps);
    long finishT = System.nanoTime();
    averageTimeT = finishT - startT + averageTimeT;
    }
    return averageTimeT/Nttime;
}

long checkruntimeMathcos(int Nttime) {
    double random = ThreadLocalRandom.current().nextDouble(-10.0, 10.0);
    for (int i = 1; i <= Nttime; i++) {
        long start = System.nanoTime();
        Math.cos(degToRad(random));
        long finish = System.nanoTime();
        averageTime = finish - start + averageTime;
    }
    return averageTime/Nttime;
}

int Nttime = 10000;

public void main() {
    double a=60.0;
    double cosine = cosTaylor(degToRad(a), 0.0001);
    println("cosine TaylorSeries is equal to: "+ cosine);
    println("cosine Math.cos is equal to: " + Math.cos(degToRad(a)));
    println("Average diference for "+ Nt+ " tries of Taylor and Math.cos is equal to: " + TestCos());
    println("Average run time for cosTaylor is equal to: " + checkruntimeTaylor(Nttime, 0.0001) + "ns");
    println("Average run time for Math.cos is equal to: " + checkruntimeMathcos(Nttime)+ "ns") ;
}