package com.example.hivedroid;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivedroid.model.Product;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductAdapter extends FirestoreRecyclerAdapter<Product, ProductAdapter.ProductHolder>{

    private OnItemClickListener listener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference products = db.collection("Products");

    public ProductAdapter(@NonNull FirestoreRecyclerOptions<Product> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductHolder productHolder, int i, @NonNull Product product) {
        productHolder.tvNameProduct.setText( product.getNameProduct() );
        productHolder.tvCreatorId.setText( "Created by : " + product.getIdCreator() );
        productHolder.tvPeriod.setText( "Interval : " + product.getStartDate() + " - " + product.getEndDate() );
        productHolder.tvDescription.setText( product.getDescription() );
        productHolder.tvGoal.setText( "Target Amount : " + product.getGoalAmount() + " RS.");
        productHolder.tvCurrent.setText( "Raised : " + product.getRaisedAmount() + " RS." );
        productHolder.goalBar.setProgress( (int) (product.getRaisedAmount() / product.getGoalAmount() * 100) );
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductHolder( v );
    }



    public class ProductHolder extends RecyclerView.ViewHolder {

        TextView tvNameProduct;
        TextView tvCreatorId;
        TextView tvPeriod;
        TextView tvDescription;
        TextView tvGoal;
        TextView tvCurrent;
        ProgressBar goalBar;
        MaterialButton btnFund;
        TextInputEditText amount;

        public ProductHolder(@NonNull final View itemView) {
            super( itemView );
            tvNameProduct = itemView.findViewById( R.id.nameProduct );
            tvCreatorId = itemView.findViewById( R.id.tvCreatorId );
            tvPeriod = itemView.findViewById( R.id.tvPeriod );
            tvDescription = itemView.findViewById( R.id.tvDescription );
            tvGoal = itemView.findViewById( R.id.tvGoal );
            tvCurrent = itemView.findViewById( R.id.tvCurrent );
            goalBar = itemView.findViewById( R.id.goalBar );
            btnFund = itemView.findViewById( R.id.btnFund );
            amount = itemView.findViewById( R.id.amount );

            btnFund.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = getSnapshots().getSnapshot( getAdapterPosition() ).getId();
                    float amountUpdate = Float.parseFloat( amount.getText().toString().trim() ) + getItem( getAdapterPosition() ).getRaisedAmount();
                    products.document(id).update( "raisedAmount", amountUpdate );
                    amount.setText( "" );
                }
            } );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =ProductHolder.this.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick( getSnapshots().getSnapshot( position ), position );
                    }
                }
            } );

        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
