package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODate;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 9/30/2015.
 */
public class AccountPeriod  extends OModel {

    public static final String TAG = AccountPeriod.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_period";


    OColumn id = new OColumn("ID",Integer.class); //OVarchar.class).setSize(100);
    OColumn name = new OColumn("Line No", OVarchar.class).setSize(100);
    OColumn company_id = new OColumn("Company",  ResCompany.class,OColumn.RelationType.ManyToOne);
    OColumn discount = new OColumn("Discount (%)", OFloat.class);
    OColumn code = new OColumn("Code", OVarchar.class);
    OColumn date_start = new OColumn("Start of Period", ODate.class);
    OColumn date_stop = new OColumn("End of Period", ODate.class);
   // OColumn fiscalyear_id = new OColumn("Fiscal Year", account.fiscalyear.class, OColumn.RelationType.ManyToOne);
    OColumn special = new OColumn("Opening/Closing Period", OBoolean.class);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class,OColumn.RelationType.ManyToOne);






    public AccountPeriod(Context context, OUser user) {
        super(context, "account.period", user);
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}

