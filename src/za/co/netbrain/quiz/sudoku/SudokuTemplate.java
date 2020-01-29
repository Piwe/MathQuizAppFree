package za.co.netbrain.quiz.sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simphiwe.Twala
 */
public class SudokuTemplate {

    public List getSoduku() {

        int[][] sudoku1 = {{0, 1, 8, 3, 2, 0, 6, 7, 9},
        {0, 2, 0, 1, 5, 7, 4, 0, 8},
        {0, 4, 7, 6, 9, 8, 1, 2, 5},
        {1, 6, 9, 5, 0, 3, 0, 4, 2},
        {7, 8, 2, 4, 1, 6, 0, 5, 3},
        {0, 3, 5, 2, 0, 0, 7, 0, 1},
        {0, 5, 3, 0, 4, 1, 0, 8, 7},
        {0, 0, 4, 7, 3, 2, 5, 1, 6},
        {0, 7, 1, 8, 6, 0, 3, 9, 4}};

        int[][] sudoku2 = {{0, 1, 6, 3, 2, 4, 0, 0, 9},
        {9, 2, 4, 5, 1, 8, 6, 0, 7},
        {3, 0, 5, 7, 0, 0, 0, 0, 2},
        {2, 0, 8, 0, 0, 7, 0, 0, 3},
        {0, 4, 9, 8, 6, 3, 7, 2, 5},
        {5, 3, 7, 2, 0, 9, 8, 6, 1},
        {8, 7, 1, 4, 3, 5, 2, 9, 6},
        {0, 9, 2, 6, 0, 1, 3, 5, 8},
        {6, 0, 3, 9, 8, 2, 1, 0, 4}};

        int[][] sudoku3 = {{7, 5, 4, 0, 2, 0, 0, 3, 9},
        {0, 2, 8, 3, 4, 0, 7, 0, 6},
        {6, 3, 0, 5, 0, 7, 1, 4, 2},
        {0, 0, 5, 6, 7, 3, 4, 9, 1},
        {0, 0, 1, 0, 9, 5, 0, 6, 7},
        {9, 0, 0, 4, 1, 2, 5, 8, 3},
        {0, 1, 0, 7, 6, 4, 9, 2, 5},
        {0, 9, 6, 2, 5, 1, 3, 7, 8},
        {5, 7, 2, 9, 3, 8, 0, 1, 4}};

        int[][] sudoku4 = {{0, 0, 6, 1, 2, 5, 8, 3, 9},
        {0, 1, 0, 3, 4, 8, 0, 7, 6},
        {8, 3, 2, 6, 0, 9, 0, 4, 5},
        {0, 5, 0, 2, 9, 4, 7, 6, 8},
        {4, 6, 7, 8, 5, 1, 9, 2, 3},
        {2, 0, 8, 7, 6, 3, 5, 0, 4},
        {5, 7, 0, 0, 0, 0, 3, 0, 2},
        {0, 0, 4, 9, 1, 2, 6, 5, 7},
        {6, 2, 9, 5, 0, 7, 0, 8, 1}};

        int[][] sudoku5 = {{8, 5, 0, 3, 1, 7, 0, 0, 6},
        {0, 0, 9, 2, 6, 5, 3, 0, 8},
        {7, 6, 3, 0, 4, 8, 0, 0, 5},
        {1, 2, 0, 7, 0, 0, 5, 8, 3},
        {6, 7, 8, 5, 3, 2, 9, 0, 4},
        {3, 0, 5, 0, 8, 4, 0, 0, 7},
        {9, 3, 6, 0, 5, 1, 7, 4, 2},
        {2, 8, 1, 4, 7, 3, 6, 5, 9},
        {0, 4, 7, 6, 2, 9, 0, 3, 1}};

        int[][] sudoku6 = {{2, 8, 1, 3, 4, 0, 7, 6, 9},
        {0, 3, 0, 7, 9, 0, 0, 8, 2},
        {0, 0, 0, 2, 0, 1, 3, 0, 5},
        {0, 5, 6, 4, 2, 0, 0, 7, 3},
        {3, 2, 7, 0, 0, 8, 4, 9, 1},
        {4, 0, 8, 1, 3, 7, 5, 2, 6},
        {0, 1, 2, 0, 7, 3, 6, 5, 4},
        {0, 4, 3, 8, 5, 2, 9, 1, 7},
        {9, 0, 5, 6, 1, 4, 2, 3, 8}};

        int[][] sudoku7 = {{8, 9, 7, 0, 0, 5, 0, 0, 6},
        {4, 6, 5, 3, 7, 9, 1, 0, 8},
        {3, 2, 1, 4, 8, 0, 5, 0, 9},
        {1, 3, 0, 5, 6, 8, 2, 9, 7},
        {2, 5, 6, 7, 0, 0, 3, 8, 1},
        {9, 7, 8, 2, 3, 1, 6, 5, 4},
        {5, 8, 9, 6, 0, 2, 7, 1, 3},
        {0, 1, 0, 0, 5, 0, 0, 0, 2},
        {0, 0, 2, 9, 1, 3, 0, 6, 5}};

        int[][] sudoku8 = {{2, 8, 6, 1, 0, 3, 0, 5, 7},
        {0, 1, 0, 6, 5, 0, 2, 4, 8},
        {5, 7, 4, 0, 0, 2, 3, 1, 6},
        {7, 0, 3, 4, 1, 8, 0, 9, 5},
        {1, 6, 5, 2, 7, 9, 0, 8, 3},
        {0, 0, 8, 0, 3, 6, 1, 0, 2},
        {8, 3, 7, 9, 0, 0, 5, 0, 4},
        {0, 4, 1, 7, 0, 5, 8, 3, 9},
        {0, 5, 2, 3, 8, 4, 7, 6, 1}};

        int[][] sudoku9 = {{6, 4, 8, 0, 0, 3, 5, 7, 9},
        {9, 5, 3, 0, 6, 0, 1, 8, 2},
        {0, 0, 2, 0, 8, 9, 0, 6, 3},
        {2, 3, 4, 0, 1, 8, 6, 9, 5},
        {0, 8, 1, 0, 9, 6, 7, 2, 4},
        {7, 0, 0, 2, 5, 4, 0, 3, 1},
        {0, 1, 7, 8, 3, 2, 0, 5, 6},
        {0, 2, 6, 9, 4, 5, 0, 1, 7},
        {0, 9, 5, 6, 7, 1, 2, 0, 8}};

        int[][] sudoku10 = {{5, 2, 9, 1, 0, 0, 4, 0, 8},
        {8, 3, 4, 2, 5, 7, 0, 9, 1},
        {1, 7, 0, 4, 8, 9, 2, 3, 5},
        {4, 0, 8, 5, 7, 0, 9, 0, 3},
        {0, 0, 5, 3, 1, 4, 0, 6, 7},
        {3, 1, 7, 6, 9, 8, 5, 4, 2},
        {6, 5, 0, 9, 0, 0, 7, 8, 4},
        {7, 4, 0, 0, 6, 3, 1, 5, 9},
        {9, 0, 1, 7, 0, 0, 3, 0, 6}};

        int[][] sudoku11 = {{8, 0, 0, 1, 0, 0, 7, 0, 9},
        {9, 3, 2, 4, 5, 7, 0, 6, 8},
        {0, 0, 7, 6, 0, 0, 3, 0, 4},
        {0, 4, 1, 3, 9, 6, 8, 0, 5},
        {7, 0, 6, 2, 1, 5, 0, 4, 3},
        {5, 0, 0, 8, 7, 4, 0, 1, 2},
        {3, 1, 8, 5, 6, 2, 4, 9, 7},
        {4, 0, 9, 7, 0, 1, 5, 8, 6},
        {6, 7, 5, 9, 4, 8, 2, 3, 1}};

        int[][] sudoku12 = {{0, 1, 5, 0, 3, 0, 4, 8, 9},
        {8, 3, 7, 4, 0, 9, 2, 5, 6},
        {0, 2, 4, 5, 8, 6, 1, 0, 7},
        {0, 0, 2, 0, 6, 0, 9, 0, 3},
        {4, 6, 9, 3, 7, 2, 5, 1, 8},
        {7, 8, 3, 1, 9, 0, 6, 4, 2},
        {0, 0, 0, 7, 4, 8, 3, 2, 1},
        {3, 4, 0, 9, 2, 0, 7, 6, 5},
        {0, 0, 1, 6, 5, 3, 8, 0, 4}};
        
        List myList = new ArrayList<>();
       
        myList.add(sudoku1);
        myList.add(sudoku2);
        myList.add(sudoku3);
        myList.add(sudoku4);
        myList.add(sudoku5);
        myList.add(sudoku6);
        myList.add(sudoku7);
        myList.add(sudoku8);
        myList.add(sudoku9);
        myList.add(sudoku10);
        myList.add(sudoku11);
        myList.add(sudoku12);
        
        return myList;
    }
}
