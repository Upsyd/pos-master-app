package com.odoo.addons.pos.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.addons.pos.models.PosCategory;
import com.odoo.addons.pos.models.PosSession;
import com.odoo.addons.pos.models.PosSessionOpening;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by My on 7/22/2015.
 */
public class PosSessionSyncService extends OSyncService {
    public static final String TAG = PosSessionSyncService.class.getSimpleName();

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), PosSessionOpening.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);
    }
}