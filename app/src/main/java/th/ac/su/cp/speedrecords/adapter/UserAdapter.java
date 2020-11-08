package th.ac.su.cp.speedrecords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.cp.speedrecords.R;
import th.ac.su.cp.speedrecords.model.Speed;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private Speed[] mSpeeds;

    public UserAdapter(Context context, Speed[] speeds) {
        this.mContext = context;
        this.mSpeeds = speeds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Speed speed = mSpeeds[position];

        holder.speedTextView.setText(speed.speed+" KM/H");
        holder.detailsTextView.setText(speed.distance+" METERS, "+speed.duration+" SECONDS");
        if(speed.overLimit){
            holder.cowImageView.setImageResource(R.drawable.red_cow);
        }

    }

    @Override
    public int getItemCount() {
        return mSpeeds.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView speedTextView;
        TextView detailsTextView;
        ImageView cowImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speedTextView = itemView.findViewById(R.id.speed_text_view);
            this.detailsTextView = itemView.findViewById(R.id.detail_text_view);
            this.cowImageView = itemView.findViewById(R.id.cow_image_view);

        }
    }
}

