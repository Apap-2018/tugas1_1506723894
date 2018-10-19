package com.apap.tugas1.repository;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface JabatanPegawaiDb extends JpaRepository<JabatanPegawaiModel, Long> {
    public List<JabatanPegawaiModel> findAllByPegawai(PegawaiModel pegawai);
    public JabatanPegawaiModel findById(long id);
}
