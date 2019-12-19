package za.co.netbrain.quiz;

import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

public abstract class FormMain {

    private Resources res;
        
    Form myForm = new Form();
    
    

    public void init(Resources res) {
        myForm.show();
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
    
    
    public ComponentGroup getForms(Form parent) {
    
        ComponentGroup formsGroup = new ComponentGroup();
        formsGroup.setTactileTouch(true);
        formsGroup.setScrollableY(true);
        
        Button addition = new Button("Addition");
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Addition additionForm = new Addition();
                Form form = new Form("Addition");
                form.add(additionForm.createForm(parent));
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
        Button subtraction = new Button("Subtraction");
        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Subtraction subtractionForm = new Subtraction();
                Form form = new Form("Subtraction");
                form.add(subtractionForm.createForm(parent));
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
        Button division = new Button("Division");
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Division divisionForm = new Division();
                Form form = new Form("Division");
                form.add(divisionForm.createForm(parent));
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
        Button multiplication = new Button("Multiplication");
        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Multiplication multiplicationForm = new Multiplication();
                Form form = new Form("Multiplication");
                form.add(multiplicationForm.createForm(parent));
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
        
        parent.revalidate();
        return formsGroup;
    }   
}
