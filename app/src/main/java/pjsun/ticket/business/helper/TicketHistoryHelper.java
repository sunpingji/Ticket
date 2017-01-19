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

    public static void addTicketEditHistory(Ticket oldTicket, Ticket newTicket) {
        TicketHistory ticketHistory = new TicketHistory();
        ticketHistory.setName(newTicket.getName());
        ticketHistory.setNumber(newTicket.getNumber() - oldTicket.getNumber());
        ticketHistory.setTime(TimeUtils.getNowTimeString());
        ticketHistory.setUniqId(newTicket.getUniqId());
        ticketHistory.setReason(TicketHelper.getTicketChangedReason(oldTicket, newTicket));
        ticketHistory.save();
    }

}
