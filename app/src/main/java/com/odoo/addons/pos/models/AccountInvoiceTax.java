package com.odoo.addons.pos.models;

import android.content.Context;

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
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 10/8/2015.
 */
public class AccountInvoiceTax  extends OModel {
    public static final String TAG = AccountInvoiceTax.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_invoice_tax";
    //OColumn account_analytic_id = new OColumn("Analytic Account", AccountAnalyticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn account_id = new OColumn("Tax Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn amount = new OColumn("Amount", OFloat.class);
    OColumn base = new OColumn("base", OFloat.class);
    OColumn base_amount = new OColumn("Base Code Amount", OFloat.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    //OColumn base_code_id = new OColumn("Base Code", AccountTaxCode.class, OColumn.RelationType.ManyToOne);
    OColumn invoice_id = new OColumn("Invoice Line", AccountInvoice.class, OColumn.RelationType.ManyToOne);
    OColumn invoice_line_tax_id = new OColumn("Taxes", AccountTax.class, OColumn.RelationType.ManyToMany);
    OColumn name = new OColumn("Tax Description", OVarchar.class);
    OColumn manual = new OColumn("Manual", OBoolean.class);
    OColumn sequence = new OColumn("Sequence", OInteger.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);
    // OColumn tax_code_id = new OColumn("Tax Code", AccountTaxCode.class, OColumn.RelationType.ManyToOne);
    OColumn tax_amount = new OColumn("Tax Code Amount", OFloat.class);


    public AccountInvoiceTax(Context context, OUser user) {
        super(context, "account.invoice.tax", user);
    }
}