package khannedy.spring.rest.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pakarjava on 8/3/13.
 */
@Entity
public class Barang {

    @Id
    private String kode;
    @Column
    private String nama;
    @Column
    private String kategori;
    @Column
    private Long harga;
    @Column
    private Integer stok;
    @Column
    private Boolean mudahTerbakar;
    @Temporal(TemporalType.DATE)
    @Column
    private Date tanggalKadaluarsa;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Boolean getMudahTerbakar() {
        return mudahTerbakar;
    }

    public void setMudahTerbakar(Boolean mudahTerbakar) {
        this.mudahTerbakar = mudahTerbakar;
    }

    public Date getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setTanggalKadaluarsa(Date tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }
}
