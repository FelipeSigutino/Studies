
import javax.swing.*;

import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;
import java.util.Random;

static Random rand = new Random(6);

public static class TTrain {
    double  position;
    double  speed;
    double  acceleration;
    double  maxSpeed;
    int direction;
    boolean isDestroyed;
}

public static class TTrack {
    double length;
    int width;
}

public static class TSimulation {
    TTrain[] trains;
    TTrack track;
    double tau;
    int delay;
}

static void Train(TTrain t, double tau) {
    if (t.isDestroyed) return;

    t.speed = t.speed + t.acceleration * tau;
    if (t.direction == 1) {
        t.position = t.position + t.speed * tau;
    }
    else {
        t.position = t.position - t.speed * tau;
    }
}

static void TrainCollision(TSimulation s) {
    for (int i = 0; i < s.trains.length; i++) {
        for (int j = i + 1; j < s.trains.length; j++) {
            TTrain t1 = s.trains[i];
            TTrain t2 = s.trains[j];
            if (Math.abs(s.trains[i].position-s.trains[j].position)<(s.trains[i].position/s.track.length * s.track.width)) {
                t2.isDestroyed = true;
                t1.isDestroyed = true;
            }
        }
    }

    for (int i = 0; i < s.trains.length; i++) {
        if (s.trains[i].position <= 0 || s.trains[i].position >= s.track.length) {
            s.trains[i].isDestroyed = true;
        }
    }
}

public static void Action(TSimulation s) {
    for (int i = 0; i < s.track.width; i++) print(" ");
    gotoxy(5, 10);

    for (int i = 0; i < s.track.width; i++) {
        boolean drawn = false;

        for (int j = 0; j < s.trains.length; j++) {
            int pos = (int)(s.trains[j].position / s.track.length * s.track.width);
            if (pos < 0) pos = 0;
            if (pos >= s.track.width) pos = s.track.width - 1;

            if (i == pos) {
                if (s.trains[j].isDestroyed) {
                    setfgcolor(red);
                    print("X");
                    setfgcolor(white);
                } else if (s.trains[j].direction == 1) {
                    print(">");
                } else {
                    print("<");
                }
                drawn = true;
                break;
            }
        }

        if (!drawn) print("=");
    }
}

 static void Simulation(TSimulation s) {
    double t = 0.0;
    cursor_hide();

    while (true) {

        gotoxy(5,4);
        System.out.printf("Simulation t = %.2f s.", t);


        for (int i = 0; i < s.trains.length; i++) {
            Train(s.trains[i], s.tau);
            gotoxy(5,5 + i);
            System.out.printf("Velocity = %.3f m/s, Acceleration = %.3f m/s^2, x1 = %.3f m", s.trains[i].speed, s.trains[i].acceleration, s.trains[i].position);
        }

        TrainCollision(s);
        Action(s);

        if (keypressed()) {
            String key = readkeystr();
            if (key.equals("q")) {
                gotoxy(5, 11);
                System.out.printf("Simulation stopped by user at t = %.2f s.", t);
                break;
            }
        }

        t = t + s.tau;
        delay(s.delay);
    }
}

public static void main() {
    clrscr();
    cursor_hide();
    setfgcolor(white);

    TSimulation s = new TSimulation();
    s.track = new TTrack();
    s.track.length = 50000;
    s.track.width = 100;
    s.tau = 0.1;
    s.delay = 50;

    s.trains = new TTrain[5];
    for (int i = 0; i < s.trains.length; i++) {
        s.trains[i] = new TTrain();
        s.trains[i].position = rand.nextDouble(1) * s.track.length;
        s.trains[i].speed = 0;
        s.trains[i].acceleration = 20 + rand.nextInt(5) * 3;
        s.trains[i].maxSpeed = 10 + rand.nextInt(3) * 10;
        s.trains[i].direction = rand.nextInt(2);
        s.trains[i].isDestroyed = false;
    }

    Simulation(s);
}
