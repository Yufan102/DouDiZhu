import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private FiftyFour cards = new FiftyFour();
    private Player user = new Player();
    private Player ai1 = new Player();
    private Player ai2 = new Player();
    private ArrayList<Card>threeCard = new ArrayList<Card>();
    private ArrayList<Card> lordCard = new ArrayList<Card>();
    private static ArrayList<Integer> StraightNum = new ArrayList<Integer>();
    private int winNum = 0;

    private int whoWin(){
        if (user.isWin()){
            winNum = 3;
        }if (ai1.isWin()) {
            winNum = 1;
        }if (ai2.isWin()){
            winNum = 2;
        }

        return winNum;
    }


    public static boolean ifCouples(ArrayList<Card>out){
        if (out.size() == 2){
            return out.get(0).getPriority() == out.get(1).getPriority();
        }return false;
    }

    public static boolean ifSingle(ArrayList<Card>out){
        return out.size() == 1;
    }

    public static boolean ifThree(ArrayList<Card>out){
        return out.size() == 3 && out.get(0).getNumber().equals(out.get(1).getNumber())
                && out.get(1).getNumber().equals(out.get(2).getNumber());
    }

    public static boolean ifStraight(ArrayList<Card>out){
        if (out.size() > 4){
            for (int i = 0; i < out.size() -1; i ++){
                if (out.get(i).getPriority() != out.get(i + 1).getPriority()-1){
                    return false;
                }
            }
            StraightNum.add(out.get(0).getPriority());
            return true;
        }return false;
    }

    public static boolean ifBoom(ArrayList<Card>out){
        if (out.size() == 4){
            return out.get(0).getPriority() == out.get(1).getPriority() &&
                    out.get(0).getPriority() == out.get(2).getPriority() &&
                    out.get(0).getPriority() == out.get(3).getPriority();
        }return false;
    }

    public static boolean ifKingBoom(ArrayList<Card>out){
        return out.get(0).getPriority() == 16 && out.get(1).getPriority() == 17;
    }

    public static void printCard(ArrayList<Card>cards){
        for (Card c : cards){
            System.out.print(c.display() + " ");
        }
        System.out.println();
    }

    public static void printCard(Player player){
        for (Card c : player.getCardsOnHand()){
            System.out.print(c.display() + " ");
        }
        System.out.println();
    }

    private void pointsSetters(int a, int b, int c){
        user.setPoints(a);
        ai1.setPoints(b);
        ai2.setPoints(c);
    }

    public static ArrayList<Integer> NoDuplicateRandom(int bounds,int n){
        Random random = new Random();
        ArrayList<Integer>list = new ArrayList<Integer>();
        for (;;){
            int next = random.nextInt(bounds);
            if (!list.contains(next)){
                list.add(next);
            }
            if (list.size() == n){
                break;
            }
        }
        return list;
    }

    public static void sleep(int num){
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Game(){}

    public void deal(){
        ArrayList<Integer> lordCardNum = NoDuplicateRandom(51,3);


        //add dizhupai
        for (int num : lordCardNum){
            lordCard.add(cards.poll(num));
        }

        ArrayList<Integer> random = NoDuplicateRandom(cards.size(),51);


        int count = 0;
        while (count < random.size()){
            user.getCardsOnHand().add(cards.getCards().get(count));
            count++;
            ai1.getCardsOnHand().add(cards.getCards().get(count));
            count++;
            ai2.getCardsOnHand().add(cards.getCards().get(count));
            count++;
        }
        user.rank();ai1.rank();ai2.rank();

        System.out.println("发牌完毕，请你看你的牌");
        printCard(user);
    }

    //call for the land lord
    public void callForLord(){
        Scanner kb = new Scanner(System.in);
        System.out.println("叫地主？给几分？");

        ArrayList<Integer>next1 = NoDuplicateRandom(10,2);
        int call1 = next1.get(0);
        int call2 = next1.get(1);

        int num = kb.nextInt();
        //first the player
        if (num == 3){
            System.out.println("你的分数最大，你是地主");
            sleep(700);
            System.out.println("地主牌为");
            sleep(500);
            printCard(lordCard);
            sleep(500);
            user.getCardsOnHand().addAll(lordCard);
            user.rank();
            System.out.println("你的新牌为");
            sleep(500);
            printCard(user);
            pointsSetters(3,1,1);


        }else if (num == 1){
            System.out.println("等待剩余玩家出分");
            sleep(1000);
            //first
            if (call1 > 2){
                System.out.println("玩家一抢到了地主");
                sleep(500);
                System.out.println("地主牌为");
                sleep(500);
                printCard(lordCard);
                ai1.getCardsOnHand().addAll(lordCard);
                ai1.rank();
                pointsSetters(1,2,1);
            }else if (call2 > 2){
                System.out.println("玩家二抢到了地主");
                sleep(500);
                System.out.println("地主牌为");
                sleep(500);
                printCard(lordCard);
                ai2.getCardsOnHand().addAll(lordCard);
                ai2.rank();
                pointsSetters(1,1,2);
            }else {
                System.out.println("他们都不想要地主");
                sleep(500);
                System.out.println("地主牌为");
                sleep(500);
                printCard(lordCard);
                user.getCardsOnHand().addAll(lordCard);
                user.rank();
                sleep(500);
                System.out.println("你的新牌为");
                sleep(500);
                printCard(user);
                pointsSetters(1,1,1);
            }


        }else if (num == 2){
            System.out.println("等待剩余玩家出分");
            sleep(500);
            if (call1 >= 6){
                System.out.println("玩家一抢到了地主");
                sleep(500);
                System.out.println("地主牌为");
                sleep(500);
                printCard(lordCard);
                ai1.getCardsOnHand().addAll(lordCard);
                ai1.rank();
                pointsSetters(1,3,1);
            }else if (call2 >= 6){
                System.out.println("玩家二抢到了地主");
                sleep(500);
                System.out.println("地主牌为");
                sleep(500);
                printCard(lordCard);
                ai2.getCardsOnHand().addAll(lordCard);
                ai2.rank();
                pointsSetters(1,1,3);
            }else {
                System.out.println("他们的牌都不咋地好，你还是地主");
                sleep(500);
                System.out.println("地主牌为");
                sleep(500);
                printCard(lordCard);
                sleep(500);
                user.getCardsOnHand().addAll(lordCard);
                user.rank();
                System.out.println("你的新牌为");
                printCard(user);
                pointsSetters(2,1,1);
            }

        }
    }

    public void playMain() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> com = new ArrayList<String>();
        ArrayList<Card> ai1Out = new ArrayList<Card>();
        ArrayList<Card> ai2Out = new ArrayList<Card>();
        ArrayList<Card> out = new ArrayList<Card>();

        //
        if (user.getCardsOnHand().size() == 20){
            System.out.println("你是地主，由你打第一轮牌");
            sleep(500);
            System.out.println("你要打的牌为：");
            String input = sc.next();
            com.add(input);

            while (true) {
                input = sc.next();
                if (input.equals("-1")) {
                    break;
                }
                com.add(input);
            }

            out = user.userPlay(com);

            while (true) {

                if (out.size() != 0) {
                    System.out.println("\n");
                    sleep(700);
                    System.out.println("你打的牌为");
                    sleep(700);
                    printCard(out);

                    ai1Out = ai1.sb.AIPlay(out);


                    if (ai1Out!= null) {
                        if (ai1Out.size() != 0) {
                            System.out.println("人工智障一打的牌为");
                            printCard(ai1Out);

                        }
                        else {
                            System.out.println("人工智障一啥也不知道");
                        }
                    }else {
                        System.out.println("人工智障一啥也不知道");
                    }

                    //if ai1out is null
                    if (ai1Out != null) {
                        if (ai1Out.size() != 0) {
                            ai2Out = ai2.sb.AIPlay(ai1Out);
                        }
                    }else {
                        ai2Out = ai2.sb.AIPlay(out);
                    }

                    sleep(700);
                    if (ai2Out != null) {
                        if (ai2Out.size() != 0) {
                            System.out.println("人工智障二打的牌为");
                            sleep(700);
                            printCard(ai2Out);
                        } else {
                            System.out.println("人工智障二啥也不会");
                        }
                    }
                    else {
                        System.out.println("人工智障二啥也不会");
                    }

                    sleep(700);


                    whoWin();
                    if (winNum != 0) {
                        break;
                    }

                    System.out.println("\n"+
                            "打完之后的牌为");
                    printCard(user.getCardsOnHand());
                    sleep(500);

                    sc = new Scanner(System.in);
                    com = new ArrayList<String>();
                    System.out.println("\n" +
                            "输入你要打的牌");

                    input = sc.next();
                    com.add(input);

                    while (true) {
                        input = sc.next();
                        if (input.equals("-1")) {
                            break;
                        }
                        com.add(input);
                    }
                    out = user.userPlay(com);
                }
            }
        }
        else if (ai1.getCardsOnHand().size() == 20){
                System.out.println("用户一地主，由他打第一轮牌");
                sleep(500);
                while (true){
                    //run for the first time
                    while (true) {
                        ai1Out = ai1.sb.FirstRound();
                        System.out.println("用户一打的牌是：");
                        sleep(500);

                        printCard(ai1Out);
                        ai2Out = ai2.sb.AIPlay(ai1Out);

                        if (ai2Out != null) {
                            if (ai2Out.size() != 0) {
                                System.out.println("用户二打的牌是：");
                                sleep(500);
                                printCard(ai2Out);
                                sleep(500);
                            }else {
                                System.out.println("用户二啥也不晓得");
                            }
                            System.out.println("用户二啥也不晓得");
                            sleep(500);
                        }
                        break;
                    }

                    System.out.println("现在归你打牌了");
                    sleep(500);
                    System.out.println("你的牌为");
                    sleep(500);
                    printCard(user.getCardsOnHand());
                    sleep(500);

                    String input = sc.next();
                    com.add(input);

                    while (true) {
                        input = sc.next();
                        if (input.equals("-1")) {
                            break;
                        }
                        com.add(input);
                    }

                    out = user.userPlay(com);

                    if (out.size() != 0) {
                        System.out.println("\n");
                        sleep(700);
                        System.out.println("你打的牌为");
                        sleep(700);
                        printCard(out);

                        ai1Out = ai1.sb.AIPlay(out);


                        if (ai1Out!= null) {
                            if (ai1Out.size() != 0) {
                                System.out.println("人工智障一打的牌为");
                                printCard(ai1Out);

                            }
                            else {
                                System.out.println("人工智障一啥也不知道");
                            }
                        }else {
                            System.out.println("人工智障一啥也不知道");
                        }

                        //if ai1out is null
                        if (ai1Out != null) {
                            if (ai1Out.size() != 0) {
                                ai2Out = ai2.sb.AIPlay(ai1Out);
                            }
                        }else {
                            ai2Out = ai2.sb.AIPlay(out);
                        }

                        sleep(700);
                        if (ai2Out != null) {
                            if (ai2Out.size() != 0) {
                                System.out.println("人工智障二打的牌为");
                                sleep(700);
                                printCard(ai2Out);
                            } else {
                                System.out.println("人工智障二啥也不会");
                            }
                        } else {
                            System.out.println("人工智障二啥也不会");
                        }

                        sleep(700);


                        whoWin();
                        if (winNum != 0) {
                            break;
                        }

                        System.out.println("\n"+
                                "打完之后的牌为");
                        printCard(user.getCardsOnHand());
                        sleep(500);

                        sc = new Scanner(System.in);
                        com = new ArrayList<String>();
                        System.out.println("\n" +
                                "输入你要打的牌");

                        input = sc.next();
                        com.add(input);

                        while (true) {
                            input = sc.next();
                            if (input.equals("-1")) {
                                break;
                            }
                            com.add(input);
                        }
                        out = user.userPlay(com);
                    }
                }
        }

        //ai2 is the landlord
        else if (ai2.getCardsOnHand().size() == 20) {
            System.out.println("用户二地主，由他打第一轮牌");
            sleep(500);
            while (true){

                ai2Out = ai2.sb.FirstRound();
                System.out.println("用户二打的牌是：");
                sleep(500);
                printCard(ai2Out);
                //further install here, the rules, user's must be larger

                System.out.println("现在归你打牌");
                String input = sc.next();
                com.add(input);

                while (true) {
                    input = sc.next();
                    if (input.equals("-1")) {
                        break;
                    }
                    com.add(input);
                }

                out = user.userPlay(com);

                if (out.size() != 0){
                    System.out.println("\n");
                    sleep(700);
                    System.out.println("你打的牌为");
                    sleep(700);
                    printCard(out);

                    ai1Out = ai1.sb.AIPlay(out);


                    if (ai1Out!= null) {
                        if (ai1Out.size() != 0) {
                            System.out.println("人工智障一打的牌为");
                            printCard(ai1Out);

                        }
                        else {
                            System.out.println("人工智障一啥也不知道");
                        }
                    }else {
                        System.out.println("人工智障一啥也不知道");
                    }

                    //if ai1out is null
                    if (ai1Out != null) {
                        if (ai1Out.size() != 0) {
                            ai2Out = ai2.sb.AIPlay(ai1Out);
                        }
                    }else {
                        ai2Out = ai2.sb.AIPlay(out);
                    }

                    sleep(700);
                    if (ai2Out != null) {
                        if (ai2Out.size() != 0) {
                            System.out.println("人工智障二打的牌为");
                            sleep(700);
                            printCard(ai2Out);
                        } else {
                            System.out.println("人工智障二啥也不会");
                        }
                    } else {
                        System.out.println("人工智障二啥也不会");
                    }

                    sleep(700);


                    whoWin();
                    if (winNum != 0) {
                        break;
                    }

                    System.out.println("\n"+
                            "打完之后的牌为");
                    printCard(user.getCardsOnHand());
                    sleep(500);

                    sc = new Scanner(System.in);
                    com = new ArrayList<String>();
                    System.out.println("\n" +
                            "输入你要打的牌");

                    input = sc.next();
                    com.add(input);

                    while (true) {
                        input = sc.next();
                        if (input.equals("-1")) {
                            break;
                        }
                        com.add(input);
                    }
                    out = user.userPlay(com);
                }


            }


        }

    }


        public Player getUser () {
            return user;
        }

        public Player getAi1 () {
            return ai1;
        }

        public Player getAi2 () {
            return ai2;
        }
    }
