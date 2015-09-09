package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCurrency;
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
 * Created by My on 7/21/2015.
 */
public class PosSession extends OModel {


    public static final String TAG = PosSession.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.pos_session";


    OColumn cash_control = new OColumn("Has Cash Control", OBoolean.class);
//    OColumn cash_journal_id = new OColumn("Cash Journal", CashJournal.class, OColumn.RelationType.ManyToOne);
    OColumn cash_register_balance_end = new OColumn("Theoretical Closing Balance", OFloat.class);
    OColumn cash_register_balance_end_real = new OColumn("Ending Balance", OFloat.class);
    OColumn cash_register_balance_start = new OColumn("Starting Balance", OFloat.class);
    OColumn cash_register_difference = new OColumn("Difference", OFloat.class);

//    OColumn case_regisgter_id = new OColumn("Cash Register", CashRegister.class, OColumn.RelationType.ManyToOne);
    OColumn cash_register_total_entry_encoding = new OColumn("Total Cash Transaction", OFloat.class);
    OColumn id = new OColumn("ID", OInteger.class);
//    OColumn config_id = new OColumn("Point of Sale", PointOfSale.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
//    OColumn details_ids = new OColumn("Cash Control", CashControl.class, OColumn.RelationType.ManyToOne);
//    OColumn journal_ids = new OColumn("Available Payment Methods", PaymentMethods.class, OColumn.RelationType.ManyToMany);
    OColumn login_number = new OColumn("Login Sequence Number", OInteger.class);
    OColumn name = new OColumn("Name", OVarchar.class);
//    OColumn opening_details_ids = new OColumn("Opening Cash Control", OpeningCashControl.class, OColumn.RelationType.OneToMany);
    OColumn order_ids = new OColumn("Orders", PosOrder.class, OColumn.RelationType.OneToMany);
    OColumn sequence_number = new OColumn("Order Sequence Number", OInteger.class);
    OColumn start_at = new OColumn("Opening Date", ODateTime.class);
    OColumn state = new OColumn("Status", OSelection.class);
//    OColumn statement_ids = new OColumn("BankStatement", BankStatement.class, OColumn.RelationType.OneToMany);
    OColumn stop_at = new OColumn("Closing Date", ODateTime.class);

    OColumn user_id = new OColumn("Responsible",ResUsers.class,OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);


    public PosSession(Context context, OUser user) {
        super(context, "pos.session", user);}
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}

