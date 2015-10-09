package com.odoo.addons.pos.models;

import android.content.Context;

import com.odoo.base.addons.mail.MailMessage;
import com.odoo.base.addons.mail.widget.MailChatterCompose;
import com.odoo.base.addons.res.ResCompany;
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
 * Created by Harshad on 10/9/2015.
 */
public class AccountAnaliticAccount extends OModel {

    public static final String TAG = AccountAnaliticAccount.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account.analytic.account";

    OColumn balance = new OColumn("Balance", OFloat.class);
    OColumn child_complete_ids = new OColumn("Account Hierarchy", AccountAnaliticAccount.class,OColumn.RelationType.ManyToMany);
    OColumn child_ids = new OColumn("Child Accounts",AccountAnaliticAccount.class,OColumn.RelationType.OneToMany);
    OColumn code = new OColumn("Reference", OVarchar.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn company_uom_id = new OColumn("unknown", ProductUom.class, OColumn.RelationType.ManyToOne);
    OColumn complete_name = new OColumn("Full Name", OVarchar.class);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn credit = new OColumn("Credit", OFloat.class);
    OColumn currency_id = new OColumn("Currency", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Expiration Date", ODate.class);
    OColumn date_start = new OColumn("Start Date", ODate.class);
    OColumn debit = new OColumn("Debit", OFloat.class);
    OColumn description = new OColumn("Description", OText.class);
    OColumn id = new OColumn("ID", OInteger.class);
    //OColumn line_ids = new OColumn("Analytic Entries", AccountAnaliticLine.class, OColumn.RelationType.OneToMany);
    OColumn manager_id = new OColumn("Account Manager",ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn message_follower_ids = new OColumn("Followers", ResPartner.class, OColumn.RelationType.ManyToMany);
    OColumn message_ids = new OColumn("Messages", MailMessage.class, OColumn.RelationType.OneToMany);
    OColumn message_is_follower = new OColumn("Is a Follower", OBoolean.class);
    OColumn message_last_post = new OColumn("Last Message Date", ODateTime.class);
    OColumn message_summary = new OColumn("Summary", OText.class);
    OColumn message_unread = new OColumn("Unread Messages", OBoolean.class);
    OColumn name = new OColumn("Account/Contract Name", OVarchar.class);
    OColumn parent_id = new OColumn("Parent Analytic Account", AccountAnaliticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn partner_id = new OColumn("Customer", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn quantity = new OColumn("Quantity", OFloat.class);
    OColumn quantity_max = new OColumn("Prepaid Service Units", OFloat.class);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn template_id = new OColumn("Template of Contract", AccountAnaliticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn type = new OColumn("Type of Account", OSelection.class);
    OColumn user_id = new OColumn("Project Manager", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn use_tasks = new OColumn("Tasks", OBoolean.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
   OColumn write_uid = new OColumn("Last Updated by",ResUsers.class, OColumn.RelationType.ManyToOne);



    public AccountAnaliticAccount(Context context, OUser user) {
        super(context, "account.analytic.account", user);
    }
}
