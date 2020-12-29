import java.util.ArrayList;

public class SillyAI extends AI {
    private int priority = 0;

    public SillyAI(Player ai) {
        super(ai);
        this.AI = ai;
        aiCard = ai.getCardsOnHand();
    }


    public ArrayList<Card> AIPlay(ArrayList<Card> out) {
        ArrayList<Card> pass = new ArrayList<Card>();
        if (Game.ifStraight(out))return null;
        if (out.get(0).getPriority() == 16 || out.get(0).getPriority() == 17)return null;
        if (Game.ifBoom(out) || Game.ifKingBoom(out) || Game.ifThree(out))return null;

        //single
        if (out.size() == 1) {
            for (int i = 0; i < aiCard.size(); i++) {
                Card check = aiCard.get(i);
                if (out.get(0).getPriority() < check.getPriority()) {
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
                    //if the first card
                    if (i == 0) {
                        if (aiCard.get(1).getPriority() == aiCard.get(0).getPriority()) {
                            pass.add(aiCard.get(0));
                            pass.add(aiCard.get(1));
                            aiCard.remove(0);
                            aiCard.remove(1);
                            return pass;
                        }
                    }
                    //cannot be the last card
                    else if (i != aiCard.size() - 1) {
                        Card left = aiCard.get(i - 1);
                        Card right = aiCard.get(i + 1);

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

    @Override
    public ArrayList<Card> FirstRound() {
        ArrayList<Card>out = new ArrayList<Card>();
        int decide = Game.NoDuplicateRandom(10,1).get(0);

        if (decide <= 5){
            out.add(aiCard.get(0));
            aiCard.remove(0);
        }else {
            for (int i = 0; i < aiCard.size(); i ++){
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
