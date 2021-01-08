import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
This is the main function for the whole game
 */
public class Game {
    private FiftyFour cards = new FiftyFour();
    private Player user = new Player();
    private Player ai1 = new Player();
    private Player ai2 = new Player();
    private ArrayList<Card> lordCard = new ArrayList<Card>();
    private static ArrayList<Integer> StraightNum = new ArrayList<Integer>();
    private int winNum = 0;
    private int runTime = 0;
    private boolean userCardStatus = false;    //if the user's card is bigger
    private boolean pass = false;              //if the user decide to pass the round

    /**
     * Check if one of three players wins this
     *
     * @return the winner number
     */
    private int whoWin() {
        if (user.isWin()) {
            winNum = 3;
        }
        if (ai1.isWin()) {
            winNum = 1;
        }
        if (ai2.isWin()) {
            winNum = 2;
        }

        return winNum;
    }


    /**
     * Print the points in this round
     */
    private void printPoints() {
        System.out.println("Points for this round is");
        System.out.println("User's points：" + user.getPoints().getNum() + " AI1：" + ai1.getPoints().getNum() +
                " AI2: " + ai2.getPoints().getNum());
    }

    /*
    The following if methods is checking the different types of passed cards
     */
    public static boolean ifCouples(ArrayList<Card> out) {
        if (out.size() == 2) {
            return out.get(0).getPriority() == out.get(1).getPriority();
        }
        return false;
    }

    public static boolean ifSingle(ArrayList<Card> out) {
        return out.size() == 1;
    }

    public static boolean ifThree(ArrayList<Card> out) {
        return out.size() == 3 && out.get(0).getNumber().equals(out.get(1).getNumber())
                && out.get(1).getNumber().equals(out.get(2).getNumber());
    }

    public static boolean ifStraight(ArrayList<Card> out) {
        if (out.size() > 4) {
            for (int i = 0; i < out.size() - 1; i++) {
                if (out.get(i).getPriority() != out.get(i + 1).getPriority() - 1) {
                    return false;
                }
            }
            StraightNum.add(out.get(0).getPriority());
            return true;
        }
        return false;
    }

    public static boolean ifBoom(ArrayList<Card> out) {
        if (out.size() == 4) {
            return out.get(0).getPriority() == out.get(1).getPriority() &&
                    out.get(0).getPriority() == out.get(2).getPriority() &&
                    out.get(0).getPriority() == out.get(3).getPriority();
        }
        return false;
    }


    public static boolean ifKingBoom(ArrayList<Card> out) {
        return out.get(0).getPriority() == 16 && out.get(1).getPriority() == 17;
    }

    /**
     * Two different methods will print the card
     *
     * @param cards cards to be printed
     */
    public static void printCard(ArrayList<Card> cards) {
        for (Card c : cards) {
            System.out.print(c.display() + " ");
        }
        System.out.println();
    }

    public static void printCard(Player player) {
        for (Card c : player.getCardsOnHand()) {
            System.out.print(c.display() + " ");
        }
        System.out.println();
    }

    //set the points for the user
    private void pointsSetters(int a, int b, int c) {
        user.setPoints(a);
        ai1.setPoints(b);
        ai2.setPoints(c);
    }

    /**
     * This method will creating random numbers
     *
     * @param bounds the bounds of number, will not touch the bounds
     * @param n      numbers of random numbers
     * @return the random numbers list
     */
    public static ArrayList<Integer> NoDuplicateRandom(int bounds, int n) {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (; ; ) {
            int next = random.nextInt(bounds);
            if (!list.contains(next)) {
                list.add(next);
            }
            if (list.size() == n) {
                break;
            }
        }
        return list;
    }

