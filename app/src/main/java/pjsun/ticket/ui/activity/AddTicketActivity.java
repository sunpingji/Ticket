package pjsun.ticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.utils.ToastUtils;

import pjsun.ticket.Constant;
import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.ui.activity.base.BaseActivity;

public class AddTicketActivity extends BaseActivity {

    public static void start(Context context, int ticketSize) {
        Intent intent = new Intent(context, AddTicketActivity.class);
        intent.putExtra(Constant.Extra.EXTRA_TICKET_SIZE, ticketSize);
        context.startActivity(intent);
    }

    private EditText nameEt;
    private EditText numberEt;
    private EditText desEt;
    private Button submitBtn;
    private int ticketSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        initViews();
        initData();
        initListeners();
    }

    private void initListeners() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                String number = numberEt.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
                    Ticket ticket = new Ticket();
                    ticket.setName(name);
                    ticket.setNumber(Integer.valueOf(number));
                    ticket.setDes(desEt.getText().toString());
                    ticket.setSequenceNumber(ticketSize + 1);
                    ticket.save();
                    finish();
                } else {
                    ToastUtils.showShortToast("something wrong");
                }
            }
        });
    }

    private void initData() {
        ticketSize = getIntent().getIntExtra(Constant.Extra.EXTRA_TICKET_SIZE, 0);
    }

    private void initViews() {
        nameEt = (EditText) findViewById(R.id.ticket_name);
        numberEt = (EditText) findViewById(R.id.ticket_number);
        desEt = (EditText) findViewById(R.id.ticket_des);
        submitBtn = (Button) findViewById(R.id.btn_submit);
    }

}
