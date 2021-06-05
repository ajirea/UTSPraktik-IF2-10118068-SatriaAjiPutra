package com.satria.utspraktik_if2_10118068_satriaajiputra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/*
    Tanggal Pengerjaan: 05 Juni 2021
    NIM: 10118068
    Nama: Satria Aji Putra Karma J
    Kelas: IF-2
 */
public class MainActivity extends AppCompatActivity {

    private String[] months = new String[] {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnNext = findViewById(R.id.btnNext);
        final EditText fieldTglLahir = findViewById(R.id.fieldTglLahir);
        final EditText fieldNIK = findViewById(R.id.fieldNIK);
        final EditText fieldNama = findViewById(R.id.fieldNama);

        fieldTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fieldTglLahir.setText(
                                String.valueOf(dayOfMonth) + " " + String.valueOf(months[month]) + " " + String.valueOf(year)
                        );
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }


        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecheckData.class);
                Bundle bundle = new Bundle();

                bundle.putString("nik", fieldNIK.getText().toString());
                bundle.putString("nama", fieldNama.getText().toString());
                bundle.putString("tglLahir", fieldTglLahir.getText().toString());
                bundle.putString("gender", selectedGender(findViewById(R.id.radioGender)));
                bundle.putString("relation", selectedRelation(findViewById(R.id.radioRelation)));

                intent.putExtra("com.satria.utspraktik_if2_10118068_satriaajiputra.BUNDLE", bundle);

                startActivity(intent);
            }
        });
    }

    private String selectedGender(RadioGroup radio) {
        return radio.getCheckedRadioButtonId() == R.id.radioMale ? "Laki - Laki" : "Perempuan";
    }

    private String selectedRelation(RadioGroup radio) {
        switch (radio.getCheckedRadioButtonId()) {
            case R.id.radioOrangtua: return "Orangtua";
            case R.id.radioSuamiIstri: return "Suami/Istri";
            case R.id.radioAnak: return "Anak";
            case R.id.radioKerabatLainnya: return "Kerabat Lainnya";
            default: return "Orangtua";
        }
    }
}