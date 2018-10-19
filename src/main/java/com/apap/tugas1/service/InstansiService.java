package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

import java.util.List;

public interface InstansiService {
    List<InstansiModel> getAllInstansi();
    InstansiModel getInstansiById(long id);
    List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi);
}
