package khannedy.spring.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import khannedy.spring.rest.model.Barang;
import khannedy.spring.rest.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pakarjava on 8/3/13.
 */
@Controller
public class BarangController {

    private Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    private Map<String, Barang> map = new HashMap<String, Barang>();

    @ResponseBody
    @RequestMapping(value = "/barang/insert", method = RequestMethod.POST)
    public String insert(HttpServletRequest servletRequest, @RequestBody String json) {

        // konversi dari json ke object barang
        Barang barang = gson.fromJson(json, Barang.class);

        // simpen data barang ke map pake key kode barang
        map.put(barang.getKode(), barang);

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

        // ambil barang di map
        Barang barang = map.get(kode);

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

        // konversi dari json ke object barang
        Barang barang = gson.fromJson(json, Barang.class);

        // chek apa ada data barang dengan kode yang sama
        if (map.containsKey(barang.getKode())) {
            // kalo ada yang sama
            // update data barang dengan yang baru
            map.put(barang.getKode(), barang);

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

        // hapus data di map
        Barang barang = map.remove(kode);

        // check apa barang ada atau enggak
        if (barang == null) {

            // kalo gak ada, return status gagal
            Status status = new Status();
            status.setKode("404");
            status.setPesan("Barang gak ditemukan");
            return gson.toJson(status);

        } else {

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

        // buat data list barang
        List<Barang> list = new ArrayList<Barang>();

        // tambahin semua data barang dari map
        list.addAll(map.values());

        // return sebagai json
        return gson.toJson(list);
    }

}
