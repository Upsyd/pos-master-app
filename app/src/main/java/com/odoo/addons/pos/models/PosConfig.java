package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCurrency;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODate;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by My on 9/30/2015.
 */
public class PosConfig extends OModel {
    public static final String TAG = PosConfig.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.pos_config";

    OColumn barcode_cashier = new OColumn("Cashier Barcodes", OVarchar.class);
    OColumn barcode_customer = new OColumn("Customer Barcodes", OVarchar.class);
    OColumn barcode_discount = new OColumn("Discount Barcodes", OVarchar.class);
    OColumn barcode_price = new OColumn("Price Barcodes", OVarchar.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Created on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn currency_id = new OColumn("Currency", ResCurrency.class, OColumn.RelationType.ManyToOne);
    OColumn barcode_product = new OColumn("Product Barcodes", OVarchar.class);
    OColumn id = new OColumn("ID", OInteger.class);
    OColumn barcode_weight = new OColumn("Weight Barcodes", OVarchar.class);
    OColumn group_by = new OColumn("Group Journal Items", OBoolean.class);
    OColumn iface_big_scrollbars = new OColumn("Large Scrollbars", OBoolean.class);
    OColumn iface_cashdrawer = new OColumn("Cashdrawer", OBoolean.class);
    OColumn iface_electronic_scale = new OColumn("Electronic Scale", OBoolean.class);
    OColumn iface_invoicing = new OColumn("Invoicing", OBoolean.class);
    OColumn iface_payment_terminal = new OColumn("Payment Terminal", OBoolean.class);
    OColumn iface_printbill = new OColumn("Bill Printing", OBoolean.class);
    OColumn iface_print_via_proxy = new OColumn("Print via Proxy", OBoolean.class);
    OColumn iface_scan_via_proxy = new OColumn("Scan via Proxy", OBoolean.class);
    OColumn iface_self_checkout = new OColumn("Self Checkout Mode", OBoolean.class);
    OColumn iface_splitbill = new OColumn("Bill Splitting", OBoolean.class);
    OColumn journal_id = new OColumn("Sale Journal", PosAccountJournal.class, OColumn.RelationType.ManyToOne);
    OColumn journal_ids = new OColumn("Available Payment Methods", PosAccountJournal.class, OColumn.RelationType.ManyToMany);
    OColumn name = new OColumn("Point of Sale Name", OVarchar.class);
   // OColumn picking_type_id = new OColumn("Picking Type", stock.picking.type.class, OColumn.RelationType.ManyToOne);
    OColumn pricelist_id = new OColumn("Pricelist", ProductPricelist.class, OColumn.RelationType.ManyToOne);
  //  OColumn printer_ids = new OColumn("Order Printers", restaurant.printer.class, OColumn.RelationType.ManyToMany);
     OColumn proxy_ip = new OColumn("IP Address", OVarchar.class);
    OColumn receipt_footer = new OColumn("Receipt Footer", OText.class);
    OColumn receipt_header = new OColumn("Receipt Header", OText.class);
 //   OColumn sequence_id = new OColumn("Order IDs Sequence", IrSquence.class, OColumn.RelationType.ManyToOne);
    OColumn session_ids = new OColumn("Sessions", PosSession.class, OColumn.RelationType.OneToMany);
    OColumn stock_location_id = new OColumn("Stock Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn state = new OColumn("Status", OSelection.class);

    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);







    public PosConfig(Context context,OUser user) {
        super(context,"pos.config", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}



