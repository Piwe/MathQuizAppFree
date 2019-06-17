package za.co.netbrain.quiz.shapes;

import com.codename1.ui.Image;

/**
 *
 * @author admin
 */
public class QuizShape {
    
    private String name;
    private Image shapeImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getShapeImage() {
        return shapeImage;
    }

    public void setShapeImage(Image shapeImage) {
        this.shapeImage = shapeImage;
    }
    
    
    /*// when the user drops a card on a drop target (currently only the deck) we remove it and animate it out
        card.addDropListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // destination.addComponent(card);
                evt.consume();
                card == button
                card.getParent().removeComponent(card);
                destination == container
                destination.animateLayout(300);
            }
        });*/
    
}
