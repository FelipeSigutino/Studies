
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

boolean quit = false;
class TString{
    int InitialPosition;
    int Direction;
    int FirstLoop;
    String text;
}

TString CreateString(int InitialPosition, int Direction, int FirstLoop, String text){
    TString s = new TString();
    s.InitialPosition = InitialPosition;
    s.Direction = Direction;
    s.FirstLoop = FirstLoop;
    s.text = text;
    return s;
}

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

int normalizeIndex(int pos, int firstLoop) {
    if (firstLoop == 1)
        return pos % 296;        
    else
        return (pos % 80) + 296;
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

void AnimateStringsOnPath(TPath path,TString... Strings ) {

    while (!quit) {
        for (int i = 0; i < Strings.length; i++) {
            TString s = Strings[i];
            DrawStringOnPath(path, s.InitialPosition, s.text, s.FirstLoop, s.Direction);

            TString s1 = Strings[0];
            TString s2 = Strings[1];

            int S1Start = s1.InitialPosition;
            int S2Start = s2.InitialPosition;

            int S1End = s1.InitialPosition + s1.text.length();
            int S2End = s2.InitialPosition + s2.text.length();

            int ListOfIndexes[];
            ListOfIndexes = new int[S1End -  S1Start];
            for (int j = 0; j < s1.text.length(); j++){
                ListOfIndexes[j] = S1Start + j;
            }

            boolean hit = false;
            for  (int j = 0; j < s1.text.length(); j++){
                if (ListOfIndexes[j] == S2Start) {
                    hit = true;
                } else if (ListOfIndexes[j] == S2End) {
                    hit = true;
                }

            }
            if (hit == true) {
                s1.Direction *= -1;
                s2.Direction *= -1;
            }

            if (s.Direction == 1) {
                s.InitialPosition += 1;
            } else {
                s.InitialPosition -= 1;
            }
            if (s.InitialPosition < 0 && s.FirstLoop == 1) {
                s.InitialPosition = 295;
            } else if (s.InitialPosition < 0 && s.FirstLoop != 1) {
                s.InitialPosition = 84;
            }


        }

        CheckForS();
        delay(10);
    }
}

boolean CheckForS(){
    if(keypressed()) {
        String key = readkeystr();
        if (key.equals("s")) {
            quit = true;
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

void main() {
    clrscr();
    cursor_hide();

    TPath Path = CreatePath();

    CreateSectionsToPathXY(Path, Point(1,1), Point(120,1),Point(120,30),Point(1,30), Point(1,1));
//    CreateSectionsToPathXY(Path, Point(49,6), Point(69,6), Point(69,26), Point(49,26), Point(49,6));
    TString WelcomeToOurGame = CreateString(60 ,1, 1,">> Welcome To Our Game <<");
    TString PressSpaceToStart = CreateString(181,-1, 1,">> Pressspacetostart <<");
//    TString BeeReady1 = CreateString(48, 1, -1, "BeeReady");
//    TString BeeReady2 = CreateString(7,1, -1,"BeeReady");
    DrawPath(Path, '*');


    AnimateStringsOnPath(Path,WelcomeToOurGame,PressSpaceToStart);


//    WhatPointsAreInPath(Path);



    gotoxy(1,31);
}