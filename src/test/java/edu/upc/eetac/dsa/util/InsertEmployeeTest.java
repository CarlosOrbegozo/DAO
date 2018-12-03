package edu.upc.eetac.dsa.util;

import edu.upc.eetac.dsa.EmployeeDAOImpl;
import edu.upc.eetac.dsa.IEmployeeDAO;
import org.junit.Test;

public class InsertEmployeeTest {
    private IEmployeeDAO dao;

    @Test
    public void insertEmployeeDB(){
        this.dao = EmployeeDAOImpl.getInstance();
        this.dao.addEmployee("Paco","Paco", 2300);
        this.dao.clear();
    }
}
