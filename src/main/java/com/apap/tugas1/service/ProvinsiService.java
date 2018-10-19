package com.apap.tugas1.service;

import com.apap.tugas1.model.ProvinsiModel;

import java.util.List;

public interface ProvinsiService {
    List<ProvinsiModel> getAllProvinsi();
    ProvinsiModel getProvinsiById(long id);
}
