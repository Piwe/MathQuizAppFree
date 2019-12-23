package za.co.netbrain.quiz;

import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import za.co.netbrain.quiz.numbers.NumberUtility;

/**
 *
 * @author admin
 */
public class Multiplication extends FormMain {

    @Override
    public String getDisplayName() {
        return "Multiplication";
    }

    private Resources myResources;

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getSourceCodeURL() {
        return "";
    }

    @Override
    public Image getFormIcon() {
        return getResources().getImage("logo.jpg");
    }

    public Container createForm(Form parent, NumberUtility numberUtility) {

        Container numbers = BorderLayout.center(getNumbers(parent, numberUtility));
        numbers.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getNumbers(parent, numberUtility));

        Container quizNumbers;
        if (!isTablet()) {
            Label placeholder = new Label(" ");

            Component.setSameHeight(actualContent, placeholder);
            Component.setSameWidth(actualContent, placeholder);

            quizNumbers = BorderLayout.center(placeholder);

            parent.addShowListener(e -> {
                if (placeholder.getParent() != null) {
                    quizNumbers.replace(placeholder, actualContent, CommonTransitions.createFade(1500));
                }
            });
        } else {
            quizNumbers = BorderLayout.center(actualContent);
        }
        quizNumbers.setUIID("InputContainerBackground");
        return numbers;

    }

    public ComponentGroup getNumbers(Form parent, NumberUtility numberUtility) {

        ComponentGroup numbersGroup = new ComponentGroup();
        numbersGroup.setTactileTouch(true);
        numbersGroup.setScrollableY(true);

        int limit = numberUtility.getNumberList(numberUtility.getLevel());

        for (int x = 0; x < limit; x++) {

            Container rowContainer = new Container(new GridLayout(6));

            Integer firstValue = numberUtility.getLeftList().get(x);
            rowContainer.addComponent(new Label("" + numberUtility.getLeftList().get(x)));
            rowContainer.addComponent(new Label(getMyResources().getImage("multiply.jpg").scaled(100, 100)));

            Integer secondValue = numberUtility.getRightList().get(x);
            rowContainer.addComponent(new Label("" + numberUtility.getRightList().get(x)));
            rowContainer.addComponent(new Label("="));

            TextField result = new TextField();
            Integer resultValue = firstValue * secondValue;

            result.setConstraint(TextArea.NUMERIC);

            Label answer = new Label();

            result.addActionListener((ActionListener) (ActionEvent t) -> {
                try {

                    if (Integer.valueOf(result.getText()).equals(resultValue)) {
                        answer.setIcon(getMyResources().getImage("trophy.jpg").scaled(100, 100));
                        result.setEditable(false);
                    } else {
                        answer.setIcon(getMyResources().getImage("tryagain.jpg").scaled(100, 100));
                    }
                } catch (NumberFormatException nfe) {

                }
            });
            rowContainer.addComponent(result);
            rowContainer.addComponent(answer);
            numbersGroup.add(rowContainer);
            parent.revalidate();
        }
        return numbersGroup;
    }

    public Resources getMyResources() {
        return myResources;
    }

    public void setMyResources(Resources myResources) {
        this.myResources = myResources;
    }
}
