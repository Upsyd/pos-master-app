package com.odoo.addons.pos;

import android.app.Activity;


import android.app.Fragment;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.OdooActivity;
import com.odoo.R;
import com.odoo.addons.pos.models.PosCategory;

import com.odoo.addons.pos.models.ProductTemplate;


import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.OValues;
import com.odoo.core.orm.annotation.Odoo;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.IOnSearchViewChangeListener;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.BitmapUtils;
import com.odoo.core.utils.IntentUtils;
import com.odoo.core.utils.OActionBarUtils;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;
import com.odoo.core.utils.OFragmentUtils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 8/12/2015.
 */
public class Product extends BaseFragment implements ISyncStatusObserverListener, LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener,
        OCursorListAdapter.OnViewBindListener, IOnSearchViewChangeListener,
        AdapterView.OnItemClickListener {

    public static final String KEY = Product.class.getSimpleName();
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    public static final int REQUEST_CODE_CART = 1;
    public static final int REQUEST_CODE_CATEGORY = 2;
    private View mView;
    ActionBar mActionBar;
    TextView textCategory;
    private String mCurFilter = null;
    private boolean syncRequested = false;
    private OCursorListAdapter listAdapter;
    private GridView gv = null;
    ImageButton btnCategoryCancel;
    String name;
    LinearLayout categoryLayout;
    private ArrayList<PosOrder> myList;
    // PosOrder posOrder;
    int counter = 0;
    TextView cart;
    TextView hotlist_icon;
    MenuItem menuItem;
    public int count = 0;
    public int listViewClickCounter = 0;
    TextView cartItems;
    ImageView pro_img;
    public Button categoryName;
    int id1;
    static int mNotifCount = 0;


    @Override
    public void onStatusChange(Boolean refreshing) {
        // Sync Status
        getLoaderManager().restartLoader(0, null, this);

    }

    public enum Type {
        Product
    }

    private Type mType = Type.Product;

    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> items = new ArrayList<>();
        items.add(new ODrawerItem(KEY).setTitle("POS")
                .setIcon(R.drawable.ic_pos_icon2)
                .setExtra(extra(Type.Product))
                .setInstance(new Product()));
        return items;
    }

    @Override
    public Class<ProductTemplate> database() {
        return ProductTemplate.class;
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
        setHasSwipeRefreshView(view, R.id.swipe_container, this);
        mView = view;
        mType = Type.Product;
        btnCategoryCancel = (ImageButton) view.findViewById(R.id.btnCategoryCancel);
        categoryLayout=(LinearLayout)view.findViewById(R.id.categoryLayout);
        gv = (GridView) mView.findViewById(R.id.gridView1);
        listAdapter = new OCursorListAdapter(getActivity(), null, R.layout.product_custom_list);
        gv.setAdapter(listAdapter);
        listAdapter.setOnViewBindListener(this);
        gv.setOnItemClickListener(this);
        categoryName = (Button) mView.findViewById(R.id.categoryButton);
        textCategory = (TextView) mView.findViewById(R.id.txtCategory);
        gv.setFastScrollAlwaysVisible(false);
        myList = new ArrayList<PosOrder>();
        setHasSyncStatusObserver(KEY, this, db());
        getLoaderManager().initLoader(0, null, this);
        ActionBar actionBar = ((OdooActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar_cart_icon);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView actionbarTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        actionbarTitle.setText("POS");
        cartItems = (TextView) actionBar.getCustomView().findViewById(R.id.cart_items);
        pro_img = (ImageView) actionBar.getCustomView().findViewById(R.id.ic_cart);
        pro_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Order.class);
//              intent.putExtra("i",myList);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("cart_details", myList);
                mBundle.putInt("items_count", count);
                intent.putExtras(mBundle);
                startActivityForResult(intent, REQUEST_CODE_CART);
                //  Toast.makeText(getActivity(), " Click Cart", Toast.LENGTH_LONG).show();
//
            }
        });
    }

    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
        Bitmap img;
        if (row.getString("image").equals("false")) {
            img = BitmapFactory.decodeResource(getResources(), R.drawable.ic_odoo_icon);

        } else {
            img = BitmapUtils.getBitmapImage(getActivity(), row.getString("image"));
        }
        OControls.setImage(view, R.id.productimag, img);
        OControls.setText(view, R.id.posname, row.getString("name"));
        OControls.setText(view, R.id.posprice, row.getFloat("list_price").toString());
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
       /* id1 = getActivity().getArguments().getInt("CategoryId",0);
        System.out.println("Id="+id1);*/
        String where = "";
        List<String> args = new ArrayList<>();
        //Bundle args1 = this.getArguments();
        // System.out.println("data="+args1);

        if (id1 == 0) {
            where = "_is_active = ?";
            args.add("true");
        } else /*iint
         ("pos_categ_id".equals(id1))*/ {
            // int i=0;
            where += "pos_categ_id = ?";
            args.add(id1 + "");
            System.out.println("category=");

        }

        // }else
           /* where="pos_categ_id = ?";
          args.add(id1,"");*/
        // }
        //if()
        if (mCurFilter != null) {
            where += " and name like ? ";
            args.add(mCurFilter + "%");
        }
        //String a=String.valueOf( "pos_categ_id".equals(count));
        //String selection =String.valueOf ((args.size() > 0) ? where : );
        // String selection="pos_categ_id=?";
        // String[] projection=new String[]{"pos_categ_id"};
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(getActivity(), db().uri(),
                null, where, selectionArgs, "pos_categ_id");

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
                    setHasSwipeRefreshView(mView, R.id.swipe_container, Product.this);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setGone(mView, R.id.swipe_container);
                    OControls.setVisible(mView, R.id.product_no_items);
                    setHasSwipeRefreshView(mView, R.id.product_no_items, Product.this);
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

        categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Categories.class);
                startActivityForResult(i, REQUEST_CODE_CATEGORY);
                //  loadActivity(null);
               /* FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Categories categories=new Categories();

               ft.replace(R.id.fragment_container,categories).commit();
*/

            }
        });
    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(ProductTemplate.AUTHORITY);
            setSwipeRefreshing(true);
        } else {
            hideRefreshingProgress();
            Toast.makeText(getActivity(), _s(R.string.toast_network_required), Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        listAdapter.changeCursor(null);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fabButton:
//                loadActivity(null);
//                break;
//        }
//    }

    private void loadActivity(ODataRow row) {
        Bundle data = null;
        if (row != null) {
            data = row.getPrimaryBundleData();

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // REQUEST_CODE is defined above
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CART) {
            // Extract name value from result extras
          /*  Bundle bundle = data.getExtras();
            id1= bundle.getInt("Category Id");
            //id1=data.getIntExtra("Category Id",0);
            System.out.println("IdCat="+ id1);*/

           /* count = data.getIntExtra("items_count", 0);
            cartItems.setText(count + "");
            System.out.println("New counter value:" + count);*/
            count = 0;
            myList = (ArrayList<PosOrder>) data.getSerializableExtra("cart_details");
            for (int i = 0; i < myList.size(); i++) {
                PosOrder posOrder = myList.get(i);
                count += posOrder.getProductQntity();
                System.out.println("Update value of cart:-" + count);

            }
            cartItems.setText(count + " ");

        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CATEGORY) {
            Bundle bundle = data.getExtras();
            name = bundle.getString("categoryname");
            System.out.println("categoryname=" + name);
            id1 = bundle.getInt("IdCategory");
            //id1=data.getIntExtra("Category Id",0);
            System.out.println("IdCat=" + id1);
            categoryLayout.setVisibility(View.VISIBLE);
            textCategory.setVisibility(View.VISIBLE);
            btnCategoryCancel.setVisibility(View.VISIBLE);
            btnCategoryCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Product product=new Product();
                    FragmentManager fm=getFragmentManager();
                  fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,product).commit();

                }
            });
        }
        textCategory.setText(name);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ODataRow row = OCursorUtils.toDatarow((Cursor) listAdapter.getItem(position));
