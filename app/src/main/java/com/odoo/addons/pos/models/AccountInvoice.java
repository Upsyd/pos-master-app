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
import com.odoo.core.orm.fields.types.OHtml;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 9/29/2015.
 */
public class AccountInvoice extends OModel {
    public static final String TAG =AccountInvoice.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_invoice";
 //OColumn account_id= new OColumn("Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn amount_tax = new OColumn("Tax", OFloat.class);
    OColumn amount_total = new OColumn("Total", OFloat.class);
    OColumn amount_untaxed= new OColumn("Subtotal", OFloat.class);
    OColumn check_total = new OColumn("Verification Total", OFloat.class);
    OColumn comment= new OColumn("Additional Information", OText.class);
    OColumn commercial_partner_id = new OColumn("Commercial Entity",ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn company_id= new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency",ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn date_due= new OColumn("Due Date", ODate.class);
    OColumn date_invoice = new OColumn("Invoice Date", ODate.class);
   // OColumn fiscal_position = new OColumn("Fiscal Position",AccountFisacalPosition.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn internal_number= new OColumn("Invoice Number", OVarchar.class);
   // OColumn invoice_line = new OColumn("Invoice Lines", AccountInvoiceLine.class, OColumn.RelationType.OneToMany);
   // OColumn journal_id = new OColumn("Journal", AccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn message_follower_ids= new OColumn("Followers", ResPartner.class, OColumn.RelationType.ManyToMany);
    //OColumn message_ids = new OColumn("Messages",MailMessege.class, OColumn.RelationType.OneToMany);
    OColumn message_is_follower = new OColumn("Is a Follower", OBoolean.class);
    OColumn message_last_post = new OColumn("Last Message Date", ODateTime.class);
    OColumn message_summary = new OColumn("Summary", OText.class);
    OColumn message_unread = new OColumn("Unread Messages", OBoolean.class);
   // OColumn move_id = new OColumn("Journal Entry", AccountMove.class, OColumn.RelationType.ManyToOne);
    OColumn move_name = new OColumn("Journal Entry", OVarchar.class);
    OColumn name = new OColumn("Reference/Description", OVarchar.class);
    OColumn number = new OColumn("Number", OVarchar.class);
    OColumn origin = new OColumn("Source Document", OVarchar.class);
   //OColumn partner_bank_id = new OColumn("Bank Account", ResPartnerBank.class, OColumn.RelationType.ManyToOne);
    OColumn partner_id = new OColumn("Partner", ResPartner.class, OColumn.RelationType.ManyToOne);
   // OColumn payment_term = new OColumn("Payment Terms", AccountPaymentTerms.class, OColumn.RelationType.ManyToOne);
    OColumn paypal_url = new OColumn("Paypal Url", OVarchar.class);
    //OColumn period_id = new OColumn("Force Period", AccountPeriod.class, OColumn.RelationType.ManyToOne);
    OColumn portal_payment_options= new OColumn("Portal Payment Options", OHtml.class);
    OColumn reconciled = new OColumn("Paid/Reconciled", OBoolean.class);
    OColumn reference = new OColumn("invoice Reference", OVarchar.class);
    OColumn reference_type = new OColumn("Payment Reference", OSelection.class);
    OColumn residual = new OColumn("Balance", OFloat.class);
   // OColumn section_id = new OColumn("Sales Team",CrmCaseSection.class, OColumn.RelationType.ManyToOne);
    OColumn sent = new OColumn("Sent", OBoolean.class);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn supplier_invoice_number = new OColumn("Supplier Invoice Number", OVarchar.class);
    //OColumn tax_line = new OColumn("Tax Lines", AccountInvoiceTax.class, OColumn.RelationType.OneToMany);
    OColumn type = new OColumn("Type", OSelection.class);
    OColumn user_id = new OColumn("Salesperson", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid= new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);





    public AccountInvoice(Context context,OUser user) {
        super(context,"account.invoice", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }


}
