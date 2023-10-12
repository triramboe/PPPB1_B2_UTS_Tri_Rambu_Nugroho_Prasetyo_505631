package com.example.caloriemonitoring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caloriemonitoring.GetStartedActivity.Companion.EXTRA_BB
import com.example.caloriemonitoring.GetStartedActivity.Companion.EXTRA_BB_TUJUAN
import com.example.caloriemonitoring.GetStartedActivity.Companion.EXTRA_TANGGAL
import com.example.caloriemonitoring.GetStartedActivity.Companion.EXTRA_TARGET
import com.example.caloriemonitoring.GetStartedActivity.Companion.EXTRA_TUJUAN
import com.example.caloriemonitoring.InputActivity.Companion.EXTRA_JENIS
import com.example.caloriemonitoring.InputActivity.Companion.EXTRA_JUMLAH_KALORI
import com.example.caloriemonitoring.InputActivity.Companion.EXTRA_KALORI_TERBAKAR
import com.example.caloriemonitoring.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    companion object{
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_SISA_KALORI = "extra_sisa_kalori"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra(EXTRA_NAMA)
        val target = intent.getStringExtra(EXTRA_TARGET)
        val bb = intent.getStringExtra(EXTRA_BB)
        val goals = intent.getStringExtra(EXTRA_BB_TUJUAN)
        val tanggal = intent.getStringExtra(EXTRA_TANGGAL)
        val tujuan = intent.getStringExtra(EXTRA_TUJUAN)

        val kaloriMakanan = intent.getStringExtra(EXTRA_JUMLAH_KALORI)
        val kaloriWO = intent.getStringExtra(EXTRA_KALORI_TERBAKAR)
        val jenis = intent.getStringExtra(EXTRA_JENIS)

        val sisaKalori = intent.getStringExtra(EXTRA_SISA_KALORI)

        with(binding) {
            namaHome.text = "Hai $nama"
            val sisaKaloriFieldText = sisaKalori ?: target
            sisakaloriFIeld.text = sisaKaloriFieldText
            bbField.text = "$bb"
            tbbField.text = "$goals"
            tglField.text = "$tanggal"
            mygoalsField.text = "$tujuan"

            val tanggalTextView = binding.tanggalHomePage
            val currentDate = SimpleDateFormat("dd/MM/yyyy").format(java.util.Date())
            tanggalTextView.text = currentDate


            if(jenis == "Pagi"){
                kaloriMakanPagi.text = "$kaloriMakanan"
                kaloriWorkoutPagi.text = "$kaloriWO"
            }else if (jenis == "Siang"){
                kaloriMakanSiang.text = "$kaloriMakanan"
                kaloriWorkoutSiang.text = "$kaloriWO"
            }else if (jenis == "Malam"){
                kaloriMakanMalam.text = "$kaloriMakanan"
                kaloriWorkoutMalam.text = "$kaloriWO"
            }


            btnInput.setOnClickListener{
                val intentToInputActivity = Intent(this@HomeActivity, InputActivity::class.java)

                // Mengambil sisa kalori dari TextView sisakaloriField
                val sisaKalori = sisakaloriFIeld.text.toString()

                // Mengirim sisa kalori ke InputActivity
                intentToInputActivity.putExtra(InputActivity.EXTRA_SISA_KALORI, sisaKalori)

                startActivity(intentToInputActivity)

            }

        }
    }
}