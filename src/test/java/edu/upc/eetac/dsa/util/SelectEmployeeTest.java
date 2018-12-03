package edu.upc.eetac.dsa.util;

import edu.upc.eetac.dsa.EmployeeDAOImpl;
import edu.upc.eetac.dsa.IEmployeeDAO;
import org.junit.Assert;
import org.junit.Test;

public class SelectEmployeeTest {
    private IEmployeeDAO dao;

    @Test
    public void selectEmployeeDB(){
        this.dao = EmployeeDAOImpl.getInstance();
        Assert.assertEquals("Paco",this.dao.getEmployee(3).getName());
    }
}
