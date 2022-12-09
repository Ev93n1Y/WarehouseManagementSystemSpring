package application.service;

import java.util.List;
import java.util.UUID;

public interface CrudService<T> {
    List<T> findAll();

    T findById(UUID id);

    T save(T t);//create

    T save(UUID id, T t);//update

    void deleteById(UUID id);
}
