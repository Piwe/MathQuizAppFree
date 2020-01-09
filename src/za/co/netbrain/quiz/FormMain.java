package za.co.netbrain.quiz;

import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import za.co.netbrain.quiz.numbers.NumberUtility;

public abstract class FormMain {

    private Resources res;
        
    Form myForm = new Form();
    
    

    public void init(Resources res) {
        myForm.show();
        this.res = res;
    }

    public Resources getResources() {
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
    
    
    public ComponentGroup getForms(Form parent, NumberUtility numberUtility,Resources resources) {
    
        ComponentGroup formsGroup = new ComponentGroup();
        formsGroup.setTactileTouch(true);
        formsGroup.setScrollableY(true);
        
        Button addition = new Button(getImage("form_addition.png").scaled(400, 300));
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Addition additionForm = new Addition();
                additionForm.setMyResources(res);
                Form form = new Form("Addition");
                form.add(additionForm.createForm(parent, numberUtility));
                form.getToolbar().setBackCommand(" ", new ActionListener<ActionEvent>() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (onBack()) {
                            parent.showBack();
                        }
                    }
                });
                form.show();
            }
        });
        Button subtraction = new Button(getImage("form_subtraction.png").scaled(400, 300));
        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Subtraction subtractionForm = new Subtraction();
                subtractionForm.setMyResources(res);
                Form form = new Form("Subtraction");
                form.add(subtractionForm.createForm(parent, numberUtility));
                form.getToolbar().setBackCommand(" ", new ActionListener<ActionEvent>() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (onBack()) {
                            parent.showBack();
                        }
                    }
                });
                form.show();
            }
        });
        Button division = new Button(getImage("form_division.png").scaled(400, 300));
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Division divisionForm = new Division();
                divisionForm.setMyResources(res);
                Form form = new Form("Division");
                form.add(divisionForm.createForm(parent, numberUtility));
                form.getToolbar().setBackCommand(" ", new ActionListener<ActionEvent>() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (onBack()) {
                            parent.showBack();
                        }
                    }
                });
                form.show();
            }
        });
        Button multiplication = new Button(getImage("form_multiplication.png").scaled(400, 300));
        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Multiplication multiplicationForm = new Multiplication();
                multiplicationForm.setMyResources(res);
                Form form = new Form("Multiplication");
                form.add(multiplicationForm.createForm(parent, numberUtility));
                form.getToolbar().setBackCommand(" ", new ActionListener<ActionEvent>() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (onBack()) {
                            parent.showBack();
                        }
                    }
                });
                form.show();
            }
        });
        Button patternMatching = new Button(getImage("form_patternmatching.png").scaled(400, 300));
        patternMatching.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PatternMatching patternMatchingForm = new PatternMatching();
                patternMatchingForm.setMyResources(res);
                patternMatchingForm.setMyPatternImages(getPattenImages());
                Form form = new Form("Pattern Matching");
                form.add(patternMatchingForm.createForm(parent, numberUtility));
                form.getToolbar().setBackCommand(" ", new ActionListener<ActionEvent>() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (onBack()) {
                            parent.showBack();
                        }
                    }
                });
                form.show();
            }
        });
        
        formsGroup.addComponent(addition);
        formsGroup.addComponent(subtraction);
        formsGroup.addComponent(division);
        formsGroup.addComponent(multiplication);
        formsGroup.addComponent(patternMatching);
        
        parent.revalidate();
        return formsGroup;
    }   
    
    public List<Image> getPattenImages() {
        
     List<Image> patternImages = new ArrayList<>();   
             
     Image diamond = getImage("s_diamond.jpg").scaled(100, 100);
     Image moon = getImage("s_moon.jpg").scaled(100, 100);
     Image octagon = getImage("s_octagon.jpg").scaled(100, 100);
     Image oval = getImage("s_oval.jpg").scaled(100, 100);
     Image rectangle = getImage("s_rectangle.jpg").scaled(100, 100);
     Image round = getImage("s_round.jpg").scaled(100, 100);
     Image square = getImage("s_square.jpg").scaled(100, 100);
     Image star = getImage("s_star.jpg").scaled(100, 100);
     Image trapezium = getImage("s_trapezium.jpg").scaled(100, 100);
     Image triangle = getImage("s_triangle.jpg").scaled(100, 100);
     
     patternImages.add(diamond);
     patternImages.add(moon);
     patternImages.add(octagon);
     patternImages.add(oval);
     patternImages.add(rectangle);
     patternImages.add(round);
     patternImages.add(square);
     patternImages.add(star);
     patternImages.add(trapezium);
     patternImages.add(triangle);
     
     Collections.shuffle(patternImages);
     
     return patternImages;
     
    }
    
}
