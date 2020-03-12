package com.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.Model.IModel;
import com.assignment.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


    private Context context;
    //private List<ImageModel> imagesList;
    private List<IModel> imagesList;

    public ImageAdapter(Context context,List<IModel> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_single_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IModel model = imagesList.get(position);

        holder.name.setText(model.getName());
        Glide.with(context)
                .load(model.getImage())
                .centerCrop()
                .into(holder.mainImage);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mainImage;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainImage = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.imageName);
        }
    }
}
