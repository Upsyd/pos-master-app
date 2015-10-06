package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBlob;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 10/1/2015.
 */
public class ProductUom extends OModel {


    public static final String TAG = ProductUom.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.product.uom";


    OColumn active = new OColumn("Active", OBoolean.class); //OVarchar.class).setSize(100);
    OColumn factor = new OColumn("Ratio", OFloat.class);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    //  OColumn category_id = new OColumn("Unit of Measure Category", product.uom.categ.class, OColumn.RelationType.ManyToOne);
    OColumn factor_inv = new OColumn("Bigger Ratio", OFloat.class);
    OColumn rounding = new OColumn("Rounding Precision", OFloat.class);
    OColumn name = new OColumn("Unit of Measure", OVarchar.class);
    OColumn uom_type = new OColumn("Type", OSelection.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ODateTime.class);


    public ProductUom(Context context, OUser user) {
        super(context, "product.uom", user);
    }

//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}

