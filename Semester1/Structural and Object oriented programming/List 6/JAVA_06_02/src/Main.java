
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;




public class Main {

    boolean quit = false;
    TString Beready1;
    TString Beready2;
    TString Welcome;
    TString Press;
    TSectionPath path1;
    TSectionPath path2;
    int i = 0;

    static class TPoint {
        int x;
        int y;
    }

    static TPoint Point(int x, int y) {
        TPoint p = new TPoint();
        p.x = x;
        p.y = y;
        return p;
    }

    static class TString {
        String text;
        int color;
        int index;
        int clockWise;
    }

    static TString createString(String text, int startingIndex, int clockWise, int color) {
        TString s = new TString();
        s.text = text;
        s.color = color;
        s.index = startingIndex;
        s.clockWise = clockWise;
        return s;

    }

    static int addab(int a , int b){
        int res = a + b;
        return res;
    }

    static class TSectionPath {
        TPoint[] Points;
        TSection[] Sections;
        TPoint beggining;

        boolean[] JumpPoint;
        char sign;
        int color;
        int CurrentIndex;
        int AnimIndex;
    }

    static class TSection {
        TPoint Beggining;
        TPoint Ending;
        char MovementAxis;
        boolean dirIncreasing;
        int Length;
    }

    static TSectionPath CreateTSectionPath(TPoint Beggining, int color, char c) {
        TSectionPath p = new TSectionPath();
        p.Points = new TPoint[2000];
        p.Sections = new TSection[2000];
        p.JumpPoint = new boolean[2000];
        p.sign = c;
        p.beggining = Beggining;
        p.color = color;
        p.CurrentIndex = 0;
        p.AnimIndex = 0;
        return p;
    }

    static TSection CreateSection(TPoint p1, TPoint p2,TSectionPath p) {
        TSection section = new TSection();
        int Case;
        if (p1.x == p2.x) {
            Case = 1;
        } else if (p1.y == p2.y) {
            Case = 2;
        } else if (Math.abs(p1.x - p2.x) == Math.abs(p1.y - p2.y)) {
            Case = 3;
        } else if (p.JumpPoint[p.CurrentIndex]) {
            Case = 4;
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
            case 3-> {
                if (p1.y > p2.y && p1.x < p2.x || p1.y > p2.y && p1.x > p2.x) {
                    section.dirIncreasing = true;
                } else  {
                    section.dirIncreasing = false;
                }
                section.MovementAxis = 'z';
                section.Length = Math.abs(p2.y - p1.y) + 1;
            }

            case 4 -> {

            }
        }
        return section;
    }


