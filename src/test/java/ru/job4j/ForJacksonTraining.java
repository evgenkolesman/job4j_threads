package ru.job4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.job4j.jackson.TypeReferenceMy;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class SuperC<T> {
    Class type;
    private final T t;


    public SuperC(T t) {
        this(t, (Class<T>) t.getClass());
    }

    public SuperC(T t, Class<T> type) {
        this.t = t;
        type = type;
        System.out.println("TYPE " + getType());
    }

    public Type getType() {
        return type;
    }


    @Override
    public String toString() {
        return "SuperC class is toString method is used";
    }
}

interface Int<T> {
    default Type parameterizedInterfaceType() {
        return ((ParameterizedType)
                this.getClass().getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
    }

    default Type parameterizedTypeSuperClass() {
        return ((ParameterizedType)
                this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

    }
}

class B {
    Z z;

    public B() {
    }

    public B(Z z) {
        this.z = z;
    }
}

class Z {
}

class El extends SuperC<List<Z>> implements Int<List<B>> {
    public El(Z z) {
        super(List.of(z));
    }
}


public class ForJacksonTraining {
    @Test
    void superCTest() throws IOException {
//        SuperC<B> bSuperC = new SuperC<>(new );
        ObjectMapper mapper = new ObjectMapper();
//        El el = new El(new Z());
        SuperC<String> bSuper = new SuperC<>("a");
        ArrayList<String> strings = new ArrayList<>();
        System.out.println((strings.getClass().getGenericSuperclass().getTypeName()));
        Type genericSuperclass = strings.getClass().getGenericSuperclass();
        System.out.println("Starting readValue");
        String content = "{\"t\":\"any\"}";


    }

    @Test
    void testJson() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<String> list = new ArrayList<String>();

        final Type actualTypeArgument = ((ParameterizedType) El.class.getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
        final Type actualTypeArgumentClass = ((ParameterizedType) El.class.getGenericSuperclass())
                .getActualTypeArguments()[0];
        System.out.println(
                actualTypeArgument.getTypeName()
        );
        Type typeName = ((ParameterizedType) actualTypeArgument).getActualTypeArguments()[0];

        Type typeNameClass = ((ParameterizedType) actualTypeArgumentClass).getActualTypeArguments()[0];

        System.out.println(Class.forName(typeName.getTypeName(), true, ClassLoader.getSystemClassLoader()).getConstructors()[0].newInstance());
        Class.forName(typeNameClass.getTypeName(), true, ClassLoader.getSystemClassLoader());
        System.out.println(this.getClass().getClassLoader().loadClass(typeNameClass.getTypeName()).getDeclaredConstructor().newInstance());

        Object o = Class.forName(typeName.getTypeName(), true, ClassLoader.getSystemClassLoader()).getConstructors()[1].newInstance(new Z());

        // --> Type is: ParameterizedStuff$Beer
    }
}


//    public SuperC(T t, Class type){
//        Type superClass = this.getClass().getGenericSuperclass();
//        if (superClass instanceof Class) {
//            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
//        } else {
//            this._type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
//        }
//        this.t = t;
//        System.out.println(_type.getTypeName());
//        System.out.println("SuperC constructor is applied");
//        _type = type;
//        System.out.println(getType());