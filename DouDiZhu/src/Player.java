import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*
This method is used for creating differ types of uses
And the method is used with AI class, which will implement different
types of player's behavior
 */
public class Player {
    private String type;
    private ArrayList<Card> cardsOnHand = new ArrayList<Card>();      // the current player's cards
    private ArrayList<Card> backUp = new ArrayList<Card>();
    private ArrayList<Card> ai1 = new ArrayList<Card>();
    AI sb = new SillyAI(this);
    private boolean coop;
    private Points points;


    public Player() {
    }

    //basic getters and setters
    public String getType() {
        return type;
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public boolean isWin() {
        return cardsOnHand.size() == 0;
    }

    public boolean isCoop() {
        return coop;
    }

    public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }

    public void setPoints(int num) {
        points = new Points(num);
    }

    public Points getPoints() {
        return points;
    }

    /**
     * This method will sort the card in increasing order
     * For example 3,4,5,6,7 ......,2,-,+
     */
    public void rank() {
        Collections.sort(cardsOnHand);
    }

    /*
    This two methods are created for further functions of game
     */
    public void isLord() {
        this.type = "Load";
        this.coop = false;
    }

    public void isFarmer() {
        this.type = "Farmer";
        this.coop = false;
    }


    /**
     * This method will get the input information from the system input
     * and it will translate into cards for user's output
     *
     * @param com the input list
     * @return user's final output
     */
    public ArrayList<Card> userPlay(ArrayList<String> com) {
        ArrayList<Card> userOutput = new ArrayList<Card>();
        int count = 0;
        backUp = cardsOnHand;

        for (String items : com) {
            for (int i = 0; i < cardsOnHand.size(); i++) {
                if (items.equals(cardsOnHand.get(i).getNumber())) {
                    count++;
                    userOutput.add(cardsOnHand.get(i));
                    cardsOnHand.remove(i);
                    break;
                }
            }
        }

        //when issues are occurred, the cards will not be deleted
        if (count != com.size()) {
            cardsOnHand = backUp;
            System.out.println("你没有这样的牌");
        }
        this.rank();
        return userOutput;
    }
}
