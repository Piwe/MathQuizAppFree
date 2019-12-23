package za.co.netbrain.quiz.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Simphiwe.Twala
 */
public class NumberUtility {

    private List<Integer> leftList = new ArrayList<>();
    private List<Integer> rightList = new ArrayList<>();

    private Integer limit;
    private String level;

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
        for (int x = 1; x < getLimit(); x++) {
            leftList.add(x);
        }
        
        for (int y = 1; y < getLimit(); y++) {
            rightList.add(y);
        }
        
        Collections.shuffle(leftList);
        Collections.shuffle(rightList);
        
        return leftList.size();
    }

    private void setLimit(String level) {
        if ("Beginner".equals(level)) {
            setLimit(11);
        }
        if ("Intermediate".equals(level)) {
            setLimit(51);
        }
        if ("Advanced".equals(level)) {
            setLimit(101);
        }
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
