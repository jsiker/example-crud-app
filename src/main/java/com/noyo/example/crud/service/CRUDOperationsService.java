package com.noyo.example.crud.service;

import com.noyo.example.crud.models.User;
import com.noyo.example.crud.models.UserIdVersionCompositeKey;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CRUDOperationsService<E> {

    E save(E entity);

    Optional<E> getById(Serializable id);

    List<E> findAll();

    List<E> getAllById(Integer id);

    void delete(UserIdVersionCompositeKey id);

}
