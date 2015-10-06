package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 9/30/2015.
 */
public class StockPickingType extends OModel {
    public static final String TAG =AccountMove.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.stock_picking_type";

    OColumn active = new OColumn("Active",OBoolean.class);
    OColumn code = new OColumn("Type of Operation",OSelection.class);
    OColumn color = new OColumn("Color",OInteger.class);
    OColumn complete_name = new OColumn("Name",OVarchar.class);
    OColumn count_picking = new OColumn("unknown",OInteger.class);
    OColumn count_picking_backorders = new OColumn("unknown",OInteger.class);
    OColumn count_picking_draft = new OColumn("unknown",OInteger.class);
    OColumn count_picking_late = new OColumn("unknown",OInteger.class);
    OColumn count_picking_ready= new OColumn("unknown",OInteger.class);
    OColumn count_picking_waiting = new OColumn("unknown",OInteger.class);
    OColumn create_date = new OColumn("Created on",ODateTime.class);
    OColumn create_uid= new OColumn("Created by",ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn default_location_dest_id = new OColumn("Default Destination Location",StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn default_location_src_id = new OColumn("Default Source Location",StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID",OInteger.class);
    OColumn last_done_picking = new OColumn("Last 10 Done Pickings",OVarchar.class);
    OColumn name = new OColumn("Picking Type Name",OVarchar.class);
    OColumn rate_picking_backorders = new OColumn("unknown",OInteger.class);
    OColumn rate_picking_late = new OColumn("unknown",OInteger.class);
    OColumn return_picking_type_id = new OColumn("Picking Type for Returns",StockPickingType.class, OColumn.RelationType.ManyToOne);
    OColumn sequence = new OColumn("Sequence",OInteger.class);
    OColumn sequence_id = new OColumn("Reference Sequence",IrSequence.class, OColumn.RelationType.ManyToOne);
    //OColumn warehouse_id= new OColumn("Warehouse",StockWhereHouse.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on",ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by",ResUsers.class, OColumn.RelationType.ManyToOne);




    public StockPickingType(Context context, OUser user) {
        super(context,"stock.picking.type", user);
    }
//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}
