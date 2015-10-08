package com.odoo.addons.pos.models;

import android.content.Context;

import com.odoo.base.addons.mail.MailMessage;
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
import com.odoo.core.orm.fields.types.OHtml;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 10/8/2015.
 */
public class AccountInvoiceLine extends OModel {
    public static final String TAG =AccountInvoiceLine.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_invoice_line";
    //OColumn account_analytic_id = new OColumn("Analytic Account", AccountAnalyticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn account_id = new OColumn("Account", AccountAccount.class, OColumn.RelationType.ManyToOne);

    OColumn company_id= new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency",ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn discount = new OColumn("Discount (%)", OFloat.class);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn invoice_id = new OColumn("Invoice Reference", AccountInvoice.class, OColumn.RelationType.ManyToOne);
     OColumn invoice_line_tax_id = new OColumn("Taxes", AccountTax.class, OColumn.RelationType.ManyToMany);
    OColumn name = new OColumn("Description", OVarchar.class);
    OColumn origin = new OColumn("Source Document", OVarchar.class);
    OColumn partner_id = new OColumn("Partner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn price_subtotal = new OColumn("Amount", OFloat.class);
    OColumn price_unit = new OColumn("Unit Price", OFloat.class);
    OColumn product_id = new OColumn("Product", ProductProduct.class, OColumn.RelationType.ManyToOne);
    OColumn quantity = new OColumn("Quantity", OFloat.class);
    OColumn sequence = new OColumn("Sequence", OInteger.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid= new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn uos_id = new OColumn("Unit of Measure", ProductUom.class, OColumn.RelationType.ManyToOne);







    public AccountInvoiceLine(Context context,OUser user) {
        super(context,"account.invoice.line", user);
    }
//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }


}

