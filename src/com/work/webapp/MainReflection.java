package com.work.webapp;

import com.work.webapp.model.Resume;
import com.work.webapp.storage.AbstractArrayStorage;
import com.work.webapp.storage.ArrayStorage;
import com.work.webapp.storage.SortedArrayStorage;
import com.work.webapp.storage.Storage;

import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Anton on 20.12.2016.
 */
public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Class<? extends Resume> resumeClass = r.getClass();
        Field field = resumeClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        Method method = resumeClass.getMethod("toString");
        Object result = method.invoke(r);

        System.out.println(result);

        /*// Мое решение invoke r.toString via reflection
        Method[] method = r.getClass().getDeclaredMethods();
        for (Method methods : method) {
            if (methods.getName().startsWith("toString")) {
                System.out.println("Имя: " + methods.invoke(r));
            }
        }*/
    }
}
