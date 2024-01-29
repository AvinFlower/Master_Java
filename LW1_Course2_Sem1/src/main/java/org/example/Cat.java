package org.example;

class Cat extends Animals {
    private static int count;

    public Cat(String name) {
        super(name, "Cat",200, 0);
        count++;
    }

    public static int getCount() {
        return count;
    }
}
