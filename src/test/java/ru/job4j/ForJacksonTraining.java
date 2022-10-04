package ru.job4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class SuperC<T> {
    static {
        System.out.println("SuperC Class is looked by JVM");
    }
    private final T t;

//    public SuperC() {
//        this((T) "empty value");
//        System.out.println("empty value");
//    }

    public SuperC(T t){
        this.t = t;
        System.out.println("SuperC constructor is applied");
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

class Z {}

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
        System.out.println("Starting readValue");
        String content = "{\"t\":\"any\"}";
        SuperC<String> bSuper = mapper.readValue(content, new TypeReference<SuperC<String>>() {});
//        Type genericSuperclass = bSuperC.getClass();
//        genericSuperclass.getClass();
    }

    @Test
    void testJson() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<String> list = new ArrayList<String>();
//        SuperC<B> bSuperC = new SuperC<>();


//        System.out.println("Type interface is: " + new El().parameterizedInterfaceType());
//        System.out.println("Type class is: " + new El().parameterizedTypeSuperClass());
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
