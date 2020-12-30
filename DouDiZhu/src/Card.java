import java.util.HashMap;
/*
This class is created for the card with different types and numbers
that will be added as lists to be used
 */
public class Card implements Comparable {
    private String type;
    private String number;
    private int priority = 3;
    private HashMap<String,Integer> cardSet = new HashMap<String, Integer>();

    // the constructor will add the number of card by hashing the char of the real card
    public Card(String type, String number){
        cardSet.put("J",11);
        cardSet.put("Q",12);
        cardSet.put("K",13);
        cardSet.put("A",14);
        cardSet.put("2",15);
        cardSet.put("-",16);
        cardSet.put("+",17);
        cardSet.put("3",3);
        cardSet.put("4",4);
        cardSet.put("5",5);
        cardSet.put("6",6);
        cardSet.put("7",7);
        cardSet.put("8",8);
        cardSet.put("9",9);
        cardSet.put("10",10);

        this.type = type;
        this.number = number;
        this.priority = cardSet.get(number);
    }

    //getters and setters
    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public String getNumber() {
        return number;
    }

    /**
     * This method will print the card for playing
     * @return the String to be printed
     */
    public String display(){
        String display = "|" + this.type + " " + this.number + "|";
        return display;
    }

    /**
     * This method is used to sort the list of cards
     * @param o the card
     * @return if the pass card is bigger
     */
    @Override
    public int compareTo(Object o) {
        Card c = (Card)o;
        if (this.priority > c.priority){
            return 1;
        }else{
            return -1;
        }
    }
}
