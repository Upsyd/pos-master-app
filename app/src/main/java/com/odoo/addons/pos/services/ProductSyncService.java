package com.odoo.addons.pos.services;

import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.odoo.addons.pos.models.AccountAccount;
import com.odoo.addons.pos.models.AccountJournal;
import com.odoo.addons.pos.models.PosCategory;
import com.odoo.addons.pos.models.PosConfig;
import com.odoo.addons.pos.models.PosOrder;
import com.odoo.addons.pos.models.ProductTemplate;
import com.odoo.core.service.ISyncFinishListener;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by My on 8/12/2015.
 */
public class ProductSyncService extends OSyncService implements ISyncFinishListener {
    public static final String TAG = ProductSyncService.class.getSimpleName();
    public Boolean firstSync = false;

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), ProductTemplate.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);

        if (!firstSync) {
            adapter.onSyncFinish(new ISyncFinishListener() {
                @Override
                public OSyncAdapter performNextSync(OUser user, SyncResult syncResult) {
                    firstSync = true;
                    return new OSyncAdapter(getApplicationContext(), AccountAccount.class, ProductSyncService.this, true);
                }
            });
        }
    }

    @Override
    public OSyncAdapter performNextSync(OUser user, SyncResult syncResult) {
        return new OSyncAdapter(getApplicationContext(), PosConfig.class, ProductSyncService.this, true);
    }
}