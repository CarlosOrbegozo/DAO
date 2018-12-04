package edu.upc.eetac.dsa.util;

import edu.upc.eetac.dsa.EmployeeDAOImpl;
import edu.upc.eetac.dsa.IEmployeeDAO;
import edu.upc.eetac.dsa.model.Employee;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class SelectAllEmployeesTest {
    private IEmployeeDAO dao;

    @Test
    public void selectAllEmployeesDB(){
        this.dao = EmployeeDAOImpl.getInstance();
        List<Employee> list = this.dao.getEmployees();
        Assert.assertEquals("Paco", list.get(0).getName());
        Assert.assertEquals("Pepe", list.get(1).getName());
        Assert.assertEquals("Paco", list.get(2).getName());
        Assert.assertEquals("Paco", list.get(3).getName());
        this.dao.clear();
    }
}
