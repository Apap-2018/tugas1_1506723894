package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.InstansiDb;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import com.apap.tugas1.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class PegawaiServiceCore implements PegawaiService{
    @Autowired
    private PegawaiDb pegawaiDb;

    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;

    @Autowired
    private InstansiDb instansiDb;

    @Override
    public void addPegawai(PegawaiModel pegawai){
        pegawaiDb.save(pegawai);
        jabatanPegawaiDb.saveAll(pegawai.getJabatanPegawai());
    }

    @Override
    public PegawaiModel getPegawaiByNip(String nip){
        return pegawaiDb.findPegawaiByNip(nip);
    }

    public PegawaiModel getPegawaiById(long id){
        return pegawaiDb.findById(id).get();
    }

    @Override
    public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi){
        return pegawaiDb.findAllByInstansi(instansi);
    }

    @Override
    public List<PegawaiModel> cariPegawai(Integer id_provinsi, Integer id_instansi, Integer id_jabatan){
        List<PegawaiModel> daftarPegawai = new ArrayList<>();
        List<PegawaiModel> removedPegawai = new ArrayList<>();

        if(id_provinsi != null || id_instansi != null || id_jabatan != null){
            daftarPegawai = pegawaiDb.findAll();
        }

        for (PegawaiModel pegawai : daftarPegawai) {

            if(id_provinsi != null && pegawai.getInstansi().getProvinsi().getId() != (id_provinsi)){
                removedPegawai.add(pegawai);
            }

            if(id_instansi != null && pegawai.getInstansi().getId() != (id_instansi)){
                removedPegawai.add(pegawai);
            }

            if(id_jabatan != null){
                boolean jabatanExist = false;
                for (JabatanPegawaiModel jabatan : pegawai.getJabatanPegawai()){
                    if(jabatan.getJabatan().getId() == id_jabatan){
                        jabatanExist = true;
                    }
                }
                if(jabatanExist == false){
                    removedPegawai.add(pegawai);
                }
            }
        }
//        System.out.println(daftarPegawai.size());
//        System.out.println(removedPegawai.size());
        daftarPegawai.removeAll(removedPegawai);
        return daftarPegawai;
    }

    @Override
    public void updatePegawai(PegawaiModel pegawai) {
        PegawaiModel pegawaiData = getPegawaiById(pegawai.getId());
        pegawaiData.setTempatLahir(pegawai.getTempatLahir());
        pegawaiData.setTanggalLahir(pegawai.getTanggalLahir());
        pegawaiData.setTahunMasuk(pegawai.getTahunMasuk());
        pegawaiData.setInstansi(pegawai.getInstansi());
        pegawaiData.setJabatanPegawai(pegawai.getJabatanPegawai());
        for (JabatanPegawaiModel jpModel : jabatanPegawaiDb.findAllByPegawai(pegawai)){
            JabatanPegawaiModel jbModel = jabatanPegawaiDb.findById(jpModel.getId());
            jabatanPegawaiDb.delete(jbModel);
        }
        jabatanPegawaiDb.saveAll(pegawai.getJabatanPegawai());

    }

    @Override
    public String generateNip(PegawaiModel pegawai) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String nip = "";

        String kodeInstansi = Long.toString(pegawai.getInstansi().getId());

        String formatTanggalLahir = df.format(pegawai.getTanggalLahir());
        String[] komponenTanggalLahir = formatTanggalLahir.split("/");
        String strTanggalLahir = komponenTanggalLahir[0] + komponenTanggalLahir[1] + komponenTanggalLahir[2];

        String strTahunMasuk = pegawai.getTahunMasuk();

        InstansiModel instansi = instansiDb.findById(pegawai.getInstansi().getId());
        List<PegawaiModel> allInsPegawai = pegawaiDb.findAllByInstansi(instansi);
        int count = 1;
        for (PegawaiModel iterPegawai : allInsPegawai){
            String formatTanggalLahirIter = df.format(iterPegawai.getTanggalLahir());
            String[] komponenTanggalLahirIter = formatTanggalLahirIter.split("/");
            String strTanggalLahirIter = komponenTanggalLahirIter[0] + komponenTanggalLahirIter[1] + komponenTanggalLahirIter[2];

            String tahunMasukIter = iterPegawai.getTahunMasuk();

            if(strTanggalLahir.equals(strTanggalLahirIter) && strTahunMasuk.equals(tahunMasukIter)){
                count++;
            }
        }

        if(count/10 >= 1){
            nip = kodeInstansi+strTanggalLahir+strTahunMasuk+count;
        } else {
            nip = kodeInstansi+strTanggalLahir+strTahunMasuk+ "0" +count;
        }

        return nip;
    }
}
