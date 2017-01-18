package pjsun.ticket.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;

import pjsun.ticket.R;
import pjsun.ticket.business.bean.TicketHistory;

/**
 * Created by sunpingji on 2017/1/18.
 */

public class TicketHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<TicketHistory> ticketHistoryList = new ArrayList<TicketHistory>();


    public TicketHistoryAdapter(List<TicketHistory> ticketHistoryList) {
        this.ticketHistoryList = ticketHistoryList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TicketHistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_main_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TicketHistoryHolder ticketHistoryHolder = (TicketHistoryHolder) holder;
        TicketHistory ticketHistory = ticketHistoryList.get(position);
        String name = TextUtils.isEmpty(ticketHistory.getName()) ? "a" : ticketHistory.getName().substring(0, 1);
        ticketHistoryHolder.tvName.setText(ticketHistory.getName());
        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .toUpperCase()
                .endConfig()
                .buildRound(name.substring(0, 1), Color.RED);
        ticketHistoryHolder.ivIcon.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return ticketHistoryList.size();
    }

    protected class TicketHistoryHolder extends RecyclerView.ViewHolder {
        LinearLayout listItemLinearLayout;
        ImageView ivIcon;
        TextView tvName;
        TextView tvTime;

        public TicketHistoryHolder(View view) {
            super(view);
            listItemLinearLayout = (LinearLayout) view.findViewById(R.id.listItemLinearLayout);
            ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
        }
    }


}


