package za.co.netbrain.quiz.utils;

import java.util.Arrays;

/**
 *
 * @author Simphiwe.Twala
 */
public class SudokuUtility {
    
     public static <T> boolean equals(final T[][] a, final T[][] b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; ++i) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }

        return true;
    }
}
