package ru.gb.zettro.lesson5;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface DaoRepository <T> {
    void saveOrUpdate(T entity);
    T findByID(Long id) throws NoSuchElementException;
    void delete(T entity) throws NoSuchElementException;
    void deleteById(Long id) throws NoSuchElementException;
    List<T> findAll();

}
