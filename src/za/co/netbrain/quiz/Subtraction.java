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
import za.co.netbrain.quiz.numbers.NumberUtility;

/**
 *
 * @author admin
 */
public class Subtraction extends FormMain {

    private NumberUtility numberUtility = new NumberUtility();
    
    @Override
    public String getDisplayName() {
        return "Subtraction";
    }

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

    @Override
    public Container createForm(Form parent) {

        Container numbers = BorderLayout.center(getNumbers(parent));
        numbers.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getNumbers(parent));

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

    public ComponentGroup getNumbers(Form parent) {

        ComponentGroup numbersGroup = new ComponentGroup();
        numbersGroup.setTactileTouch(true);
        numbersGroup.setScrollableY(true);

        int limit = numberUtility.getNumberList("beginner");

        for (int x = 0; x < limit; x++) {

            Container rowContainer = new Container(new GridLayout(6));

            TextField result = new TextField();

            Integer firstValue = numberUtility.getLeftList().get(x);
            Integer secondValue = numberUtility.getRightList().get(x);
            
            result.setConstraint(TextArea.NUMERIC);

            Label answer = new Label();

            if (secondValue > firstValue) {
                rowContainer.addComponent(new Label("" + numberUtility.getRightList().get(x)));
                rowContainer.addComponent(new Label("-"));

                rowContainer.addComponent(new Label("" + numberUtility.getLeftList().get(x)));
                rowContainer.addComponent(new Label("="));

                Integer resultValue = secondValue - firstValue;

                result.addActionListener((ActionListener) (ActionEvent t) -> {
                    try {
                        if (Integer.valueOf(result.getText()).equals(resultValue)) {
                            answer.setIcon(getResources().getImage("trophy.jpg").scaled(100, 100));
                            result.setEditable(false);
                        } else {
                            answer.setIcon(getResources().getImage("tryagain.jpg").scaled(100, 100));
                        }
                    } catch (NumberFormatException nfe) {

                    }
                });

            } else {
                rowContainer.addComponent(new Label("" + numberUtility.getLeftList().get(x)));
                rowContainer.addComponent(new Label("-"));

                rowContainer.addComponent(new Label("" + numberUtility.getRightList().get(x)));
                rowContainer.addComponent(new Label("="));

                Integer resultValue = firstValue - secondValue;

                result.addActionListener((ActionListener) (ActionEvent t) -> {
                    try {
                        if (Integer.valueOf(result.getText()).equals(resultValue)) {
                            answer.setIcon(getResources().getImage("trophy.jpg").scaled(100, 100));
                            result.setEditable(false);
                        } else {
                            answer.setIcon(getResources().getImage("tryagain.jpg").scaled(100, 100));
                        }
                    } catch (NumberFormatException nfe) {

                    }
                });
            }

            rowContainer.addComponent(result);
            rowContainer.addComponent(answer);
            numbersGroup.add(rowContainer);
            parent.revalidate();
        }
        return numbersGroup;
    }
}
