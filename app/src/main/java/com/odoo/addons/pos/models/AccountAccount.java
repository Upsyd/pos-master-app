package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCurrency;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODate;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 10/1/2015.
 */
public class AccountAccount  extends OModel {
    public static final String TAG = AccountAccount.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_account";

    OColumn active = new OColumn("Active", OBoolean.class);
    OColumn adjusted_balance = new OColumn("Adjusted Balance", OFloat.class);
    OColumn balance = new OColumn("balance", OFloat.class);
    OColumn child_consol_ids = new OColumn("child_consol_ids", AccountAccount.class, OColumn.RelationType.ManyToMany);
    OColumn child_id = new OColumn("Child Accounts", AccountAccount.class, OColumn.RelationType.ManyToMany);
    OColumn child_parent_ids = new OColumn("Children", AccountAccount.class, OColumn.RelationType.OneToMany);
    OColumn company_currency_id = new OColumn("Company Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn code = new OColumn("Code", OVarchar.class);
    OColumn credit = new OColumn("Credit", OFloat.class);
    OColumn currency_id = new OColumn("Secondary Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn currency_mode = new OColumn("Outgoing Currencies Rate", OSelection.class);
    OColumn debit = new OColumn("Debit", OFloat.class);
     OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
   OColumn level = new OColumn("Level", OInteger.class);
    OColumn id = new OColumn("ID", OInteger.class);
 //   OColumn financial_report_ids = new OColumn("Financial Reports", account.financial.report.class, OColumn.RelationType.ManyToMany);
    OColumn foreign_balance = new OColumn("Foreign Balance", OFloat.class);
    OColumn name = new OColumn("Name", OVarchar.class);
    OColumn note = new OColumn("Internal Notes", OText.class);
    OColumn exchange_rate = new OColumn("Exchange Rate", OFloat.class);
    OColumn  parent_id = new OColumn("Parent", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn parent_left = new OColumn("Parent Left", OInteger.class);
    OColumn parent_right = new OColumn("Parent Right", OInteger.class);
    OColumn reconcile = new OColumn("Allow Reconciliation", OBoolean.class);
    OColumn shortcut = new OColumn("Shortcut", OVarchar.class);
   // OColumn tax_ids = new OColumn("Default Taxes", AccountTax.class, OColumn.RelationType.ManyToMany);
   // OColumn  user_type = new OColumn("Account Type", account.account.type.class, OColumn.RelationType.ManyToOne);
    OColumn type = new OColumn("Internal Type", OSelection.class);
    OColumn unrealized_gain_loss = new OColumn("Unrealized Gain or Loss", OFloat.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);







    public AccountAccount(Context context,OUser user) {
        super(context,"account.account", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}