    //let the system sleep
    public static void sleep(int num) {
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Game() {
    }

    // deal for the cards to three players
    public void deal() {
        ArrayList<Integer> lordCardNum = NoDuplicateRandom(51, 3);


        //add landlord's cards
        for (int num : lordCardNum) {
            lordCard.add(cards.poll(num));
        }

        //adding random cards for three users
        ArrayList<Integer> random = NoDuplicateRandom(cards.size(), 51);

        int count = 0;
        while (count < random.size()) {
            user.getCardsOnHand().add(cards.getCards().get(count));
            count++;
            ai1.getCardsOnHand().add(cards.getCards().get(count));
            count++;
            ai2.getCardsOnHand().add(cards.getCards().get(count));
            count++;
        }
        //sorting the cards
        user.rank();
        ai1.rank();
        ai2.rank();

        System.out.println("Deal! This is your Card:");
        printCard(user);
    }

    //call for the landlord
    public void callForLord() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Are you calling for the landlord? What is your given scour:");

        ArrayList<Integer> next1 = NoDuplicateRandom(10, 2);
        int call1 = next1.get(0);
        int call2 = next1.get(1);

        int num = kb.nextInt();
        //first the player
        if (num == 3) {
            System.out.println("You are the landlord");
            sleep(700);
            System.out.println("The land lord cards are:");
            sleep(500);
            printCard(lordCard);
            sleep(500);
            user.getCardsOnHand().addAll(lordCard);
            user.rank();
            System.out.println("Your new cards are");
            sleep(500);
            printCard(user);
            pointsSetters(3, 1, 1);


        } else if (num == 1) {
            System.out.println("Waiting for other users");
            sleep(1000);
            //first
            if (call1 > 2) {
                System.out.println("User1 is the landlord");
                sleep(500);
                System.out.println("The land lord cards are:");
                sleep(500);
                printCard(lordCard);
                ai1.getCardsOnHand().addAll(lordCard);
                ai1.rank();
                pointsSetters(1, 2, 1);
            } else if (call2 > 2) {
                System.out.println("User2 is the landlord");
                sleep(500);
                System.out.println("The land lord cards are:");
                sleep(500);
                printCard(lordCard);
                ai2.getCardsOnHand().addAll(lordCard);
                ai2.rank();
                pointsSetters(1, 1, 2);
            } else {
                System.out.println("Other two users give up calling for landlord");
                sleep(500);
                System.out.println("The land lord cards are:");
                sleep(500);
                printCard(lordCard);
                user.getCardsOnHand().addAll(lordCard);
                user.rank();
                sleep(500);
                System.out.println("Your new cards are");
                sleep(500);
                printCard(user);
                pointsSetters(1, 1, 1);
            }


        } else if (num == 2) {
            System.out.println("Waiting for other users");
            sleep(500);
            if (call1 >= 6) {
                System.out.println("User1 is the landlord");
                sleep(500);
                System.out.println("The land lord cards are:");
                sleep(500);
                printCard(lordCard);
                ai1.getCardsOnHand().addAll(lordCard);
                ai1.rank();
                pointsSetters(1, 3, 1);
            } else if (call2 >= 6) {
                System.out.println("User2 is the landlord");
                sleep(500);
                System.out.println("The land lord cards are:");
                sleep(500);
                printCard(lordCard);
                ai2.getCardsOnHand().addAll(lordCard);
                ai2.rank();
                pointsSetters(1, 1, 3);
            } else {
                System.out.println("Other two users give up calling for landlord");
                sleep(500);
                System.out.println("The land lord cards are:");
                sleep(500);
                printCard(lordCard);
                sleep(500);
                user.getCardsOnHand().addAll(lordCard);
                user.rank();
                System.out.println("Your new cards are");
                printCard(user);
                pointsSetters(2, 1, 1);
            }

        }
    }

    //the method of common method to decide types of card
    private static boolean ifInsideBigger(ArrayList<Card> passed, ArrayList<Card> inputs) {
        int passNum = passed.get(0).getPriority();
        int inputNum = inputs.get(0).getPriority();
        if (inputNum > passNum) {
            System.out.println("You have passed bigger cards");
            return true;
        } else {
            System.out.println("You have passed smaller cards, passing failed");
            return false;
        }
    }

    /**
     * This method is checking whether the input cards is bigger than
     * the previous cards
     *
     * @param passed the previous cards
     * @param inputs the input cards
     * @return true if bigger, false if not
     */
    public static boolean IfInputBigger(ArrayList<Card> passed, ArrayList<Card> inputs) {

        if (passed == null) return true;

        if (ifStraight(passed)) {
            if (!ifStraight(inputs)) {
                System.out.println("Passing is not a straight, passing failed");
                return false;
            }

            if (passed.size() != inputs.size()) {
                System.out.println("Passing straight's length is not correct, passing failed");
                return false;
            }
            return ifInsideBigger(passed, inputs);

        } else if (ifCouples(passed)) {
            if (!ifCouples(inputs)) {
                System.out.println("Passing is not a couples, passing failed");
                return false;
            }
            return ifInsideBigger(passed, inputs);

        } else if (ifBoom(passed)) {
            if (!ifBoom(inputs)) {
                System.out.println("Passing is not a boom, passing failed");
            }
            return ifInsideBigger(passed, inputs);
        } else if (ifKingBoom(passed)) {
            return false;
        } else if (passed.size() == 1) {
            if (inputs.size() != 1) {
                System.out.println("Passing is not a single, passing failed");
                return false;
            }

            return ifInsideBigger(passed, inputs);
        }


        return false;
    }

