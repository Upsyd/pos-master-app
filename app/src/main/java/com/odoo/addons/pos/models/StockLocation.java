package com.odoo.addons.pos.models;

import android.content.Context;
import android.net.Uri;

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
 * Created by My on 9/30/2015.
 */
public class StockLocation extends OModel {

    public static final String TAG = StockLocation.class.getSimpleName();
    public static final String AUTHORITY = "com.bi.pos.core.provider.content.sync.stock_location";


    OColumn active = new OColumn("Active", OBoolean.class);
    OColumn company_id = new OColumn("Company", ResCompany.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("Creation on", ODateTime.class);
    OColumn create_uid = new OColumn("Created by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn comment = new OColumn("Additional Information", OText.class);
    OColumn complete_name = new OColumn("Location Name", OVarchar.class);
    OColumn location_id = new OColumn("Parent Location", StockLocation.class, OColumn.RelationType.ManyToOne);
    OColumn loc_barcode = new OColumn("Location Barcode", OVarchar.class);
    OColumn name = new OColumn("Location Name", OVarchar.class);
    OColumn parent_left = new OColumn("Left Parent", OInteger.class);
    OColumn parent_right = new OColumn("Right Parent", OInteger.class);
    OColumn partner_id = new OColumn("Owner", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn posx = new OColumn("Corridor (X)", OInteger.class);
    OColumn posy = new OColumn("Shelves (Y)", OInteger.class);
    OColumn posz = new OColumn("Height (Z)", OInteger.class);
    //    OColumn putaway_strategy_id = new OColumn("Put Away Strategy", product.putaway.class,OColumn.RelationType.ManyToOne);
//    OColumn removal_strategy_id = new OColumn("Removal Strategy", product.removal.class,OColumn.RelationType.ManyToOne);
    OColumn scrap_location = new OColumn("Is a Scrap Location?", OBoolean.class);
    OColumn usage = new OColumn("Location Type", OSelection.class);
    OColumn valuation_in_account_id = new OColumn("Stock Valuation Account (Incoming)", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn valuation_out_account_id = new OColumn("Stock Valuation Account (Outgoing)", AccountAccount.class, OColumn.RelationType.ManyToOne);
    OColumn write_date = new OColumn("Last Updated on", ODateTime.class);
    OColumn write_uid = new OColumn("Last Updated by", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn id = new OColumn("ID", Integer.class);


    public StockLocation(Context context, OUser user) {
        super(context, "stock.location", user);
    }

//    @Override
//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}

