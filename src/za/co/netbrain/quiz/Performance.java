package za.co.netbrain.quiz;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Simphiwe.Twala
 */
public class Performance extends FormMain {

    @Override
    public String getDisplayName() {
        return "Performance";
    }

    @Override
    public Image getFormIcon() {
        return getResources().getImage("performance.png");
    }

    @Override
    public Container createForm(Form parent) {

        Container forms = BorderLayout.center(getPerformanceForms(parent, getResources()));
        forms.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getPerformanceForms(parent, getResources()));

        Container formsContainer;
        if (!isTablet()) {
            Label placeholder = new Label(" ");

            Component.setSameHeight(actualContent, placeholder);
            Component.setSameWidth(actualContent, placeholder);

            formsContainer = BorderLayout.center(placeholder);

            parent.addShowListener(e -> {
                if (placeholder.getParent() != null) {
                    formsContainer.replace(placeholder, actualContent, CommonTransitions.createFade(1500));
                }
            });
        } else {
            formsContainer = BorderLayout.center(actualContent);
        }

        formsContainer.setUIID("InputContainerBackground");
        return forms;

    }

    public ComponentGroup getPerformanceForms(Form parent, Resources resources) {

        ComponentGroup formsGroup = new ComponentGroup();
        formsGroup.setTactileTouch(true);
        formsGroup.setScrollableY(true);

        Button addition = new Button(getImage("form_addition.png").scaled(400, 300));
        Style additionStyle = addition.getAllStyles();
        additionStyle.setBorder(RoundRectBorder.create().shadowOpacity(100));
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Report additionReportForm = new Report();
                additionReportForm.setMyResources(res);
                Form form = new Form("Addition Report");
                form.add(additionReportForm.createForm(parent));
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
                Report subtractionReportForm = new Report();
                subtractionReportForm.setMyResources(res);
                Form form = new Form("Subtraction Report");
                form.add(subtractionReportForm.createForm(parent));
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
                Report divisionReportForm = new Report();
                divisionReportForm.setMyResources(res);
                Form form = new Form("Division Report");
                form.add(divisionReportForm.createForm(parent));
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
                Report multiplicationReportForm = new Report();
                multiplicationReportForm.setMyResources(res);
                Form form = new Form("Multiplication Report");
                form.add(multiplicationReportForm.createForm(parent));
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
                Report patternMatchingReportForm = new Report();
                patternMatchingReportForm.setMyResources(res);
                Form form = new Form("Pattern Matching Report");
                form.add(patternMatchingReportForm.createForm(parent));
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
}
