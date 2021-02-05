package ru.gb.zettro.lesson2;

import ru.gb.zettro.lesson2.arraylist.MyArrayList;
import ru.gb.zettro.lesson2.linkedlist.MyLinkedList;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger("");

    static {
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return "\n" + record.getLevel() + "\t" + record.getMessage();
            }
        });
    }

    public static void main(String[] args) {

        MyList<Integer> myLinkedList = new MyLinkedList<>();
        MyList<Integer> myArrayList = new MyArrayList<>();

        // Let's run some tests))
        logger.info("=========== TESTING LINKED LIST ============");
        runTest(myLinkedList);
        logger.info("=========== TESTING ARRAY LIST =============");
        runTest(myArrayList);
        logger.info("========= RUNNING HARD SYNCHRONOUS TEST ON LINKED AND ARRAY LISTS ==========");
        for (int i = 0; i < 2000; i++) {
            myLinkedList.clear();
            myArrayList.clear();
            runHardTest(myLinkedList, myArrayList);
            if (myArrayList.size() != myLinkedList.size()) {
                logger.info("ALERT!!! LISTS WORKS NOT IDENTICAL!");
                break;
            }
        }
        logger.info("============= LAST RUN RESULTS ==============");
        logger.info(myLinkedList.toString());
        logger.info(myArrayList.toString());
    }

    private static void runTest(MyList<Integer> myList) {
        logger.info("myList.size() = " + myList.size());
        logger.info("myList.isEmpty() = " + myList.isEmpty());
        myList.add(23);
        myList.add(17);
        myList.add(67);
        myList.add(12);
        myList.add(3);
        myList.add(95);
        myList.add(44);
        myList.add(82);
        logger.info(myList.toString());

        // Testing all methods in MyLinkedList
        myList.addAtHead(34);
        logger.info(myList.toString());
        myList.addAtTail(71);
        logger.info(myList.toString());
        logger.info(String.valueOf(myList.contains(12))); // should be true
        logger.info(String.valueOf(myList.contains(13))); // should be false
        logger.info(String.valueOf(myList.get(5)));       // should be 3
        logger.info(String.valueOf(myList.indexOf(44)));  // should be 7
        myList.set(3, 28);                       // should change 67 to 28
        logger.info(myList.toString());
        myList.remove((Integer) 3);              // should remove 3
        logger.info(myList.toString());
        myList.remove(0);                  // should remove 34
        logger.info(myList.toString());
        myList.remove(4);                  // should remove 95
        logger.info(myList.toString());
        myList.add(2, 15);
        logger.info(myList.toString());
        myList.clear();
        logger.info(myList.toString());
    }

    private static void runHardTest(MyList<Integer> myLinkedList, MyList<Integer> myArrayList) {
        int action;
        int value;
        int index;
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            action = random.nextInt(10);
            value = random.nextInt(1000);
            index = random.nextInt(1000);
            // System.out.print("\nAction = " + action + "\t\tValue = " + value + "\t\tIndex = " + index);
            try {
                // System.out.print("\nLinkedList: ");
                doAction(myLinkedList, action, value, index);
            } catch (IllegalArgumentException | NoSuchElementException e) {
                // System.out.print(e.getMessage());
            }
            try {
                // System.out.print("\nArrayList:  ");
                doAction(myArrayList, action, value, index);
            } catch (IllegalArgumentException | NoSuchElementException e) {
                //  System.out.print(e.getMessage());
            }
        }

    }

    private static void doAction(MyList<Integer> myList, int action, Integer value, int index) {
        switch (action) {
            case 0:
                myList.add(value);
                break;
            case 1:
                myList.addAtHead(value);
                break;
            case 2:
                myList.addAtTail(value);
                break;
            case 3:
                myList.add(index, value);
                break;
            case 4:
                myList.contains(value);
                break;
            case 5:
                myList.get(index);
                break;
            case 6:
                myList.set(index, value);
                break;
            case 7:
                myList.remove(index);
                break;
            case 8:
                myList.remove(value);
                break;
            case 9:
                myList.indexOf(value);
                break;
        }
    }
}
