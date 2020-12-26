import java.util.ArrayList;

public class SillyAI {
    private Player AI;
    private ArrayList<Card>aiCard = new ArrayList<Card>();

    private int priority = 0;

    public SillyAI(Player ai){
        this.AI = ai;
        aiCard = ai.getCardsOnHand();
    }

    public void setPriority(){
        priority = 0;
    }


    public ArrayList<Card> getAiCard() {
        return aiCard;
    }



    public ArrayList<Card> SillyAIPlay(ArrayList<Card>out){
        ArrayList<Card>pass = new ArrayList<Card>();
        if (!Game.ifStraight(out)){
            if (out.size() == 1 && out.get(0).getPriority() != 16 || out.get(0).getPriority() == 17){
                if (!Game.ifBoom(out) || !Game.ifKingBoom(out) || !Game.ifThree(out)){
                    //single
                    if (out.size() == 1){
                        for (int i = 0; i < aiCard.size(); i ++){
                            Card check = aiCard.get(i);
                            if(out.get(0).getPriority() < check.getPriority()){
                                pass.add(check);
                                aiCard.remove(i);
                                return pass;
                            }
                        }
                        return null;
                    }
                    //couples
                    else if (Game.ifCouples(out)){
                        Card first = out.get(0);
                        for (int i = 0; i < aiCard.size(); i ++){
                            Card check = aiCard.get(i);
                            if(first.getPriority() < check.getPriority()){
                                //if the first card
                                if (i == 0){
                                    if (aiCard.get(1).getPriority() == aiCard.get(0).getPriority()){
                                        pass.add(aiCard.get(0));
                                        pass.add(aiCard.get(1));
                                        aiCard.remove(0);
                                        aiCard.remove(1);
                                        return pass;
                                    }
                                }
                                //cannot be the last card
                                else if (i != aiCard.size() -1){
                                    Card left = aiCard.get(i-1);
                                    Card right = aiCard.get(i + 1);

                                    if (left.getPriority() == aiCard.get(i).getPriority()){
                                        pass.add(left);
                                        pass.add(aiCard.get(i));
                                        return pass;
                                    }else if (right.getPriority() == aiCard.get(i).getPriority()){
                                        pass.add(right);
                                        pass.add(aiCard.get(i));
                                        return pass;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}