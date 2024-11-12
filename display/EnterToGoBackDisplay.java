package display;

import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class EnterToGoBackDisplay {
    /**
     * Displays a message to prompt the user to press enter to go back.
     * 
     * @throws PageBackException
     */
    public static void display() throws PageBackException {
        System.out.printf("Press enter to go back. ");
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }
}
