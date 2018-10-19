package com.apap.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "provinsi")
public class ProvinsiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "presentase_tunjangan", nullable = false)
    private int presentaseTunjangan;

    @OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<InstansiModel> provinsiInstansi;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getPresentaseTunjangan() {
        return presentaseTunjangan;
    }

    public void setPresentaseTunjangan(int presentaseTunjangan) {
        this.presentaseTunjangan = presentaseTunjangan;
    }

    public List<InstansiModel> getProvinsiInstansi() {
        return provinsiInstansi;
    }

    public void setProvinsiInstansi(List<InstansiModel> provinsiInstansi) {
        this.provinsiInstansi = provinsiInstansi;
    }
}
