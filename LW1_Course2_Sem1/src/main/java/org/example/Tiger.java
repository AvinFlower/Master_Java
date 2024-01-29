package org.example;

class Tiger extends Animals {
    private static int count;

    public Tiger(String name) {
        super(name, "Tiger",700, 30);
        count++;
    }

    public static int getCount() {
        return count;
    }
}
