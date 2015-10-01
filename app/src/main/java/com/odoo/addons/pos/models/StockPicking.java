package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBlob;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**y
 * Created by My on 9/29/2015.
 */
public class StockPicking extends OModel {

    public static final String TAG = StockPicking.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.stock_picking";




   OColumn backorder_id = new OColumn("Back Order of", StockPicking.class, OColumn.RelationType.ManyToOne);
    //OColumn attribute_value_ids = new OColumn("Attributes", product.attribute.value.class,OColumn.RelationType.ManyToMany);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Creation on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Creation Date", ODateTime.class);
    OColumn date_done = new OColumn("Date of Transfer", ODateTime.class);
  //  OColumn group_id = new OColumn("Procurement Group", procurement.group.class,OColumn.RelationType.ManyToOne);
    OColumn invoice_state = new OColumn("Invoice Control", OSelection.class);
   OColumn location_dest_id = new OColumn("Destination Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn location_id= new OColumn("Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn max_date = new OColumn("Max. Expected Date", ODateTime.class);
    OColumn message_follower_ids = new OColumn("Followers", ResPartner.class, OColumn.RelationType.ManyToMany);
    OColumn is_product_variant = new OColumn("Is product variant", OBoolean.class);
    OColumn lst_price = new OColumn("Public Price", OFloat.class);
    OColumn message_last_post = new OColumn("Last Message Date", ODateTime.class);
    OColumn message_summary = new OColumn("Summary", OText.class);
    OColumn message_unread = new OColumn("Unread Messages", OBoolean.class);
    OColumn min_date = new OColumn("Scheduled Date", ODateTime.class);
   // OColumn move_lines = new OColumn("Internal Moves", stock.move.class,OColumn.RelationType.OneToMany);
    OColumn move_type = new OColumn("Delivery Method", OSelection.class);
    OColumn name= new OColumn("Reference", OVarchar.class);
    OColumn origin = new OColumn("Source Document", OVarchar.class);
    OColumn note = new OColumn("Notes", OText.class);
    OColumn owner_id = new OColumn("Owner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn pack_operation_exist = new OColumn("Pack Operation Exists?", OBoolean.class);
  //  OColumn pack_operation_ids = new OColumn("Related Packing Operations", stock.pack.operation.class,OColumn.RelationType.OneToMany);
    OColumn partner_id = new OColumn("Partner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn picking_type_code = new OColumn("Picking Type Code", OVarchar.class);
   OColumn picking_type_id = new OColumn("Picking Type", StockPicking.class,OColumn.RelationType.ManyToOne);
    OColumn priority = new OColumn("Priority", OSelection.class);
    OColumn product_id = new OColumn("Product", PosProductProduct.class,OColumn.RelationType.ManyToOne);
    OColumn quant_reserved_exist = new OColumn("Quant already reserved ?", OBoolean.class);
    OColumn recompute_pack_op = new OColumn("Recompute pack operation?", OBoolean.class);
   // OColumn sale_id = new OColumn("Sale Order", SaleOrder.class,OColumn.RelationType.ManyToMany);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class,OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", Integer.class);







    public StockPicking(Context context, OUser user) {
        super(context, "stock.picking", user);
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}


