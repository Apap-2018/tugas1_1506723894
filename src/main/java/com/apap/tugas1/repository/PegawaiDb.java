package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
    public PegawaiModel findPegawaiByNip(String nip);
    public List<PegawaiModel> findAllByInstansi(InstansiModel instansi);
//
//    public Long countPegawaiByTanggalLahirAndTahunMasuk(String tanggalAndTahunMasuk);
}
