package controller.threads;

import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.Employee;

import java.util.List;

public class ThreadManagerDuplicateValues implements Runnable {
    private final List<Employee> listSplitted;

    public ThreadManagerDuplicateValues(List<Employee> listSplitted) {
        this.listSplitted = listSplitted;
    }

    public void populateDupTableWithThreads(List<Employee> listSplitted) {
        System.out.println("populating dup table...");
        DatabaseDriver dbDriver = new DatabaseDriver();
        dbDriver.populateThreadTableDuplicateEmployee(listSplitted);
    }

    @Override
    public void run() {
        populateDupTableWithThreads(this.listSplitted);
    }
}