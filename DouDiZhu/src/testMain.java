import java.util.ArrayList;
import java.util.Scanner;

public class testMain {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        Game gp = new Game();gp.deal();
        gp.callForLord();



        ArrayList<String>com = new ArrayList<String>();
       System.out.println("\n" +
               "输入你要打的牌");

       String input = sc.next();
        com.add(input);

        while (true) {
                input = sc.next();
                if (input.equals("-1")){
                    break;
                }
                com.add(input);
        }

        gp.getUser().userPlay(com);

        Game.printCard(gp.getUser().getUserOutput());

//        Card a = new Card("*","3");
//        Card b = new Card("*","4");
//        Card c = new Card("*","5");
//        Card d = new Card("*","6");
//        Card e = new Card("*","7");
//
//        ArrayList<Card>check = new ArrayList<Card>();
//        check.add(a);check.add(b);check.add(c);check.add(d);check.add(e);
//
//        System.out.println(gp.ifBoom(check));
//        System.out.println(gp.ifStraight(check));


    }
}
