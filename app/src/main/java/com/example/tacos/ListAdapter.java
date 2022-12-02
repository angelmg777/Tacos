package com.example.tacos;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<ClaseOrden> mData;
    private LayoutInflater inflater;
    private Context context;
    final ListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ClaseOrden item);
    }



    public ListAdapter(List<ClaseOrden> ordenes,Context context, ListAdapter.OnItemClickListener listener) {
        this.inflater= LayoutInflater.from(context);
        this.context=context;
        this.mData=ordenes;
        this.listener=listener;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listelements, null);

        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems(List<ClaseOrden> items){mData = items;}


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView IconImage;
        TextView name;

        ViewHolder(View itemView){
            super(itemView);
           // IconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);

        }


        void bindData(final ClaseOrden item){
           // IconImage.setColorFilter(Color.parseColor("009688"), PorterDuff.Mode.SRC_IN);
            String xls = Integer.toString(item.getId());
            name.setText("ID de la orden:"+xls);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}


