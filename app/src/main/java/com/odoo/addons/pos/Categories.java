package com.odoo.addons.pos;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.odoo.R;
import com.odoo.addons.pos.models.PosCategory;
import com.odoo.addons.pos.models.ProductTemplate;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.IOnSearchViewChangeListener;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.IntentUtils;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 7/21/2015.
 */
public class Categories extends BaseFragment implements  ISyncStatusObserverListener,LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener,
        OCursorListAdapter.OnViewBindListener, IOnSearchViewChangeListener, View.OnClickListener,
        AdapterView.OnItemClickListener {
   // AdapterView.OnItemClickListener,OCursorListAdapter.OnViewBindListener, View.OnClickListener{
    public static final String KEY = Categories.class.getSimpleName();
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    private View mView;
//    private ListView mPartnersList = null;
    private String mCurFilter = null;
    private boolean syncRequested = false;
    private OCursorListAdapter listAdapter;
   private  GridView gv = null;
   private ProductTemplate  producttemp;

    @Override
    public void onStatusChange(Boolean refreshing) {
        // Sync Status
       getLoaderManager().restartLoader(0, null, this);

    }

    public enum Type {
        Categories
    }
    private Type mType = Type.Categories;

    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> items = new ArrayList<>();
        items.add(new ODrawerItem(KEY).setTitle("Categories")
                .setIcon(R.drawable.ic_action_customers)
        .setExtra(extra(Type.Categories))
                .setInstance(new Categories()));


        return items;
    }

    @Override
    public Class<PosCategory> database() {
        return PosCategory.class;
    }

    public Bundle extra(Type type) {
        Bundle extra = new Bundle();
        extra.putString(EXTRA_KEY_TYPE, type.toString());
        return extra;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setHasSyncStatusObserver(KEY, this, db());
        return inflater.inflate(R.layout.product_list, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mPartnersList.setFastScrollAlwaysVisible(true);

        setHasSwipeRefreshView(view, R.id.swipe_container, this);
        mView = view;
        mType = Type.valueOf(getArguments().getString(EXTRA_KEY_TYPE));
       //mPartnersList = (ListView) mView.findViewById(R.id.listview);
        gv = (GridView) mView.findViewById(R.id.gridView1);
        listAdapter = new OCursorListAdapter(getActivity(), null, R.layout.categories_row_item);
        gv.setAdapter(listAdapter);
//        listAdapter.setHasSectionIndexers(true, "name");
        listAdapter.setOnViewBindListener(this);
        gv.setOnItemClickListener(this);
        gv.setFastScrollAlwaysVisible(true);
        producttemp = new ProductTemplate(getActivity(),null);
       // setHasFloatingButton(view, R.id.fabButton, gv, this);
        setHasSyncStatusObserver(KEY, this, db());
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
//        Bitmap img;
//        if (row.getString("image_small").equals("false")) {
//            img = BitmapUtils.getAlphabetImage(getActivity(), row.getString("po"));
//        } else {
//            img = BitmapUtils.getBitmapImage(getActivity(), row.getString("image_small"));
//        }

      //z // OControls.setImage(view, R.id.image_small, img);
       // OControls.setText(view, R.id.gridView1, row.getString("pos_categ_id"));
       OControls.setText(view, R.id.text1, row.getString("name"));
//        OControls.setText(view, R.id.complete_name, (row.getString("complete_name").equals("false"))
//                ? " " : row.getString("complete_name"));
//        OControls.setText(view, R.id.complete_name, row.getString("complete_name").equals("false"))
//         ? "" : row.getString("complete_name"));
       // OControls.setText(view, R.id.complete_name, (row.getString("complete_name").equals("false") ? " " : row.getString("complete_name")));
       // OControls.setText(view, R.id.email, (row.getString("email").equals("false") ? " "
              //  : row.getString("email")));
   }
//       OControls.setText(view, android.R.id.text1, row.getString("name"));
//
//       OControls.setText(view, android.R.id.text2, row.getString("complete_name"));
////      Bitmap img;
//        if (row.getString("image_small").equals("false")) {
//            img = BitmapUtils.getAlphabetImage(getActivity(), row.getString("name"));
//        }
//  } else {
//        img = BitmapUtils.getBitmapImage(getActivity(), row.getString("image_small"));
//    //OControls.setImage(view, R.id.image_small, img);//   }

    //OControls.setText(view, R.id.name, row.getString("name"));
   //OControls.setText(view, R.id.complete_name, row.getString("complete_name"));//.equals("false"))
    // ? "" : row.getString("complete_name"));
  //OControls.setText(view, R.id.sequence, row.getString("sequence"));
  //  .equals("false") ? " "
               // : row.getString("sequence")));



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
        String where = "";
        List<String> args = new ArrayList<>();
        switch (mType) {
            case Categories:
                where = "image_small = ?";
               // where = " = ?";
                break;

        }
        args.add("false");
        if (mCurFilter != null) {
            where += " and name like ? ";
            args.add(mCurFilter + "%");
        }
        String selection = (args.size() > 0) ? where : null;
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(getActivity(), producttemp.uri(),
                null, selection, selectionArgs, "name");

     ///        return new CursorLoader(getActivity(), db().uri(), null, null, null, null);

}

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        listAdapter.changeCursor(data);
        if (data.getCount() > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setVisible(mView, R.id.swipe_container);
                    OControls.setGone(mView, R.id.product_no_items);
                  setHasSwipeRefreshView(mView, R.id.swipe_container, Categories.this);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setGone(mView, R.id.swipe_container);
                    OControls.setVisible(mView, R.id.product_no_items);
                   setHasSwipeRefreshView(mView, R.id.product_no_items, Categories.this);
                    OControls.setImage(mView, R.id.icon, R.drawable.ic_action_customers);
                    OControls.setText(mView, R.id.title, _s(R.string.label_no_pro_found));
                    OControls.setText(mView, R.id.subTitle, "");
                }
            }, 500);

            if (db().isEmptyTable() && !syncRequested) {
                // Request for sync
                syncRequested = true;
                onRefresh();
            }

        }
    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(PosCategory.AUTHORITY);
            setSwipeRefreshing(true);
        } else {
            hideRefreshingProgress();
            //Toast.makeText(getActivity(), _s(R.string.toast_network_required), Toast.LENGTH_LONG)
                    //.show();
        }
        }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        listAdapter.changeCursor(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabButton:
                loadActivity(null);
                break;
        }
    }

    private void loadActivity(ODataRow row) {
        Bundle data = null;
        if (row != null) {
            data = row.getPrimaryBundleData();
        }
        IntentUtils.startActivity(getActivity(), CategoriesDetails.class, data);
        //Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ODataRow row = OCursorUtils.toDatarow((Cursor) listAdapter.getItem(position));
      //  Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
        loadActivity(row);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_partners, menu);
        setHasSearchView(this, menu, R.id.menu_partner_search);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
        switch (item.getItemId())
        {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSearchViewTextChange(String newFilter) {
        mCurFilter = newFilter;
        getLoaderManager().restartLoader(0, null, this);
       // Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onSearchViewClose() {
        // nothing to do
    }


}