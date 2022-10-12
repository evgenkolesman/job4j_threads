package ru.job4j.jackson;

import com.fasterxml.jackson.databind.type.ClassStack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JavaTypeMy {

    public Type[] fromAny(String context, Type srcType) {
//        resultType = _fromParamType(context, (ParameterizedType) srcType);
        Type[] args = ((ParameterizedType) srcType).getActualTypeArguments();
        return args;
    }

}
