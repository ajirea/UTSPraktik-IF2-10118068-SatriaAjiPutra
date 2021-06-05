package com.satria.utspraktik_if2_10118068_satriaajiputra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/*
    Tanggal Pengerjaan: 05 Juni 2021
    NIM: 10118068
    Nama: Satria Aji Putra Karma J
    Kelas: IF-2
 */
public class RecheckData extends AppCompatActivity {

    private View bottomSheet;
    private BottomSheetBehavior sheetBehavior;
    private BottomSheetDialog sheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recheck_data);

        final Button btnNext = findViewById(R.id.btnNext);
        final Button btnChange = findViewById(R.id.btnChange);
        bottomSheet = findViewById(R.id.bottomSheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        Bundle bundle = getIntent()
                .getBundleExtra("com.satria.utspraktik_if2_10118068_satriaajiputra.BUNDLE");

        String nik = bundle.getString("nik");
        String nama = bundle.getString("nama");
        String tglLahir = bundle.getString("tglLahir");
        String gender = bundle.getString("gender");
        String relation = bundle.getString("relation");

        final TextView resNIK = findViewById(R.id.resNIK);
        final TextView resNama = findViewById(R.id.resNama);
        final TextView resTglLahir = findViewById(R.id.resTglLahir);
        final TextView resGender = findViewById(R.id.resGender);
        final TextView resRelation = findViewById(R.id.resRelation);

        resNIK.setText(nik);
        resNama.setText(nama);
        resTglLahir.setText(tglLahir);
        resGender.setText(gender);
        resRelation.setText(relation);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.sheet_success, null);

                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                (view.findViewById(R.id.btnOK)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent mainIntent = new Intent(RecheckData.this, MainActivity.class);
                        startActivity(mainIntent);
                        sheetDialog.dismiss();
                    }
                });

                sheetDialog = new BottomSheetDialog(RecheckData.this);

                sheetDialog.setContentView(view);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    sheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }

                sheetDialog.show();
                sheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        sheetDialog = null;
                    }
                });
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecheckData.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}