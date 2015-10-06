package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 7/21/2015.
 */
public class PosOrder extends OModel {
    public static final String TAG = PosOrder.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.pos_order";
    OColumn account_move = new OColumn("Journal Entry", AccountMove.class, OColumn.RelationType.ManyToOne);
    OColumn amount_paid = new OColumn("Paid", OFloat.class);
    OColumn amount_return = new OColumn("unknown", OFloat.class);
    OColumn amount_tax = new OColumn("Taxes", OFloat.class);
    OColumn amount_total = new OColumn("Total", OFloat.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn date_order = new OColumn("Order Date", ODateTime.class);
    OColumn id = new OColumn("Id", OInteger.class);
    OColumn invoice_id = new OColumn("Invoice", AccountInvoice.class, OColumn.RelationType.ManyToOne);
    OColumn lines = new OColumn("Order Lines", PosOrderLine.class, OColumn.RelationType.OneToMany);
    OColumn location_id = new OColumn("Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("Order Ref", OVarchar.class);
    OColumn nb_print = new OColumn("Number of Prints", OInteger.class);
    OColumn note = new OColumn("Internal Notes", OText.class);
    OColumn partner_id = new OColumn("Customer", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn picking_id = new OColumn("Picking", StockPicking.class, OColumn.RelationType.ManyToOne);
    OColumn picking_type_id = new OColumn("Picking Type", StockPickingType.class, OColumn.RelationType.ManyToOne);
    OColumn pos_refrence = new OColumn("Receipt Ref", OVarchar.class);
    OColumn pricelist_id = new OColumn("Pricelist", ProductPricelist.class, OColumn.RelationType.ManyToOne);
    OColumn sale_journal = new OColumn("Sale Journal", AccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn sequence_no = new OColumn("Sequence Number", OInteger.class);
    OColumn session_id = new OColumn("Session", PosSession.class, OColumn.RelationType.ManyToOne);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn statement_ids = new OColumn("Payments", AccountBankStatementLine.class, OColumn.RelationType.OneToMany);
    OColumn user_id = new OColumn("Salesman", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Update on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Update by", ResUsers.class, OColumn.RelationType.ManyToOne);

    public PosOrder(Context context, OUser user) {
        super(context, "pos.order", user);
    }
    /*public static ODataRow getOrder(Context context) {
        posOrder order = new posOrder(context, null);
        int row_id = order.selectRowId(Integer.parseInt(order.getUser().getCompany_id()));
       return order.browse(row_id).getM2ORecord("currency_id").browse();
        return
    }
*/
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}






