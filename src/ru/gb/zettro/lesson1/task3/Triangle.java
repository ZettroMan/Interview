package ru.gb.zettro.lesson1.task3;

public class Triangle extends Shape {

    private double aLen;
    private double bLen;
    private double cLen;

    public Triangle(String color, double aLen, double bLen, double cLen) {
        super("Треугольник", color);
        this.aLen = aLen;
        this.bLen = bLen;
        this.cLen = cLen;
    }

    public Triangle(double aLen, double bLen, double cLen) {
        this("Черный", aLen, bLen, cLen);
    }

    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeterLength() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - aLen) * (halfPerimeter - bLen) * (halfPerimeter - cLen));
    }

    @Override
    public double getPerimeterLength() {
        return aLen + bLen + cLen;
    }

}
