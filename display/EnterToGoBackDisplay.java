package display;

import utils.exceptions.PageBackException;
import utils.iocontrol.CustScanner;

public class EnterToGoBackDisplay {
    public static void display() throws PageBackException {
        System.out.printf("Press enter to go back. ");
        if (CustScanner.getStrChoice().equals("")) {
            throw new PageBackException();
        }
    }
}
