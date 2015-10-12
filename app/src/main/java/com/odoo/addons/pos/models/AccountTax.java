package com.odoo.addons.pos.models;

import android.content.Context;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCurrency;
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
 * Created by My on 10/8/2015.
 */
public class AccountTax  extends OModel {
    public static final String TAG = AccountTax.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.account_tax";

    OColumn active = new OColumn("Active", OBoolean.class);
    OColumn account_analytic_collected_id = new OColumn("Invoice Tax Analytic Account", AccountAnalyticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn account_analytic_paid_id = new OColumn("Refund Tax Analytic Account", AccountAnalyticAccount.class, OColumn.RelationType.ManyToOne);
    OColumn account_collected_id= new OColumn("Invoice Tax Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn account_paid_id = new OColumn("Refund Tax Account", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn amount = new OColumn("Amount", OFloat.class);
    OColumn applicable_type = new OColumn("Applicability", OSelection.class);
    //OColumn base_code_id = new OColumn("Account Base Code", AccountTaxCode.class, OColumn.RelationType.ManyToOne);

    OColumn base_sign = new OColumn("Base Code Sign", OFloat.class);
    OColumn child_depend = new OColumn("Tax on Children", OBoolean.class);
    OColumn child_ids = new OColumn("Child Tax Accounts", AccountTax.class, OColumn.RelationType.OneToMany);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn domain = new OColumn("Domain", OVarchar.class);
    OColumn include_base_amount = new OColumn("Included in base amount", OBoolean.class);
    OColumn name = new OColumn("Tax Name", OVarchar.class);
    OColumn parent_id = new OColumn("Parent Tax Account", AccountTax.class, OColumn.RelationType.ManyToOne);
    OColumn debit = new OColumn("Debit", OFloat.class);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn description = new OColumn("Tax Code", OVarchar.class);
    OColumn id = new OColumn("ID", OInteger.class);
    //OColumn ref_base_code_id = new OColumn("Refund Base Code", AccountTaxCode.class, OColumn.RelationType.ManyToOne);
    //OColumn ref_tax_code_id = new OColumn("Refund Tax Code",  AccountTaxCode.class, OColumn.RelationType.ManyToOne);
    OColumn ref_tax_sign = new OColumn("Refund Tax Code Sign", OFloat.class);
    OColumn price_include = new OColumn("Tax Included in Price", OBoolean.class);
    OColumn python_applicable = new OColumn("Applicable Code", OText.class);
    OColumn python_compute = new OColumn("Python Code", OText.class);
    OColumn python_compute_inv = new OColumn("Python Code (reverse)", OText.class);
    OColumn ref_base_sign = new OColumn("Refund Base Code Sign", OFloat.class);
    OColumn sequence= new OColumn("Sequence", OInteger.class);
  //  OColumn tax_code_id = new OColumn("Account Tax Code", AccountTaxCode.class, OColumn.RelationType.ManyToMany);
    OColumn  type_tax_use = new OColumn("Tax Application", OSelection.class);
    OColumn type = new OColumn("Tax Type", OSelection.class);
    OColumn tax_sign = new OColumn("Tax Code Sign", OFloat.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);


    public AccountTax(Context context, OUser user) {
        super(context, "account.tax", user);
    }

}



