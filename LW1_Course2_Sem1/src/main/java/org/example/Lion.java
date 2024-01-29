package org.example;

class Lion extends Beast {
    private static int count;

    public Lion(String name) {
        super(name, "Lion", 250, 25);
        count++;
    }

    public static int getCount() {
        return count;
    }
}
