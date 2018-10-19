package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface InstansiDb extends JpaRepository<InstansiModel, Long> {
//    public List<InstansiModel>
    public InstansiModel findById(long id);
    public List<InstansiModel> findAllByProvinsi(ProvinsiModel provinsi);
}
