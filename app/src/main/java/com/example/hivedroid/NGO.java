package com.example.hivedroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class NGO extends AppCompatActivity {

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
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ngo );
        firebaseAuth = FirebaseAuth.getInstance();
    }
}