    /**
     * This method is the main playing method following the rules under.
     * landlord - play first
     * following the circle user - AI1 - AI2
     * choosing to pass, if the given cards are bigger
     */
    public void playMain() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> com = new ArrayList<String>();
        ArrayList<Card> ai1Out = new ArrayList<Card>();
        ArrayList<Card> ai2Out = new ArrayList<Card>();
        ArrayList<Card> out = new ArrayList<Card>();

        //if the user is the landlord
        if (user.getCardsOnHand().size() == 20) {
            //run for the first round
            System.out.println("You are the landlord, you are going to Play the first round");
            sleep(500);
            System.out.println("Please enter your cards：");
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
            System.out.println("\n");
            sleep(700);
            System.out.println("You passed:");
            sleep(700);
            printCard(out);

            //loop for the entire game
            while (true) {

                //AI1's decision
                if (out.size() != 0) {
                    ai1Out = ai1.sb.AIPlay(out);

                    if (ai1Out != null) {
                        if (ai1Out.size() != 0) {
                            System.out.println("User1 passed:");
                            printCard(ai1Out);

                        } else {
                            System.out.println("User1 does not how to replay");
                        }
                    } else {
                        System.out.println("User1 does not how to replay");
                    }

                    //if ai1out is null
                    if (ai1Out != null) {
                        if (ai1Out.size() != 0) {
                            ai2Out = ai2.sb.AIPlay(ai1Out);
                        }
                    } else {
                        ai2Out = ai2.sb.AIPlay(out);
                    }
                    sleep(700);

                    //now for the AI2's decision
                    if (ai2Out != null) {
                        if (ai2Out.size() != 0) {
                            System.out.println("User2 passed:");
                            sleep(700);
                            printCard(ai2Out);
                        } else {
                            System.out.println("User2 does not how to replay");
                        }
                    } else {
                        System.out.println("User2 does not how to replay");
                    }

                    sleep(700);

                    //checking if one of the players has won
                    whoWin();
                    if (winNum != 0) {
                        break;
                    }

                    System.out.println("\n" +
                            "After your passing:");
                    printCard(user.getCardsOnHand());
                    sleep(500);
                    //second and lasting round, the user can choose to pass the round
                    sc = new Scanner(System.in);
                    com = new ArrayList<String>();
                    System.out.println("You can enter pass to give up passing a card");
                    System.out.println("\n" +
                            "Please enter your passing card");

                    input = sc.next();
                    pass = input.equals("pass");
                    com.add(input);

                    //if the user choose not to pass
                    if (!pass) {
                        //adding user's commend to pick its cards
                        while (true) {
                            input = sc.next();
                            if (input.equals("-1")) {
                                break;
                            }
                            com.add(input);
                        }
                        out = user.userPlay(com);
                        com.clear();
                        userCardStatus = IfInputBigger(ai2Out, out);

                        //if the user's card is not following the rules
                        if (!userCardStatus) {
                            user.getCardsOnHand().addAll(out);
                            System.out.println("Passing failed, your round is failed");
                            if (ai2Out != null) {
                                out = ai2Out;
                            } else if (ai1Out != null) {
                                out = ai1Out;
                            }
                        } else {
                            System.out.println("\n");
                            sleep(700);
                            System.out.println("Your passing cards:");
                            sleep(700);
                            printCard(out);
                        }
                    } else {
                        //the user choose to pass the round
                        com.clear();
                        if (ai2Out != null) {
                            out = ai2Out;
                        } else if (ai1Out != null) {
                            out = ai1Out;
                        }
                    }

                }
            }
        }

