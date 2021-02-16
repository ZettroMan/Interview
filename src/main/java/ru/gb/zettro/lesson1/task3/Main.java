package ru.gb.zettro.lesson1.task3;

public class Main {

    public static void main(String[] args) {
        Square square = new Square(5);
        Circle circle = new Circle("Красный", 6);
        Triangle triangle = new Triangle("Зеленый",3, 4, 5);

        getShapeInfoAndDraw(square);
        getShapeInfoAndDraw(circle);
        getShapeInfoAndDraw(triangle);
    }

    private static void getShapeInfoAndDraw(Shape shape) {
        shape.getInfo();
        shape.draw();
    }
}
