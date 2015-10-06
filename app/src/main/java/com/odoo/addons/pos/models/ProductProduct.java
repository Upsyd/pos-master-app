package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.mail.MailMessage;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBlob;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 9/29/2015.
 */
public class ProductProduct extends OModel {

    public static final String TAG = ProductProduct.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.product_product";




    OColumn active = new OColumn("Active", OBoolean.class);
    //OColumn attribute_value_ids = new OColumn("Attributes", product.attribute.value.class,OColumn.RelationType.ManyToMany);
    OColumn code = new OColumn("Internal Reference", OVarchar.class);
    OColumn create_date = new OColumn("Creation on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn default_code = new OColumn("Internal Reference", OVarchar.class);
    OColumn delivery_count = new OColumn("Delivery", OInteger.class);
    OColumn ean13 = new OColumn("EAN13 Barcode", OVarchar.class);
    OColumn image = new OColumn("Big-sized image", OBlob.class);
    OColumn image_medium = new OColumn("Medium-sized image", OBlob.class);
    OColumn image_small = new OColumn("Small-sized image", OBlob.class);
    OColumn image_variant = new OColumn("Variant Image", OBlob.class);
    OColumn incoming_qty = new OColumn("Incoming", OFloat.class);
    OColumn is_product_variant = new OColumn("Is product variant", OBoolean.class);
    OColumn location_id = new OColumn("Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn lst_price = new OColumn("Public Price", OFloat.class);
    OColumn message_follower_ids = new OColumn("Followers", ResPartner.class, OColumn.RelationType.ManyToMany);
    OColumn message_ids = new OColumn("Messages", MailMessage.class, OColumn.RelationType.OneToMany);
    OColumn message_is_follower = new OColumn("Is a Follower", OBoolean.class);
    OColumn message_last_post = new OColumn("Last Message Date", ODateTime.class);
    OColumn message_summary = new OColumn("Summary", OText.class);
    OColumn message_unread = new OColumn("Unread Messages", OBoolean.class);
    OColumn name_template = new OColumn("Template Name", OVarchar.class);
   // OColumn orderpoint_ids = new OColumn("Minimum Stock Rules", stock.warehouse.orderpoint.class, OColumn.RelationType.OneToMany);
    OColumn outgoing_qty = new OColumn("Outgoing", OFloat.class);
    OColumn partner_ref = new OColumn("Customer ref", OVarchar.class);
    OColumn price = new OColumn("Price", OFloat.class);
    OColumn price_extra = new OColumn("Variant Extra Price", OVarchar.class);
    OColumn product_tmpl_id = new OColumn("Product Template", ProductTemplate.class, OColumn.RelationType.ManyToOne);
    OColumn qty_available = new OColumn("Quantity On Hand", OFloat.class);
    OColumn qty_available_text = new OColumn("unknown", OBoolean.class);
    OColumn reception_count = new OColumn("Receipt", OInteger.class);
    OColumn sales_count = new OColumn("# Sales", OInteger.class);
    OColumn virtual_available = new OColumn("Forecast Quantity", OFloat.class);
   // OColumn warehouse_id = new OColumn("Warehouse", stock.warehouse.class,OColumn.RelationType.ManyToOne);
    OColumn website_url = new OColumn("Website url", OVarchar.class);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class,OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", Integer.class);







    public ProductProduct(Context context, OUser user) {
        super(context, "product.product", user);
    }

//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}


