package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1,0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getFieldName(int i, ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String name = rsmd.getColumnName(i);

        System.out.println("columna "+i+" name: "+name);
        return name;
    }

    public Object get(Class theClass, int ID) {
        String selectQuery = QueryHelper.createQuerySELECT2(theClass);

        Object entity = null;
        try {
            entity = theClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1,ID);
            rs = pstm.executeQuery();


            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){


                    String fieldName = this.getFieldName(i+2, rs);

                    ObjectHelper.setter(entity, fieldName, rs.getObject(i + 2));
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public void update(Object object, int ID) {
        String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(object)){
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i,ID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void delete(Object object, int ID) {
        String deleteQuery = QueryHelper.createQueryDELETE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, ID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Object> findAll(Class theClass) {
        String findAllQuery = QueryHelper.findAllQuery(theClass);

        Object entity = null;
        List<Object> listOfObjects = new ArrayList<>();

        try {
            entity = theClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(findAllQuery);
            rs = pstm.executeQuery();

            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){
                    ObjectHelper.setter(entity, fields[i].getName(), rs.getObject(i + 2));
                }

                listOfObjects.add(entity);

                entity = theClass.newInstance();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return listOfObjects;
    }


    /*
     *          HashMap params = new HashMap();
     *          params.put("edat", new Condition("=", 15);
     *          params.put("salary", new Condition(100000, ">=");
     *          session.findAll(Employee.class, params);
     *
     *          SELECT * FROM Employee WHERE ( edat = 15 AND salary>=10000)
     */
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

     /*
             *          SELECT * FROM Employee, Deparment  WHERE ( edat = 15 AND salary>=10000)
    */
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
