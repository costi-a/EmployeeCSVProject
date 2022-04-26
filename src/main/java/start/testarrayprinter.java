package start;

import com.sparta.employeecsv.model.EmployeeFileReader;

import java.io.FileNotFoundException;

public class testarrayprinter {

    public static void main(String... args)  {
        try {
            EmployeeFileReader rf = new EmployeeFileReader();
            rf.readFile("EmployeeRecords.csv");

            // System.out.println(employeesList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
