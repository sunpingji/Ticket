package pjsun.ticket.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.List;

import pjsun.ticket.R;
import pjsun.ticket.business.bean.TicketHistory;
import pjsun.ticket.ui.adapter.TicketHistoryAdapter;
import pjsun.ticket.ui.fragment.base.BaseFragment;

/**
 * Created by sunpingji on 2017/1/18.
 */

public class TicketHistoryFragment extends BaseFragment {

    private RecyclerView ticketHistory;
    private List<TicketHistory> ticketHistoryList;
    private TicketHistoryAdapter ticketHistoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ticket_history_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketHistory = (RecyclerView) view.findViewById(R.id.ticket_history);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        ticketHistoryList = DataSupport.findAll(TicketHistory.class);

        ticketHistoryAdapter = new TicketHistoryAdapter(ticketHistoryList);
        ticketHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        ticketHistory.setHasFixedSize(true);
        ticketHistory.setItemAnimator(new DefaultItemAnimator());
        ticketHistory.setAdapter(ticketHistoryAdapter);
    }

}
