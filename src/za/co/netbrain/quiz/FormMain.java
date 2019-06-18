package za.co.netbrain.quiz;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import za.co.netbrain.quiz.numbers.QuizNumber;

public abstract class FormMain {

    private Resources res;

    private final QuizNumber one = new QuizNumber();
    private final QuizNumber two = new QuizNumber();
    private final QuizNumber three = new QuizNumber();
    private final QuizNumber four = new QuizNumber();
    private final QuizNumber five = new QuizNumber();
    private final QuizNumber six = new QuizNumber();
    private final QuizNumber seven = new QuizNumber();
    private final QuizNumber eight = new QuizNumber();
    private final QuizNumber nine = new QuizNumber();
    private final QuizNumber ten = new QuizNumber();

    private List<QuizNumber> rightList = new ArrayList<>();
    private List<QuizNumber> leftList = new ArrayList<>();

    public void init(Resources res) {
        this.res = res;
    }

    protected Resources getResources() {
        return res;
    }

    public abstract String getDisplayName();

    public abstract Image getFormIcon();

    public Image getImage(String image) {
        return res.getImage(image);
    }

    public Container createForm() {
        throw new RuntimeException("Created Form");
    }

    public String getDescription() {
        return "";
    }

    public String getSourceCodeURL() {
        return "";
    }

    public Container createForm(Form parentForm) {
        return createForm();
    }

    boolean onBack() {
        return true;
    }

    public void populateQuizNumbers() {

        one.setValue(1);
        one.setNumberImage(getResources().getImage("one.jpg"));

        two.setValue(2);
        two.setNumberImage(getImage("two.jpg"));

        three.setValue(3);
        three.setNumberImage(getImage("three.jpg"));

        four.setValue(4);
        four.setNumberImage(getImage("four.jpg"));

        five.setValue(5);
        five.setNumberImage(getImage("five.jpg"));

        six.setValue(6);
        six.setNumberImage(getImage("six.jpg"));

        seven.setValue(7);
        seven.setNumberImage(getImage("seven.jpg"));

        eight.setValue(8);
        eight.setNumberImage(getImage("eight.jpg"));

        nine.setValue(9);
        nine.setNumberImage(getImage("nine.jpg"));

        ten.setValue(10);
        ten.setNumberImage(getImage("ten.jpg"));

        rightList.add(one);
        rightList.add(two);
        rightList.add(three);
        rightList.add(four);
        rightList.add(five);
        rightList.add(six);
        rightList.add(seven);
        rightList.add(eight);
        rightList.add(nine);
        rightList.add(ten);

        leftList.add(one);
        leftList.add(two);
        leftList.add(three);
        leftList.add(four);
        leftList.add(five);
        leftList.add(six);
        leftList.add(seven);
        leftList.add(eight);
        leftList.add(nine);
        leftList.add(ten);

        Collections.shuffle(rightList);
        Collections.shuffle(leftList);

    }

    public List<QuizNumber> getRightList() {
        return rightList;
    }

    public void setRightList(List<QuizNumber> rightList) {
        this.rightList = rightList;
    }

    public List<QuizNumber> getLeftList() {
        return leftList;
    }

    public void setLeftList(List<QuizNumber> leftList) {
        this.leftList = leftList;
    }
}
