package edu.upc.eetac.dsa.util;


import java.lang.reflect.Field;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;

    }


    public static void setter(Object entity, String property, Object value) {
        // Method // invoke
        Object result = null;
        Class theClass = entity.getClass();
        Method[] methods = theClass.getMethods();

        for(Method method : methods){
            if(isSetter(method)){
                if(method.contains(property))
                    method.invoke(value);
            }

        }


    }

    public static Object getter(Object entity, String property) {
        // Method // invoke
        Object result = null;
        Class theClass = entity.getClass();
        Method[] methods = theClass.getMethods();

        for(Method method : methods){
            if(isGetter(method)){
                if(method.contains(property))
                    result = method.invoke();
            }

        }

        return result;
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType()) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
}
