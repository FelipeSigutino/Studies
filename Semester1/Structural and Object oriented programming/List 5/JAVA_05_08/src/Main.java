
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

boolean quit = false;

class TPoint{
    int x;
    int y;
}

TPoint Point(int x, int y){
    TPoint p = new TPoint();
    p.x = x;
    p.y = y;
    return p;
}

record TPath(TPoint[] points){
    static int index = 0;
}

class TPlayer{
    TPoint Postion;
    int speed;
    int direction;
    int color;
    TPoint LastPosition;
    boolean lost;
    int length;
    int CurrentLenght;
}

TPlayer CreatePlayer(int x, int y, int speed, int direction, int color){
    TPlayer p = new TPlayer();
    p.Postion = Point(x,y);
    p.speed = speed;
    p.color = color;
    p.direction = direction;
    p.LastPosition = null;
    p.lost = false;
    p.length = 4;
    for (int i = 0;  i < p.length;  i++) {
        AddPointToSection(Point(x-i,y),Path);
    }
    return p;
};

TPath CreatePath(){
    TPath p = new TPath(new TPoint[2000]);
    CreateSectionsToPathXY(p, Point(1,1), Point(120,1),Point(120,30),Point(1,30), Point(1,1));
    return p;
}

TPath CreateSectionToPathXY(TPath path, TPoint p1, TPoint p2) {
    int Case;
    if(p1.x == p2.x){Case=1;}
    else if(p1.y == p2.y){Case=2;}
    else if(Math.abs(p1.x-p2.x) == Math.abs(p1.y-p2.y)){Case=3;}
    else {Case=4;}

    switch (Case){
        case 1 ->{
            for (int i = 0; i < Math.abs(p1.y-p2.y)+1;  i++) {
                int dy = p1.y-  p2.y;

                int direction = (dy > 0) ? 1 : -1;

                TPoint point = Point( p1.x,p1.y  - direction*i);

                if (!IsPointInPath(path, point)) {
                    AddPointToSection(point, path);
                }
            }

        }
        case 2 ->{
            for (int i = 0; i < Math.abs(p1.x-p2.x)+1;  i++) {
                int dx = p1.x-  p2.x;

                int direction = (dx > 0) ? 1 : -1;
                TPoint point = Point( p1.x - direction*i, p1.y);
                if (!IsPointInPath(path, point)) {
                    AddPointToSection(point, path);
                }
            }

        }
        case 3 ->{
            int dx = p2.x - p1.x; //checks whether second point or first point has bigger value
            int dy = p2.y - p1.y;

            int xGoesRight = (dx > 0) ? 1 : -1; //assigns value that will reasure us that it will follow good path
            int yGoesDown = (dy > 0) ? 1 : -1;
            for (int i = 0; i < Math.abs(p1.x-p2.x)+1;  i++) {
                TPoint point = Point(p1.x + i * xGoesRight,p1.y + i * yGoesDown);


                AddPointToSection(point, path);

            }
        }
        case 4 ->{

        }
    }
    return path;
}

TPath CreateSectionsToPathXY(TPath path, TPoint... points) {
    for (int i = 0; i < points.length -1; i++){
        CreateSectionToPathXY(path, points[i], points[i+1]);
    }
    return path;
}

TPath AddPointToSection(TPoint p, TPath path){

    if (path.index > 0) {
        TPoint prev = path.points[path.index - 1];
        if (prev.x == p.x && prev.y == p.y) {
            return path;
        }
    }

    path.points[TPath.index] = p;
    TPath.index++;
    return path;
}

TPoint IfPointInPathReturn(TPath path, int index){
    if (path.points[index] != null) {
        TPoint point = path.points[index];

        return point;
    } else {
        return null;
    }
}

boolean IsPointInPath(TPath path, TPoint p){
    for (int i = 0; i < path.points().length; i++) {
        TPoint point = path.points[i];
        if (point != null) {
            if (p.x == point.x && p.y == point.y) {
                return true;
            }
        }
    }
    return false;
}

void DrawPath(TPath p, char c){

    for (int i = 0; i < p.points.length; i++){
        if (p.points[i] != null) {
            gotoxy(p.points[i].x, p.points[i].y);
            print(c);
        }
    }
    gotoxy(12, 15);
    print(" ");
}

void DrawStringOnPath(TPath path, int startIndex, String s, int FirstLoop, int direction){
    int count;
    int loopindex;
    if (FirstLoop == 1) {
        count = 296;
        loopindex = 0;
    } else {
        count = 80;
        loopindex = 296;
    }

    for (int j = 0; j < s.length(); j++) {

        int correctIndex = (startIndex+j)%count+loopindex;

        TPoint p = path.points[correctIndex];
        if (p == null) return;

        gotoxy(p.x, p.y);
        print(s.charAt(j));
    }
    if (direction == 1) {
        TPoint p = path.points[(startIndex-1) % count + loopindex];
        gotoxy(p.x, p.y);
        print("*");
    } else {
        TPoint p = path.points[(startIndex + s.length()) % count + loopindex];
        gotoxy(p.x, p.y);
        print("*");
    }
}


boolean CheckForQ(){
    if(keypressed()) {
        String key = readkeystr();
        if (key.equals("q")) {
            quit = true;
        }
    }
    return quit;
}

boolean WaitForQ(){
    while (!quit){
        if(keypressed()) {
            String key = readkeystr();
            if (key.equals("q")) {
                quit = true;
            }
        }
    }
    return quit;
}

