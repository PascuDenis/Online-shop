package learning.shop.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController <E, I> {
    ResponseEntity<E> getOne(I id);
    List<E> getAll();
    E save(E entity);
    E update(E entity);
    void remove(I id);
}
