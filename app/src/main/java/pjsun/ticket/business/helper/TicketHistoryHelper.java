package pjsun.ticket.business.helper;

import com.blankj.utilcode.utils.TimeUtils;

import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.business.bean.TicketHistory;

/**
 * Created by sunpingji on 2017/1/18.
 */

public class TicketHistoryHelper {

    public static void addTicketCreateHistory(Ticket ticket) {
        TicketHistory ticketHistory = new TicketHistory();
        ticketHistory.setName(ticket.getName());
        ticketHistory.setNumber(ticket.getNumber());
        ticketHistory.setTime(TimeUtils.getNowTimeString());
        ticketHistory.setReason("add");
        ticketHistory.setUniqId(ticket.getUniqId());
        ticketHistory.save();
    }

    public static void addicketEditHistory(Ticket oldTicket, Ticket newTicket) {
        TicketHistory ticketHistory = new TicketHistory();
        ticketHistory.setName(newTicket.getName());
        ticketHistory.setNumber(newTicket.getNumber() - oldTicket.getNumber());
        ticketHistory.setTime(TimeUtils.getNowTimeString());
        ticketHistory.setUniqId(newTicket.getUniqId());
        ticketHistory.setReason("edit");
        ticketHistory.save();
    }

    public static void addTicketUseHistory(Ticket ticket) {
        TicketHistory ticketHistory = new TicketHistory();
        ticketHistory.setName(ticket.getName());
        ticketHistory.setNumber(1);
        ticketHistory.setTime(TimeUtils.getNowTimeString());
        ticketHistory.setReason("use");
        ticketHistory.setUniqId(ticket.getUniqId());
        ticketHistory.save();
    }
}
