package com.example.AuthDemo.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Component
public class CopyProperties  {
    public static boolean isEmpty(Object str) {
        return !isNotEmpty(str);
    }
    public static boolean isNotEmpty(Object str) {
        if (str == null || str.toString().trim().length() == 0 || str.toString().trim().equalsIgnoreCase("null") ) {
            return false;
        } else {
            return true;
        }
    }
    public static void copyNonNullProperties(Object source, Object destination) {
        // getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            // check if value of this property is null add fullName
            String name = pd.getName();
            if (name.equals("request") || name.equals("response")) {
                continue;
            }
            try {
                Object srcValue = src.getPropertyValue(name);
                if (CopyProperties.isEmpty(srcValue) || srcValue.toString().contains("***")) {
                    emptyNames.add(name);
                }
            } catch (InvalidPropertyException e) {
                emptyNames.add(name);
                continue;
            }
        }
        String[] result = new String[emptyNames.size()];
        String[] ignoreProperties = emptyNames.toArray(result);
        BeanUtils.copyProperties(source, destination, ignoreProperties);
    }
}
