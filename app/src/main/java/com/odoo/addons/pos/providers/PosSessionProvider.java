package com.odoo.addons.pos.providers;

import com.odoo.addons.pos.models.PosSession;
import com.odoo.addons.pos.models.PosSessionOpening;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by My on 7/22/2015.
 */
public class PosSessionProvider extends BaseModelProvider {
    public static final String TAG = PosSessionProvider.class.getSimpleName();

    @Override
    public String authority() {
        return PosSessionOpening.AUTHORITY;
    }
}