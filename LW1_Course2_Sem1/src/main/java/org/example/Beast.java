package org.example;

abstract class Beast extends Animals {
    private static int count;
    public Beast(String name, String type, int maxRunDist, int maxSwimDist) {
        super(name, type, maxRunDist, maxSwimDist);
        count++;
    }

    public void eat(Animals animals) {
        System.out.println(this.name + " ест " + animals.getName());
    }

    public static int getCount() {
        return count;
    }
}
