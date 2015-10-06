package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
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
 * Created by Harshad on 9/30/2015.
 */
public class IrSequence extends OModel {

    public static final String TAG = IrSequence.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.product_template";
    OColumn active = new OColumn("Active",OBoolean.class);
    OColumn code = new OColumn("Sequence Type",OSelection.class);
    OColumn company_id = new OColumn("Company",ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on",ODateTime.class);
    OColumn create_uid = new OColumn("Created by",ResUsers.class, OColumn.RelationType.ManyToOne);
   // OColumn fiscal_ids = new OColumn("Sequences",AccountSequenceFiscalYear.class, OColumn.RelationType.OneToMany);
    OColumn id = new OColumn("ID",OInteger.class);
    OColumn implementation = new OColumn("implementation",OSelection.class);
    OColumn number_increment = new OColumn("Increment Number",OInteger.class);
    OColumn number_next= new OColumn("Next Number",OInteger.class);
    OColumn number_next_actual = new OColumn("Next Number",OInteger.class);
    OColumn padding = new OColumn("Number Padding",OInteger.class);
    OColumn prefix = new OColumn("Prefix",OVarchar.class);
    OColumn suffix = new OColumn("Suffix",OVarchar.class);
    OColumn write_date = new OColumn("Last Updated on",ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by",ResUsers.class, OColumn.RelationType.ManyToOne);

    public IrSequence(Context context, OUser user) {
        super(context,"ir.sequence", user);
    }

//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}
