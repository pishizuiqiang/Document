package com.pishi.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pishi
 * @description: class 反射相关工具
 * @date 2024年05月12日 下午2:50
 */
public class ClassUtils {


    /**
     * 根据class 实例递归查找 需要的类型
     *
     * @param clazz     实例对象
     * @param className 需要查找的泛型类名 如需找{@link com.pishi.doc20240530.service.AnalysisService}的泛型, 则传 "AnalysisService"
     * @return {@link ParameterizedType} 父类/父接口的类型,包含了泛型信息
     */
    public static ParameterizedType getParameterizedTypeByClassName(Class clazz, String className) {
        final Type type = clazz.getGenericSuperclass();

        ParameterizedType result = getParameterizedType(className, type);
        if (result != null) {
            return result;
        }


        for (Type genericInterface : clazz.getGenericInterfaces()) {
            result = getParameterizedType(className, genericInterface);

            if (result != null) {
                return result;
            }
        }

        return null;

    }

    private static ParameterizedType getParameterizedType(String className, Type type) {
        if (type instanceof Class) {
            final ParameterizedType aClass = getParameterizedTypeByClassName((Class) type, className);

            if (aClass != null) {
                return aClass;
            }
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (StringUtils.contains(parameterizedType.getTypeName(), className)) {
                return parameterizedType;
            }

            final Type rawType = parameterizedType.getRawType();
            final ParameterizedType result = getParameterizedTypeByClassName((Class) rawType, className);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

}
