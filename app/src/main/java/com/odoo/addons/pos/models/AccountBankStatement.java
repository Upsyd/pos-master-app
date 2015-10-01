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
 * Created by My on 9/30/2015.
 */
public class AccountBankStatement extends OModel {
    public static final String TAG = AccountBankStatement.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_bank_statement";

       OColumn account_id = new OColumn("Account used in this journal", AccountAccount.class,OColumn.RelationType.ManyToOne);
    OColumn all_lines_reconciled = new OColumn("All lines reconciled", OBoolean.class);
    OColumn amount_currency = new OColumn("Amount Currency", OFloat.class);
    OColumn balance_end = new OColumn("Computed Balance", OFloat.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn balance_end_real = new OColumn("Ending Balance", OFloat.class);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn balance_start = new OColumn("Starting Balance", OFloat.class);
    OColumn journal_id = new OColumn("Journal", PosAccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn cash_control = new OColumn("Cash control", PosAccountJournal.class);
    OColumn closing_date = new OColumn("Closed On", ODateTime.class);
   // OColumn closing_details_ids = new OColumn("Closing Cashbox Lines", account.cashbox.line.class, OColumn.RelationType.OneToMany);
    OColumn date = new OColumn("Date", ODate.class);
   // OColumn details_ids = new OColumn("CashBox Lines", account.cashbox.line.class, OColumn.RelationType.OneToMany);
    OColumn difference = new OColumn("Difference", OFloat.class);
    OColumn last_closing_balance = new OColumn("Last Closing Balance", OFloat.class);
    OColumn line_ids = new OColumn("Statement lines", AccountBankStatementLine.class, OColumn.RelationType.OneToMany);
    OColumn message_follower_ids= new OColumn("Followers", ResPartner.class, OColumn.RelationType.ManyToMany);
    //OColumn message_ids = new OColumn("Messages",MailMessege.class, OColumn.RelationType.OneToMany);
    OColumn message_is_follower = new OColumn("Is a Follower", OBoolean.class);
    OColumn message_last_post = new OColumn("Last Message Date", ODateTime.class);
    OColumn message_summary = new OColumn("Summary", OText.class);
    OColumn message_unread = new OColumn("Unread Messages", OBoolean.class);
   // OColumn move_line_ids = new OColumn("Entry lines", account.move.line.class, OColumn.RelationType.OneToMany);
    OColumn name = new OColumn("Reference", OVarchar.class);
   // OColumn opening_details_ids = new OColumn("Opening Cashbox Lines", account.cashbox.line.class, OColumn.RelationType.OneToMany);
    OColumn period_id = new OColumn("Period", AccountPeriod.class, OColumn.RelationType.ManyToOne);
    OColumn pos_session_id = new OColumn("unknown", PosSession.class);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn total_entry_encoding = new OColumn("Total Transactions", OFloat.class);
    OColumn user_id = new OColumn("User", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);







    public AccountBankStatement(Context context,OUser user) {
        super(context,"account.bank.statement", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}


