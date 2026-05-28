
import java.util.Random;
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;
int k = 12345;
Random rand = new Random(k);
SecureRandom rand2 = new SecureRandom();


int[][] matrix = new int[30][100];

class TPoint {
    int x;
    int y;
}

TPoint CreatePoint() {
    TPoint p = new TPoint();
    p.x =  rand2.nextInt(100);
    p.y = rand2.nextInt(30);
    return p;
}
void printMatrix() {
    for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 100; j++) {
            print(matrix[i][j]);
        }
        println();
    }
}

void RandMtx(int Simulation){
    if (Simulation == 2) {
        for (int i = 0; i < 500000; i++) {

            rand.setSeed(k);
            int y = rand.nextInt(30);
            k++;
            rand.setSeed(k);
            int x = rand.nextInt(100);
            k++;
            matrix[y][x]++;

        }
    }
    if (Simulation == 1) {
        for (int i = 0; i < 500000; i++) {

            int y = rand.nextInt(30);

            int x = rand.nextInt(100);

            matrix[y][x]++;

        }
    }
    if  (Simulation == 3) {
        for (int i = 0; i < 500000; i++) {
            int y = rand2.nextInt(30);

            int x = rand2.nextInt(100);
            matrix[y][x]++;
        }
    }
    if (Simulation == 4) {
        for (int i = 0; i < 500000; i++) {
            rand2.setSeed(k);
            int y = rand2.nextInt(30);
            k++;
            rand2.setSeed(k);
            int x = rand2.nextInt(100);
            k++;
            matrix[y][x]++;
        }
    }
}

void InitMatrix() {
    for  (int i = 0; i < 100; i++) {
        for (int j = 0; j < 30; j++) {
            matrix[j][i] = (int) (0);
        }
    }
}

int findMax(int[][] matrix) {
    int max = matrix[0][0];
    for (int y = 0; y < 30; y++) {
        for (int x = 0; x < 100; x++) {
            if (matrix[y][x] > max) {
                max = matrix[y][x];
            }
        }
    }
    return max;
}

int findMin(int[][] matrix) {
    int min = matrix[0][0];
    for (int y = 0; y < 30; y++) {
        for (int x = 0; x < 100; x++) {
            if (matrix[y][x] < min) {
                min = matrix[y][x];
            }
        }
    }
    return min;
}

void PresentResoult() {
    int hist_min = findMin(matrix);
    int hist_max = findMax(matrix);
    for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 100; j++) {

            int green = (int)(255.0 * ((matrix[i][j] - hist_min) / (double) (hist_max - hist_min)));
            setfgcolor_rgb(10,green,10);
            write('█');

        }
        println();
    }
}

void main() {
    clrscr();
    cursor_hide();

    InitMatrix();
    RandMtx(3);
    PresentResoult();

    delay(5000);
    clrscr();
    RandMtx(4);
    PresentResoult();
    setfgcolor(15);
}
