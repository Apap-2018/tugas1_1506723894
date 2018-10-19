package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstansiServiceCore implements InstansiService{
    @Autowired
    private InstansiDb instansiDb;

    @Override
    public List<InstansiModel> getAllInstansi(){
        return instansiDb.findAll();
    }

    @Override
    public InstansiModel getInstansiById(long id){
        return instansiDb.findById(id);
    }

    @Override
    public List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi){
        return instansiDb.findAllByProvinsi(provinsi);
    }
}
