package com.odoo.addons.pos.providers;

import com.odoo.addons.pos.models.PosCategory;
import com.odoo.addons.pos.models.ProductTemplate;
import com.odoo.addons.pos.services.ProductSyncService;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by My on 8/12/2015.
 */
public class PosProductProvider extends BaseModelProvider {
    public static final String TAG = PosProductProvider.class.getSimpleName();

    @Override
    public String authority() {
        return ProductTemplate.AUTHORITY;
    }
}