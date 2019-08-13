package com.handshake.example.crud.repositories;

import com.handshake.example.crud.models.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, BigInteger> {
}
