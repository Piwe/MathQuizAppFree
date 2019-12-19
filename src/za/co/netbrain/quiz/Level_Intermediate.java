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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;

/**
 *
 * @author Simphiwe.Twala
 */
public class Level_Intermediate extends FormMain {

    @Override
    public String getDisplayName() {
        return "Intermediate";
    }

    @Override
    public Image getFormIcon() {
        return getResources().getImage("intermediate.png");
    }
    
    @Override
    public Container createForm(Form parent) {

        Container forms = BorderLayout.center(getForms(parent));
        forms.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(getForms(parent));

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