package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Atest {
    private interface Example<T> {
        void addEx(T element);

        T getEx(int index);
    }


    private class A implements Example<A> {
        @Override
        public void addEx(A element) {

        }

        @Override
        public A getEx(int index) {
            return new A();
        }
    }

    private class B extends A {
        private final String b;

        public B() {
            this.b = null;
        }

        public B(String b) {
            this.b = b;
        }

        void methodB() {
        }
    }

    private class C extends B {
        void methodC() {
        }

    }

    private class D extends B {
        void methodD() {
        }

    }

//    List<B> list1 = ...
//    List<? extends B> list = ...
//    List<? super B> list3 = ...


    @Test
    void listTestWithBInitAdd() {
        List<B> list = new ArrayList<B>();
        List<C> listC = new ArrayList<C>();
//        list.add(new A()); //need to cast because it is older than B it can`t has access to B class`s methods
        list.add(new C());
        list.add(new B());
        Object b = list.get(0);
//        list.get(0).methodC();
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);

    }

    @Test
    void listTestWithSuperBandAInit() {
        List<? super B> list = new ArrayList<A>(); // because Super is  the low line
//        list.add(new A()); //we cannot do it because  A class has no methods as B class
        list.add(new B());
        list.add(new C());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);

    }

    @Test
    void listTestWithSuperCAndAInit() {
        List<? super C> list = new ArrayList<A>(); // because Super is  the low line
//        list.add(new A()); //we cannot do it because  A class has no methods as C class
//        list.add(new B());//we cannot do it because  B class has no methods as C class
        list.add(new C());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);

    }

    @Test
    void listTestWithSuperAandAInit() {
        List<? super A> list = new ArrayList<>(); // because Super is  the low line
        list.add(new A());
        list.add(new B());
        list.add(new C());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);


        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void listTestWithCInitAdd() {
        List<C> list = new ArrayList<>();
        list.add(new C()); // these will have all methods from A, B, C
//        list.add(new B());//need to cast because it is older than C and won`t have metods from C
//        list.add(new A());//need to cast because it is older than C and won`t have metods from C, B
        list.get(0).methodC();
        list.get(0).methodB();
        list.get(0).getEx(1);
        list.get(0).addEx(new A());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void listTestWithExtendsBAndBInit() {
        List<? extends B> list = List.of(new B(), new C()); // A class cannot be here because of extends B (high line)
        List<? extends C> list2 = List.of(new C(), new C()); // A class cannot be here because of extends B (high line)
//        List<? extends B> list  = new ArrayList<C>(); // A class cannot be here because of extends B (high line)
        B b = list.get(0);
        C c = list2.get(0);
        list.get(0).methodB();
        list.get(1).getEx(1);
        list.get(1).addEx(new A());
//        list.get(1).methodC(); is not allowed because JAVA knows only about B methods

        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void listTestWithExtendsAAndCInit() {
//        List<? extends B> list  = List.of(new B(), new C()); // A class cannot be here because of extends B (high line)
        List<? extends A> list = List.of(new C(), new B(), new A()); // A class cannot be here because of extends B (high line)
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void listTestWithExtendsCAndCInit() {
//        List<? extends B> list  = List.of(new B(), new C()); // A class cannot be here because of extends B (high line)
        List<? extends C> list = List.of(new C()); // A class cannot be here because of extends B (high line)
        list.get(0).methodC();
        System.out.println(list.get(0).getClass());
    }


    @Test
    void listTestWithSuperBandBInit() {
        List<? super B> list = new ArrayList<B>(); // because B is  the low line
        List<? super B> list1 = list; // because B is  the low line
        list.add(new B());
        list.add(new C());
        Object object = list.get(0);
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void listTestWithBandAInit() {
        List<B> list = new ArrayList<>(); // because Super is  the low line
//        list.add(new A()); //we cannot do it because B extends A
        list.add(new B());
        list.add(new C());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }


    @Test
    void listTestWithSuperAOnly() {
        List<A> list = new ArrayList<>(); // because Super is  the low line
        list.add(new A());
        list.add(new B());
        list.add(new C());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void listTestWithBandCInit() {
        List<? extends B> list3 = new ArrayList<C>();

        System.out.println(list3.getClass().getName());
    }


    @Test
    void listTestWithSuperBandCInit() {
        List<? super B> list; // because B is  the low line
        list = new ArrayList<>();
        list.add(new B());
        list.add(new C());
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);

    }

    @Test
    void forInterfaces() {
        List<? super B> list = new ArrayList();
        list.add(new B());
        list.add(new C());


        Object object = list.get(0); // recognize as Object but in fact it is B

        new B().addEx(new A());
        List<B> list1 = new ArrayList<>();
        list1.add(new B());
        list1.get(0).addEx(new A());
        list1.get(0).getEx(0);

        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);


    }

    @Test
    void forInterfacesWithA() {
        List<? super A> list = new ArrayList<>(); // because Super is  the low line
//        list.add(new A()); //we cannot do it because  because A class would not have B class methods
        list.add(new A());
        list.add(new B());
        list.add(new C());

        list.get(0); // here we can`t get ONLY Object methods because it is not recognized as A
        list.get(1); // here we can`t get ONLY Object methods because it is not recognized as B
        list.get(2); // here we can`t get ONLY Object methods because it is not recognized as C
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
    }

    @Test
    void forInterfacesWithB() {
        List<? extends B> list = new ArrayList<B>(List.of(new B(), new C())); // A class cannot be here because of extends B (high line)
        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);
//        list.add(new B()) // not allowed because can be a situation when new ArrayList<C> will try to add B class
        list.get(0).methodB();
        list.get(0).getEx(1);
        list.get(0).addEx(new A());  // extends has full access to B class`s methods
        list.get(1).methodB();
//        list.get(1).methodC();//can`t because we know only about B class`s methods
        list.get(1).getEx(1);
        list.get(1).addEx(new A());  // extends has full access to B class`s methods
        list.remove(1);
        System.out.println(list.size());
    }

    @Test
    void forBExtendsAdding() {
        List<? extends B> list = Arrays.asList(new C(), new B()); // A class cannot be here because of extends B (high line)

        list.stream().map(z -> z.getClass().getName()).forEach(System.out::println);

        list.get(0).methodB();
        list.get(0).getEx(1);
        list.get(0).addEx(new A());  // element has full access to B class`s methods
    }


    @Test
    void whyWeCannotAddToExtends() {
        List<? extends B> list = new ArrayList<>(); // A class cannot be here because of extends B (high line)
//        list.add(new C());
//        list.add(new D()); both method can have different realization of same called methods that`s why it can be a problem
//        list may be List<C> with the left condition but left condition also allowed us to add D or B that may be a problem with methods too

    }


    @Test
    void forReassignSuper() {
        List<? super B> listB = new ArrayList();
        List<? super A> listA = new ArrayList();
        List<? super C> listC = new ArrayList();
//        listA = listB;  // low line A Class
//        listA = listC; // low line A Class

        listB = listA;
//        listB = listC;// low line B Class

        listC = listA;
        listC = listB;

    }

    @Test
    void forReassignExtends() {
        List<? extends B> listB = new ArrayList<B>();
        List<? extends A> listA = new ArrayList<A>();
        List<? extends C> listC = new ArrayList<C>();
        listA = listB;
        listA = listC;

//        listB = listA; //high line
        listB = listC;

//        listC = listA; // high Line C is lower
//        listC = listB; // high Line C is lower

    }

}
