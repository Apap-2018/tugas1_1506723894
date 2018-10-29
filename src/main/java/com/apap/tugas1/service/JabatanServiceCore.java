package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
//import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanDb;
//import com.apap.tugas1.repository.JabatanPegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JabatanServiceCore implements JabatanService{

    @Autowired
    private JabatanDb jabatanDb;

//    @Autowired
//    private JabatanPegawaiDb jabatanPegawaiDb;

    @Override
    public void addJabatan(JabatanModel jabatan) { jabatanDb.save(jabatan); }

    @Override
    public List<JabatanModel> getAllJabatan(){
        return jabatanDb.findAll();
    }

    @Override
    public JabatanModel getJabatanById(long jabatan){
        return jabatanDb.findById(jabatan);
    }

    @Override
    public void updateJabatan(JabatanModel jabatan){
//        System.out.println(jabatanDb.allJabatan().get(0).getNama());
//        System.out.println(jabatanDb.allJabatan().get(0).getId());
        JabatanModel jabatanCurrent = this.getJabatanById(jabatan.getId());
        jabatanCurrent.setNama(jabatan.getNama());
        jabatanCurrent.setDeskripsi(jabatan.getDeskripsi());
        jabatanCurrent.setGajiPokok(jabatan.getGajiPokok());
    }

    @Override
    public void deleteJabatan(JabatanModel jabatan){
        jabatanDb.delete(jabatan);
    }

//    @Override
//    public List<JabatanPegawaiModel> getJabatanByPegawai(PegawaiModel pegawai){
//        System.out.println("i've been here");
//        List<JabatanPegawaiModel> list = jabatanPegawaiDb.findJabatanPegawaiByPegawai(pegawai);
//        System.out.println("i've been here2");
//        return list;
//    }
}
