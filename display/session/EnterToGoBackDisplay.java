package display.session;

import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

/**
 * The {@code EnterToGoBackDisplay} class provides a utility to prompt the user
 * to press "Enter"
 * to go back to the previous menu or display.
 */

public class EnterToGoBackDisplay {
    /**
     * Displays a message prompting the user to press "Enter" to go back.
     * 
     * @throws PageBackException if the user presses "Enter", indicating they want
     *                           to go back.
     */
    public static void display() throws PageBackException {
        System.out.printf("Press enter to go back. ");
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }
}
