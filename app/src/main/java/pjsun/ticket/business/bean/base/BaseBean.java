package pjsun.ticket.business.bean.base;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by sunpingji on 2016/12/22.
 */

public class BaseBean extends DataSupport implements Serializable{

    @Override
    public long getBaseObjId() {
        return super.getBaseObjId();
    }
}