//        loadActivity(row);
        // Toast.makeText(getActivity()," Select
//        System.out.println("View ==" + view);
        // Toast.makeText(getActivity()," Select parent"+parent, Toast.LENGTH_LONG).show();
//        System.out.println("Parent ==" + parent);
        //Toast.makeText(getActivity()," Select position"+position, Toast.LENGTH_LONG).show();
//        System.out.println("Position==" + position);
        //Toast.makeText(getActivity()," Select row"+row, Toast.LENGTH_LONG).show();
//        System.out.println("Parent ==" + row);

        PosOrder pos = new PosOrder();
        TextView product_name, product_price;

        product_name = (TextView) view.findViewById(R.id.posname);
        product_price = (TextView) view.findViewById(R.id.posprice);


        pos.setProductId(row.getInt(OColumn.ROW_ID));
        pos.setProductName(product_name.getText().toString());
        String strprize = product_price.getText().toString();
        Float f = Float.parseFloat(strprize);
        pos.setProductPrize(f);
        pos.setProductQntity(1);



        ImageView imgProduct;
        Bitmap bm;
        imgProduct = (ImageView) view.findViewById(R.id.productimag);
        imgProduct.setDrawingCacheEnabled(true);

        imgProduct.buildDrawingCache();
        bm = imgProduct.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        pos.setImage(byteArray);

        Log.i(KEY, "Seleted Product: " + pos.getProductName());
        count++;
        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1),
                Toast.LENGTH_SHORT);
        cartItems.setText(count + "");
        boolean isFound = false;
        for (int i = 0; i < myList.size(); i++) {
            PosOrder posListItem = myList.get(i);
            if (posListItem.getProductId() == pos.getProductId()) {
                posListItem.setProductQntity(posListItem.getProductQntity() + 1);
                System.out.println("item found.");
                isFound = true;
                break;
            }

        }
        if (!isFound) {
            System.out.println("Not found");
            myList.add(pos);


        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        getActivity().getMenuInflater().inflate(R.menu.menu_partner_product, menu);
        setHasSearchView(this, menu, R.id.menu_partner_search);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
        //           switch (item.getItemId()) {
//                case R.id.badge:
//                    //Intent intent = new Intent(getActivity(), .class);
//                    Intent intent = new Intent(getActivity(), Order.class);
//                    // intent.putExtra("i",myList);
//                    Bundle mBundle = new Bundle();
//                    mBundle.putSerializable("cart_details", myList);
//                    intent.putExtras(mBundle);
//                    getActivity().startActivity(intent);
//                    Toast.makeText(getActivity(), " Click Cart", Toast.LENGTH_LONG).show();
//                    return true;
//                default:

        // }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSearchViewTextChange(String newFilter) {
        mCurFilter = newFilter;
        getLoaderManager().restartLoader(0, null, this);

        return true;
    }

    @Override
    public void onSearchViewClose() {
        // nothing to do
    }

}


