import java.util.ArrayList;

/*
This class is one of the AI classes, it is created by authors
understanding of AI decisions of playing card.
The other hand, this AI class is doing silly jobs, it can
only do some basic card playing
 */

public class SillyAI extends AI {
    private int priority = 0;

    public SillyAI(Player ai) {
        super(ai);
        this.AI = ai;
        aiCard = ai.getCardsOnHand();
    }


    /*
    The implemented AIPlay method, for this method, the AI can only do following jobs:
    1 : the AI cannot beat when passed cards are straight, boom, and kings.
    2 : the AI will only search for the one number bigger card than passed cards
     */
    public ArrayList<Card> AIPlay(ArrayList<Card> out) {
        ArrayList<Card> pass = new ArrayList<Card>();

        //when passed cards is null, it will randomly select one
        if (out == null){
            pass = FirstRound();
        }

        //rules 1 of the silly AI
        if (Game.ifStraight(out))return null;
        if (out.get(0).getPriority() == 16 || out.get(0).getPriority() == 17)return null;
        if (Game.ifBoom(out) || Game.ifKingBoom(out) || Game.ifThree(out))return null;

        //when pass card is single
        if (out.size() == 1) {
            for (int i = 0; i < aiCard.size(); i++) {
                Card check = aiCard.get(i);
                if (out.get(0).getPriority() < check.getPriority()) {        //looking for the bigger one
                    pass.add(check);
                    aiCard.remove(i);
                    return pass;
                }
            }
            return null;
        }

        //couples
        else if (Game.ifCouples(out)) {
            Card first = out.get(0);
            for (int i = 0; i < aiCard.size(); i++) {
                Card check = aiCard.get(i);
                if (first.getPriority() < check.getPriority()) {
                    /*
                    When finding for the card, and it is the first card
                    the method can only look for the right card, or the null pointer will be thrown
                     */
                    if (i == 0) {
                        if (aiCard.get(1).getPriority() == aiCard.get(0).getPriority()) {
                            pass.add(aiCard.get(0));
                            pass.add(aiCard.get(1));
                            aiCard.remove(0);
                            aiCard.remove(1);
                            return pass;
                        }
                    }

                    //the couple card cannot be the last card
                    else if (i != aiCard.size() - 1) {
                        Card left = aiCard.get(i - 1);
                        Card right = aiCard.get(i + 1);

                        //checking for the left or the right card that can be added as the couples
                        if (left.getPriority() == aiCard.get(i).getPriority()) {
                            pass.add(left);
                            pass.add(aiCard.get(i));
                            aiCard.remove(i - 1);
                            return pass;
                        } else if (right.getPriority() == aiCard.get(i).getPriority()) {
                            pass.add(right);
                            pass.add(aiCard.get(i));
                            aiCard.remove(i + 1);
                            return pass;
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
    This method is implemented by creating random number to help the AI
    to decide the first round card is the couples or the single
     */
    @Override
    public ArrayList<Card> FirstRound() {
        ArrayList<Card>out = new ArrayList<Card>();
        int decide = Game.NoDuplicateRandom(10,1).get(0);

        //if it is single it will choose the first card
        if (decide <= 5){
            out.add(aiCard.get(0));
            aiCard.remove(0);
        }else {
            for (int i = 0; i < aiCard.size(); i ++){                   //for loops to looking for the couples
                Card first = aiCard.get(i);
                for (int j = 1; j < aiCard.size()-1; j ++){
                    Card second = aiCard.get(j);
                    if (first.getPriority() == second.getPriority()){
                        out.add(first);out.add(second);
                        aiCard.remove(i); aiCard.remove(j);
                        i = 17;
                        break;
                    }
                }
            }
        }

        return out;
    }
}
