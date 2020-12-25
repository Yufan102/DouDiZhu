import java.util.HashMap;

public class Card implements Comparable {
    private String type;
    private String number;
    private int priority = 3;
    private HashMap<String,Integer> cardSet = new HashMap<String, Integer>();

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

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public String getNumber() {
        return number;
    }

    public Boolean isBigger(Card card){
        return this.priority > card.getPriority();
    }

    public String display(){
        String display = "|" + this.type + " " + this.number + "|";

        return display;
    }

    public Boolean compareTo(Card card){
        return card.getPriority() > this.priority;
    }


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
