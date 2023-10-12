package com.example.caloriemonitoring

import android.R
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.caloriemonitoring.databinding.ActivityGetStartedBinding
import java.util.Calendar

class GetStartedActivity : AppCompatActivity() {
    private val TAG = "GetStartedActiviyLifeCycle"
    private lateinit var binding: ActivityGetStartedBinding

    private val tujuanUser = arrayOf(
        "cutting", "bulking", "maintaning"
    )

    companion object{
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_BB = "extra_bb"
        const val EXTRA_BB_TUJUAN = "extra_bb_tujuan"
        const val EXTRA_TUJUAN = "extra_tujuan"
        const val EXTRA_TANGGAL = "extra_tanggal"
        const val EXTRA_TARGET = "extra_target"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            val adapterTujuanUser = ArrayAdapter(
                this@GetStartedActivity,
                R.layout.simple_spinner_item, tujuanUser
            )
            adapterTujuanUser.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )
            tujuanProgramSpinner.adapter = adapterTujuanUser

            // Mengatur OnClickListener pada EditText tanggal_field
            tanggalField.setOnClickListener {
                showDatePickerDialog()
        }

            //getStartButton
            btnGetStarted.setOnClickListener{
                val intentToHomeActivity =
                    Intent(this@GetStartedActivity, HomeActivity::class.java)

                val name = namaField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_NAMA,name)

                val beratBadan = beratBadanField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_BB,beratBadan)

                val bbTujuan = tujuanBBField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_BB_TUJUAN,bbTujuan)

                val tujuan = tujuanProgramSpinner.selectedItem.toString()
                intentToHomeActivity.putExtra(EXTRA_TUJUAN,tujuan)

                val tanggal = tanggalField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_TANGGAL,tanggal)

                val targetKalori = targetKaloriField.text.toString()
                intentToHomeActivity.putExtra(EXTRA_TARGET,targetKalori)


                startActivity(intentToHomeActivity)
            }
    }
}

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                // Proses tanggal yang dipilih di sini
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                binding.tanggalField.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
