package edu.upc.eetac.dsa.util;

import edu.upc.eetac.dsa.EmployeeDAOImpl;
import edu.upc.eetac.dsa.IEmployeeDAO;
import org.junit.Test;

public class DeleteEmployeeTest {
    private IEmployeeDAO dao;

    @Test
    public void deleteEmployeeDB(){
        this.dao = EmployeeDAOImpl.getInstance();
        this.dao.deleteEmployee( 2);
        this.dao.clear();
    }
}
