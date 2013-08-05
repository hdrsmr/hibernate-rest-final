package khannedy.spring.rest.service;

import khannedy.spring.rest.model.Barang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pakarjava on 8/5/13.
 */
@Service
public class DefaultBarangService implements BarangService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insert(Barang barang) {
        // ngambil session hibernate
        Session session = sessionFactory.getCurrentSession();

        // simpen data barang ke database
        session.save(barang);

        // SELESAI!
    }

    @Transactional
    public void update(Barang barang) {
        // ngambil session hibernate
        Session session = sessionFactory.getCurrentSession();

        // update data barang ke database
        session.update(barang);

        // SELESAI!
    }

    @Transactional
    public void delete(String kode) {
        // ngambil session hibernate
        Session session = sessionFactory.getCurrentSession();

        // ngambil data barang
        Barang barang = (Barang) session.get(Barang.class, kode);

        // ngapus data barang di database
        session.delete(barang);

        // SELESAI!
    }

    @Transactional(readOnly = true)
    public Barang find(String kode) {
        // ngambil session hibernate
        Session session = sessionFactory.getCurrentSession();

        // ngambil data barang
        Barang barang = (Barang) session.get(Barang.class, kode);

        // SELESAI!
        return barang;
    }

    @Transactional(readOnly = true)
    public List<Barang> findAll() {
        // ngambil session hibernate
        Session session = sessionFactory.getCurrentSession();

        // simpen data barang ke database
        List<Barang> list = session.createCriteria(Barang.class).list();

        // SELESAI!
        return list;
    }
}
