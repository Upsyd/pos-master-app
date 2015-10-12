package com.odoo.addons.pos.models;

import android.content.Context;

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
 * Created by My on 10/8/2015.
 */
public class AccountAnalyticLine  extends OModel {
    public static final String TAG = AccountAnalyticLine.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_analytic_line";
    OColumn account_id = new OColumn("Analytic Account", AccountAnalyticLine.class, OColumn.RelationType.ManyToOne);
    OColumn amount_currency = new OColumn("Amount Currency", OFloat.class);
    OColumn amount = new OColumn("Amount", OFloat.class);
    OColumn code = new OColumn("Code", OVarchar.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Effective date", ODate.class);
    OColumn general_account_id = new OColumn("General Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn journal_id = new OColumn("Analytic Journal", AccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn move_id = new OColumn("Move Line", AccountMoveLine.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn name = new OColumn("Description", OVarchar.class);
    OColumn ref = new OColumn("Ref.", OVarchar.class);
    OColumn product_id = new OColumn("Product", ProductProduct.class, OColumn.RelationType.ManyToOne);
    OColumn product_uom_id = new OColumn("Unit of Measure", ProductUom.class, OColumn.RelationType.ManyToOne);
    OColumn unit_amount = new OColumn("Quantity", OFloat.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn user_id = new OColumn("User", ResUsers.class, OColumn.RelationType.ManyToOne);






    public AccountAnalyticLine(Context context, OUser user) {
        super(context, "account.analytic.line", user);
    }
}
//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }

