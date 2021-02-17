package ru.gb.zettro.lesson5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class DaoRepositoryImpl<T> implements DaoRepository<T> {

    private Class<T> entityType;
    private SessionFactory factory;
    private final String FIND_ALL_QUERY;

    public DaoRepositoryImpl(Class<T> entityType, SessionFactory factory) {
        this.entityType = entityType;
        this.factory = factory;
        FIND_ALL_QUERY = "FROM " + entityType.getName();

    }

    @Override
    public void saveOrUpdate(T entity) throws NoSuchElementException {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new NoSuchElementException("Element " + entity + " is not found.");
        }
    }

    @Override
    public T findByID(Long id) {
        try (Session session = factory.openSession()) {
            return session.get(entityType, id);
        }
    }

    @Override
    public void delete(T entity) throws NoSuchElementException {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new NoSuchElementException("Element " + entity + " is not found.");
        }
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        try (Session session = factory.openSession()) {
            T entity = session.get(entityType, id);
            if(entity == null) {
              throw new NoSuchElementException("Element with id = " + id + " is not found.");
            }
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = factory.openSession()) {
            return (List<T>) session.createQuery(FIND_ALL_QUERY).list();
        }
    }
}
