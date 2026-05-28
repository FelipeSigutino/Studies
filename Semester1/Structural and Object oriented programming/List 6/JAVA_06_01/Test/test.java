


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class test {
    @Test
    public void twoPlusTwoEqualsFour() {
        assertEquals(4,Main.addab(2,2));
    }
    @Test
    public void SevenPlusEightEqualsFifteen() {
        assertEquals(15,Main.addab(7,8));
    }
//Examples Of Working Tests with differing outputs but being passed positively
    @Test
    public void CheckIfPointBelongsToSectionXTrue() {
        Main.TPoint beggining = Main.Point(1,1);
        Main.TPoint ending = Main.Point(10,1);
        Main.TSection section = Main.CreateSection(beggining,ending);
        Main.TPoint point = Main.Point(4,1);

        assertTrue(Main.CheckWhetherPointInSection(section, point));
    }
    @Test
    public void CheckIfPointBelongsToSectionYFalse() {
        Main.TPoint beggining = Main.Point(1,1);
        Main.TPoint ending = Main.Point(1,10);
        Main.TSection section = Main.CreateSection(beggining,ending);
        Main.TPoint point = Main.Point(4,1);

        assertFalse(Main.CheckWhetherPointInSection(section, point));
    }
    @Test
    public void CheckIfPointBelongsToSectionYTrue() {
        Main.TPoint beggining = Main.Point(1,1);
        Main.TPoint ending = Main.Point(1,10);
        Main.TSection section = Main.CreateSection(beggining,ending);
        Main.TPoint point = Main.Point(1,4);
        assertTrue(Main.CheckWhetherPointInSection(section, point));
    }
    @Test
    public void CheckIfPointBelongsToSectionXFalse() {
        Main.TPoint beggining = Main.Point(1,1);
        Main.TPoint ending = Main.Point(10,1);
        Main.TSection section = Main.CreateSection(beggining,ending);
        Main.TPoint point = Main.Point(1,12);

        assertFalse(Main.CheckWhetherPointInSection(section, point));
    }
//Other Useful Tests for future
    @Test
    public void WhichPointInSectionTrue() {
        Main.TPoint beggining = Main.Point(1,1);
        Main.TPoint ending = Main.Point(10,1);
        Main.TSection section = Main.CreateSection(beggining,ending);

        Main.TPoint probePoint = Main.Point(1,1);
        Main.TPoint functionPoint = Main.WhichPointInSectionByIndex(section,0);

        assertEquals(probePoint.x,functionPoint.x);
        assertEquals(probePoint.y,functionPoint.y);
    }
    @Test
    public void WhichPointInSectionFalse() {
        Main.TPoint beggining = Main.Point(1,1);
        Main.TPoint ending = Main.Point(10,1);
        Main.TSection section = Main.CreateSection(beggining,ending);

        Main.TPoint probePoint = Main.Point(1,1);
        Main.TPoint functionPoint = Main.WhichPointInSectionByIndex(section,1);

        assertNotEquals(probePoint.x,functionPoint.x); //because x is changing with increasing index
        assertEquals(probePoint.y,functionPoint.y);    //because y isnt changing with increasing index
    }

}


// assertNotEquals
// assertTrue
// assertFalse
// assertNull
// assertNotNull
