package mullatoez.getimages.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.Query;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;


    RecyclerView friendList;

    private FirebaseFirestore db;
   private FriendsAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progress_bar);
        friendList = findViewById(R.id.friend_list);

        init();
        getFriendList();

    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        friendList.setLayoutManager(linearLayoutManager);

    }

    private void getFriendList() {

        CollectionReference query = db.collection("friends");

        FirestoreRecyclerOptions<FriendsResponse> options = new FirestoreRecyclerOptions.Builder<FriendsResponse>()
                .setQuery(query, FriendsResponse.class)
                .build();

        adapter = new FriendsAdapter(options);

        adapter.notifyDataSetChanged();
        friendList.setAdapter(adapter);
    }

    public class FriendsHolder extends RecyclerView.ViewHolder {

        TextView textName;

        CircleImageView imageView;

        TextView textTitle;

        TextView textCompany;

        public FriendsHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.title);
            textCompany = itemView.findViewById(R.id.company);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}