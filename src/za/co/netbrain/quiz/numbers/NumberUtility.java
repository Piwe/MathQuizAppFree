package za.co.netbrain.quiz.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Simphiwe.Twala
 */
public class NumberUtility {

    private List<Integer> numberList = new ArrayList<>();
    private List<Integer> leftList = new ArrayList<>();
    private List<Integer> rightList = new ArrayList<>();

    private Integer limit;

    public List<Integer> getLeftList() {
        return leftList;
    }

    public void setLeftList(List<Integer> leftList) {
        this.leftList = leftList;
    }

    public List<Integer> getRightList() {
        return rightList;
    }

    public void setRightList(List<Integer> rightList) {
        this.rightList = rightList;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getNumberList(String level) {

        setLimit(level);
        for (int x = 0; x < getLimit(); x++) {
            numberList.add(x);
        }
        
        leftList = numberList;
        rightList = numberList;
        Collections.shuffle(leftList);
        Collections.shuffle(rightList);
        
        return numberList.size();
    }

    private void setLimit(String level) {
        if ("beginner".equals(level)) {
            setLimit(10);
        }
        if ("intermediate".equals(level)) {
            setLimit(50);
        }
        if ("advanced".equals(level)) {
            setLimit(100);
        }
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList = numberList;
    }
}
