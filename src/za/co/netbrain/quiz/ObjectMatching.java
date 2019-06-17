package za.co.netbrain.quiz;

import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import za.co.netbrain.quiz.shapes.QuizShape;

/**
 *
 * @author admin
 */
public class ObjectMatching extends FormMain {

    @Override
    public String getDisplayName() {
        return "Pattern Matching";
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

        Container shapes = BorderLayout.center(getShapes(parent));
        shapes.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getShapes(parent));

        Container quizShapes;
        if (!isTablet()) {
            Label placeholder = new Label(" ");

            Component.setSameHeight(actualContent, placeholder);
            Component.setSameWidth(actualContent, placeholder);

            quizShapes = BorderLayout.center(placeholder);

            parent.addShowListener(e -> {
                if (placeholder.getParent() != null) {
                    quizShapes.replace(placeholder, actualContent, CommonTransitions.createFade(1500));
                }
            });
        } else {
            quizShapes = BorderLayout.center(actualContent);
        }
        quizShapes.setUIID("InputContainerBackground");
        return shapes;
    }

    public ComponentGroup getShapes(Form parent) {

        ComponentGroup shapesGroup = new ComponentGroup();
        shapesGroup.setTactileTouch(true);
        shapesGroup.setScrollableY(true);

        populateQuizShapes();

        List<QuizShape> quizShapeList_1 = getQuizShapeList();
        Collections.shuffle(quizShapeList_1, new Random(7L));
        List<QuizShape> quizShapeList_2 = getQuizShapeList();
        Collections.shuffle(quizShapeList_2, new Random(4L));

        getQuizShapeList().addAll(quizShapeList_1);
        getQuizShapeList().addAll(quizShapeList_2);

        for (int y = 0; y < 16; y++) {
            Container rowContainer = new Container(new GridLayout(6));
            Label one = new Label(getQuizShapeList().get(y+1).getShapeImage().scaled(50,50));
            Label two = new Label(getQuizShapeList().get(y+3).getShapeImage().scaled(50,50));
            Label three = new Label(getQuizShapeList().get(y+5).getShapeImage().scaled(50,50));
            Label four = new Label(getQuizShapeList().get(y+7).getShapeImage().scaled(50,50));
            Label five = new Label(getQuizShapeList().get(y+9).getShapeImage().scaled(50,50));
            Label six = new Label(getQuizShapeList().get(y+11).getShapeImage().scaled(50,50));

            rowContainer.addComponent(one);
            rowContainer.addComponent(two);
            rowContainer.addComponent(three);
            rowContainer.addComponent(four);
            rowContainer.addComponent(five);
            rowContainer.addComponent(six);

            shapesGroup.add(rowContainer);
            parent.revalidate();

        }
        return shapesGroup;
    }
}
