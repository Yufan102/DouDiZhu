/*
This is the point system, and will implemented in the furture
 */
public class Points {
    private int num = 1000;
    private int startPoint = 10;
    private int callNum = 1;
    private int times = 1;

    /*
    According to rules the calling for the landlord will change the times
     */
    public Points(int callNum) {
        this.callNum = callNum;
    }

    /*
    Those function are for the rules;
    1 : calling for adding
    2 : two jokes will times 4
    3 : normal booms times 2
     */
    public void callForLord(int callNum) {
        times *= callNum;
        startPoint = startPoint * callNum;
    }

    public void jokerBoom() {
        times *= 4;
        startPoint = startPoint * 4;
    }

    public void boom() {
        times *= 2;
        startPoint = startPoint * 2;
    }

    public int winThis() {
        num += startPoint;
        return num;
    }

    public int loseThis() {
        num -= startPoint;
        return num;
    }

    public int getNum() {
        return num;
    }

    public int getTimes() {
        return times;
    }

    public int getStartPoint() {
        return startPoint;
    }
}
