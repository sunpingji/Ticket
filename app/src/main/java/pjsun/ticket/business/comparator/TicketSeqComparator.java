package pjsun.ticket.business.comparator;

import java.util.Comparator;

import pjsun.ticket.business.bean.Ticket;

/**
 * Created by sunpingji on 2017/1/11.
 */

public class TicketSeqComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getSequenceNumber() - o2.getSequenceNumber();
    }
}
