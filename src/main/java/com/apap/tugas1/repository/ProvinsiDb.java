package com.apap.tugas1.repository;

import com.apap.tugas1.model.ProvinsiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProvinsiDb extends JpaRepository<ProvinsiModel, Long> {
    public ProvinsiModel findById(long id);
}
