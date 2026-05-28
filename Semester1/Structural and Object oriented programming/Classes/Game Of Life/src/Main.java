import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;



class TBoard {
    boolean [][] XY;
    int width;
    int height;
    boolean [][] memory;
    int alivearound[][];
}

TBoard CreateBoard(int width, int height) {
    TBoard b = new TBoard();
    b.width = width;
    b.height = height;
    b.memory = new boolean[height][width];
    b.XY = new boolean[height][width];
    b.alivearound = new int[height][width];

    for(int i = 0; i < height; i++) {
        for(int j = 0; j < width; j++) {
            b.XY[i][j] = Math.random() < 0.5 ;
        }
    }
    return b;
}

class TGameOfLife {

}

String DrawBoardToBuffer(TBoard b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.XY.length; i++) {
            for (int j = 0; j < b.XY[i].length; j++) {
                sb.append(b.XY[i][j] ? "█" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
}



void AliveAroundCell(TBoard b) {
    for (int i = 0; i < b.height; i++) {
        for (int j = 0; j < b.width; j++) {
            b.alivearound[i][j] = 0;
        }
    }

    for(int i = 0; i < b.height; i++) {
        for(int j = 0; j < b.width; j++) {

            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (k == 0 && l == 0){
                        continue;
                    }

                    int x = i + k;
                    int y = j + l;

                    if(x<0 || x >= b.height) {continue;}
                    if(y<0 || y >= b.width) {continue;}

                    if (b.XY[x][y]) {
                        b.alivearound[i][j] ++;
                    }
                }
            }
        }
    }
}

void AliveOrDead(TBoard b) {
    for(int i = 0; i < b.height; i++) {
        for(int j = 0; j < b.width; j++) {
            int neighbors = b.alivearound[i][j];

            if (b.XY[i][j]) {
                // alive cell
                b.memory[i][j] = neighbors == 2 || neighbors == 3;
            } else {
                // dead cell
                b.memory[i][j] = neighbors == 3;
            }
        }
    }
}

void MemoryToNew(TBoard b) {
    for(int i = 0; i < b.height; i++) {
        for(int j = 0; j < b.width; j++) {
            b.XY[i][j] = b.memory[i][j];
        }
    }
}
boolean quit = false;
boolean checkForQ() {
    if (keypressed()) {
        String key = readkeystr();
        if (key.equals("q")) quit = true;
    }
    return quit;
}

void main() {
    clrscr();
    TBoard Board = CreateBoard(150, 40);


    while (!quit) {

        gotoxy(1,1);

        print(DrawBoardToBuffer(Board));
        delay(100);

        AliveAroundCell(Board);
        AliveOrDead(Board);
        MemoryToNew(Board);

        checkForQ();
    }
}