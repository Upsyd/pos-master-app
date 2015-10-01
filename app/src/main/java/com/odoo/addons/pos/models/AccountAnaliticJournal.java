package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 10/1/2015.
 */
public class AccountAnaliticJournal extends OModel {
    public static final String TAG =AccountAnaliticJournal.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_analytic_journal";


    OColumn active = new OColumn("Active",OBoolean.class);
    OColumn code = new OColumn("Journal Code",OVarchar.class);
    OColumn company_id = new OColumn("Company",ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on",ODateTime.class);
    OColumn create_uid = new OColumn("Created by",ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID",OInteger.class);
    //OColumn line_ids = new OColumn("Lines",AccountAnaliticLine.class, OColumn.RelationType.OneToMany);
    OColumn name = new OColumn("Journal Name",OVarchar.class);
    OColumn type = new OColumn("Type",OSelection.class);
    OColumn write_date = new OColumn("Last Updated on",ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by",ResUsers.class, OColumn.RelationType.ManyToOne);

    public AccountAnaliticJournal(Context context, OUser user) {
        super(context,"account.analytic.journal", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