void WhatPointsAreInPath(TPath path){
    for (int i = 0;i < 2000; i++){
        TPoint point = IfPointInPathReturn(path, i);
        if (point != null){
            println(i +" " + point.x + " " + point.y);
        }
    }
}

void CheckIfPositionAcceptable(TPath path, TPlayer p){
    TPoint Point = Point(p.Postion.x,p.Postion.y);
    switch (p.direction) {
        case 1:
            Point.x += 1;
            break;
        case 2:
            Point.y -= 1;
            break;
        case 3:
            Point.x -= 1;
            break;
        case 4:
            Point.y += 1;
    }
    if (IsPointInPath(path,Point)){
        p.lost = true;
    }
}

void MovePlayer(TPlayer p,TPath path) {
    for (int i = p.length - 1; i > 0; i--) {
        path.points[296 + i] = path.points[296 + i - 1];
    }

    switch (p.direction) {
        case 1:
            p.Postion.x += 1;
            break;
        case 2:
            p.Postion.y -= 1;
            break;
        case 3:
            p.Postion.x -= 1;
            break;
        case 4:
            p.Postion.y += 1;
    }
    Path.points[296] = Point(p.Postion.x,p.Postion.y);
}

void CheckControl(TPlayer p){
    if (p == Player1){
        if (keypressed()) {
            String key = readkeystr();
            switch (key) {
                case "w":
                    if (p.direction == 4) {p.lost = true;}
                    p.direction = 2;
                    break;
                case "s":
                    if (p.direction == 2) {p.lost = true;}
                    p.direction = 4;
                    break;
                case "a":
                    if (p.direction == 1) {p.lost = true;}
                    p.direction = 3;
                    break;
                case "d":
                    if (p.direction == 3) {p.lost = true;}
                    p.direction = 1;
            }
        }
    }
    else if (1 == 0) {
        if (keypressed()) {
            String key = readkeystr();
            switch (key) {
                case "arrow_up": p.direction = 2;
                    break;
                case "arrow_dn": p.direction = 4;
                    break;
                case "arrow_lt": p.direction = 3;
                    break;
                case "arrow_rt": p.direction = 1;
            }
        }
    }

}

void DrawSnake(TPlayer p, TPath path){
    gotoxy(p.Postion.x, p.Postion.y);
    setfgcolor(p.color);
    print("@");

    for (int i = 0;i<p.length-2; i++) {
        TPoint segment = path.points[297 + i];
        if (segment == null) continue;
        gotoxy(segment.x, segment.y);
        print("#");
    }
}

void DrawBehindPlayerMovement(TPlayer p, TPath path){
    TPoint Tail = path.points[296 + p.length - 1];
    if (Tail != null) {
        gotoxy(Tail.x, Tail.y);

        print(" ");
    }
}

void DrawApple(TPoint apple) {
    setfgcolor(9); // red
    gotoxy(apple.x, apple.y);
    print("$");     // apple symbol
}

TPoint GenerateApple(TPath path) {
    while (true) {
        int x = (int)(Math.random() * 108) + 10;  // valid X: 2..119
        int y = (int)(Math.random() * 23) + 5;   // valid Y: 2..29

        TPoint apple = Point(x, y);


        if (!IsPointInPath(path, apple)) {
            return apple;
        }
    }
}
TPoint Apple;
TPath Path = CreatePath();
TPlayer Player1 = CreatePlayer(15,15,200,1,4);
//TPlayer Player2 = CreatePlayer(110,15,200,3,9);

void Init() {
    clrscr();
    cursor_hide();



    DrawPath(Path, '*');

    Apple = GenerateApple(Path);
    DrawApple(Apple);

    setfgcolor(15);
    DrawStringOnPath(Path, 55, "Snake",1,1);
    DrawStringOnPath(Path, 195, "margorp hsiniF ot q sserP",1,1);

}

void Model() {

    CheckIfPositionAcceptable(Path,Player1);
//    CheckIfPositionAcceptable(Path,Player2);

    if (Player1.Postion.x == Apple.x && Player1.Postion.y == Apple.y) {

        Player1.length++;

        Apple = GenerateApple(Path);
        DrawApple(Apple);
    }

    MovePlayer(Player1, Path);

//    MovePlayer(Player2);
}

void Control() {
    for (int i = 0;i < 10;i++) {
        CheckForQ();
        CheckControl(Player1);
    }
//    CheckControl(Player2);
}

void View() {
//    if (Player1.lost && Player2.lost) {
//        setfgcolor(15);
//        gotoxy(60, 15);
//        print("Its a Draw!");
//        WaitForQ();
//        quit = true;
//        return;
//    }
//
    if (Player1.lost) {
        setfgcolor(15);
        gotoxy(60, 15);
        print("You Lost");
        WaitForQ();
        quit = true;
        return;
    }
//
//    if (Player2.lost) {
//        setfgcolor(15);
//        gotoxy(60, 15);
//        print("Red Player Won!");
//        WaitForQ();
//        quit = true;
//        return;
//    }

    DrawBehindPlayerMovement(Player1, Path);
//    DrawBehindPlayerMovement(Player2);

    DrawSnake(Player1, Path);
//    DrawPlayerMovement(Player2);
}
int i = 0;
void main() {

    Init();
    while (!quit) {
        Model();
        View();
        Control();


        delay(100);
        i ++;
    }

//    WhatPointsAreInPath(Path);
    setfgcolor(15);
    gotoxy(1,300);
}
