import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Player {
    private String type;
    private ArrayList<Card>cardsOnHand = new ArrayList<Card>();
    private ArrayList<Card>AIOutput = new ArrayList<Card>();
    private ArrayList<Card>userOutput = new ArrayList<Card>();
    private ArrayList<Card>ai1 = new ArrayList<Card>();
    SillyAI sb = new SillyAI(this);
    private boolean coop;
    private Points points;


    public Player(){}

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

    public void rank(){
        Collections.sort(cardsOnHand);
    }

    public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }

    public void isLord(){
        this.type = "Load";
        this.coop = false;
    }

    public void isFarmer(){
        this.type = "Farmer";
        this.coop = false;
    }

    public void setPoints(int num){
        points = new Points(num);
    }

    public Points getPoints() {
        return points;
    }

    public void userPlay(ArrayList<String>com){
        int count = 0;
        ArrayList<Card>backUp = cardsOnHand;

        for (String items : com){
            for(int i = 0; i < cardsOnHand.size();i ++){
                if (items.equals(cardsOnHand.get(i).getNumber())){
                    count++;
                    userOutput.add(cardsOnHand.get(i));
                    cardsOnHand.remove(i);
                }
                if (count == com.size()){
                    break;
                }
            }
        }

        if (count != com.size()){
            cardsOnHand = backUp;
            System.out.println("你没有这样的牌");
        }
        this.rank();
    }

    //stupid AI
    public void AIPlayer(ArrayList<Card>out){
        ai1 = sb.SillyAIPlay(out);
        cardsOnHand = sb.getAiCard();
    }




    public ArrayList<Card> getAi1() {
        return ai1;
    }


    public ArrayList<Card> getUserOutput() {
        return userOutput;
    }
}
