
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)


static final double X =50.0;// [km]
static final double t1 =1.0;// [min]
static double t1s = t1 * 60.0; //[s]
static double a1 =4.5;// [m/s^2]
static final double t2 =2.0;// [min]
static double t2s = t2 * 60.0; //[s]
static double a2 =0.7;// [m/s^2]
static double a22 =0.0;//[m/s^2]
static final double tau =0.1;// [s]
static double dev_v = 4.0;
static double t_osc = 180.0;


static double v1 = 0.0;// [m/s]
static double v2 = 0.0;// [m/s]
static double s1 = 0.0;// [m]
static double s2 = 0.0;// [m]
static double ssum = 0.0;
static double DTC = 0.0;
static double W = 100.0;


static void time(double t){
    gotoxy(5, 4);
    System.out.printf("Time: %.3f s",t);
}

static void train1( double t,double t1s, double tau) {
    gotoxy(5, 5);
    System.out.printf("Velocity = %.3f m/s, Acceleration = %.3f m/s^2, x1 = %.3f m", v1, a1, s1);

    if (t1s>t) {

        s1 = s1 + v1 * tau;
        v1 = v1 + a1 * tau;
    }
    else{
        a1 = 0.0;
        s1 = s1 + v1 * tau;
    }


}

static void train2(double a1, double t, double t2,  double tau, double dev_v, double t_osc) {
    gotoxy(5, 6);
    if (t < t2) {
        a22 = a2 * Math.sin((Math.PI / 2) * t / t2);
    }
    else{
        a22 = 0.0;
    }

    if (t < t2){
        v2 = v2 + a22 * tau;
    }
    else {
        v2 = v2 + dev_v * Math.cos(2 * Math.PI * t / t_osc) * tau;
    }

    s2 = s2 + v2 * tau;

    gotoxy(5, 6);
    System.out.printf("Velocity = %.3f m/s, Acceleration = %.3f m/s^2, x2 = %.3f m", v2, a22, s2);
}

static void distancesum(double s1, double s2, double v1, double v2){
    gotoxy(5,7);
    System.out.printf("Total distance: %.3f km, Distance to collision: %.3f km", ssum, DTC);
    ssum = (s2 + s1)/1000;
    DTC = X - ssum;

}
static double cos=W/X;


static void PrintSituation() {
    cursor_hide();
    double t = 0.0;
    gotoxy(5, 8);
    print("press q to stop simulation...");


    for (t = 0; ;t+=tau) {
        time(t);
        train1( t ,t1s, tau);
        train2(a2, t, t2s, tau,  dev_v, t_osc);
        distancesum(s1, s2, v1, v2);
        t = Math.round(t * 1000) / 1000.0;


        TrackOfDoom(W, X, s1, s2);
        if ("finish".equals(e)) {
            gotoxy(5,10);
            print(Line(W));
            setfgcolor(red);
            int r = (int) Math.floor(cos*s1/1000)+5;
            gotoxy(r,10);
            print("x");
            setfgcolor(white);
            gotoxy(5,15);
            print("colision detected press q");
            break;
        }
        if (t%10 == 0) {
            gotoxy(5,10);
            print(TrackOfDoom(W, X, s1, s2));

        }
        if (keypressed()) {
            String key = readkeystr();
            if (key.equals("q")) {
                gotoxy(5, 9);
                System.out.printf("Simulation stopped by user at t = %.2f s.", t);
                break;
            }
        }

        //delay(10);
    }
}

static String e;

static String Line(double W) {
    StringBuilder Line = new StringBuilder();

    for (int i = 0; i < W; i++){
            Line.append('=');

    }
    return Line.toString();
}

static String TrackOfDoom(double W, double X, double s1, double s2) {
    StringBuilder Line = new StringBuilder();
    double cos=W/X;
    for (int i = 0; i < W; i++)
    {
        if (Math.floor((W*1000-1000.0-s2*cos)/1000) == Math.floor(cos*s1/1000)){
            setfgcolor(red);
            int r = (int) Math.floor(cos*s1/1000)+5;
            gotoxy(r,10);
            print("x");
            setfgcolor(white);
            gotoxy(5,15);
            print("colision detected press q");
            e = "finish";
            return e;
        }

        else if (i == Math.floor((W*1000-1000.0-s2*cos)/1000)){
            Line.append("<");
        }

        else if (i == Math.floor(cos*s1/1000)){
            Line.append(">");
        }

        else{
            Line.append('=');
        }

    }
    return Line.toString();
}




public static void main(String[] args) {
    clrscr();
    gotoxy(5,5);

    print("Starting program...");
    delay(1000);
    PrintSituation();
    gotoxy(5,20);

}
//    //        System.out.println(makeLine(20));