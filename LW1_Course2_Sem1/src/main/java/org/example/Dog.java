package org.example;

class Dog extends Animals {
    private static int count;

    public Dog(String name) {
        super(name, "Dog",500, 10);
        count++;
    }

    public static int getCount() {
        return count;
    }
}
