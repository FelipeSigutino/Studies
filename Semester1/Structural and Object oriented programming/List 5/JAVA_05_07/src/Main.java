
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
}

TPlayer CreatePlayer(int x, int y, int speed, int direction, int color){
    TPlayer p = new TPlayer();
    p.Postion = Point(x,y);
    p.speed = speed;
    p.color = color;
    p.direction = direction;
    p.LastPosition = null;
    p.lost = false;
    return p;
};

TPath CreatePath(){
    TPath p = new TPath(new TPoint[2000]);

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
    for (int i = 0; i < path.index; i++) {
        TPoint point = path.points[i];
        if (p.x == point.x && p.y == point.y) {
            return true;
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

void MovePlayer(TPlayer p) {
    p.LastPosition = new TPoint();
    p.LastPosition.x = p.Postion.x;
    p.LastPosition.y = p.Postion.y;

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
    AddPointToSection(p.Postion, Path);
}

void CheckControl(TPlayer p){
    if (p == Player1){
        if (keypressed()) {
            String key = readkeystr();
            switch (key) {
                case "w": p.direction = 2;
                    break;
                case "s": p.direction = 4;
                    break;
                case "a": p.direction = 3;
                    break;
                case "d": p.direction = 1;
            }
        }
    }
    else if (p == Player2) {
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

void DrawPlayerMovement(TPlayer p){
    gotoxy(p.Postion.x, p.Postion.y);
    setfgcolor(p.color);
    print("#");
}

void DrawBehindPlayerMovement(TPlayer p){
    gotoxy(p.LastPosition.x, p.LastPosition.y);
    setfgcolor(p.color);
    print("*");
}

TPath Path = CreatePath();
TPlayer Player1 = CreatePlayer(3,15,200,1,4);
TPlayer Player2 = CreatePlayer(110,15,200,3,9);
int t = 0;
void Init() {
    clrscr();
    cursor_hide();

    CreateSectionsToPathXY(Path, Point(1,1), Point(120,1),Point(120,30),Point(1,30), Point(1,1));

    DrawPath(Path, '*');

    DrawStringOnPath(Path, 55, "Tron",1,1);
    DrawStringOnPath(Path, 195, "margorp hsiniF ot q sserP",1,1);
}

void UpdateModel() {
    CheckIfPositionAcceptable(Path,Player1);
    CheckIfPositionAcceptable(Path,Player2);

    MovePlayer(Player1);
    MovePlayer(Player2);

}

void Control() {

    CheckForQ();
    CheckControl(Player1);
    CheckControl(Player2);

}

void View() {
    if (Player1.lost && Player2.lost) {
        setfgcolor(15);
        gotoxy(60, 15);
        print("Its a Draw!");
        WaitForQ();
        quit = true;
        return;
    }

    if (Player1.lost) {
        setfgcolor(15);
        gotoxy(55, 15);
        print("Red Player Won!");
        WaitForQ();
        quit = true;
        return;
    }

    if (Player2.lost) {
        setfgcolor(15);
        gotoxy(55, 15);
        print("Blue Player Won!");
        WaitForQ();
        quit = true;
        return;
    }




    DrawBehindPlayerMovement(Player1);
    DrawBehindPlayerMovement(Player2);

    DrawPlayerMovement(Player1);
    DrawPlayerMovement(Player2);
}

void main() {

    Init();
    while (!quit) {
        UpdateModel();
        View();
        Control();

        delay((int) (200-t*0.2));
        t++;
    }

//    WhatPointsAreInPath(Path);
    setfgcolor(15);
    gotoxy(1,31);
}