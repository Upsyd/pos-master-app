package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCurrency;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 9/29/2015.
 */
public class ProductPricelist extends OModel {
    public static final String TAG = ProductPricelist.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.product_pricelist";

    OColumn active = new OColumn("Active", OBoolean.class);
    OColumn code = new OColumn("Promotional Code", OVarchar.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn name = new OColumn("Pricelist Name", OVarchar.class);
    OColumn type = new OColumn("Pricelist Type", OSelection.class);
    // OColumn version_id=new OColumn("Pricelist Versions",ProductPricelistVersion.class,OColumn.RelationType.OneToMany);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);

    public ProductPricelist(Context context, OUser user) {
        super(context, "product.pricelist", user);
    }

//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}

