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
        const val EXTRA_NAMA_MAKANAN = "extra_nama_makanan"
        const val EXTRA_WAKTU_MAKAN = "extra_waktu_makan"
        const val EXTRA_JUMLAH_KALORI = "extra_jumlah_kalori"
        const val EXTRA_NAMA_WORKOUT = "extra_nama_workout"
        const val EXTRA_DURASI_WORKOUT = "extra_duraasi_workout"
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
                // Dapatkan data yang diinput dari form
                val namaMakanan = binding.namaMakananField.text.toString()
                val jumlahKalori = binding.jumlahKaloriField.text.toString().toInt()

                // Hitung total kalori masukan (kalori in)
                totalKaloriIn += jumlahKalori

                // Dapatkan data kalori out (sesuai dengan nama variabel yang sesuai di Activity Input)
                val namaWorkout = binding.namaWorkoutField.text.toString()
                val durasiWorkout = binding.durasiWorkoutField.text.toString().toInt()
                val kaloriTerbakar = binding.kaloriTerbakarField.text.toString().toInt()

                // Hitung total kalori keluaran (kalori out)
                totalKaloriOut += kaloriTerbakar

                // Hitung sisa kalori berdasarkan target maksimum kalori harian
                val targetKaloriString = intent.getStringExtra(EXTRA_SISA_KALORI)
                val targetKalori = targetKaloriString?.toIntOrNull() ?: 0
                    sisaKalori = targetKalori - (totalKaloriIn - totalKaloriOut)

                // Kembali ke Activity Home dan kirim sisa kalori jika diperlukan
                val intentToHomeActivity = Intent(this@InputActivity, HomeActivity::class.java)
                intentToHomeActivity.putExtra(HomeActivity.EXTRA_SISA_KALORI, sisaKalori)

                val namaMkn = namaMakananField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_NAMA_MAKANAN,namaMkn)

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