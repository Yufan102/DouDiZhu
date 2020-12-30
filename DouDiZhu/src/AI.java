import java.util.ArrayList;
/*
This class is used for creating AI play method and some AI rules
It can be extended by sub-class by using the method
 */
public abstract class AI {
    protected Player AI;                      // the AI player that is used in this class
    protected ArrayList<Card>aiCard;          // the card, AI user has
    protected int priority;                   // this variable will be used in the future for types of cards

    public AI(Player ai){
        this.AI = ai;
        aiCard = ai.getCardsOnHand();
    }

    //setters and getters
    protected void setPriority(int priority) {
        this.priority = priority;
    }

    protected ArrayList<Card> getAiCard() {
        return aiCard;
    }

    /**
     * This abstract class is used by AI player, and it is not well documented yet.
     * Different types of AI playing cards rules can be added by using this method
     * @param out this perivous card that passes to AI to determine
     * @return cards that has been determined by AI user to beat others
     */
    public abstract ArrayList<Card> AIPlay(ArrayList<Card>out);


    /**
     * This method is only used when the AI player is the landlord, same as AIPlay method,
     * it can be changed by creating different types of AI with high skills or not.
     * @return the cards the user's first round card
     */
    public abstract ArrayList<Card> FirstRound();
}
