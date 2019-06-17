package za.co.netbrain.quiz;

import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author admin
 */
public class Division extends FormMain {

    @Override
    public Image getImage(String image) {
        return getResources().getImage(image);
    }

    @Override
    public String getDisplayName() {
        return "Division";
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
            System.out.println("QuizNumbers : " + quizNumbers.getComponentCount());

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

        populateQuizNumbers();

        for (int x = 0; x < 10; x++) {

            Container rowContainer = new Container(new GridLayout(6));

            TextField result = new TextField();
            Integer firstValue = getLeftList().get(x).getValue();
            Integer secondValue = getRightList().get(x).getValue();

            Label answer = new Label();

            if ((firstValue > secondValue) && (firstValue % secondValue == 0)) {
                rowContainer.addComponent(new Label((getLeftList().get(x).getNumberImage().scaled(100, 100))));
                rowContainer.addComponent(new Label(getResources().getImage("devide.jpg").scaled(100, 100)));

                rowContainer.addComponent(new Label((getRightList().get(x).getNumberImage().scaled(100, 100))));
                rowContainer.addComponent(new Label(getResources().getImage("equals.jpg").scaled(100, 100)));

                Integer resultValue = firstValue / secondValue;

                result.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent t) {

                        try {

                            if (Integer.valueOf(result.getText()).equals(resultValue)) {
                                answer.setIcon(getResources().getImage("trophy.jpg").scaled(100, 100));
                                result.setEditable(false);
                            } else {
                                answer.setIcon(getResources().getImage("tryagain.jpg").scaled(100, 100));
                            }
                        } catch (NumberFormatException nfe) {
                        }
                    }
                });

                if (resultValue > 0) {
                    rowContainer.addComponent(result);
                    rowContainer.addComponent(answer);
                    numbersGroup.add(rowContainer);
                }
            }
            if ((secondValue > firstValue) && (secondValue % firstValue == 0)) {
                rowContainer.addComponent(new Label((getRightList().get(x).getNumberImage().scaled(100, 100))));
                rowContainer.addComponent(new Label(getResources().getImage("devide.jpg").scaled(100, 100)));

                rowContainer.addComponent(new Label((getLeftList().get(x).getNumberImage().scaled(100, 100))));
                rowContainer.addComponent(new Label(getResources().getImage("equals.jpg").scaled(100, 100)));

                Integer resultValue = secondValue / firstValue;

                result.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent t) {

                        try {

                            if (Integer.valueOf(result.getText()).equals(resultValue)) {
                                answer.setIcon(getResources().getImage("trophy.jpg").scaled(100, 100));
                                result.setEditable(false);
                            } else {
                                answer.setIcon(getResources().getImage("tryagain.jpg").scaled(100, 100));
                            }
                        } catch (NumberFormatException nfe) {

                        }
                    }
                });

                if (resultValue > 0) {
                    rowContainer.addComponent(result);
                    rowContainer.addComponent(answer);
                    numbersGroup.add(rowContainer);
                }
            }
            parent.revalidate();
        }

        /*if ((firstValue == 4) && (counter == 2)) {
            System.out.println("firstValue : " + firstValue);
            populateStaticLists();
            return getStaticDivisionsGroup();
        } else {*/
        return numbersGroup;
        //}
    }
}
