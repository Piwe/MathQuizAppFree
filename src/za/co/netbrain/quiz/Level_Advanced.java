package za.co.netbrain.quiz;

import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import za.co.netbrain.quiz.utils.NumberUtility;

/**
 *
 * @author Simphiwe.Twala
 */
public class Level_Advanced extends FormMain {

    @Override
    public String getDisplayName() {
        return "Advanced";
    }

    @Override
    public Image getFormIcon() {
        return getResources().getImage("advanced.png");
    }
    
    @Override
    public Container createForm(Form parent) {

        NumberUtility numberUtility = new NumberUtility();
        numberUtility.setLevel(getDisplayName());
        
        Container forms = BorderLayout.center(getForms(parent, numberUtility,getResources()));
        forms.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getForms(parent, numberUtility,getResources()));

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
}