package com.example.caloriemonitoring

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.caloriemonitoring.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding
    private val waktuMakan = arrayOf(
        "Pagi", "Siang", "Malam"
    )

    companion object{
        const val EXTRA_JUMLAH_KALORI = "extra_jumlah_kalori"
        const val EXTRA_KALORI_TERBAKAR = "extra_kalori_terbakar"
        const val EXTRA_SISA_KALORI = "extra_sisa_kalori"
        const val EXTRA_JENIS = "extra_jenis"
    }

    private var totalKaloriIn = 0
    private var totalKaloriOut = 0
    private var sisaKalori = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            val adapterWaktu = ArrayAdapter(
                this@InputActivity,
                R.layout.simple_spinner_item, waktuMakan
            )
            adapterWaktu.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )
            jenisInputSpinner.adapter = adapterWaktu


            val btnSave = binding.btnSave

            btnSave.setOnClickListener {

                val intentToHomeActivity = Intent(this@InputActivity, HomeActivity::class.java)

                val jmlKalori = jumlahKaloriField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_JUMLAH_KALORI,jmlKalori)

                val kaloriterbakar = kaloriTerbakarField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_KALORI_TERBAKAR,kaloriterbakar)

                val jenis = jenisInputSpinner.selectedItem.toString()
                intentToHomeActivity.putExtra(EXTRA_JENIS, jenis)


                startActivity(intentToHomeActivity)
            }
        }

    }
}