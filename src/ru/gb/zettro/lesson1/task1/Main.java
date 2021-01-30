package ru.gb.zettro.lesson1.task1;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .withFirstName("Sherlock")
                .withLastName("Holmes")
                .withGender("Male")
                .withAge(28)
                .withCountry("Great Britain")
                .withAddress("Baker Street, 221b")
                .build();

        System.out.println(person);
    }
}
