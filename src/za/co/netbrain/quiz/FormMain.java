package za.co.netbrain.quiz;

import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import za.co.netbrain.quiz.numbers.QuizNumber;
import za.co.netbrain.quiz.shapes.QuizShape;

public abstract class FormMain {

    private Resources res;
    private Integer result;

    private final QuizNumber one = new QuizNumber();
    private final QuizNumber two = new QuizNumber();
    private final QuizNumber three = new QuizNumber();
    private final QuizNumber four = new QuizNumber();
    private final QuizNumber five = new QuizNumber();
    private final QuizNumber six = new QuizNumber();
    private final QuizNumber seven = new QuizNumber();
    private final QuizNumber eight = new QuizNumber();
    private final QuizNumber nine = new QuizNumber();
    private final QuizNumber ten = new QuizNumber();

    private final QuizShape circle = new QuizShape();
    private final QuizShape triangle = new QuizShape();
    private final QuizShape square = new QuizShape();
    private final QuizShape rectangle = new QuizShape();
    private final QuizShape oval = new QuizShape();
    private final QuizShape heart = new QuizShape();
    private final QuizShape diamond = new QuizShape();
    private final QuizShape star = new QuizShape();
    private final QuizShape trapezium = new QuizShape();
    private final QuizShape octagon = new QuizShape();
    private final QuizShape hexagon = new QuizShape();

    private List<QuizNumber> rightList = new ArrayList<>();
    private List<QuizNumber> leftList = new ArrayList<>();

    private List<QuizShape> quizShapeList = new ArrayList<>();

    public void init(Resources res) {
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

    public void populateQuizNumbers() {

        one.setValue(1);
        one.setNumberImage(getResources().getImage("one.jpg"));

        two.setValue(2);
        two.setNumberImage(getImage("two.jpg"));

        three.setValue(3);
        three.setNumberImage(getImage("three.jpg"));

        four.setValue(4);
        four.setNumberImage(getImage("four.jpg"));

        five.setValue(5);
        five.setNumberImage(getImage("five.jpg"));

        six.setValue(6);
        six.setNumberImage(getImage("six.jpg"));

        seven.setValue(7);
        seven.setNumberImage(getImage("seven.jpg"));

        eight.setValue(8);
        eight.setNumberImage(getImage("eight.jpg"));

        nine.setValue(9);
        nine.setNumberImage(getImage("nine.jpg"));

        ten.setValue(10);
        ten.setNumberImage(getImage("ten.jpg"));

        rightList.add(one);
        rightList.add(two);
        rightList.add(three);
        rightList.add(four);
        rightList.add(five);
        rightList.add(six);
        rightList.add(seven);
        rightList.add(eight);
        rightList.add(nine);
        rightList.add(ten);

        leftList.add(one);
        leftList.add(two);
        leftList.add(three);
        leftList.add(four);
        leftList.add(five);
        leftList.add(six);
        leftList.add(seven);
        leftList.add(eight);
        leftList.add(nine);
        leftList.add(ten);

        Collections.shuffle(rightList);
        Collections.shuffle(leftList);

    }

    public List<QuizNumber> getRightList() {
        return rightList;
    }

    public void setRightList(List<QuizNumber> rightList) {
        this.rightList = rightList;
    }

    public List<QuizNumber> getLeftList() {
        return leftList;
    }

    public void setLeftList(List<QuizNumber> leftList) {
        this.leftList = leftList;
    }

    public void populateQuizShapes() {

        circle.setName("circle");
        circle.setShapeImage(getImage("round.jpg"));

        triangle.setName("triangle");
        triangle.setShapeImage(getImage("triangle.jpg"));

        square.setName("square");
        square.setShapeImage(getImage("square.jpg"));

        rectangle.setName("rectangle");
        rectangle.setShapeImage(getImage("rectangle.jpg"));

        oval.setName("oval");
        oval.setShapeImage(getImage("oval.jpg"));

        heart.setName("heart");
        heart.setShapeImage(getImage("heart.jpg"));

        diamond.setName("diamond");
        diamond.setShapeImage(getImage("diamond.jpg"));

        star.setName("star");
        star.setShapeImage(getImage("star.jpg"));

        trapezium.setName("trapezium");
        trapezium.setShapeImage(getImage("trapezium.jpg"));

        octagon.setName("octagon");
        octagon.setShapeImage(getImage("octagon.jpg"));

        hexagon.setName("hexagon");
        hexagon.setShapeImage(getImage("hexagon.jpg"));

        quizShapeList.add(circle);
        quizShapeList.add(triangle);
        quizShapeList.add(square);
        quizShapeList.add(rectangle);
        quizShapeList.add(oval);
        quizShapeList.add(heart);
        quizShapeList.add(diamond);
        quizShapeList.add(star);
        quizShapeList.add(trapezium);
        quizShapeList.add(octagon);
        quizShapeList.add(hexagon);

        Collections.shuffle(quizShapeList);

    }

    public List<QuizShape> getQuizShapeList() {
        return quizShapeList;
    }

    public void setQuizShapeList(List<QuizShape> quizShapeList) {
        this.quizShapeList = quizShapeList;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Container getResultsContainer(String operation, Form parent) {

        Container container = new Container();

        Database db = null;
        Cursor cur = null;
        String query = "select * from " + operation;
        try {
            db = Display.getInstance().openOrCreate("Results.db");
            cur = db.executeQuery(query);
            int columns = cur.getColumnCount();
            parent.removeAll();
            if (columns > 0) {
                boolean next = cur.next();
                if (next) {
                    ArrayList<String[]> data = new ArrayList<>();
                    String[] columnNames = new String[columns];
                    for (int iter = 0; iter < columns; iter++) {
                        columnNames[iter] = cur.getColumnName(iter);
                    }
                    while (next) {
                        Row currentRow = cur.getRow();
                        String[] currentRowArray = new String[columns];
                        for (int iter = 0; iter < columns; iter++) {
                            currentRowArray[iter] = currentRow.getString(iter);
                        }
                        data.add(currentRowArray);
                        next = cur.next();
                    }
                    Object[][] arr = new Object[data.size()][];
                    data.toArray(arr);
                    Table tbl = new Table(new DefaultTableModel(columnNames, arr)) {
                        @Override
                        protected Component createCell(Object value, int row, int column, boolean editable) {
                            Component c = super.createCell(value, row, column, editable);
                            if (row > -1) {
                                if (row % 2 == 0) {
                                    c.setUIID("TableCellEven");
                                } else {
                                    c.setUIID("TableCell");
                                }
                            }
                            return c;
                        }
                    };
                    tbl.setDrawBorder(false);
                    container.addComponent(tbl);
                } else {
                    ToastBar.showMessage("Query returned no results", FontImage.MATERIAL_INFO);
                }
            } else {
                ToastBar.showMessage("Query returned no results", FontImage.MATERIAL_INFO);
            }
            parent.revalidate();
        } catch (IOException err) {
            Log.e(err);
            parent.removeAll();
            parent.revalidate();
            ToastBar.showErrorMessage("Error: " + err);
        } finally {
            Util.cleanup(db);
            Util.cleanup(cur);
        }
        return container;
    }
}
