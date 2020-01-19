package za.co.netbrain.quiz;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import za.co.netbrain.quiz.numbers.NumberUtility;

/**
 *
 * @author admin
 */
public class PatternMatching extends FormMain {

    private List<Image> myPatternImages = new ArrayList<>();

    @Override
    public String getDisplayName() {
        return "Pattern Matching";
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

        ComponentGroup patternGroup = new ComponentGroup();
        patternGroup.setTactileTouch(true);
        patternGroup.setScrollableY(true);

        for (int x = 0; x < getImageContainers().size(); x++) {
            patternGroup.add(getImageContainers().get(x));
        }
        parent.revalidate();

        return patternGroup;

    }

    public Resources getMyResources() {
        return myResources;
    }

    public void setMyResources(Resources myResources) {
        this.myResources = myResources;
    }

    public List<Container> getImageContainers() {

        List<Image> rowList_1 = new ArrayList<>();
        List<Image> rowList_2 = new ArrayList<>();
        List<Image> rowList_3 = new ArrayList<>();
        List<Image> rowList_4 = new ArrayList<>();

        List<Container> myPatternContainer = new ArrayList<>();

        for (int x = 1; x < 11; x++) {
            if (x == 1) {
                rowList_1.add(getMyPatternImages().get(x));
            }
            if (x == 2) {
                rowList_1.add(getMyPatternImages().get(x));
            }
            if (x == 3) {
                rowList_1.add(getMyPatternImages().get(x));
            }
            if (x == 4) {
                rowList_1.add(getBlank());
                break;
            }
        }
        Button row1Image = new Button(getMyPatternImages().get(1));
        row1Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        Container rowContainer_1 = new Container(new GridLayout(5));
        for (int x = 0; x < rowList_1.size(); x++) {
            Button rowButton = new Button(rowList_1.get(x));
            Style rowButtonStyle = rowButton.getAllStyles();
            rowButtonStyle.setBorder(RoundBorder.create().shadowOpacity(100));
            rowContainer_1.add(rowButton);
        }
        rowContainer_1.add(row1Image);
        myPatternContainer.add(rowContainer_1);

        for (int x = 1; x < 11; x++) {
            if (x == 1) {
                rowList_2.add(getMyPatternImages().get(x));
            }
            if (x == 2) {
                rowList_2.add(getMyPatternImages().get(x - 1));
            }
            if (x == 3) {
                rowList_2.add(getBlank());
            }
            if (x == 4) {
                rowList_2.add(getMyPatternImages().get(x - 2));
                break;
            }
        }
        Button row2Image = new Button(getMyPatternImages().get(2));
        row2Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        Container rowContainer_2 = new Container(new GridLayout(5));
        for (int x = 0; x < rowList_2.size(); x++) {
            Button rowButton = new Button(rowList_2.get(x));
            Style rowButtonStyle = rowButton.getAllStyles();
            rowButtonStyle.setBorder(RoundBorder.create().shadowOpacity(100));
            rowContainer_2.add(rowButton);
        }
        rowContainer_2.add(row2Image);
        myPatternContainer.add(rowContainer_2);

        for (int x = 1; x < 11; x++) {
            if (x == 1) {
                rowList_3.add(getMyPatternImages().get(x));
            }
            if (x == 2) {
                rowList_3.add(getMyPatternImages().get(x));
            }
            if (x == 3) {
                rowList_3.add(getBlank());
            }
            if (x == 4) {
                rowList_3.add(getMyPatternImages().get(x - 3));
                break;
            }
        }
        Button row3Image = new Button(getMyPatternImages().get(3));
        row3Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        Container rowContainer_3 = new Container(new GridLayout(5));
        for (int x = 0; x < rowList_3.size(); x++) {
            Button rowButton = new Button(rowList_3.get(x));
            Style rowButtonStyle = rowButton.getAllStyles();
            rowButtonStyle.setBorder(RoundBorder.create().shadowOpacity(100));
            rowContainer_3.add(rowButton);
        }
        rowContainer_3.add(row3Image);
        myPatternContainer.add(rowContainer_3);

        for (int x = 1; x < 11; x++) {
            if (x == 1) {
                rowList_4.add(getBlank());
            }
            if (x == 2) {
                rowList_4.add(getMyPatternImages().get(x));
            }
            if (x == 3) {
                rowList_4.add(getMyPatternImages().get(x - 2));
            }
            if (x == 4) {
                rowList_4.add(getMyPatternImages().get(x - 2));
                break;
            }
        }
        Button row4Image = new Button(getMyPatternImages().get(4));
        row4Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        Container rowContainer_4 = new Container(new GridLayout(5));
        for (int x = 0; x < rowList_4.size(); x++) {
            Button rowButton = new Button(rowList_4.get(x));
            Style rowButtonStyle = rowButton.getAllStyles();
            rowButtonStyle.setBorder(RoundBorder.create().shadowOpacity(100));
            rowContainer_4.add(rowButton);
        }
        rowContainer_4.add(row4Image);
        myPatternContainer.add(rowContainer_4);

        //Collections.shuffle(myPatternContainer);
        return myPatternContainer;

    }

    public List<Image> getMyPatternImages() {
        return myPatternImages;
    }

    public void setMyPatternImages(List<Image> myPatternImages) {
        this.myPatternImages = myPatternImages;
    }

}
