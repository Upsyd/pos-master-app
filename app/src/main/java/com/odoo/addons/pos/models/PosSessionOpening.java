package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResUsers;
import com.odoo.addons.pos.models.PosOrder;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 7/21/2015.
 */
public class PosSessionOpening extends OModel {


    public static final String TAG = PosSessionOpening.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.pos_session_opening";

    OColumn id = new OColumn("ID", OInteger.class);
    OColumn name = new OColumn("Name", OVarchar.class);
    OColumn pos_session_name = new OColumn("Name", OVarchar.class);
    OColumn pos_session_username = new OColumn("Name", OVarchar.class);
    OColumn pos_state = new OColumn("Session Status", OSelection.class);
    OColumn pos_state_str = new OColumn("Status", OVarchar.class);
    OColumn pos_config_id = new OColumn("Point of Sale", PosOrder.class, OColumn.RelationType.ManyToOne);
    OColumn pos_session_id = new OColumn("PoS Session", PosSession.class, OColumn.RelationType.ManyToOne);
    OColumn show_config = new OColumn("Show Config", OBoolean.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);


    public PosSessionOpening(Context context, OUser user) {
        super(context, "pos.session.opening", user);}
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}

