package org.example;

public class Animals
{
    public static int count;
    String name;
    String type;
    int maxRunDist;
    int maxSwimDist;
    public String getType() {
        return type;
    }

    public Animals(String name, String type, int maxRunDist, int maxSwimDist)
    {
        this.name = name;
        this.maxRunDist = maxRunDist;
        this.maxSwimDist = maxSwimDist;
        this.type = type;
        Animals.count++;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void run(int distance) {
        if (distance <= maxRunDist)
        {
            System.out.println(name + " пробежал " + distance + "м");
        }
        else
        {
            System.out.println(name + " не может пробежать " + distance + "м");
        }
    }

    public void swim(int distance) {
        if (distance <= maxSwimDist)
        {
            System.out.println(name + " проплыл " + distance + "м");
        }
        else
        {
            System.out.println(name + " не может проплыть " + distance + "м");
        }
    }
}

