package ru.gb.zettro.lesson1.task3;

abstract public class Shape {

    private String name;
    private String color;

    public Shape(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public abstract void draw();
    public abstract double getArea();
    public abstract double getPerimeterLength();

     public void getInfo() {
        System.out.println("=================================");
        System.out.println(color + " " + name);
        System.out.println("Периметр равен " + getPerimeterLength());
        System.out.println("Площадь равна " + getArea());
    }
}
