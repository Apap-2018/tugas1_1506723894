package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

import java.util.List;

public interface PegawaiService {
    void addPegawai(PegawaiModel pegawai);
    PegawaiModel getPegawaiByNip(String nip);
    List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
    List<PegawaiModel> cariPegawai(Integer id_provinsi, Integer id_instansi, Integer id_jabatan);
    void updatePegawai(PegawaiModel pegawai);
    String generateNip(PegawaiModel pegawai);
}
