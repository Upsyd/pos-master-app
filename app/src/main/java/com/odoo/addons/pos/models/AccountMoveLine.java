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
public class AccountMoveLine  extends OModel {
    public static final String TAG = AccountMoveLine.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_move_line";
    OColumn account_id = new OColumn("Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn account_tax_id = new OColumn("Tax", AccountTax.class, OColumn.RelationType.ManyToOne);
    OColumn amount_currency = new OColumn("Amount Currency", OFloat.class);
    OColumn amount_residual = new OColumn("Residual Amount", OFloat.class);
    OColumn amount_residual_currency = new OColumn("Residual Amount in Currency", OFloat.class);
    OColumn analytic_account_id = new OColumn("Analytic Account", AccountAnalyticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn analytic_lines = new OColumn("Analytic lines", AccountAnalyticLine.class, OColumn.RelationType.OneToMany);
    OColumn balance = new OColumn("balance", OFloat.class);
    OColumn blocked = new OColumn("No Follow-up", OBoolean.class);
    OColumn centralisation = new OColumn("centralisation", OSelection.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn credit = new OColumn("Credit", OFloat.class);
    OColumn currency_id = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Effective date", ODate.class);
    OColumn date_created = new OColumn("Creation date", ODate.class);
    OColumn date_maturity = new OColumn("Due date", ODate.class);
    OColumn debit = new OColumn("Debit", ODate.class);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn invoice = new OColumn("Invoice", AccountInvoice.class, OColumn.RelationType.ManyToOne);
    OColumn journal_id = new OColumn("Journal", AccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn move_id = new OColumn("Journal Entry", AccountMove.class, OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("Name", OVarchar.class);
    OColumn partner_id = new OColumn("Partner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn narration = new OColumn("Internal Note", OText.class);
    OColumn period_id = new OColumn("Period", AccountPeriod.class, OColumn.RelationType.ManyToOne);
    OColumn product_id = new OColumn("Product", com.odoo.addons.pos.models.ProductProduct.class, OColumn.RelationType.ManyToOne);
    OColumn product_uom_id = new OColumn("Unit of Measure", ProductUom.class, OColumn.RelationType.ManyToOne);
    OColumn quantity = new OColumn("Quantity", OFloat.class);
    //OColumn reconcile_id = new OColumn("Reconcile", AccountMoveReconcile.class, OColumn.RelationType.ManyToOne);
   // OColumn reconcile_partial_id = new OColumn("Partial Reconcile",  AccountMoveReconcile.class, OColumn.RelationType.ManyToOne);
    OColumn reconcile_ref = new OColumn("Reconcile Ref", OVarchar.class);
    OColumn ref = new OColumn("Reference", OVarchar.class);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn statement_id = new OColumn("Statement", AccountBankStatement.class, OColumn.RelationType.ManyToOne);
    //OColumn tax_code_id  = new OColumn("Tax Account", AccountTaxCode.class, OColumn.RelationType.ManyToOne);
    OColumn tax_amount = new OColumn("Tax/Base Amount", OFloat.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);


    public AccountMoveLine(Context context, OUser user) {
        super(context, "account.move.line", user);
    }
}
//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
