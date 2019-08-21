package random;

//non private class
class PrivateClass {

    private String s = "private";

    private PrivateClass() {
        System.out.println("Rolling a private constructor");
    }

    private void method() {
        System.out.println("Rolling a private method");
    }
}
