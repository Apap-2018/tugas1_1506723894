package com.apap.tugas1.controller;

import com.apap.tugas1.model.*;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import com.apap.tugas1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PegawaiController {
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private InstansiService instansiService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @RequestMapping("/")
    private String home(Model model){
        List<JabatanModel> daftarJabatan = jabatanService.getAllJabatan();
        List<InstansiModel> daftarInstansi = instansiService.getAllInstansi();
        model.addAttribute("daftarJabatan", daftarJabatan);
        model.addAttribute("daftarInstansi", daftarInstansi);
        return "home";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
    private String add(Model model){
        List<ProvinsiModel> daftarProvinsi = provinsiService.getAllProvinsi();
        List<JabatanModel> daftarJabatan = jabatanService.getAllJabatan();
        PegawaiModel pegawai = new PegawaiModel();
        JabatanModel newJabatan = new JabatanModel();
        List<JabatanPegawaiModel> listNewJabatan = new ArrayList<>();
        listNewJabatan.add(new JabatanPegawaiModel());
        pegawai.setJabatanPegawai(listNewJabatan);
//        pegawai.setInstansi(instansiService.getInstansiById(1101));
//        System.out.println(pegawai.getInstansi().getNama());
        model.addAttribute("daftarProvinsi", daftarProvinsi);
        model.addAttribute("pegawai",pegawai);
        model.addAttribute("daftarJabatan", daftarJabatan);
        return "pegawai/addPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
    private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai){
        String nip = pegawaiService.generateNip(pegawai);
        pegawai.setNip(nip);
//        System.out.println(pegawai.getNip());
//        System.out.println(pegawai.getInstansi().getNama());
//        System.out.println(pegawai.getTempatLahir());
//        System.out.println(pegawai.getTanggalLahir());
//        System.out.println(pegawai.getTahunMasuk());
        for (JabatanPegawaiModel jabatan : pegawai.getJabatanPegawai()){
            jabatan.setPegawai(pegawai);
            System.out.println(jabatan.getJabatan().getId());
            System.out.println(jabatan.getJabatan().getNama());
            System.out.println(jabatan.getPegawai().getId());
            System.out.println(jabatan.getPegawai().getNama());
        }
        pegawaiService.addPegawai(pegawai);
//        PegawaiModel newPegawai = pegawaiService.getPegawaiByNip(pegawai.getNip());
//        for (JabatanPegawaiModel jpModel : newPegawai.getJabatanPegawai()){
//            jabatanPegawaiService.addJabatanPegawaiRel(jpModel);
//        }
        return "pegawai/add";
    }

//    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
//    private String addJabatanRow(@ModelAttribute PegawaiModel pegawai,
//                                    @RequestParam("id_instansi") int id_instansi){
//
//        InstansiModel instansi = instansiService.getInstansiById(id_instansi);
//        pegawai.setInstansi(instansi);
//        System.out.println(pegawai.getInstansi().getNama());
//
//        return "pegawai/addPegawai";
//    }

    @RequestMapping(value = "/pegawai", method = RequestMethod.GET)
    private String viewPegawaiDetail(@RequestParam("nip") String nip, Model model){
        PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
        Integer maxGaji = 0;
        Integer presentaseTunjangan = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan();

        for (JabatanPegawaiModel jabatanRel : pegawai.getJabatanPegawai()){
            if(jabatanRel.getJabatan().getGajiPokok() > maxGaji){
                maxGaji = jabatanRel.getJabatan().getGajiPokok();
            }
        }
        maxGaji = maxGaji + (maxGaji * presentaseTunjangan);
        model.addAttribute("pegawai", pegawai);
//        model.addAttribute("daftarJabatanRel", daftarJabatanRel);
        model.addAttribute("maxGaji", maxGaji);

        return "pegawai/viewPegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
    private String update(@RequestParam("nip") String nip, Model model){
        List<ProvinsiModel> daftarProvinsi = provinsiService.getAllProvinsi();
        List<JabatanModel> daftarJabatan = jabatanService.getAllJabatan();
        PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
        List<InstansiModel> daftarInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
        String strListId = "";
        for(int i = 0; i < pegawai.getJabatanPegawai().size(); i++){
            if(i == pegawai.getJabatanPegawai().size() - 1){
                strListId += pegawai.getJabatanPegawai().get(i).getId();
            } else {
                strListId += pegawai.getJabatanPegawai().get(i).getId() + "-";
            }
        }

        model.addAttribute("daftarProvinsi", daftarProvinsi);
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("daftarJabatan", daftarJabatan);
        model.addAttribute("daftarInstansi", daftarInstansi);
        model.addAttribute("jumlahCurrentJabatan", pegawai.getJabatanPegawai().size());
        model.addAttribute("listIdJabatan", strListId);

        return "pegawai/updatePegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
    private String updatePegawaiSubmit(@ModelAttribute PegawaiModel pegawai){
        String newNip = pegawaiService.generateNip(pegawai);
        pegawai.setNip(newNip);
        for (JabatanPegawaiModel jabatan : pegawai.getJabatanPegawai()){
            jabatan.setPegawai(pegawai);
        }
        pegawaiService.updatePegawai(pegawai);
        return "pegawai/update";
    }

    @RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
    private String cariPegawai(@RequestParam(value = "id_provinsi", required = false) Integer id_provinsi,
                               @RequestParam(value = "id_instansi", required = false) Integer id_instansi,
                               @RequestParam(value = "id_jabatan", required = false) Integer id_jabatan,
                               Model model){
        List<InstansiModel> daftarInstansi = instansiService.getAllInstansi();
        List<ProvinsiModel> daftarProvinsi = provinsiService.getAllProvinsi();
        List<JabatanModel> daftarJabatan = jabatanService.getAllJabatan();
        List<PegawaiModel> daftarPegawai = pegawaiService.cariPegawai(id_provinsi, id_instansi, id_jabatan);
        model.addAttribute("daftarProvinsi", daftarProvinsi);
        model.addAttribute("daftarInstansi", daftarInstansi);
        model.addAttribute("daftarJabatan", daftarJabatan);
        model.addAttribute("daftarPegawai", daftarPegawai);

        return "pegawai/cariPegawai";
    }

    @RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
    private String youngOld(@RequestParam("id_instansi") int id_instansi, Model model){
        InstansiModel instansi = instansiService.getInstansiById(id_instansi);
        PegawaiModel pegawaiTertua = new PegawaiModel();
        PegawaiModel pegawaiTermuda = new PegawaiModel();

        for (PegawaiModel pegawai : instansi.getInstansiPegawai()){

            if(pegawaiTertua.getTanggalLahir() == null || pegawai.getTanggalLahir().compareTo(pegawaiTertua.getTanggalLahir()) < 0){
                pegawaiTertua = pegawai;
            }
            if(pegawaiTermuda.getTanggalLahir() == null || pegawai.getTanggalLahir().compareTo(pegawaiTermuda.getTanggalLahir()) > 0){
                pegawaiTermuda = pegawai;
            }
        }

        model.addAttribute("pegawaiTertua", pegawaiTertua);
        model.addAttribute("pegawaiTermuda", pegawaiTermuda);
        return "pegawai/youngestAndOldestPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah/select-provinsi/{id_provinsi}", method = RequestMethod.GET)
    private @ResponseBody
    List<InstansiModel> seleksiInstansi(@PathVariable(value = "id_provinsi") long id_provinsi, Model model){
        ProvinsiModel provinsi = provinsiService.getProvinsiById(id_provinsi);
        List<InstansiModel> selectedInstansi = instansiService.getInstansiByProvinsi(provinsi);
        System.out.println(selectedInstansi.size());
        return selectedInstansi;
    }

    @RequestMapping(value = "/pegawai/tambah/tambah-row-jabatan", method = RequestMethod.GET)
    private @ResponseBody
    List<JabatanModel> tambahJabatan(Model model){
        List<JabatanModel> daftarJabatan = jabatanService.getAllJabatan();
        return daftarJabatan;
    }

    @RequestMapping(value="/provinsi", method=RequestMethod.GET)
    @ResponseBody
    public List<ProvinsiModel> accountSummary() {
        return provinsiService.getAllProvinsi();
    }

    @RequestMapping(value = "/pegawai/testing", method = RequestMethod.GET)
    private String testing(Model model){

        PegawaiModel pegawai = pegawaiService.getPegawaiByNip("9404030787201301");
        model.addAttribute("pegawai", pegawai);
        return "pegawai/testing";
    }

    @RequestMapping(value = "/pegawai/testing/tambah", method = RequestMethod.POST)
    private String testingSubmit(@ModelAttribute PegawaiModel pegawai){
        System.out.println(pegawai.getInstansi());

        return "pegawai/add";
    }
}
