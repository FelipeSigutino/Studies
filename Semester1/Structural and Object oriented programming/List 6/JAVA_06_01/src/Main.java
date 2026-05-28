
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;




public class Main {

    boolean quit = false;
    TString BUSSIN;
    TString BUSSIN2;
    TPathCorners path;


    public static class TPoint {
        int x;
        int y;
    }

    public static TPoint Point(int x, int y) {
        TPoint p = new TPoint();
        p.x = x;
        p.y = y;
        return p;
    }

    public class TString {
        String text;
        int index;
        int clockWise;
    }

    TString createString(String text, int startingIndex, int clockWise) {
        TString s = new TString();
        s.text = text;
        s.index = startingIndex;
        s.clockWise = clockWise;
        return s;

    }

    public static int addab(int a , int b){
        int res = a + b;
        return res;
    }

    class TPathCorners {
        TPoint[] Points;
        TSection[] Sections;
        TPoint beggining;

        int CurrentIndex;
        int AnimIndex;
    }

    public static class TSection {
        TPoint Beggining;
        TPoint Ending;
        char MovementAxis;
        boolean dirIncreasing;
        int Length;
    }

    TPathCorners CreatePathCorners(TPoint Beggining) {
        TPathCorners p = new TPathCorners();
        p.Points = new TPoint[2000];
        p.Sections = new TSection[2000];
        p.beggining = Beggining;
        p.CurrentIndex = 0;
        p.AnimIndex = 0;
        return p;
    }

    static TSection CreateSection(TPoint p1, TPoint p2) {
        TSection section = new TSection();
        int Case;
        if (p1.x == p2.x) {
            Case = 1;
        } else if (p1.y == p2.y) {
            Case = 2;
        } else if (Math.abs(p1.x - p2.x) == Math.abs(p1.y - p2.y)) {
            Case = 3;
        } else {
            Case = 4;
        }
        section.Beggining = p1;
        section.Ending = p2;
        switch (Case) {
            case 1 -> {
                if (p1.y < p2.y) {
                    section.dirIncreasing = true;
                } else {//p1.y > p2.y
                    section.dirIncreasing = false;
                }
                section.MovementAxis = 'y';
                section.Length = Math.abs(p2.y - p1.y) + 1;
            }
            case 2 -> {
                if (p1.x < p2.x) {
                    section.dirIncreasing = true;
                } else {
                    section.dirIncreasing = false;
                }
                section.MovementAxis = 'x';
                section.Length = Math.abs(p2.x - p1.x) + 1;
            }

            case 4 -> {

            }
        }
        return section;
    }

