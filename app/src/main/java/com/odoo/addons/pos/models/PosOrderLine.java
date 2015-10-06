package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 9/29/2015.
 */
public class PosOrderLine extends OModel {

    public static final String TAG = PosOrderLine.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.pos_order_line";


    OColumn id = new OColumn("ID",Integer.class); //OVarchar.class).setSize(100);
    OColumn name = new OColumn("Line No", OVarchar.class).setSize(100);
    OColumn company_id = new OColumn("Company",  ResCompany.class,OColumn.RelationType.ManyToOne);
    OColumn discount = new OColumn("Discount (%)", OFloat.class);
    OColumn notice = new OColumn("Discount Notice", OVarchar.class);
    OColumn order_id = new OColumn("Order Ref", PosOrder.class,OColumn.RelationType.ManyToOne);
    OColumn price_subtotal = new OColumn("Subtotal w/o Tax", OFloat.class);
    OColumn price_subtotal_incl = new OColumn("Subtotal", OFloat.class);
    OColumn price_unit = new OColumn("Unit Price", OFloat.class);
    OColumn create_date = new OColumn("Creation Date", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn qty = new OColumn("Quantity", OFloat.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class,OColumn.RelationType.ManyToOne);
 // ********************* Requird ProductProduct ***************//
   OColumn product_id = new OColumn("Product", ProductProduct.class,OColumn.RelationType.ManyToOne);

    public PosOrderLine(Context context, OUser user) {
        super(context, "pos.order.line", user);
    }


}

