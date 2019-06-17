package za.co.netbrain.quiz;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

/**
 *
 * @author admin
 */
public class Shapes extends FormMain {

    @Override
    public String getDisplayName() {
        return "Shapes";
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
        
        Container container = new Container();

        return container;
    }

}
