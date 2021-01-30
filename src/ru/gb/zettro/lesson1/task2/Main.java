package ru.gb.zettro.lesson1.task2;

// Из задания не совсем ясно чего мы должны достичь оптимизацией кода, без этого
// понимания проблематично что-либо сделать. После исправления ошибок
// код сам по себе вполне рабочий, как конкретно его нужно оптимизировать я не понял.
// В зависимости от поставленной цели можно попробовать реализовать паттерны
// Builder, Strategy, Abstract Factory.
//

interface Movable {
    void move();
}

interface Stoppable {
    void stop();
}

abstract class Car {
    private Engine engine;
    private String color;
    private String name;

    //добавил конструктор
    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    protected void start() {
        engine.powerOn();
        System.out.println(name + " is starting");
    }

    //убрал модификатор abstract
    public void open() {
        System.out.println(name + " is opened");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class LightWeightCar extends Car implements Movable {

    //добавил конструктор
    public LightWeightCar(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    // убрал @Override поскольку здесь не идет речь о переопределении метода
    public void move() {
        getEngine().getPower(10000);
        System.out.println(getName() + " is moving");
    }

}

// вставил "implements" в нужной форме
class Lorry extends Car implements Movable, Stoppable {

    //добавил конструктор
    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    public void move() {
        getEngine().getPower(500000);
        System.out.println(getName() + " is flying");
    }

    public void stop() {
        System.out.println(getName() + " is landed");
        getEngine().powerOff();
    }
}

//чтобы код компилировался добавил абстрактный класс Engine
// и два его наследника CombustionEngine и AtomicEngine
abstract class Engine {
    private int maxPower;
    private String name;

    public Engine(int maxPower, String name) {
        this.maxPower = maxPower;
        this.name = name;
    }

    public void powerOn() {
        System.out.println(name + " is powered on.");
    }

    public void powerOff() {
        System.out.println(name + " is powered off.");
    }

    public int getPower(int desirableAmount) {
        int power = (desirableAmount > maxPower) ? maxPower : desirableAmount;
        System.out.println(power + " power units delivered");
        return power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "maxPower=" + maxPower +
                ", name='" + name + '\'' +
                '}';
    }
}

class CombustionEngine extends Engine {
    public CombustionEngine() {
        super(5000, "CombustionEngine");
    }
}

class AtomicEngine extends Engine {
    public AtomicEngine() {
        super(10000000, "AtomicEngine");
    }
}



class Main {
    public static void main(String[] args) {
        LightWeightCar mini = new LightWeightCar(new CombustionEngine(), "Green", "Mini Countryman");
        Lorry delorean = new Lorry(new AtomicEngine(), "Red", "DeLorean DMC-12");

        System.out.println(mini);
        System.out.println(delorean);
        System.out.println();
        mini.open();
        mini.start();
        mini.move();
        System.out.println();
        delorean.open();
        delorean.start();
        delorean.move();
        delorean.stop();
    }
}
