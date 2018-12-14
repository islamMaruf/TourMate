package com.example.maruf.tourMateApplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {
    private Context context;
    private  List<EventCreate> eventCreateList;

    public EventAdapter(Context context, List<EventCreate> eventCreateList){
        this.context = context;
        this.eventCreateList = eventCreateList;

    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.event_list_recycler_view, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        EventCreate currentEvent = eventCreateList.get(position);
        holder.eventName.setText(currentEvent.getEventName());
        holder.fromDate.setText(currentEvent.getFromDate());
        holder.toDate.setText(currentEvent.getToDate());
        holder.estimateBudget.setText(currentEvent.getEstimatedBudget());



    }

    @Override
    public int getItemCount() {
        return eventCreateList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView eventName,fromDate,toDate,estimateBudget;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventDestinationTv);
            fromDate = itemView.findViewById(R.id.eventStartTv);
            toDate = itemView.findViewById(R.id.eventEndTv);
            estimateBudget = itemView.findViewById(R.id.estimateBudgetTv);



        }
    }
}