        //Ai1 is the landlord
        else if (ai1.getCardsOnHand().size() == 20) {

            System.out.println("User1 are the landlord, it is going to Play the first round");
            sleep(500);
            while (true) {
                //run for the first time
                if (runTime == 0) {
                    ai1Out = ai1.sb.FirstRound();
                    System.out.println("User1 passed:");
                    sleep(500);

                    printCard(ai1Out);
                    ai2Out = ai2.sb.AIPlay(ai1Out);

                    if (ai2Out != null) {
                        if (ai2Out.size() != 0) {
                            System.out.println("User2 passed:");
                            sleep(500);
                            printCard(ai2Out);
                            sleep(500);
                        } else {
                            System.out.println("User2 does not know how to replay");
                        }
                        sleep(500);
                    }
                }

                System.out.println("You can enter pass to give up");
                System.out.println("You passed:");
                sleep(500);
                System.out.println("Your cards:");
                sleep(500);
                printCard(user.getCardsOnHand());
                sleep(500);

                String input = sc.next();
                pass = input.endsWith("pass");
                com.add(input);

                if (!pass) {
                    while (true) {
                        input = sc.next();
                        if (input.equals("-1")) {
                            break;
                        }
                        com.add(input);
                    }


                    out = user.userPlay(com);
                    com.clear();
                    userCardStatus = IfInputBigger(ai2Out, out);
                    if (!userCardStatus) {
                        user.getCardsOnHand().addAll(out);
                        user.rank();
                        if (ai2Out != null) {
                            out = ai2Out;
                        } else {
                            out = ai1Out;
                        }
                    }

                    if (out.size() != 0) {
                        if (userCardStatus) {
                            System.out.println("\n");
                            sleep(700);
                            System.out.println("Your passed cards:");
                            sleep(700);
                            printCard(out);
                        } else {
                            System.out.println("\n");
                            sleep(700);
                            System.out.println("Entering error, your round is failed");
                        }
                    } else {
                        com.clear();
                        if (ai2Out != null) {
                            out = ai2Out;
                        } else if (ai1Out != null) {
                            out = ai1Out;
                        }
                    }


                    ai1Out = ai1.sb.AIPlay(out);


                    if (ai1Out != null) {
                        if (ai1Out.size() != 0) {
                            System.out.println("User1 passing:");
                            printCard(ai1Out);

                        } else {
                            System.out.println("User1 does not know how to replay");
                        }
                    } else {
                        System.out.println("User1 does not know how to replay");
                    }

                    //if ai1out is null
                    if (ai1Out != null) {
                        if (ai1Out.size() != 0) {
                            ai2Out = ai2.sb.AIPlay(ai1Out);
                        }
                    } else {
                        ai2Out = ai2.sb.AIPlay(out);
                    }

                    sleep(700);
                    if (ai2Out != null) {
                        if (ai2Out.size() != 0) {
                            System.out.println("User2 passed:");
                            sleep(700);
                            printCard(ai2Out);
                        } else {
                            System.out.println("User2 does not know how to replay");
                        }
                    } else {
                        System.out.println("User2 does not know how to replay");
                    }

                    sleep(700);


                    winNum = whoWin();
                    if (winNum != 0) {
                        break;
                    }
                }
                runTime++;
            }
        }

        //ai2 is the landlord
        else if (ai2.getCardsOnHand().size() == 20) {
            System.out.println("User1 are the landlord, it is going to Play the first round");
            sleep(500);
            while (true) {
                if (runTime == 0) {
                    ai2Out = ai2.sb.FirstRound();
                } else {
                    if (ai1Out != null) {
                        ai2Out = ai2.sb.AIPlay(ai1Out);
                    } else {
                        ai2Out = ai2.sb.AIPlay(out);
                    }
                }
                runTime++;

                if (ai2Out == null) {
                    System.out.println("User2 does not know how to replay");
                } else {
                    System.out.println("User2 passed:");
                    sleep(500);
                    printCard(ai2Out);
                }

                System.out.println("You passed:");
                sleep(500);
                printCard(user.getCardsOnHand());
                String input = sc.next();
                pass = input.equals("pass");
                com.add(input);

                if (!pass) {
                    while (true) {
                        input = sc.next();
                        if (input.equals("-1")) {
                            break;
                        }
                        com.add(input);
                    }
                    out = user.userPlay(com);
                    com.clear();

                    if (out.size() != 0) {
                        userCardStatus = IfInputBigger(ai2Out, out);
                        if (!userCardStatus) {
                            user.getCardsOnHand().addAll(out);
                            user.rank();
                            if (ai2Out != null) {
                                out = ai2Out;
                            } else {
                                out = ai1Out;
                            }
                        }

                        if (userCardStatus) {
                            System.out.println("\n");
                            sleep(700);
                            System.out.println("Your passed:");
                            sleep(700);
                            printCard(out);
                        } else {
                            System.out.println("\n");
                            sleep(700);
                            System.out.println("Entering error, your round is failed");
                        }
                    }
                } else {
                    com.clear();
                    if (ai2Out != null) {
                        out = ai2Out;
                    } else if (ai1Out != null) {
                        out = ai1Out;
                    }
                }

                ai1Out = ai1.sb.AIPlay(out);


                if (ai1Out != null) {
                    if (ai1Out.size() != 0) {
                        System.out.println("User1 passing:");
                        printCard(ai1Out);

                    } else {
                        System.out.println("User1 does not know how to replay");
                    }
                } else {
                    System.out.println("User1 does not know how to replay");
                }


                whoWin();
                if (winNum != 0) {
                    break;
                }

            }


            runTime++;
        }


    }

    //getters
    public Player getUser() {
        return user;
    }

    public Player getAi1() {
        return ai1;
    }

    public Player getAi2() {
        return ai2;
    }
}
