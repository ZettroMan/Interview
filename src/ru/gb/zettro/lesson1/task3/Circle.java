package ru.gb.zettro.lesson1.task3;

public class Circle extends Shape {

    private double radius;

    public Circle(String color, double radius) {
        super("Круг", color);
        this.radius = radius;
    }

    public Circle(double radius) {
        this("Черный", radius);
    }

    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeterLength() {
        return Math.PI * radius * 2;
    }


}
