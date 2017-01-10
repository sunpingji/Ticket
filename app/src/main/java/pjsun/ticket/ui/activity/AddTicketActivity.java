package pjsun.ticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.ui.activity.base.BaseActivity;

public class AddTicketActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, AddTicketActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        Ticket ticket = new Ticket();
        ticket.setUniqId("id");
        ticket.setNumber(5);
        ticket.setName("majiang");
        ticket.save();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Ticket> tickets = DataSupport.findAll(Ticket.class);
        if (tickets != null) {
            Log.d("tag", "ticket size " + tickets.size());
        }
    }
}
