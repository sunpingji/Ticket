package pjsun.ticket.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.business.comparator.TicketSeqComparator;
import pjsun.ticket.ui.activity.adapter.TicketMainAdapter;
import pjsun.ticket.ui.activity.base.BaseActivity;
import pjsun.ticket.ui.activity.view.controller.ItemTouchHelperClass;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TicketMainAdapter ticketMainAdapter;
    List<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListeners();
    }

    private void initListeners() {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tickets = DataSupport.findAll(Ticket.class);
        Collections.sort(tickets, new TicketSeqComparator());
        ticketMainAdapter.refresh(tickets);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            ticket.setSequenceNumber(i);
            ticket.save();
        }
    }

    private void initData() {
        tickets = DataSupport.findAll(Ticket.class);
        Collections.sort(tickets, new TicketSeqComparator());
        ticketMainAdapter = new TicketMainAdapter(tickets, new TicketMainAdapter.IViewHolderClickListener() {
            @Override
            public void OnItemClick(int pos) {
                TicketDetailActivity.start(MainActivity.this, tickets.get(pos));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ItemTouchHelper.Callback callback = new ItemTouchHelperClass(ticketMainAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ticketMainAdapter);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTicketActivity.start(MainActivity.this, tickets.size());
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.ticket_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
