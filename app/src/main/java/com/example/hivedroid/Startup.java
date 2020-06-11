package com.example.hivedroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hivedroid.model.Product;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Startup extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference products = db.collection("Products");
    private ProductAdapter adapter;

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
        setContentView( R.layout.activity_startup );

        firebaseAuth = FirebaseAuth.getInstance();
        setUpRecyclerView();

        //Product product = new Product("test", 2000, 0, "08-06-2020", "18-06-2020", "test description", "qwertyuiop");

        /*products.document().set( product )
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d( "product", "Success" );
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d( "product", "Failed" );
                    }
                } );;*/

    }

    private void setUpRecyclerView() {
        Query query = products;

        FirestoreRecyclerOptions<Product> options = new FirestoreRecyclerOptions.Builder<Product>()
                .setQuery(query, Product.class)
                .build();

        adapter = new ProductAdapter( options );

        RecyclerView worksRecyclerView = findViewById(R.id.productsRecyclerView);
        worksRecyclerView.setHasFixedSize(true);
        worksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        worksRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener( new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
            }
        } );

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}