package mullatoez.getimages.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

import de.hdodenhof.circleimageview.CircleImageView;

public
class FriendsAdapter extends FirestoreRecyclerAdapter<FriendsResponse, FriendsAdapter.FriendsHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private Context context;


    public FriendsAdapter(FirestoreRecyclerOptions<FriendsResponse> response) {
        super(response);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull FriendsHolder holder, int position, @NonNull FriendsResponse model) {

        holder.textName.setText(model.getName());
        holder.textTitle.setText(model.getTitle());
        holder.textCompany.setText(model.getCompany());

        Glide.with(context)
                .load(model.getImage())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Hi " + model.getName() + "," + model.getTitle() + "from" + model.getCompany(), Toast.LENGTH_SHORT).show();
        });

    }

    @NonNull
    @Override
    public FriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new FriendsHolder(view);
    }

    public class FriendsHolder extends RecyclerView.ViewHolder {

        private TextView textName;

        private CircleImageView imageView;

        private TextView textTitle;

        private TextView textCompany;

        public FriendsHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.title);
            textCompany = itemView.findViewById(R.id.company);

        }

    }

}
