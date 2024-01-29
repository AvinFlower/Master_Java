package org.example;

class Wolf extends Beast {
    private static int count;

    public Wolf(String name) {
        super(name, "Wolf", 600, 15);
        count++;
    }

    public static int getCount() {
        return count;
    }
}
