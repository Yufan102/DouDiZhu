import java.util.ArrayList;

public class Points {
    private int num = 1000;
    private int startPoint = 10;
    private int callNum = 1;
    private int times = 1;

    public Points(int callNum){
        this.callNum = callNum;
    }

    public void callForLord(int callNum){
        times *= callNum;
        startPoint = startPoint * callNum;
    }

    public void jokerBoom(){
        times *= 4;
        startPoint = startPoint * 4;
    }

    public void boom(){
        times *= 2;
        startPoint = startPoint * 2;
    }

    public int winThis(){
        num += startPoint;
        return num;
    }

    public int loseThis(){
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
