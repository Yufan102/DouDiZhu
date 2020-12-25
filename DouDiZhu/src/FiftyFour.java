import java.util.ArrayList;

public class FiftyFour {
    private ArrayList<Card> cards = new ArrayList<Card>();

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

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void displayAll(){
        for (Card card : cards){
            System.out.print(card.display()+ " ");
        }
    }

    public int size(){
        return cards.size();
    }

    public Card poll(int i){
        Card now = cards.get(i);
        cards.remove(i);
        return now;
    }

    public String cardToString(int i){
        return cards.get(i).display();
    }
}
