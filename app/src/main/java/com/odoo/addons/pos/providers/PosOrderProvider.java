package com.odoo.addons.pos.providers;

import android.net.Uri;

import com.odoo.addons.pos.models.PosOrder;
import com.odoo.addons.pos.models.PosSession;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.provider.BaseModelProvider;
import com.odoo.core.support.OdooFields;

import java.util.ArrayList;
import java.util.List;

import odoo.helper.ODomain;

/**
 * Created by My on 7/22/2015.
 */
public class PosOrderProvider extends BaseModelProvider {
    public static final String TAG = PosOrderProvider.class.getSimpleName();

    @Override
    public String authority() {
        return PosOrder.AUTHORITY;
    }


}