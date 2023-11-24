package br.edu.ifpi.data.dao;

import java.util.List;

public interface Dao<T> {
    public int insert(T t);

    public int update(T t);
    
    public int delete(T t);

    public T select(int id);

    public List<T> selectAll();

    public List<T> selectAll(String condition);

    public List<T> selectAll(String[] conditions);
}