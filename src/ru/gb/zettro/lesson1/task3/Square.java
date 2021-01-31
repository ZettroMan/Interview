package ru.gb.zettro.lesson1.task3;

public class Square extends Shape {

    private double sideLen;

    public Square(String color, double sideLen) {
        super("Квадрат", color);
        this.sideLen = sideLen;
    }

    public Square(double sideLen) {
        this("Черный", sideLen);
    }

    public void setSideLen(double sideLen) {
        this.sideLen = sideLen;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем квадрат");
    }

    @Override
    public double getArea() {
        return sideLen * sideLen;
    }

    @Override
    public double getPerimeterLength() {
        return sideLen * 4;
    }


}
