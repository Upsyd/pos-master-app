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
public class PosAccountJournal  extends OModel {

    public static final String TAG = PosAccountJournal.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_journal";



   OColumn account_control_ids = new OColumn("Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn allow_date = new OColumn("Check Date in Period", OBoolean.class);
    OColumn create_date = new OColumn("Creation on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
 //   OColumn amount_authorized_diff = new OColumn("Amount Authorized Difference", account.analytic.journal.class, OColumn.RelationType.ManyToOne);
   // OColumn analytic_journal_id = new OColumn("Analytic Journal", account.analytic.journal.class, OColumn.RelationType.ManyToOne);
   // OColumn cashbox_line_ids= new OColumn("CashBox", account.journal.cashbox.line.class, OColumn.RelationType.OneToMany);
    OColumn cash_control = new OColumn("Cash Control", OBoolean.class);
    OColumn centralisation= new OColumn("Centralized Counterpart", OBoolean.class);
    OColumn code = new OColumn("Code", OVarchar.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
   OColumn default_credit_account_id = new OColumn("Default Credit Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn currency = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn default_debit_account_id = new OColumn("Default Debit Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn entry_posted = new OColumn("Autopost Created Moves", OBoolean.class);
    //    OColumn putaway_strategy_id = new OColumn("Put Away Strategy", product.putaway.class,OColumn.RelationType.ManyToOne);
//    OColumn removal_strategy_id = new OColumn("Removal Strategy", product.removal.class,OColumn.RelationType.ManyToOne);
    OColumn group_invoice_lines = new OColumn("Group invoice Lines", OBoolean.class);
 //   OColumn groups_id = new OColumn("Groups", res.group.class, OColumn.RelationType.ManyToMany);
      OColumn internal_account_id= new OColumn("Internal Transfers Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
     OColumn valuation_out_account_id = new OColumn("Stock Valuation Account (Outgoing)", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn journal_user = new OColumn("PoS Payment Method",OBoolean.class);
    OColumn loss_account_id = new OColumn("Loss Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("Journal Name", OVarchar.class);
   OColumn profit_account_id = new OColumn("Profit Account",AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn self_checkout_payment_method = new OColumn("Self Checkout Payment Method", OBoolean.class);
//    OColumn sequence_id = new OColumn("Entry Sequence", ir.sequence.class, OColumn.RelationType.ManyToOne);
    OColumn type = new OColumn("Type", OSelection.class);
 // OColumn type_control_ids = new OColumn("Type Controls",account.account.type.class, OColumn.RelationType.ManyToMany);
    OColumn update_posted = new OColumn("Allow Cancelling Entries", OBoolean.class);
    OColumn user_id = new OColumn("User", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn with_last_closing_balance = new OColumn("Opening With Last Closing Balance", OBoolean.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class,OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", Integer.class);







    public PosAccountJournal(Context context, OUser user) {
        super(context, "account.journal", user);
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}


