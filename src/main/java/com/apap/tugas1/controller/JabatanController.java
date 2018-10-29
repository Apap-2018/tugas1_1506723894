package com.apap.tugas1.controller;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JabatanController {
    @Autowired
    private JabatanService jabatanService;

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("jabatan", new JabatanModel());
        return "jabatan/addJabatan";
    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model){
        System.out.println(jabatan.getNama());
        jabatanService.addJabatan(jabatan);
        model.addAttribute("jabatan", new JabatanModel());
        model.addAttribute("successJabatan", jabatan);
        return "jabatan/addJabatan";
    }

    @RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
    private String viewJabatan(@RequestParam(value = "id_jabatan") int id_jabatan, Model model){
        JabatanModel jabatan = jabatanService.getJabatanById(id_jabatan);
        model.addAttribute("jabatan", jabatan);
        return "jabatan/viewJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String update(@RequestParam(value = "id_jabatan") int id_jabatan, Model model){
        JabatanModel jabatan = jabatanService.getJabatanById(id_jabatan);
        model.addAttribute("jabatan", jabatan);
        return "jabatan/updateJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String updateJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model){
        jabatanService.updateJabatan(jabatan);
        model.addAttribute("jabatan", jabatan);
        model.addAttribute("successJabatan", jabatan);
        return "jabatan/updateJabatan";
    }

    @RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
    private String delete(@RequestParam(value = "id_jabatan") int id_jabatan, Model model){
        JabatanModel jabatan = jabatanService.getJabatanById(id_jabatan);
        System.out.println(jabatan.getJabatanPegawai().size());
        if(jabatan.getJabatanPegawai().size() == 0){
            jabatanService.deleteJabatan(jabatan);
            model.addAttribute("successJabatan", jabatan);
            return "jabatan/delete";
        }
        model.addAttribute("jabatan", jabatan);
        model.addAttribute("failedJabatan", jabatan);
        return "jabatan/viewJabatan";
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    private String update(Model model){
        List<JabatanModel> daftarJabatan = jabatanService.getAllJabatan();
        model.addAttribute("daftarJabatan", daftarJabatan);
        return "jabatan/listJabatan";
    }
}
