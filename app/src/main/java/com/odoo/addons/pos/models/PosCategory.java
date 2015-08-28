package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBlob;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

import odoo.controls.OBlobField;
import odoo.controls.ODateTimeField;

/**
 * Created by My on 7/21/2015.
 */
public class PosCategory extends OModel {


    public static final String TAG = PosCategory.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.addons.categories.pos_category";


//    OColumn child_id = new OColumn("Children Categories", PosCategory.class, OColumn.RelationType.OneToMany); //OVarchar.class).setSize(100);
    OColumn complete_name = new OColumn("complete_name", OVarchar.class).setSize(100);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn image = new OColumn("Image", OBlob.class);
    OColumn image_medium = new OColumn("Medium-sized image", OBlob.class);
    OColumn image_small = new OColumn("Smal-sized image", OBlob.class);
    OColumn name = new OColumn("Name", OVarchar.class).setSize(100);
    OColumn parent_id = new OColumn("Parent Category",PosCategory.class,OColumn.RelationType.ManyToOne);
    OColumn sequence = new OColumn("Sequence", OInteger.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ODateTime.class);


    public PosCategory(Context context, OUser user) {
        super(context, "pos.category", user);}
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}

