package za.co.netbrain.quiz;

import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import za.co.netbrain.quiz.utils.NumberUtility;

public abstract class FormMain {

    Resources res;
        
    Form myForm = new Form();
    private Image blank;
    

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
        Style additionStyle = addition.getAllStyles();
        additionStyle.setBorder(RoundRectBorder.create().shadowOpacity(100));     
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
        Style subtractionStyle = subtraction.getAllStyles();
        subtractionStyle.setBorder(RoundRectBorder.create().shadowOpacity(100));
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
        Style divisionStyle = division.getAllStyles();
        divisionStyle.setBorder(RoundRectBorder.create().shadowOpacity(100)); 
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
        Style multiplicationStyle = multiplication.getAllStyles();
        multiplicationStyle.setBorder(RoundRectBorder.create().shadowOpacity(100));
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
        Style patternStyle = patternMatching.getAllStyles();
        patternStyle.setBorder(RoundRectBorder.create().create().shadowOpacity(100)); 
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
        Button sudoku = new Button(getImage("form_patternmatching.png").scaled(400, 300));
        Style sudokuStyle = sudoku.getAllStyles();
        sudokuStyle.setBorder(RoundRectBorder.create().create().shadowOpacity(100)); 
        sudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Sudoku_ sudokuForm = new Sudoku_();
                sudokuForm.setMyResources(res);
                Form form = new Form("Pattern Matching");
                form.add(sudokuForm.createForm(parent));
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
        formsGroup.addComponent(sudoku);
        
        parent.revalidate();
        return formsGroup;
    }   
    
    public List<Image> getPattenImages() {
        
     List<Image> patternImages = new ArrayList<>();   
             
     Image diamond = getImage("s_diamond.png").scaled(100, 100);
     Image moon = getImage("s_moon.png").scaled(100, 100);
     Image octagon = getImage("s_octagon.png").scaled(100, 100);
     Image oval = getImage("s_oval.png").scaled(100, 100);
     Image rectangle = getImage("s_rectangle.png").scaled(100, 100);
     Image round = getImage("s_round.png").scaled(100, 100);
     Image square = getImage("s_square.png").scaled(100, 100);
     Image star = getImage("s_star.png").scaled(100, 100);
     Image trapezium = getImage("s_trapezium.png").scaled(100, 100);
     Image triangle = getImage("s_triangle.png").scaled(100, 100);
       
     setBlank(getImage("circle.png").scaled(100, 100));
     
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

    public Image getBlank() {
        return blank;
    }

    public void setBlank(Image blank) {
        this.blank = blank;
    }
    
    
    
}
