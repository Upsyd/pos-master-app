package com.odoo.addons.pos.models;

import android.content.Context;

import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 10/9/2015.
 */
public class AccountCashboxLine  extends OModel{
    public static final String TAG = AccountCashboxLine.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_cashbox_line";




    OColumn active = new OColumn("unknown", AccountBankStatement.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn number_closing = new OColumn("Number of Units", OInteger.class);
    OColumn number_opening = new OColumn("Number of Units", OInteger.class);
    OColumn pieces = new OColumn("Unit of Currency", OFloat.class);
    OColumn subtotal_closing = new OColumn("Closing Subtotal", OFloat.class);
    OColumn subtotal_opening = new OColumn("Opening Subtotal", OFloat.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);



    public AccountCashboxLine(Context context, OUser user) {
        super(context, "account.cashbox.line", user);
    }
}
