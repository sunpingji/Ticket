package pjsun.ticket.business.bean;

import java.io.Serializable;

import pjsun.ticket.business.bean.base.BaseBean;

/**
 * Created by sunpingji on 2017/1/18.
 */

public class TicketHistory extends BaseBean implements Serializable {

    private String name;

    private int number;

    private String reason;

    private String uniqId;

    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
