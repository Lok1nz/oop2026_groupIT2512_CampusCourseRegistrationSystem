package edu.aitu.oop3.repositories.interfaces;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    void create(T entity);
    void delete(int id);
}