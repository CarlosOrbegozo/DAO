package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


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
            pstm.setObject(1, 0);
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
            pstm.setObject(1, ID);

            rs = pstm.executeQuery();

            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){
                    ObjectHelper.setter(entity, fields[i].getName(), rs.getObject(i +1));
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity; //Por ver
    }

    public void update(Object object) {
        /*String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            pstm.setObject(1,0);
            int i = 2;

            ObjectHelper.setter(object, property, value) //REVISAR!!!

            for(String field: ObjectHelper.getFields(object)){
                pstm.setObject(i++, );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
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
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
