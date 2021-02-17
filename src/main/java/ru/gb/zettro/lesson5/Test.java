package ru.gb.zettro.lesson5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.NoSuchElementException;

public class Test {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate.cfg.xml").buildSessionFactory();

        DaoRepository<SampleEntity> sampleEntityRepository = new DaoRepositoryImpl<>(SampleEntity.class, sessionFactory);

        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 1"));
        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 2"));
        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 3"));
        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 4"));
        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 5"));
        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 6"));
        sampleEntityRepository.saveOrUpdate(new SampleEntity("Test entity 7"));

        System.out.println("\n======================= 1 ===========================");

        System.out.println(sampleEntityRepository.findAll());

        System.out.println("\n======================= 2 ===========================");

        SampleEntity sampleEntity1 = sampleEntityRepository.findByID(4L);
        SampleEntity sampleEntityX = sampleEntityRepository.findByID(250L);

        System.out.println("sampleEntity1 = " + sampleEntity1);
        System.out.println("sampleEntityX = " + sampleEntityX);

        System.out.println("\n======================= 3 ===========================");

        try {
            sampleEntityRepository.delete(sampleEntity1);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        try {
            sampleEntityRepository.deleteById(6L);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(sampleEntityRepository.findAll());

        System.out.println("\n======================= 4 ===========================");

        // deleting transient entity
        try {
            sampleEntityRepository.delete(new SampleEntity("Test entity 8"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        // deleting entity that is not exists in database
        try {
            sampleEntityRepository.deleteById(1645L);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(sampleEntityRepository.findAll());

        System.out.println("\n======================= 5 ===========================");

    }
}
