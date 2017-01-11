package pjsun.ticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pjsun.ticket.Constant;
import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.ui.activity.base.BaseActivity;

public class TicketDetailActivity extends BaseActivity {

    public static void start(Context context, Ticket ticket) {
        Intent intent = new Intent(context, TicketDetailActivity.class);
        intent.putExtra(Constant.Extra.EXTRA_TICKET,ticket);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
