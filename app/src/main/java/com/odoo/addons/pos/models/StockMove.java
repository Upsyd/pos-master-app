package com.odoo.addons.pos.models;

import android.content.Context;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OBoolean;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Harshad on 10/9/2015.
 */
public class StockMove extends OModel {
    public static final String TAG = StockMove.class.getSimpleName();

    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.stock.move";

    OColumn availability = new OColumn("Quantity Available", OFloat.class);
    OColumn backorder_id = new OColumn("Back Order of", StockPicking.class, OColumn.RelationType.ManyToOne);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Creation Date", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn date = new OColumn("Date", ODateTime.class);
    OColumn date_expected = new OColumn("Expected Date", ODateTime.class);
   // OColumn group_id = new OColumn("Procurement Group",Procurment.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", OInteger.class);
    //OColumn inventory_id = new OColumn("Inventory", StockInventory.class, OColumn.RelationType.ManyToOne);
    OColumn invoice_state = new OColumn("Invoice Control", OSelection.class);
   // OColumn linked_move_operation_ids = new OColumn("Linked Operations",StockMoveOperationLink.class, OColumn.RelationType.OneToMany);
    OColumn location_dest_id = new OColumn("Destination Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn location_id = new OColumn("Source Location", StockLocation.class, OColumn.RelationType.ManyToOne);
   // OColumn lot_ids = new OColumn("Lots", StockProductionLot.class, OColumn.RelationType.ManyToOne);
    OColumn move_dest_id = new OColumn("Destination Move", StockMove.class, OColumn.RelationType.ManyToOne);
    OColumn move_orig_ids = new OColumn("Original Move", StockMove.class, OColumn.RelationType.OneToMany);

    OColumn name = new OColumn("Description", OVarchar.class);
    OColumn note = new OColumn("Notes", OText.class);
    OColumn origin = new OColumn("Source", OVarchar.class);
    OColumn origin_returned_move_id = new OColumn("Origin return move", StockMove.class, OColumn.RelationType.ManyToOne);
    OColumn partially_available = new OColumn("Partially Available", OBoolean.class);
    OColumn partner_id = new OColumn("Destination Address", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn picking_id = new OColumn("Reference",StockPicking.class, OColumn.RelationType.ManyToOne);
    OColumn picking_type_id = new OColumn("Picking Type", StockPickingType.class, OColumn.RelationType.ManyToOne);
    OColumn price_unit = new OColumn("Unit Price", OFloat.class);
    OColumn priority = new OColumn("Priority", OSelection.class);
  //  OColumn procurement_id = new OColumn("Procurement", ProcuremnetOrder.class, OColumn.RelationType.ManyToOne);
    OColumn procure_method = new OColumn("Supply Method", OSelection.class);
    OColumn product_id = new OColumn("Product", ProductProduct.class, OColumn.RelationType.ManyToOne);
  //  OColumn product_packaging = new OColumn("Prefered Packaging", ProductPackaging.class, OColumn.RelationType.ManyToOne);
    OColumn product_qty = new OColumn("Quantity", OFloat.class);
    OColumn product_tmpl_id = new OColumn("Product Template", ProductTemplate.class, OColumn.RelationType.ManyToOne);
    OColumn product_uom = new OColumn("Unit of Measure", ProductUom.class, OColumn.RelationType.ManyToOne);
    OColumn product_uom_qty = new OColumn("Quantity", OFloat.class);
    OColumn propagate = new OColumn("Propagate cancel and split", OBoolean.class);
   // OColumn push_rule_id = new OColumn("Push Rule",StockLocationPath.class, OColumn.RelationType.ManyToMany);
   // OColumn quant_ids = new OColumn("Moved Quants", StockQuant.class, OColumn.RelationType.ManyToOne);
    OColumn remaining_qty = new OColumn("Remaining Quantity", OFloat.class);
    OColumn reserved_availability = new OColumn("Quantity Reserved", OFloat.class);
 //   OColumn reserved_quant_ids = new OColumn("Reserved quants", StockQuant.class, OColumn.RelationType.OneToMany);
   // OColumn restrict_lot_id = new OColumn("Lot",StockProductionLot.class, OColumn.RelationType.ManyToOne);
    OColumn restrict_partner_id = new OColumn("Owner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn returned_move_ids = new OColumn("All returned moves", StockMove.class, OColumn.RelationType.OneToMany);
   // OColumn route_ids = new OColumn("Destination route", StockLocationRoute.class, OColumn.RelationType.ManyToMany);
    //OColumn rule_id = new OColumn("Procurement Rule",ProcurementRule.class, OColumn.RelationType.ManyToOne);
    OColumn scrapped = new OColumn("Scrapped", OBoolean.class);
    OColumn split_from = new OColumn("Move Split From", StockMove.class, OColumn.RelationType.ManyToOne);
    OColumn state = new OColumn("Status", OSelection.class);
    OColumn string_availability_info = new OColumn("Availability", OText.class);
    //OColumn warehouse_id = new OColumn("Warehouse", StockWherehouse.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);

















    public StockMove(Context context, OUser user) {
        super(context,"stock.move", user);
    }
}
