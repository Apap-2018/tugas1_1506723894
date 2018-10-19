package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
//import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;

import java.util.List;

public interface JabatanService {
    void addJabatan(JabatanModel jabatan);
    List<JabatanModel> getAllJabatan();
    JabatanModel getJabatanById(long jabatan);
    void updateJabatan(JabatanModel jabatan);
    void deleteJabatan(JabatanModel jabatan);
//    List<JabatanPegawaiModel> getJabatanByPegawai(PegawaiModel pegawai);
}
