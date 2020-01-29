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
import com.codename1.ui.util.Resources;
import za.co.netbrain.quiz.utils.NumberUtility;

/**
 *
 * @author Simphiwe.Twala
 */
public class Report extends FormMain {

    private Resources myResources;
    
    @Override
    public String getDisplayName() {
        return "Addition Report";
    }

    @Override
    public Image getFormIcon() {
        return null;
    }
    
    public Container createForm(Form parent) {

        Container numbers = new Container();
        return numbers;
    }
    
    

    public Resources getMyResources() {
        return myResources;
    }

    public void setMyResources(Resources myResources) {
        this.myResources = myResources;
    }
    
    
    
}
