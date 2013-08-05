package khannedy.spring.rest.service;

import khannedy.spring.rest.model.Barang;

import java.util.List;

/**
 * Created by pakarjava on 8/5/13.
 */
public interface BarangService {

    void insert(Barang barang);

    void update(Barang barang);

    void delete(String kode);

    Barang find(String kode);

    List<Barang> findAll();

}
