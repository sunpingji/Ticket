package pjsun.ticket.business.bean;

import org.litepal.annotation.Column;

import java.io.Serializable;

import pjsun.ticket.business.bean.base.BaseBean;

/**
 * Created by sunpingji on 2016/12/22.
 */

public class Ticket extends BaseBean implements Serializable {

    private String uniqId;

    private String time;

    private String name;

    private String icon;

    private String des;

    private int number;

    private int sequenceNumber;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
