package learning.shop.service;

import java.util.List;

public interface IService<E, I> {

    E findOne(I id);
    List<E> findAll();
    E save(E entity);
    E update(E entity);
    void remove(I id);
}
