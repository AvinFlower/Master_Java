package org.example;

import javax.swing.plaf.PanelUI;

public class Main
{
    public static void main(String[] args)
    {
        Animals[] animals ={
                new Cat("Том"),
                new Dog("Бо"),
                new Tiger("Тим"),
                new Wolf("Тайлер"),
                new Lion("Лева"),
                new Crocodile("Лакост")
        };

        ((Beast) animals[5]).eat(animals[0]);

        for(Animals animal : animals)
        {
            animal.run(300);
            animal.swim(15);
        }

        System.out.println("Создано котов: " + Cat.getCount());
        System.out.println("Создано собак: " + Dog.getCount());
        System.out.println("Создано тигров: " + Tiger.getCount());
        System.out.println("Создано волков: " + Wolf.getCount());
        System.out.println("Создано львов: " + Lion.getCount());
        System.out.println("Создано крокодилов: " + Crocodile.getCount());
        System.out.println("Создано животных: " + Animals.getCount());
        System.out.println("Создано хищников: " + Beast.getCount());

    }
}