package tests.utils;

import utils.iocontrol.CSVReader;

import java.util.List;

/**
 * This class tests the {@link CSVReader} class.
 */
public class CSVReaderTest {
    /**
     * This method tests the {@link CSVReader} class.
     * To run locally: 
     * javac -d bin -sourcepath HMS utils/iocontrol/CSVReader.java tests/utils/CSVReaderTest.java
     * java -cp bin tests.utils.CSVReaderTest
     *
     * @param args the command line arguments
     * 
     * 
     */
    public static void main(String[] args) {
        // hasHeader put to false, allows you to view the column names as part of the
        // rows
        List<List<String>> list = CSVReader.read("./resources/Medicine_List.csv", false);
        for (List<String> row : list) {
            System.out.println("Row size: " + row.size());
            for (String value : row) {
                System.out.print(value + "\t | \t");
            }
            System.out.println();
        }
    }
}
