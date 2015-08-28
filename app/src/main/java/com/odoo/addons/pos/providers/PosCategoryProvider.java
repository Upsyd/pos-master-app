package com.odoo.addons.pos.providers;

import com.odoo.addons.pos.models.PosCategory;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by My on 7/22/2015.
 */
public class PosCategoryProvider extends BaseModelProvider {
    public static final String TAG = PosCategoryProvider.class.getSimpleName();

    @Override
    public String authority() {
        return PosCategory.AUTHORITY;
    }
}