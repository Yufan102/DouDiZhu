import java.util.ArrayList;
/*
This method is only used to creat the whole tables of card
 */
public class FiftyFour {
    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * Creating 54 cards
     * @param type the char of cards
     */
    private void creatCards(String type){
        for (int i = 3; i < 16; i ++){
            if (i <= 10){
                String number = String.valueOf(i);
                Card card = new Card(type, number);
                cards.add(card);
            }else if (i == 11){
                Card card = new Card(type,"J");
                cards.add(card);
            }else if (i == 12){
                Card card = new Card(type,"Q");
                cards.add(card);
            }else if (i == 13){
                Card card = new Card(type,"K");
                cards.add(card);
            }else if (i == 14){
                Card card = new Card(type,"A");
                cards.add(card);
            }else if (i == 15){
                Card card = new Card(type,"2");
                cards.add(card);
            }
        }
    }

    public FiftyFour(){
        creatCards("*");
        creatCards("^");
        creatCards("@");
        creatCards("&");
        Card k = new Card("-","-");
        Card K = new Card("-", "+");
        cards.add(k);
        cards.add(K);
    }

    //getters
    public ArrayList<Card> getCards() {
        return cards;
    }

    public int size(){
        return cards.size();
    }

    //This method will remove the card with adding to another
    public Card poll(int i){
        Card now = cards.get(i);
        cards.remove(i);
        return now;
    }

}
