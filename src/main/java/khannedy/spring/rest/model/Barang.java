package khannedy.spring.rest.model;

import java.util.Date;

/**
 * Created by pakarjava on 8/3/13.
 */
public class Barang {

    private String kode;
    private String nama;
    private String kategori;
    private Long harga;
    private Integer stok;
    private Boolean mudahTerbakar;
    private Date tanggalKadaluarsa;

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
