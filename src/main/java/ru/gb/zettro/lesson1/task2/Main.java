package ru.gb.zettro.lesson1.task2;

// Из задания не совсем ясно, чего мы должны достичь оптимизацией кода.
// После исправления ошибок код сам по себе вполне рабочий, что касается
// оптимизации, я сделал несколько поправок, опираясь на примеры из методички
// (по принципам SOLID). Engine реализовал в виде абстрактного класса
// (не стал его делать интерфейсом, поскольку хотел добавить параметр мощности
// двигателя). Можно, конечно, еще было в интерфейс Movable добавить методы
// start() и stop(), как это сделано в методичке, но мне показалось это
// не совсем правильным с точки зрения принципа разделения интерфейсов.
// Как вариант, можно было бы сделать один объединенный интерфейс
// StartStoppable, в котором будут два метода start() и stop(),
// добавить implements StartStoppable к абстрактному классу Car,
// а потом сделать реализации этих методов в LightWeightCar и в Lorry.
// Но тогда бы мы изменили функциональность класса LightWeightCar,
// в котором изначально не было метода stop(). Из формулировки задачи
// не понятно, умышленно это было сделано, или это тоже можно исправлять...
// Если бы функциональность классов LightWeightCar и Lorry была одинаковая,
// можно было бы еще сделать фабрику.
//

interface Movable {
    void move();
}

interface Stoppable {
    void stop();
}

// сразу подключил интерфейс Movable к классу Car. Теперь все неабстрактные наследники Car
// должны будут реализовать метод move()
abstract class Car implements Movable {
    private String name;
    private String color;
    protected Engine engine;

    // добавил конструктор
    protected Car(Engine engine, String name, String color) {
        this.engine = engine;
        this.name = name;
        this.color = color;
    }

    // этот метод можно было бы включить в состав отдельного интерфейса и добавить implements
    public void start() {
        engine.powerOn();
        System.out.println(name + " is starting");
    }

    // убрал модификатор abstract, кому надо - переопределят
    public void open() {
        System.out.println(name + " opened");
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
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", engine=" + engine +
                '}';
    }
}

// Убрал Movable, так как абстрактный класс Car implements Movable.
class LightWeightCar extends Car {

    // добавил конструктор с двигателем внутреннего сгорания
    public LightWeightCar(String name, String color) {
        super(new CombustionEngine(), name, color);
    }

    // убрал @Override, поскольку здесь не идет речь о переопределении метода
    public void move() {
        engine.getPower(10000);
        System.out.println(getName() + " is moving");
    }

}

// вставил implements, убрал Movable
class Lorry extends Car implements Stoppable {

    // добавил конструктор с атомным двигателем
    public Lorry(String name, String color) {
        super(new AtomicEngine(), name, color);
    }

    public void move() {
        engine.getPower(500000);
        System.out.println(getName() + " is flying");
    }

    public void stop() {
        System.out.println(getName() + " landed");
        engine.powerOff();
    }
}

// чтобы код компилировался добавил абстрактный класс Engine
// и два его наследника CombustionEngine и AtomicEngine
abstract class Engine {
    private String name;
    private int maxPower;

    protected Engine(String name, int maxPower) {
        this.name = name;
        this.maxPower = maxPower;
    }

    public void powerOn() {
        System.out.println(name + " powered on.");
    }

    public void powerOff() {
        System.out.println(name + " powered off.");
    }

    public int getPower(int desirableAmount) {
        int power = (desirableAmount > maxPower) ? maxPower : desirableAmount;
        System.out.println(power + " power units delivered");
        return power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", maxPower=" + maxPower +
                '}';
    }
}

class CombustionEngine extends Engine {
    public CombustionEngine() {
        super("CombustionEngine", 5000);
    }
}

class AtomicEngine extends Engine {
    public AtomicEngine() {
        super("AtomicEngine", 10000000);
    }
}


class Main {
    public static void main(String[] args) {
        LightWeightCar mini = new LightWeightCar("Mini Countryman", "Green");
        Lorry delorean = new Lorry("DeLorean DMC-12", "Red");

        System.out.println(mini);
        System.out.println(delorean);
        System.out.println();
        mini.open();
        mini.start();
        mini.move();
        System.out.println("Mini Countryman will never be stopped! =)) \n");
        delorean.open();
        delorean.start();
        delorean.move();
        delorean.stop();  // у mini этого метода нет ;)
    }
}
