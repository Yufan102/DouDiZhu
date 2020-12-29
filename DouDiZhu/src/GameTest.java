import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static Card a = new Card("^","3");
    private static Card b = new Card("^","4");
    private static Card c = new Card("^","5");
    private static Card d = new Card("^","6");
    private static Card e = new Card("^","7");
    private static Card f = new Card("^","8");
    private static Card g = new Card("^","9");


    @org.junit.jupiter.api.Test
    void ifInputBigger_Straight() {
        ArrayList<Card> list1 = new ArrayList<Card>();
        ArrayList<Card> list2 = new ArrayList<Card>();
        list1.add(a);list1.add(b);list1.add(c);list1.add(d);list1.add(e);
        list2.add(b);list2.add(c);list2.add(d);list2.add(e);list2.add(f);list2.add(g);

        System.out.println(Game.IfInputBigger(list1,list2));
    }

    @Test
    void ifInputBigger_Couples(){
        ArrayList<Card> list1 = new ArrayList<Card>();
        ArrayList<Card> list2 = new ArrayList<Card>();
        list1.add(a);list1.add(a);
        list2.add(a);list2.add(a);
        System.out.println(Game.IfInputBigger(list1,list2));
    }

    @Test
    void ifInputBigger_Single(){
        ArrayList<Card> list1 = new ArrayList<Card>();
        ArrayList<Card> list2 = new ArrayList<Card>();
        list1.add(a);

        System.out.println(Game.IfInputBigger(list1,list2));
    }

}