package controller.threads;

import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.Employee;

import java.util.List;

public class ThreadManager implements Runnable {
    private List<Employee> listSplitted;

    public ThreadManager(List<Employee> listSplitted) {
        this.listSplitted = listSplitted;
    }

    public void insertListsLargeFile(List<Employee> listSplitted) {
        DatabaseDriver dbDriver = new DatabaseDriver();
        dbDriver.populateTableMultiList(listSplitted);
    }

    @Override
    public void run() {
        insertListsLargeFile(this.listSplitted);
    }
}