    void DrawSection(TSection s, char c) {
        int Case = 0;
        if (s.MovementAxis == 'y') {
            Case = 1;
        } else if (s.MovementAxis == 'x') {
            Case = 2;
        } else {
            Case = 4;
        }

        switch (Case) {
            case 1 -> {
                if (s.dirIncreasing) {
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x, s.Beggining.y + i);
                        delay(10);
                        print(c);
                    }
                } else {
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x, s.Beggining.y - i);
                        delay(10);
                        print(c);
                    }
                }
            }
            case 2 -> {
                if (s.dirIncreasing) {
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x + i, s.Beggining.y);
                        delay(10);
                        print(c);
                    }
                } else {
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x - i, s.Beggining.y);
                        delay(10);
                        print(c);
                    }
                }
            }
            case 4 -> {

            }
        }
    }

    public static Boolean CheckWhetherPointInSection(TSection s, TPoint p) {
        boolean pointInSection = false;
        int Case = 0;
        if (s.MovementAxis == 'y') {
            Case = 1;
        } else if (s.MovementAxis == 'x') {
            Case = 2;
        } else {
            Case = 4;
        }
        switch (Case) {
            case 1 -> {
                for (int i = 0; i < s.Length; i++) {
                    if (s.dirIncreasing) {
                        if (p.x == s.Beggining.x && p.y == s.Beggining.y + i) {
                            pointInSection = true;
                        }
                    } else {
                        if (p.x == s.Beggining.x && p.y == s.Beggining.y - i) {
                            pointInSection = true;
                        }
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < s.Length; i++) {
                    if (s.dirIncreasing) {
                        if (p.x == s.Beggining.x + i && p.y == s.Beggining.y) {
                            pointInSection = true;
                        }
                    } else {
                        if (p.x == s.Beggining.x - i && p.y == s.Beggining.y) {
                            pointInSection = true;
                        }
                    }
                }
            }
        }
        return pointInSection;
    }

    int WhichPointInSection(TSection s, TPoint p) {
        int index = -1;
        if (CheckWhetherPointInSection(s, p)) {
            int Case = 0;

            if (s.MovementAxis == 'y') {
                Case = 1;
            } else if (s.MovementAxis == 'x') {
                Case = 2;
            } else {
                Case = 4;
            }
            switch (Case) {
                case 1 -> {
                    if (s.dirIncreasing) {
                        for (int i = 0; i < s.Length; i++) {
                            if (s.Beggining.y + i == p.y && s.Beggining.x == p.x) {
                                index = i;
                            }
                        }
                    } else {
                        for (int i = 0; i < s.Length; i++) {
                            if (s.Beggining.y - i == p.y && s.Beggining.x == p.x) {
                                index = i;
                            }
                        }
                    }
                }
                case 2 -> {
                    if (s.dirIncreasing) {
                        for (int i = 0; i < s.Length; i++) {
                            if (s.Beggining.x + i == p.x && s.Beggining.y == p.y) {
                                index = i;
                            }
                        }
                    } else {
                        for (int i = 0; i < s.Length; i++) {
                            if (s.Beggining.x - i == p.x && s.Beggining.y == p.y) {
                                index = i;
                            }
                        }
                    }
                }
            }
        }
        return index;
    }

    static TPoint WhichPointInSectionByIndex(TSection s, int index) {
        int Case = 0;
        TPoint p = new TPoint();
        if (s.MovementAxis == 'y') {
            Case = 1;
        } else if (s.MovementAxis == 'x') {
            Case = 2;
        } else {
            Case = 4;
        }
        switch (Case) {
            case 1 -> {
                if (s.dirIncreasing) {
                    p.x = s.Beggining.x;
                    p.y = s.Beggining.y + index;
                } else {
                    p.x = s.Beggining.x;
                    p.y = s.Beggining.y - index;
                }
            }
            case 2 -> {
                if (s.dirIncreasing) {
                    p.x = s.Beggining.x + index;
                    p.y = s.Beggining.y;
                } else {
                    p.x = s.Beggining.x - index;
                    p.y = s.Beggining.y;
                }
            }
        }
        return p;
    }

    TPathCorners AddPointToPath(TPathCorners p, TPoint point) {
        p.Points[p.CurrentIndex] = point;
        p.CurrentIndex++;
        return p;
    }

    void DrawPath(TPathCorners p) {

        for (int i = 0; i < p.CurrentIndex; i++) {
            TSection section;
            TSection sectionproper;
            if (i == 0) {
                section = CreateSection(p.beggining, p.Points[i]);
                sectionproper = section;
            } else {
                section = CreateSection(p.beggining, p.Points[i]);
                switch (section.MovementAxis) {
                    case 'y' -> {
                        if (section.dirIncreasing) {
                            p.beggining.y = p.beggining.y + 1;
                        } else {
                            p.beggining.y = p.beggining.y - 1;
                        }
                    }
                    case 'x' -> {
                        if (section.dirIncreasing) {
                            p.beggining.x = p.beggining.x + 1;
                        } else {
                            p.beggining.x = p.beggining.x - 1;
                        }
                    }
                }
                sectionproper = CreateSection(p.beggining, p.Points[i]);
            }

            DrawSection(sectionproper, 'X');
            p.beggining = p.Points[i];
            p.Sections[i] = sectionproper;
        }
    }

    int calculateHowLongPath(TPathCorners p) {
        int length = 0;
        for (int i = 0; i < p.CurrentIndex; i++) {
            length = length + p.Sections[i].Length;
        }
        return length;
    }

    void printAllSectionsLenghts(TPathCorners p) {
        for (int i = 0; i < p.CurrentIndex; i++) {
            println(p.Sections[i].Length);
        }
    }

    TPoint whichPointInPathByIndex(TPathCorners p, int count) {
        int index = count % calculateHowLongPath(p);  //This effectively makes us skip point which overlaps Yieppie
        TPoint point = new TPoint();
        for (int i = 0; i < p.CurrentIndex; i++) {
            if (index > p.Sections[i].Length - 1) {
                index = index - p.Sections[i].Length;
            } else {
                point = WhichPointInSectionByIndex(p.Sections[i], index);
                break;
            }
        }
        return point;
    }

    void drawCharOnPathWithStartingIndex(TPathCorners p, int index, char W) {
        TPoint previousPoint = whichPointInPathByIndex(p, index - 1);
        TPoint point = whichPointInPathByIndex(p, index);
        gotoxy(point.x, point.y);
        print(W);


    }

    void drawStringOnPathWithStartingIndex(TPathCorners p, int index, TString s) {
        if (s.clockWise == 1) {
            for (int i = 0; i < s.text.length(); i++) {
                drawCharOnPathWithStartingIndex(p, index + i, s.text.charAt(i));
            }
            TPoint previousPoint = whichPointInPathByIndex(p, index - 1);
            gotoxy(previousPoint.x, previousPoint.y);
            print("X");
        } else {
            for (int i = 0; i < s.text.length(); i++) {
                drawCharOnPathWithStartingIndex(p, index + i, s.text.charAt(i));
            }
            TPoint previousPoint = whichPointInPathByIndex(p, index + s.text.length());
            gotoxy(previousPoint.x, previousPoint.y);
            print("X");
        }
    }

    boolean CheckForS() {
        if (keypressed()) {
            String key = readkeystr();
            if (key.equals("s")) {
                quit = true;
            }
        }
        return quit;
    }

    void animateStringOnPath(TPathCorners p, TString s) {
        if (s.clockWise == 1) {
            drawStringOnPathWithStartingIndex(p, s.index + 1, s);
            s.index += 1;
        } else {
            while (s.index - 1 < 0) {
                s.index = s.index + calculateHowLongPath(p);
            }
            drawStringOnPathWithStartingIndex(p, s.index - 1, s);
            s.index -= 1;
        }

    }

    boolean doesStringCollide(TPathCorners path, TString s1, TString s2) {
        boolean result = false;
        if (s1.clockWise == s2.clockWise) {
        } else {
            for (int i = 0; i < s1.text.length(); i++) {
                if (s1.clockWise == 1) {
                    if ((s1.index + i) % calculateHowLongPath(path) == s2.index % calculateHowLongPath(path)) {
                        result = true;
                    }
                } else {
                    if ((s1.index - i) % calculateHowLongPath(path) == s2.index % calculateHowLongPath(path)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    void reverseDirOfBouncedStrings(TPathCorners path, TString s, TString s1) {
        if (doesStringCollide(path, s, s1)) {
            s.clockWise *= -1;
            s1.clockWise *= -1;
        }
    }

    //doesStringCollide(s,s1)
    void Init() {
        clrscr();
        cursor_hide();

        path = CreatePathCorners(Point(1, 1));
        BUSSIN = createString("HIPPOPATOSOSDAJGFF", 20, -1);
        BUSSIN2 = createString("BUSSSIN2", 1, 1);

        AddPointToPath(path, Point(120, 1));
        AddPointToPath(path, Point(120, 30));
        AddPointToPath(path, Point(1, 30));
        AddPointToPath(path, Point(1, 1));

        DrawPath(path);
    }

    int i = 0;

    void main() {
        Init();

        while (!quit) {
            animateStringOnPath(path, BUSSIN); //f real I need to implement view here
            animateStringOnPath(path, BUSSIN2);

            reverseDirOfBouncedStrings(path, BUSSIN, BUSSIN2);

            delay(200);
            CheckForS();
        }
        gotoxy(1, 31);
    }
}
//is nice for now i need to focus on

// 2) Performing Tests
// 3) Beep Optamisation With tests
// 4) Starting From New point continuation of path
// 5) Diagonal case