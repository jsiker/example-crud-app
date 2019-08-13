package com.handshake.example.crud.service;

import com.handshake.example.crud.models.Alarm;
import com.handshake.example.crud.repositories.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    @Override
    public Alarm save(Alarm entity) {
        return alarmRepository.save(entity);
    }

    @Override
    public Optional<Alarm> getById(Serializable id) {
        return alarmRepository.findById((BigInteger) id);
    }

    @Override
    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    @Override
    public List<Alarm> getAllById(Integer id) {
        return null;
    }

    @Override
    public void delete(Serializable id) {

    }
}
