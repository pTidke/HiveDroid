package com.example.hivedroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    TextView tvTitle, tvSubtitle;
    ImageView imgPhotos;
    ImageButton btnPageNgo, btnPageStart;
    MaterialButton btnNgo, btnStart;
    Animation btt, bttwo, imgbounce;

    FirebaseAuth firebaseAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.btnLogout:
                LogOut();
                return true;
            case R.id.btnProfile:
                showProfileView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LogOut() {
        firebaseAuth.signOut();
        finish();
        //starting login activity
        startActivity(new Intent(this, MainActivity.class));
    }

    private void showProfileView() {
        Toast.makeText( this, firebaseAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT ).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnNgo = findViewById(R.id.btnNgo);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        firebaseAuth = FirebaseAuth.getInstance();

        imgPhotos = findViewById(R.id.imgPhotos);
        btnStart = findViewById(R.id.btnStart);

        btnPageNgo = findViewById(R.id.btnPageNgo);
        btnPageStart = findViewById(R.id.btnPageStart);

        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        imgbounce = AnimationUtils.loadAnimation(this, R.anim.imgbounce);

        btnPageStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPhotos.setImageResource(R.drawable.startup);
                tvTitle.setText("Fund Startup");
                tvSubtitle.setText("Probably The Best assist In The World");

                btnStart.setVisibility(View.VISIBLE);
                btnNgo.setVisibility(View.INVISIBLE);

                // give animation
                imgPhotos.startAnimation(imgbounce);
                tvSubtitle.startAnimation(btt);
                tvSubtitle.startAnimation(btt);
                btnStart.startAnimation(bttwo);

                // bottom animation
                btnPageNgo.setBackgroundResource(R.drawable.ngob);
                btnPageStart.setBackgroundResource(R.drawable.startupb);

                btnPageNgo.animate().scaleX(0.7f).scaleY(0.7f).setDuration(350).start();
                btnPageStart.animate().scaleX(1f).scaleY(1f).setDuration(350).start();

            }
        });

        btnPageNgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPhotos.setImageResource(R.drawable.ngo);
                tvTitle.setText("Donate NGO");
                tvSubtitle.setText("Your vision does not require sight");

                btnNgo.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);

                // give animation
                imgPhotos.startAnimation(imgbounce);
                tvSubtitle.startAnimation(btt);
                tvSubtitle.startAnimation(btt);
                btnNgo.startAnimation(bttwo);

                // bottom animation
                btnPageNgo.setBackgroundResource(R.drawable.ngob);
                btnPageStart.setBackgroundResource(R.drawable.startupb);

                btnPageStart.animate().scaleX(0.7f).scaleY(0.7f).setDuration(350).start();
                btnPageNgo.animate().scaleX(1f).scaleY(1f).setDuration(350).start();

            }
        });

        btnNgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, NGO.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Startup.class);
                startActivity(intent);
            }
        });

    }
}