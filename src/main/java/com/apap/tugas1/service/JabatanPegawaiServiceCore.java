package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JabatanPegawaiServiceCore implements JabatanPegawaiService {
    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;

    @Override
    public void addJabatanPegawaiRel(JabatanPegawaiModel jabatanPegawai){
        jabatanPegawaiDb.save(jabatanPegawai);
    }
}
