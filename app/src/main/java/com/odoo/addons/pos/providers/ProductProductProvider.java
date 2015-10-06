package com.odoo.addons.pos.providers;

import com.odoo.addons.pos.models.PosOrder;
import com.odoo.addons.pos.models.ProductProduct;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by My on 7/22/2015.
 */
public class ProductProductProvider extends BaseModelProvider {
    public static final String TAG = ProductProductProvider.class.getSimpleName();

    @Override
    public String authority() {
        return ProductProduct.AUTHORITY;
    }


}