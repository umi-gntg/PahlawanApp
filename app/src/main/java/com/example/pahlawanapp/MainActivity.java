package com.example.pahlawanapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lvdata = findViewById(R.id.lvdata);
        ArrayList<String> daftarpahlawan = new ArrayList<>();
        daftarpahlawan.add("Soekarno");
        daftarpahlawan.add("Hatta");
        daftarpahlawan.add("Soedirman");
        daftarpahlawan.add("Pattimura");
        daftarpahlawan.add("Imam Bonjol");

        // Data untuk setiap pahlawan
        HashMap<String, Pahlawan> detailPahlawan = new HashMap<>();
        detailPahlawan.put("Soekarno", new Pahlawan(
                "Ir. Soekarno",
                R.drawable.soekarno,
                "Ir. Soekarno; 6 Juni 1901 – 21 Juni 1970), Ir. Soekarno, atau Bung Karno, adalah tokoh besar dalam sejarah Indonesia yang dikenal sebagai Proklamator Kemerdekaan sekaligus Presiden pertama Republik Indonesia. Dengan kepemimpinannya yang karismatik, ia berhasil menggalang persatuan bangsa dalam melawan penjajahan. Sebagai pemikir yang visioner, Bung Karno juga menjadi penggagas Pancasila sebagai dasar negara. Selain itu, ia memainkan peran penting dalam memperjuangkan kedaulatan Indonesia di dunia internasional melalui konferensi besar seperti Konferensi Asia-Afrika, menjadikan Indonesia sebagai pemimpin gerakan non-blok."
        ));
        detailPahlawan.put("Hatta", new Pahlawan(
                "Mohammad Hatta",
                R.drawable.hatta,
                "Mohammad Hatta (12 Agustus 1902 – 14 Maret 1980),  Bung Hatta adalah tokoh pejuang kemerdekaan yang dikenal karena integritas dan kecerdasannya. Sebagai Wakil Presiden pertama Indonesia, ia bersama Ir. Soekarno memproklamasikan kemerdekaan pada 17 Agustus 1945. Selain menjadi negarawan, Hatta juga seorang ekonom yang berjasa dalam merumuskan kebijakan ekonomi nasional. Ia dikenal sebagai Bapak Koperasi Indonesia karena komitmennya dalam memperjuangkan ekonomi kerakyatan. Sikap sederhana dan prinsip-prinsipnya yang teguh menjadikan Hatta panutan bagi banyak generasi."
        ));
        detailPahlawan.put("Soedirman", new Pahlawan(
                "Jenderal Soedirman",
                R.drawable.soedirman,
                "Jenderal Soedirman (24 Januari 1916 – 29 Januari 1950), Jenderal Soedirman adalah tokoh militer yang menjadi simbol keberanian dan pengorbanan dalam mempertahankan kemerdekaan Indonesia. Meski dalam kondisi kesehatan yang buruk akibat penyakit paru-paru, ia tetap memimpin perang gerilya melawan Belanda dalam Agresi Militer. Dengan semangat juang yang tinggi dan strategi yang brilian, ia mampu menunjukkan bahwa kemerdekaan harus dipertahankan dengan segala daya upaya. Namanya kini dikenang sebagai salah satu pahlawan besar dalam sejarah perjuangan bangsa."
        ));
        detailPahlawan.put("Pattimura", new Pahlawan(
                "Kapitan Pattimura",
                R.drawable.pattimura,
                "Kapitan Pattimura (Thomas Matulessy, 8 Juni 1783 – 16 Desember 1817)Thomas Matulessy, atau yang lebih dikenal sebagai Kapten Pattimura, adalah pahlawan nasional dari Maluku yang memimpin perlawanan rakyat melawan penjajahan Belanda pada tahun 1817. Dengan keberanian dan strategi militernya, Pattimura berhasil mempersatukan rakyat Maluku untuk melawan penindasan. Meski akhirnya gugur di tangan penjajah, perjuangannya meninggalkan warisan semangat pantang menyerah bagi generasi penerus. Ia menjadi simbol perlawanan rakyat Maluku dalam mempertahankan martabat dan tanah air mereka."
        ));
        detailPahlawan.put("Imam Bonjol", new Pahlawan(
                "Tuanku Imam Bonjol",
                R.drawable.imambonjol,
                "Tuanku Imam Bonjol adalah pemimpin ulama sekaligus pejuang yang memimpin Perang Padri di Sumatera Barat melawan kolonial Belanda pada awal abad ke-19. Ia berjuang untuk mempertahankan ajaran Islam dan nilai-nilai adat Minangkabau dari pengaruh penjajahan. Meski pada akhirnya ditangkap dan diasingkan oleh Belanda, perjuangannya menjadi inspirasi besar dalam melawan penindasan. Imam Bonjol kini dikenang sebagai simbol perlawanan rakyat yang berlandaskan pada nilai agama dan kebangsaan."
        ));

        // Buat adapter untuk menghubungkan data ke listview
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, daftarpahlawan
        );

        // menghubungkan listview ke adapter
        lvdata.setAdapter(adapter);

        lvdata.setOnItemClickListener((parent, view, position, id) -> {
            String namaPahlawan = daftarpahlawan.get(position);
            Toast.makeText(MainActivity.this, "Anda memilih " + namaPahlawan, Toast.LENGTH_SHORT).show();

            Pahlawan pahlawan = detailPahlawan.get(namaPahlawan);
            if (pahlawan != null) {
                Intent intent = new Intent(MainActivity.this, DetailList.class);
                intent.putExtra("nama", pahlawan.nama);
                intent.putExtra("gambar", pahlawan.gambar);
                intent.putExtra("deskripsi", pahlawan.deskripsi);
                startActivity(intent);
            }
        });
    }

    // Inner class untuk menyimpan data pahlawan
    static class Pahlawan {
        String nama;
        int gambar;
        String deskripsi;

        Pahlawan(String nama, int gambar, String deskripsi) {
            this.nama = nama;
            this.gambar = gambar;
            this.deskripsi = deskripsi;
        }
    }
}