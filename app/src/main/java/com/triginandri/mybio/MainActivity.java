package com.triginandri.mybio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MaterialButton btn_detail;
    CardView cv_addres,cv_telephone, cv_email, cv_socmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_detail = findViewById(R.id.btnDetail);
        cv_addres = findViewById(R.id.cvAddress);
        cv_telephone = findViewById(R.id.cvTelephone);
        cv_email = findViewById(R.id.cvEmail);
        cv_socmed = findViewById(R.id.cvSocmed);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnDetail:
                        detailDialog();
                        break;
                    case R.id.cvAddress:
                        maps();
                        break;
                    case R.id.cvTelephone:
                        dial();
                        break;
                    case R.id.cvEmail:
                        gmail();
                        break;
                    case R.id.cvSocmed:
                        sosmed();
                        break;


                }
            }
        };

        btn_detail.setOnClickListener(listener);
        cv_addres.setOnClickListener(listener);
        cv_telephone.setOnClickListener(listener);
        cv_email.setOnClickListener(listener);
        cv_socmed.setOnClickListener(listener);

    }

    public void detailDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tentang saya")
                .setIcon(R.drawable.logo)
                .setMessage("Hobby: Traveling\n\n" + "Memiliki ketertarikan dengan bidang pengembangan aplikasi mobile. Pekerja keras, kolaboratif, berpikir kreatif, dan suka dengan hal baru")
                .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();

        builder.show();

    }

    public void gmail(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"rifqit19@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hai rifqi...");
//        intent.putExtra(Intent.EXTRA_TEXT,"Your message here...");
        startActivity(intent);
    }

    public void maps(){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Jl.R.Wijaya+2+langensari+timur+ungaran+barat+kab.semarang");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void dial(){

        String phone_number = "+6285795097094";
        Intent phone_intent = new Intent(Intent.ACTION_DIAL);
        phone_intent.setData(Uri.parse("tel:" + phone_number));
        startActivity(phone_intent);


    }

    public void sosmed(){
        Uri uri = Uri.parse("https://linktr.ee/exlibrys1995"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    private void callNumber() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);

        }

        else {

            String phoneNumber = "085795097094";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+ phoneNumber));
            startActivity(intent);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                callNumber();

            } else {

                Toast.makeText(this, "Izin aplikasi ditolak", Toast.LENGTH_SHORT).show();

            }

        }

    }



}