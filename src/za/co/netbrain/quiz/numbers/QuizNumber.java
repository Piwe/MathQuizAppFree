
package za.co.netbrain.quiz.numbers;

import com.codename1.ui.Image;
import com.codename1.ui.Component;
/**
 *
 * @author admin
 */
public class QuizNumber extends Component {
    
    private Integer value;
    private Image numberImage;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Image getNumberImage() {
        return numberImage;
    }

    public void setNumberImage(Image numberImage) {
        this.numberImage = numberImage;
    } 
    
    
}
