package br.edu.ifpi.dao;

public interface Dao<T> {
    public int insert(T t);

    public int update(T t);
    
    public int delete(T t);

    public T select(int id);

    public T[] selectAll();

    public T[] selectAll(String condition);

    public T[] selectAll(String[] conditions);
}