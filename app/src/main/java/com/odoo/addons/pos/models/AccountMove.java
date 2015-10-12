package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODate;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 9/30/2015.
 */
public class AccountMove extends OModel {
    public static final String TAG =AccountMove.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_move";
    OColumn amount = new OColumn("Amount",OFloat.class);
    OColumn balance = new OColumn("balance",OFloat.class);
    OColumn company_id= new OColumn("Company",ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on",ODateTime.class);
    OColumn create_uid = new OColumn("Created by",ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Date",ODate.class);
    OColumn id = new OColumn("Id",OInteger.class);
    OColumn journal_id = new OColumn("Journal",AccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn line_id = new OColumn("Entries",AccountMoveLine.class, OColumn.RelationType.OneToMany);
    OColumn name = new OColumn("Number",OVarchar.class);
    OColumn narration = new OColumn("Internal Note",OText.class);
    OColumn partner_id = new OColumn("Partner",ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn period_id = new OColumn("Period",AccountPeriod.class, OColumn.RelationType.ManyToOne);
    OColumn ref = new OColumn("Reference",OVarchar.class);
    OColumn to_check = new OColumn("To Review",OBoolean.class);
    OColumn write_date= new OColumn("Last Updated on",ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by",ResUsers.class, OColumn.RelationType.ManyToOne);

    public AccountMove(Context context, OUser user) {
        super(context, "account.move", user);}

}
