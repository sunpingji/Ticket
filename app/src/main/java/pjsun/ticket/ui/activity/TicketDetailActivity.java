package pjsun.ticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.utils.ToastUtils;

import pjsun.ticket.Constant;
import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.ui.activity.base.BaseActivity;

public class TicketDetailActivity extends BaseActivity {

    public static void start(Context context, Ticket ticket, long id) {
        Intent intent = new Intent(context, TicketDetailActivity.class);
        intent.putExtra(Constant.Extra.EXTRA_TICKET, ticket);
        intent.putExtra(Constant.Extra.EXTRA_TICKET_OBJ_ID, id);
        context.startActivity(intent);
    }

    private EditText nameEt;
    private EditText numberEt;
    private EditText desEt;
    private Button submitBtn;
    private Ticket ticket;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
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
                    ticket.setName(name);
                    ticket.setNumber(Integer.valueOf(number));
                    ticket.setDes(desEt.getText().toString());
                    ticket.update(id);
                    finish();
                } else {
                    ToastUtils.showShortToast("something wrong");
                }
            }
        });
    }

    private void initData() {
        ticket = (Ticket) getIntent().getSerializableExtra(Constant.Extra.EXTRA_TICKET);
        id = getIntent().getLongExtra(Constant.Extra.EXTRA_TICKET_OBJ_ID, 0);
        nameEt.setText(ticket.getName());
        numberEt.setText(String.valueOf(ticket.getNumber()));
        if (!TextUtils.isEmpty(ticket.getDes())) {
            desEt.setText(ticket.getDes());
        }
    }

    private void initViews() {
        nameEt = (EditText) findViewById(R.id.ticket_name);
        numberEt = (EditText) findViewById(R.id.ticket_number);
        desEt = (EditText) findViewById(R.id.ticket_des);
        submitBtn = (Button) findViewById(R.id.btn_submit);
    }

}
