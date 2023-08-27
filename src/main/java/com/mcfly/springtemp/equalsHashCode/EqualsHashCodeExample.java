package com.mcfly.springtemp.equalsHashCode;

final class EqualsHashCodeExample {

    public static void main(String... args) {
        final A a = new A("123", 456);
        final A aa = new A("123", 456);
        System.out.printf("[a].equals([aa]) = %s%n", a.equals(aa));
        System.out.printf("[a].hashCode() = %s%n", a.hashCode());
        System.out.printf("[aa].hashCode() = %s%n", aa.hashCode());
        System.out.printf("[a].hashCode() == [aa].hashCode() = %s%n", a.hashCode() == aa.hashCode());
    }

    private static final class A {

        private String text;
        private int value;

        private A(String text, int value) {
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final A other = (A) obj;
            if (text == null ? other.getText() != null : !text.equals(other.getText())) {
                return false;
            }
            if (value != other.getValue()) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + (text == null ? 0 : text.hashCode());
            result = 31 * result + value;
            return result;
        }
    }
}
