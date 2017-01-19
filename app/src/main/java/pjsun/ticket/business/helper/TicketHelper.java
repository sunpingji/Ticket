package pjsun.ticket.business.helper;

import android.text.TextUtils;

import com.blankj.utilcode.utils.TimeUtils;

import pjsun.ticket.business.bean.Ticket;
import pjsun.ticket.business.bean.TicketHistory;

/**
 * Created by sunpingji on 2017/1/18.
 */

public class TicketHelper {

    public static String getTicketChangedReason(Ticket oldTicket, Ticket newTicket) {
        if (!hasTicketChanged(oldTicket, newTicket)) {
            return null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.equals(oldTicket.getName(), newTicket.getName())) {
                stringBuilder.append("name changed ");
            }
            if (oldTicket.getNumber() != newTicket.getNumber()) {
                stringBuilder.append("number changed " + (oldTicket.getNumber() - newTicket.getNumber()));
            }
            if (!TextUtils.equals(oldTicket.getDes(), newTicket.getDes())) {
                stringBuilder.append("des changed");
            }
            return stringBuilder.toString();
        }
    }


    public static boolean hasTicketChanged(Ticket oldTicket, Ticket newTicket) {
        if (TextUtils.equals(oldTicket.getName(), newTicket.getName())
                && oldTicket.getNumber() == newTicket.getNumber()
                && TextUtils.equals(oldTicket.getDes(), newTicket.getDes())) {
            return false;
        } else {
            return true;
        }
    }

}
