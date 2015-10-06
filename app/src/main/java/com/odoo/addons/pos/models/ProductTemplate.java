package com.odoo.addons.pos.models;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.odoo.App;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBlob;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 8/7/2015.
 */
public class ProductTemplate extends OModel {


    public static final String TAG = ProductTemplate.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.product_template";


     OColumn id = new OColumn("ID",Integer.class); //OVarchar.class).setSize(100);
    OColumn pos_categ_id = new OColumn("Point of Sale Category", PosCategory.class,OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("Name", OVarchar.class).setSize(100);
    OColumn image = new OColumn("Image", OBlob.class);
    OColumn list_price = new OColumn("Sale Price", OFloat.class);

    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn image_medium = new OColumn("Medium-sized image", OBlob.class);
    OColumn image_small = new OColumn("Smal-sized image", OBlob.class);
    OColumn product_variant_ids = new OColumn("Product",PosCategory.class,OColumn.RelationType.OneToMany);
    OColumn sequence = new OColumn("Sequence", OInteger.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ODateTime.class);


    public ProductTemplate(Context context, OUser user) {
        super(context, "product.template", user);
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}