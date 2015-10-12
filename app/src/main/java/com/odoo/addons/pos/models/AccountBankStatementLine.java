package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCurrency;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODate;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 9/29/2015.
 */
public class AccountBankStatementLine extends OModel {
    public static final String TAG = AccountBankStatementLine.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_bank_statement_line";

    OColumn account_id = new OColumn("Account", AccountAccount.class,OColumn.RelationType.ManyToOne);
    OColumn amount = new OColumn("Amount", OFloat.class);
    OColumn amount_currency = new OColumn("Amount Currency", OFloat.class);
    //OColumn bank_account_id = new OColumn("Bank Account", ResPartnerBank.class, OColumn.RelationType.ManyToOne);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Date", ODate.class);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn journal_entry_id = new OColumn("Journal Entry", AccountMove.class, OColumn.RelationType.ManyToOne);
    OColumn journal_id = new OColumn("Journal", AccountJournal.class,OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("Communication", OVarchar.class);
    OColumn note = new OColumn("Notes", OText.class);
    OColumn partner_id = new OColumn("Partner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn partner_name = new OColumn("Partner Name", OVarchar.class);
    OColumn pos_statement_id = new OColumn("unknown", PosOrder.class, OColumn.RelationType.ManyToOne);
    OColumn ref = new OColumn("Reference", OVarchar.class);
    OColumn sequence = new OColumn("Sequence", OInteger.class);
    OColumn statement_id = new OColumn("Statement",AccountBankStatement.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);

    public AccountBankStatementLine(Context context,OUser user) {
        super(context,"account.bank.statement.line", user);
    }
//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }

}

