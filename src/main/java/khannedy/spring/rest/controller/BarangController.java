package khannedy.spring.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import khannedy.spring.rest.helper.BasicAuth;
import khannedy.spring.rest.model.Barang;
import khannedy.spring.rest.model.Status;
import khannedy.spring.rest.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by pakarjava on 8/3/13.
 */
@Controller
public class BarangController {

    private Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    @Autowired
    private BarangService barangService;

    @ResponseBody
    @RequestMapping(value = "/barang/insert", method = RequestMethod.POST)
    public String insert(HttpServletRequest servletRequest, @RequestBody String json) {

        // check hak akses
        if (!checkAccess(servletRequest)) {
            Status status = new Status();
            status.setKode("408");
            status.setPesan("Hak akses ditolak");
            return gson.toJson(status);
        }

        // konversi dari json ke object barang
        Barang barang = gson.fromJson(json, Barang.class);

        // simpen data ke database
        barangService.insert(barang);

        // bikin status sukses
        Status status = new Status();
        status.setKode("200"); // kode terserah bisa berapa aja
        status.setPesan("Sukses nyimpen data barang");

        // bikin response json
        return gson.toJson(status);
    }

    @ResponseBody
    @RequestMapping(value = "/barang/find/{kode}", method = RequestMethod.GET)
    public String find(HttpServletRequest servletRequest,
                       @PathVariable("kode") String kode) {

        // check hak akses
        if (!checkAccess(servletRequest)) {
            Status status = new Status();
            status.setKode("408");
            status.setPesan("Hak akses ditolak");
            return gson.toJson(status);
        }

        // ambil barang di map
        Barang barang = barangService.find(kode);

        // check apa barang ada atau enggak
        if (barang == null) {
            // kalo gak ada, return null aja
            return null;
        } else {
            // kalo ada, convert jadi JSON
            return gson.toJson(barang);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/barang/update", method = RequestMethod.PUT)
    public String update(HttpServletRequest servletRequest, @RequestBody String json) {

        // check hak akses
        if (!checkAccess(servletRequest)) {
            Status status = new Status();
            status.setKode("408");
            status.setPesan("Hak akses ditolak");
            return gson.toJson(status);
        }

        // konversi dari json ke object barang
        Barang barang = gson.fromJson(json, Barang.class);

        // chek apa ada data barang dengan kode yang sama
        if (barangService.find(barang.getKode()) != null) {
            // kalo ada yang sama
            // update data barang dengan yang baru
            barangService.update(barang);

            // bikin status sukses
            Status status = new Status();
            status.setKode("200"); // kode terserah bisa berapa aja
            status.setPesan("Sukses mengubah data barang");

            // bikin response json
            return gson.toJson(status);
        } else {
            // kalo barang nya gak ada
            // bikin status gagal
            Status status = new Status();
            status.setKode("404"); // kode terserah bisa berapa aja
            status.setPesan("Barang gak ditemukan");

            // bikin response json
            return gson.toJson(status);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/barang/delete/{kode}", method = RequestMethod.DELETE)
    public String delete(HttpServletRequest servletRequest,
                         @PathVariable("kode") String kode) {

        // check hak akses
        if (!checkAccess(servletRequest)) {
            Status status = new Status();
            status.setKode("408");
            status.setPesan("Hak akses ditolak");
            return gson.toJson(status);
        }

        // check apa barang ada atau enggak
        if (barangService.find(kode) == null) {
            // kalo gak ada, return status gagal
            Status status = new Status();
            status.setKode("404");
            status.setPesan("Barang gak ditemukan");
            return gson.toJson(status);

        } else {
            // hapus barang
            barangService.delete(kode);

            // kalo gak ada, return status sukses
            Status status = new Status();
            status.setKode("200");
            status.setPesan("Barang berhasil dihapus");
            return gson.toJson(status);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/barang/findall", method = RequestMethod.GET)
    public String findAll(HttpServletRequest servletRequest) {

        // check hak akses
        if (!checkAccess(servletRequest)) {
            Status status = new Status();
            status.setKode("408");
            status.setPesan("Hak akses ditolak");
            return gson.toJson(status);
        }

        // buat data list barang
        List<Barang> list = barangService.findAll();

        // return sebagai json
        return gson.toJson(list);
    }

    public boolean checkAccess(HttpServletRequest servletRequest) {

        // misal username dan password harus ...
        String username = "eko";
        String password = "@khannedy";

        // buat object BasicAuth
        BasicAuth basicAuth = new BasicAuth(servletRequest);

        // check username dan password
        return username.equals(basicAuth.getUsername()) &&
                password.equals(basicAuth.getPassword());

    }

}
