package org.example;

class Crocodile extends Beast {
    private static int count;

    public Crocodile(String name) {
        super(name, "Crocodile",50, 100);
        count++;
    }

    public static int getCount() {
        return count;
    }
}
