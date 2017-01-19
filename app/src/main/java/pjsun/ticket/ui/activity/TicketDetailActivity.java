package pjsun.ticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.utils.ToastUtils;

import pjsun.ticket.Constant;
import pjsun.ticket.R;
import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.business.helper.TicketHelper;
import pjsun.ticket.business.helper.TicketHistoryHelper;
import pjsun.ticket.ui.activity.base.BaseActivity;
import pjsun.ticket.ui.fragment.TicketHistoryFragment;

public class TicketDetailActivity extends BaseActivity implements View.OnClickListener {

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
    private Ticket oldTicket;
    private Ticket newTicket;
    private long id;

    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        initViews();
        initData();
        initListeners();
    }

    private void updateFragment() {
        getFragmentManager().beginTransaction().replace(R.id.ticket_history_container, new TicketHistoryFragment()).commitAllowingStateLoss();
    }

    private void initListeners() {
        submitBtn.setOnClickListener(this);
    }

    private void initData() {
        oldTicket = (Ticket) getIntent().getSerializableExtra(Constant.Extra.EXTRA_TICKET);
        id = getIntent().getLongExtra(Constant.Extra.EXTRA_TICKET_OBJ_ID, 0);
        newTicket = new Ticket();
        nameEt.setText(oldTicket.getName());
        numberEt.setText(String.valueOf(oldTicket.getNumber()));
        if (!TextUtils.isEmpty(oldTicket.getDes())) {
            desEt.setText(oldTicket.getDes());
        }
        setEditMode(false);
    }

    private void initViews() {
        nameEt = (EditText) findViewById(R.id.ticket_name);
        numberEt = (EditText) findViewById(R.id.ticket_number);
        desEt = (EditText) findViewById(R.id.ticket_des);
        submitBtn = (Button) findViewById(R.id.btn_submit);
        updateFragment();
    }

    private void setEditMode(boolean flag) {
        isEditMode = flag;
        setTextEditable();
        setBtnText();
    }

    private void setBtnText() {
        if (isEditMode) {
            submitBtn.setText(getString(R.string.complete));
        } else {
            submitBtn.setText(getString(R.string.use));
        }
    }

    private void setTextEditable() {
        nameEt.setEnabled(isEditMode);
        numberEt.setEnabled(isEditMode);
        desEt.setEnabled(isEditMode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            setEditMode(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                handleSubmitClick();
                break;
        }
    }

    private void handleSubmitClick() {
        if (updateTicket(!isEditMode)) {
            updateFragment();
            ToastUtils.showShortToast(isEditMode ? "edit ticket success" : "use ticket success");
            setEditMode(false);
        }
    }

    /***
     * if not changed or change not success will return false
     *
     * @param use
     * @return true when successfully changed
     */
    private boolean updateTicket(boolean use) {
        String name = nameEt.getText().toString();
        String number = numberEt.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
            int num = Integer.valueOf(number);
            if (use && num < 1) {
                return false;
            }
            if (use) {
                num--;
                numberEt.setText(String.valueOf(num));
            }
            newTicket.setName(name);
            newTicket.setNumber(num);
            newTicket.setDes(desEt.getText().toString());
            if (TicketHelper.hasTicketChanged(oldTicket, newTicket)) {
                newTicket.update(id);
                TicketHistoryHelper.addTicketEditHistory(oldTicket, newTicket);
                oldTicket = Ticket.deepCopy(newTicket);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
