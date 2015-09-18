package com.odoo.addons.pos;

import android.annotation.TargetApi;
import android.app.Activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
        poscategory = new PosCategory(this, null);
        view=  this.findViewById(android.R.id.content);

        ListView list=(ListView)view.findViewById(R.id.list_category);
       /* view.setEnabled(false);
        view.setOnClickListener(null);*/
        list.setFastScrollAlwaysVisible(false);
     adapter = new OCursorListAdapter(Categories.this, null, R.layout.categies_of_product);
        adapter.setOnViewBindListener(this);

        System.out.println("View="+view);
        list.setAdapter(adapter);
        System.out.println("Create");
       // mView = view;
       // setHasSwipeRefreshView(view, R.id.swipe_container, this);
       // mView = view;
        mType = Type.Categories;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ODataRow row = OCursorUtils.toDatarow((Cursor)adapter.getItem(i));
               int id1=row.getInt("_id");
                System.out.println("Id="+ id1);
                Intent intn=getIntent();
                intn.putExtra("IdCategory", id1);
                setResult(Activity.RESULT_OK, intn);
                System.out.println("value of count: " + id1);
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
                // where = " = ?";
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
                   // OControls.setVisible(view, R.id.swipe_container);
                    // OControls.setGone(mView, R.id.product_no_items);
                   // setHasSwipeRefreshView(mView, R.id.swipe_container, Categories.this);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(view, R.id.loadingProgress1);
                    //OControls.setGone(view, R.id.swipe_container);
                    // OControls.setVisible(mView, R.id.product_no_items);
                    // setHasSwipeRefreshView(mView, R.id.product_no_items, Categories.this);
                    //OControls.setImage(mView, R.id.icon, R.drawable.ic_pos_icon2);
                    //OControls.setText(mView, R.id.title, _s(R.string.label_no_pro_found));
                   // OControls.setText(mView, R.id.subTitle, "");
                }
            }, 500);



        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    //}



    private void loadActivity(ODataRow row) {
        Bundle data = null;
        if (row != null) {
            data = row.getPrimaryBundleData();

        }
    }

} /*BaseFragment implements ISyncStatusObserverListener,SwipeRefreshLayout.OnRefreshListener,LoaderManager.LoaderCallbacks<Cursor>, OCursorListAdapter.OnViewBindListener
        {
    public static final String KEY = Categories.class.getSimpleName();
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
            public static final int REQUEST_CODE_CART = 1;
            private static int id1;
            private View mView;
            ListView listcategory;
            Context context=this.getActivity();

    private String mCurFilter = null;
    private OCursorListAdapter listAdapter;
    private boolean syncRequested = false;


    public enum Type {
        Categories
    }

    private Type mType = Type.Categories;
    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> items = new ArrayList<>();
        items.add(new ODrawerItem(KEY).setTitle("POS")
                .setIcon(R.drawable.ic_pos_icon2)
                .setExtra(extra(Type.Categories))
                .setInstance(new PosUserSession()));


        return items;
    }

    private Bundle extra(Type type) {
        Bundle extra = new Bundle();
        extra.putString(EXTRA_KEY_TYPE, type.toString());
        return extra;
    }

    @Override
    public Class<PosCategory> database() {
        return PosCategory.class;
    }

    @Override
    public void onStatusChange(Boolean refreshing) {
        getLoaderManager().restartLoader(0, null, this);
    }
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                setHasOptionsMenu(true);
                setHasSyncStatusObserver(KEY, this, db());
                return inflater.inflate(R.layout.categorylist, container, false);
            }
            @Override
            public void onViewCreated(View view, Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);
                setHasSwipeRefreshView(view, R.id.swipe_container, this);
                mView = view;
                mType = Type.Categories;
               listcategory = (ListView) mView.findViewById(R.id.listview);
                listAdapter = new OCursorListAdapter(getActivity(), null, R.layout.pos_category_textview);
                listcategory.setAdapter(listAdapter);
                listAdapter.setOnViewBindListener(this);
                listcategory.setFastScrollAlwaysVisible(false);
                setHasSyncStatusObserver(KEY, this, db());
                getLoaderManager().initLoader(0, null, this);


                listcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view,int i, long l) {

                        ODataRow row = OCursorUtils.toDatarow((Cursor) listAdapter.getItem(i));
                         id1=row.getInt("_id");
                        System.out.println("Id="+ id1);
                        Bundle args = new Bundle();
                        args.putInt("CategoryId", id1);
                        Product product = new Product();
                        product.setArguments(args);
                        FragmentManager fm=getFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.fragment_container,product).commit();
                        System.out.println("Bundle="+args);

                        // args.putString("someTitle", someTitle);
                       // product.setArguments(args);


                        //getActivity().onBackPressed();
                   *//*
                        fm.popBackStack();
                        FragmentTransaction ft = fm.beginTransaction();
                     //newInstance(id1).getReturnTransition();

                        ft.addToBackStack("Back");
                        System.out.println("Back="+ft);*//*

                       *//* if (fm.getBackStackEntryCount() > 0) {
                            fm.popBackStack();
                        }
                       // fm.popBackStack();
                        System.out.println("Fm="+fm);*//*
                       // ft.commit();



                       *//* Bundle bundle =new Bundle();
                        bundle.putInt("Id",id1);
                        Product product=new Product();
                        product.setArguments(bundle);*//*
                       *//* android.support.v4.app.FragmentManager fm=getFragmentManager();
                        fm.putFragment(bundle,"fragment",product);*//*
                        //fm.popBackStack();
                       // product.setArguments(bundle);
                      //  OFragmentUtils fragment=new OFragmentUtils((OdooActivity)getActivity(),null);
                       // Fragment.instantiate(context,Product.class.getName(),bundle);



                        //ft.addToBackStack(Product.class.getName());
                       // ft.commit();


                       //fragmentManager.popBackStack();
                       //ft.show(new Product());
                      *//* ;*//*


                        //System.out.println("set="+ product.setArguments(bundle););
                      // getFragmentManager().popBackStack();





                       // product.setArguments(bundle);



                      //  fragmentManager.beginTransaction().addToBackStack("Id").commit();

                       // fragmentManager.popBackStackImmediate();



                       // product.getActivity().getSupportFragmentManager().beginTransaction().commit();

                       // product.setArguments(bundle);

                      //  fragmentManager.beginTransaction().commit();
                      //


                       // fragment.getFragmentManager().beginTransaction().addToBackStack(null).commit();



                       // frgment.startFragment(new Product(),false,bundle);
                        //System.out.println("Bundle=" + bundle);

                    }
                });
                   }

                @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
        String where = "";
        List<String> args = new ArrayList<>();

        switch (mType) {
            case Categories:
                where = "name";
                // where = " = ?";
                break;


        }
        args.add("true");
        String selection = (args.size() > 0) ? where : null;
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(getActivity(), db().uri(),
                null, null, null, "name");

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
                    // OControls.setGone(mView, R.id.product_no_items);
                    setHasSwipeRefreshView(mView, R.id.swipe_container, Categories.this);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setGone(mView, R.id.swipe_container);
                    // OControls.setVisible(mView, R.id.product_no_items);
                    // setHasSwipeRefreshView(mView, R.id.product_no_items, Categories.this);
                    OControls.setImage(mView, R.id.icon, R.drawable.ic_pos_icon2);
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
   // }
            @Override
            public void onRefresh() {
                if (inNetwork()) {
                    parent().sync().requestSync(ProductTemplate.AUTHORITY);
                    setSwipeRefreshing(true);
                } else {
                    hideRefreshingProgress();
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
    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
        OControls.setText(view,R.id.categoryText,row.getString("name"));

    }
}
*/