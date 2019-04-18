package com.example.karuna.sdpd_project;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return  new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Put all the logic, access the holder view to update its data
        // Call holder.vehicles to access the ViewHolder vehicles view here
        ListItem listItem = listItems.get(position);
        holder.heading.setText(listItem.getHeading());
        holder.desc.setText(listItem.getDescription());

    }

    @Override
    public int getItemCount()
    {
        return  listItems.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView heading;
        public TextView desc;
        public ViewHolder(View itemView) {
            super(itemView);
            heading = (TextView)itemView.findViewById(R.id.HeadingTextView);
            desc = (TextView)itemView.findViewById(R.id.descTextView);
        }

    }
}