    void DrawSection(TSection s, char c, TSectionPath p) {
        int Case = 0;
        if (s.MovementAxis == 'y') {
            Case = 1;
        } else if (s.MovementAxis == 'x') {
            Case = 2;
        } else if (s.MovementAxis == 'z') {
            Case = 3;
        } else {
            Case = 4;
        }
        setfgcolor(p.color);
        switch (Case) {
            case 1 -> {
                if (s.dirIncreasing) {
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x, s.Beggining.y + i);
                        delay(10);

                        print(c);
                    }
                } else {
                    setfgcolor(p.color);
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x, s.Beggining.y - i);
                        delay(10);
                        print(c);
                    }
                    setfgcolor(15);
                }
            }
            case 2 ->{
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
            case 3 -> {
                if (s.dirIncreasing) {
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x + i, s.Beggining.y - i);
                        delay(10);
                        print(c);
                    }
                } else{
                    for (int i = 0; i < s.Length; i++) {
                        gotoxy(s.Beggining.x + i, s.Beggining.y + i);
                        delay(10);
                        print(c);
                    }
                }
            }
            case 4 -> {

            }
        }
        setfgcolor(15);
    }

    void CheckForP() {
        if (keypressed()) {
            String key = readkeystr();
            if (key.equals("p")) {

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
        } else if (s.MovementAxis == 'z') {
            Case = 3;
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
            case 3 -> {
                if (s.dirIncreasing) {
                    p.x = s.Beggining.x + index;
                    p.y = s.Beggining.y - index;
                }
            }
        }
        return p;
    }

    TSectionPath AddPointToPath(TSectionPath p, TPoint point, boolean JumpPoint) {
        p.Points[p.CurrentIndex] = point;
        p.JumpPoint[p.CurrentIndex] = JumpPoint;
        p.CurrentIndex++;
        return p;
    }

    void drawAndCreatePathSections(TSectionPath p) {

        for (int i = 0; i < p.CurrentIndex; i++) {
            TSection section;
            TSection sectionproper;
            if (i == 0) {
                section = CreateSection(p.beggining, p.Points[i], p);
                sectionproper = section;
            } else {
                section = CreateSection(p.beggining, p.Points[i], p);
                if (!p.JumpPoint[p.CurrentIndex]) {
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
                        case 'z' -> {
                            if (section.dirIncreasing) {
                                p.beggining.x = p.beggining.x + 1;
                                p.beggining.y = p.beggining.y - 1;
                            }
                        }
                    }
                }
            }
            if (p.JumpPoint[i]) {
                p.beggining = p.Points[i];
            } else {
                sectionproper = CreateSection(p.beggining, p.Points[i], p);
                DrawSection(sectionproper, 'X', p);
                p.beggining = p.Points[i];
                p.Sections[i] = sectionproper;
            }

        }

    }

    int calculateHowLongPath(TSectionPath p) {
        int length = 0;
        for (int i = 0; i < p.CurrentIndex; i++) {
            if (p.JumpPoint[i]) {
                continue;
            } else {
                length = length + p.Sections[i].Length;
            }
        }
        return length;
    }

    void printAllSectionsLenghts(TSectionPath p) {
        for (int i = 0; i < p.CurrentIndex; i++) {
            println(p.Sections[i].Length);
        }
    }

    TPoint whichPointInPathByIndex(TSectionPath p, int count) {
        int index = count % calculateHowLongPath(p);  //This effectively makes us skip point which overlaps Yieppie
        TPoint point = new TPoint();
        for (int i = 0; i < p.CurrentIndex; i++) {
            if (p.JumpPoint[i]) {
                continue;
            }
            if (index > p.Sections[i].Length - 1) {
                index = index - p.Sections[i].Length;
            } else {
                point = WhichPointInSectionByIndex(p.Sections[i], index);
                break;
            }
        }
        return point;
    }

    void drawCharOnPathWithStartingIndex(TSectionPath p, int index, char W) {
        TPoint point = whichPointInPathByIndex(p, index);
        gotoxy(point.x, point.y);
        print(W);
    }

    void drawStringOnPathWithStartingIndex(TSectionPath p, int index, TString s) {
        setfgcolor(s.color);
        if (s.clockWise == 1) {
            for (int i = 0; i < s.text.length(); i++) {
                drawCharOnPathWithStartingIndex(p, index + i, s.text.charAt(i));
            }
            setfgcolor(p.color);
            TPoint previousPoint = whichPointInPathByIndex(p, index - 1);
            gotoxy(previousPoint.x, previousPoint.y);
            print(p.sign);
        } else {
            for (int i = 0; i < s.text.length(); i++) {
                drawCharOnPathWithStartingIndex(p, index + i, s.text.charAt(i));
            }
            setfgcolor(p.color);
            TPoint previousPoint = whichPointInPathByIndex(p, index + s.text.length());
            gotoxy(previousPoint.x, previousPoint.y);
            print(p.sign);
        }
        setfgcolor(15);
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

    void animateStringOnPath(TSectionPath p, TString s) {
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

    boolean doesStringCollide(TSectionPath path, TString s1, TString s2) {
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

    void reverseDirOfBouncedStrings(TSectionPath path, TString s, TString s1) {
        if (doesStringCollide(path, s, s1)) {
            s.clockWise *= -1;
            s1.clockWise *= -1;
        }
    }

    TSectionPath CreateSquarePath(int color , char c, TPoint... points) {
        TSectionPath p = CreateTSectionPath(points[0],color,c);
        for (int i = 1; i < points.length; i++) {
            AddPointToPath(p,points[i],false);
        }
        AddPointToPath(p,points[0],false);
        return p;
    }

    void PresentView() {
        animateStringOnPath(path2, Beready1);
        animateStringOnPath(path2, Beready2);

        animateStringOnPath(path1, Welcome);
        animateStringOnPath(path1, Press);
    }

    void UpdateModel() {
        reverseDirOfBouncedStrings(path1,Welcome,Press);
        if (i < 150) {
            i += 2;
        }
    }

        void Init() {
        clrscr();
        cursor_hide();

        path1 = CreateSquarePath(15,'X',Point(1,1),Point(120,1),Point(120,30),Point(1,30));
        path2 = CreateSquarePath(15,'X',Point(40,7),Point(80,7),Point(80,23),Point(40,23));

        Beready1  = createString(" Be ready! ", 1, 1, 15);
//        Beready2 = createString(" Be ready! ", 50, 1, 15);
        Beready2 = createString(" 8=======D ", 50, 1, 15);
        Welcome = createString(">> Welcome to our world! <<", 60,-1, 82);
        Press = createString("<< trats ot ecaps sserP >>", 180,1, 4);

        drawAndCreatePathSections(path1);
        drawAndCreatePathSections(path2);

    }
    void CheckControl() {
        CheckControlsInTime();
    }

    void CheckControlsInTime() {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 200 - i) {
            CheckForP();
            CheckForS();
            delay(1);
        }
    }

    void main() {
        Init();

        while (!quit) {
            UpdateModel();
            PresentView();
            CheckControl();
        }
        gotoxy(1, 31);
    }

}



//is nice for now i need to focus on

// 4) Starting From New point continuation of path
// 5) Diagonal case