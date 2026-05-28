import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)

public class WaterTankSim {


    static final double VT=100.0;
    static final double V0=0.0;
    static final double x=10.0;
    static final double TAU=0.1;


    static double TankFillTimeLin(double vt, double v0, double x) {
        if (x == 0) return Double.POSITIVE_INFINITY;
        return (vt - v0) / x;
    }


    static double TankFillTimeSim(double vt, double v0, double x, double tau) {
        double v = v0;
        double t = 0.0 -tau;
        clrscr();

         while ((x > 0 && v < vt) || (x < 0 && v > 0)){
            v += x * tau;
            t += tau;

            if (v > vt) v = vt;
            if (v < 0) v = 0;
            gotoxy(4,4);
            System.out.printf("Time: %.6f s%n", t);
            gotoxy(4,5);
            System.out.printf("Volume: %.6f L%n", v);

            delay(10);

            if ((x > 0 && v >= vt) || (x < 0 && v <= 0)){
                break;
            }

        }


        gotoxy(1,7);

        return t;
    }

    public static void main(String[] args) {
        cursor_hide();
        println("Starting simulation...");
        double tSim = TankFillTimeSim(VT, V0, x, TAU);
        double tLin = TankFillTimeLin(VT, V0, x);

        println(" ");
        System.out.printf("Simulation time: %.6f ", tSim);
        System.out.printf("Linear time: %.6f ", tLin);
    }
}
