package com.apap.tugas1.repository;

import com.apap.tugas1.model.JabatanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface JabatanDb extends JpaRepository<JabatanModel, Long> {
    public JabatanModel findById(long id);

//    @Query("select * from JabatanModel")
//    List<JabatanModel> allJabatan();
}
