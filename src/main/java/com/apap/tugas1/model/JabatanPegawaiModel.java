package com.apap.tugas1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private PegawaiModel pegawai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jabatan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private JabatanModel jabatan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PegawaiModel getPegawai() {
        return pegawai;
    }

    public void setPegawai(PegawaiModel pegawai) {
        this.pegawai = pegawai;
    }

    public JabatanModel getJabatan() {
        return jabatan;
    }

    public void setJabatan(JabatanModel jabatan) {
        this.jabatan = jabatan;
    }
}
