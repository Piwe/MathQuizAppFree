package za.co.netbrain.quiz;

import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;
import com.codename1.io.Util;
import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Results extends FormMain {

    private Integer score = 0;

    @Override
    public Image getImage(String image) {
        return getResources().getImage(image);
    }

    @Override
    public String getDisplayName() {
        return "Performance";
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

        Container numbers = BorderLayout.center(getResults(parent));
        numbers.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getResults(parent));

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

    public ComponentGroup getResults(Form parent) {

        ComponentGroup resultsGroup = new ComponentGroup();
        resultsGroup.setTactileTouch(true);
        resultsGroup.setScrollableY(true);

        Container additionContainer = getResultsContainer("addition", parent);
        Container mutiplicationContainer = getResultsContainer("multiplication", parent);
        Container subtractionContainer = getResultsContainer("subtraction", parent);
        Container divisionContainer = getResultsContainer("division", parent);
        
        resultsGroup.add(additionContainer);
        resultsGroup.add(mutiplicationContainer);
        resultsGroup.add(subtractionContainer);
        resultsGroup.add(divisionContainer);
        
        return resultsGroup;
    }
}
