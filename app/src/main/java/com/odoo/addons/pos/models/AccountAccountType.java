package com.odoo.addons.pos.models;

import android.content.Context;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCurrency;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 10/8/2015.
 */
public class AccountAccountType extends OModel {
    public static final String TAG = AccountAccountType.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_account_type";


    OColumn code = new OColumn("Code", OVarchar.class);
    OColumn close_method = new OColumn("Deferral Method", OSelection.class);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn name = new OColumn("Account Type", OVarchar.class);
    OColumn note = new OColumn("Description", OText.class);
    OColumn report_type = new OColumn("P&L / BS Category", OSelection.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);


    public AccountAccountType(Context context, OUser user) {
        super(context, "account.account.type", user);
    }

}



