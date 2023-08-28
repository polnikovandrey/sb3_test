package com.mcfly.springtemp.equalsHashCode;

public class ClassesHierarchyInstantiationOrder {

    static class A {

        static {
            System.out.println("A static block");
        }

        {
            System.out.println("A instance block");
        }

        A() {
            System.out.println("A Constructor");
        }

    }

    static class B extends A {

        static {
            System.out.println("B static block");
        }

        {
            System.out.println("B instance block");
        }

        B() {
            System.out.println("B Constructor");
        }


    }

    static class C extends B {

        static {
            System.out.println("C static block");
        }

        {
            System.out.println("C instance block");
        }

        C() {
            System.out.println("C Constructor");
        }

        C(A a) {
            System.out.println("C Constructor");
        }

        C(B b) {
            System.out.println("C Constructor");
        }
    }

    public static void main(String... args) {
        new C(new B());
    }

}
