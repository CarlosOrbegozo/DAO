package edu.upc.eetac.dsa.util;

import edu.upc.eetac.dsa.EmployeeDAOImpl;
import edu.upc.eetac.dsa.IEmployeeDAO;
import org.junit.Assert;
import org.junit.Test;

public class UpdateEmployeeTest {
    private IEmployeeDAO dao;

    @Test
    public void updateEmployeeDB(){
        this.dao = EmployeeDAOImpl.getInstance();
        this.dao.updateEmployee(3, "Pepe", "Pepe", 1000);
        Assert.assertEquals("Pepe", this.dao.getEmployee(3).getName());
        this.dao.clear();
    }
}
