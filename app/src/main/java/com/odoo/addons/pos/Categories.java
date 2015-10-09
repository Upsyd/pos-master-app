package com.odoo.addons.pos;

import android.annotation.TargetApi;
import android.app.Activity;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.odoo.App;
import com.odoo.OdooActivity;
import com.odoo.R;
import com.odoo.addons.pos.models.PosCategory;
import com.odoo.addons.pos.models.ProductTemplate;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.IOnSearchViewChangeListener;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.support.sync.SyncUtils;
import com.odoo.core.utils.IntentUtils;
import com.odoo.core.utils.OActionBarUtils;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;
import com.odoo.core.utils.OFragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 7/21/2015.
 */
public class Categories extends AppCompatActivity implements ISyncStatusObserverListener,LoaderManager.LoaderCallbacks<Cursor>, OCursorListAdapter.OnViewBindListener
{
    public static final String KEY = Categories.class.getSimpleName();
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    private View view;
    private Bundle extras;
    PosCategory poscategory;
    //ListAdapter adapter;
    private String mCurFilter = null;
    private Boolean mEditMode = false;
    private final String KEY_MODE = "key_edit_mode";
    private String mEditkey ;
    private OCursorListAdapter adapter;
    private boolean syncRequested = false;


    public enum Type {
       Categories
    }

    private Type mType = Type.Categories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_list);
        OActionBarUtils.setActionBar(this, true);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle("Select Category");
        }

        poscategory = new PosCategory(this, null);
        view=  this.findViewById(android.R.id.content);

        ListView list=(ListView)view.findViewById(R.id.list_category);
        list.setFastScrollAlwaysVisible(false);
        adapter = new OCursorListAdapter(Categories.this, null, R.layout.categies_of_product);
        adapter.setOnViewBindListener(this);
        System.out.println("View="+view);
        list.setAdapter(adapter);
        System.out.println("Create");
        mType = Type.Categories;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ODataRow row = OCursorUtils.toDatarow((Cursor)adapter.getItem(i));
               int id1=row.getInt("_id");
                String name=row.getString("name");
                System.out.println("CategoryName="+name);
                System.out.println("Id="+ id1);
                Intent intn=getIntent();
                intn.putExtra("IdCategory", id1);
               intn.putExtra("categoryname",name);
                setResult(Activity.RESULT_OK, intn);
                System.out.println("value of count: " + id1);
                Product product=new Product();
               FragmentTransaction transaction = getFragmentManager().beginTransaction();
                finish();

            }
        });
        if (extras == null)
            mEditMode = true;
        if (savedInstanceState != null) {
            mEditkey = savedInstanceState.getString(EXTRA_KEY_TYPE);
            mType = Type.valueOf(extra(mType).getString(EXTRA_KEY_TYPE));

        }
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStatusChange(Boolean refreshing) {
        getSupportLoaderManager().restartLoader(0, null, this);
        System.out.println("Status changed");
    }
    public Bundle extra(Type type) {
        Bundle extra = new Bundle();
        extra.putString(KEY_MODE, type.toString());
        return extra;
    }
    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {

        OControls.setText(view,R.id.category_name,row.getString("name"));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
        String where = "";
        System.out.println("Loader Create");
        List<String> args = new ArrayList<>();
        switch (mType) {
            case Categories:
                where = "name";
               break;


        }
        args.add("true");
        String selection = (args.size() > 0) ? where : null;
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(this, poscategory.uri(),
                null, null, null, "name");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
       adapter.changeCursor(data);
        if (data.getCount() > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(view, R.id.loadingProgress1);
                                   }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(view, R.id.loadingProgress1);

                }
            }, 500);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void loadActivity(ODataRow row) {
        Bundle data = null;
        if (row != null) {
            data = row.getPrimaryBundleData();

        }

    }

}