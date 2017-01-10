package pjsun.ticket.ui.activity.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.ui.activity.view.controller.ItemTouchHelperClass;

/**
 * Created by sunpi on 2016/12/23.
 */

public class TicketMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperClass.ItemTouchHelperAdapter {

    private List<Ticket> tickets = new ArrayList<>();

    public TicketMainAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void refresh(List<Ticket> tickets){
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TicketHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_main_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TicketHolder ticketHolder = (TicketHolder) holder;
        Ticket ticket = tickets.get(position);
        String name = TextUtils.isEmpty(ticket.getName())?"a":ticket.getName().substring(0,1);
        ticketHolder.tvName.setText(ticket.getName());
        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .toUpperCase()
                .endConfig()
                .buildRound(name.substring(0,1),Color.RED);
        ticketHolder.ivIcon.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        if(fromPosition<toPosition){
            for(int i=fromPosition; i<toPosition; i++){
                Collections.swap(tickets, i, i+1);
            }
        }
        else{
            for(int i=fromPosition; i > toPosition; i--){
                Collections.swap(tickets, i, i-1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemRemoved(int position) {

    }

    public static class TicketHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivIcon;

        TicketHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        }
    }
}
