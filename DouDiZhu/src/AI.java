import java.util.ArrayList;

public abstract class AI {
    protected Player AI;
    protected ArrayList<Card>aiCard;
    protected int priority;

    public AI(Player ai){
        this.AI = ai;
        aiCard = ai.getCardsOnHand();
    }

    protected void setPriority(int priority) {
        this.priority = priority;
    }

    protected ArrayList<Card> getAiCard() {
        return aiCard;
    }

    public abstract ArrayList<Card> AIPlay(ArrayList<Card>out);

    public abstract ArrayList<Card> FirstRound();
}
