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
import za.co.netbrain.quiz.sudoku.Sudoku;

/**
 *
 * @author Simphiwe.Twala
 */
public class Sudoku_ extends FormMain {

    private Resources myResources;

    @Override
    public String getDisplayName() {
        return "Sudoku";
    }

    @Override
    public Image getFormIcon() {
        return null;
    }

    public Container createForm(Form parent) {

        int N = 9, K = 20;
        Sudoku sudoku = new Sudoku(N, K);
        sudoku.fillValues();

        Container sudokuNumbers = BorderLayout.center(sudoku.printSudoku(parent, N,K));
        sudokuNumbers.setUIID("InputContainerForeground");

        Container actualContent = LayeredLayout.encloseIn(sudoku.printSudoku(parent, N,K));

        Container quizSudoku;
        if (!isTablet()) {
            Label placeholder = new Label(" ");

            Component.setSameHeight(actualContent, placeholder);
            Component.setSameWidth(actualContent, placeholder);

            quizSudoku = BorderLayout.center(placeholder);
            parent.addShowListener(e -> {
                if (placeholder.getParent() != null) {
                    quizSudoku.replace(placeholder, actualContent, CommonTransitions.createFade(1500));
                }
            });
        } else {
            quizSudoku = BorderLayout.center(actualContent);
        }

        quizSudoku.setUIID("InputContainerBackground");
        return sudokuNumbers;
    }

    public Resources getMyResources() {
        return myResources;
    }

    public void setMyResources(Resources myResources) {
        this.myResources = myResources;
    }

}
