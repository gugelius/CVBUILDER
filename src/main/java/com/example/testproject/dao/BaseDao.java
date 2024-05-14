package com.example.testproject.dao;

import com.example.testproject.entity.AbstractEntity;
import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t);
    public abstract boolean delete(T t);
    public abstract List<T> findAll();
    public abstract T  update(T t);
}
