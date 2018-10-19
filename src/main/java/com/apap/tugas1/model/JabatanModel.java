package com.apap.tugas1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 200)
    @Column(name = "deskripsi")
    private String deskripsi;

    @NotNull
    @Column(name = "gaji_pokok", nullable = false)
    private int gajiPokok;

    @OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<JabatanPegawaiModel> pegawai;

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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(int gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public List<JabatanPegawaiModel> getJabatanPegawai() {
        return pegawai;
    }

    public void setJabatanPegawai(List<JabatanPegawaiModel> pegawaiJabatan) {
        this.pegawai = pegawaiJabatan;
    }
}
