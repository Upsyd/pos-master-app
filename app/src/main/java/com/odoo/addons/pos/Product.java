package com.odoo.addons.pos;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.odoo.core.utils.BitmapUtils;
import com.odoo.core.utils.IntentUtils;
import com.odoo.core.utils.OActionBarUtils;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 8/12/2015.
 */
public class Product extends BaseFragment implements ISyncStatusObserverListener,LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener,
        OCursorListAdapter.OnViewBindListener, IOnSearchViewChangeListener,
        AdapterView.OnItemClickListener {
    // AdapterView.OnItemClickListener,OCursorListAdapter.OnViewBindListener, View.OnClickListener{
    public static final String KEY = Product.class.getSimpleName();
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    private View mView;
    ActionBar mActionBar;

    // ActionBar bar;
    //    private ListView mPartnersList = null;
    private String mCurFilter = null;
    private boolean syncRequested = false;
    private OCursorListAdapter listAdapter;
    private GridView gv = null;
    private ArrayList<PosOrder> myList;
    PosOrder posOrder;
    int counter = 0;
    TextView cart;
    TextView hotlist_icon;
    MenuItem menuItem;
    public int count=0;
    public int listViewClickCounter=0;


    static int mNotifCount = 0;


    // private ProductTemplate  producttemp;

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
        //mPartnersList.setFastScrollAlwaysVisible(true);
        //  bar.setCustomView(R.layout.action_bar_cart_icon);
        setHasSwipeRefreshView(view, R.id.swipe_container, this);
        mView = view;
        mType = Type.valueOf(getArguments().getString(EXTRA_KEY_TYPE));
        //mPartnersList = (ListView) mView.findViewById(R.id.listview);
        gv = (GridView) mView.findViewById(R.id.gridView1);
//        listAdapter = new OCursorListAdapter(getActivity(), null, R.layout.categories_row_item);
        listAdapter = new OCursorListAdapter(getActivity(), null, R.layout.product_custom_list);
        gv.setAdapter(listAdapter);
//        listAdapter.setHasSectionIndexers(true, "name");
        listAdapter.setOnViewBindListener(this);
        gv.setOnItemClickListener(this);
        gv.setFastScrollAlwaysVisible(false);
        myList = new ArrayList<PosOrder>();
        //producttemp = new ProductTemplate(getActivity(),null);
        //setHasFloatingButton(view, R.id.fabButton, gv, this);
        setHasSyncStatusObserver(KEY, this, db());
        getLoaderManager().initLoader(0, null, this);
        //initMenuBar();

    }


    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
        Bitmap img;
        if (row.getString("image").equals("false")) {
            img = BitmapFactory.decodeResource(getResources(), R.drawable.ic_odoo_icon);
            // img =    myImageView.setImageResource(R.drawable.icon);
            // setImageDrawable(getResources().getDrawable(R.drawable.ic_));
            // img = BitmapUtils.getAlphabetImage(getActivity(), row.getString("name"));
        } else {
            img = BitmapUtils.getBitmapImage(getActivity(), row.getString("image"));
        }

        //z // OControls.setImage(view, R.id.image_small, img);
        // OControls.setText(view, R.id.gridView1, row.getString("pos_categ_id"));
        OControls.setImage(view, R.id.productimag, img);
        OControls.setText(view, R.id.posname, row.getString("name"));
        OControls.setText(view, R.id.posprice, row.getFloat("list_price").toString());
        // OControls.setText(view,R.id.posid, row.getInt("id"));
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
            case Product:
                where = "_is_active = ?";
                // where = " = ?";
                break;

        }
        args.add("true");
        if (mCurFilter != null) {
            where += " and name like ? ";
            args.add(mCurFilter + "%");
        }
        String selection = (args.size() > 0) ? where : null;
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(getActivity(), db().uri(),
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
    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(ProductTemplate.AUTHORITY);
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
        // IntentUtils.startActivity(getActivity(), ProductList.class, data);

    }

//    public void showOrder() {
//
//        myList = new ArrayList<PosOrder>();
//       // final PosOrder pos = (PosOrder) getApplicationContext();
//        com.odoo.addons.pos.PosOrder pos = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos1 = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos2 = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos3 = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos4 = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos5 = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos6 = new com.odoo.addons.pos.PosOrder();
//        com.odoo.addons.pos.PosOrder pos7 = new com.odoo.addons.pos.PosOrder();
//
//
//        pos.setProductName("Mobile");
//        pos.setProductPrize(100);
//        pos.setProductQntity(1);
//        pos1.setProductName("Laptop");
//        pos1.setProductPrize(200);
//        pos1.setProductQntity(1);
//        pos2.setProductName("Watch");
//        pos2.setProductPrize(300);
//        pos2.setProductQntity(1);
//        pos3.setProductName("Ball");
//        pos3.setProductPrize(400);
//        pos3.setProductQntity(1);
//        pos4.setProductName("T.v");
//        pos4.setProductPrize(500);
//        pos4.setProductQntity(1);
//        pos5.setProductName("Note");
//        pos5.setProductPrize(600);
//        pos5.setProductQntity(1);
//        pos6.setProductName("Pen");
//        pos6.setProductPrize(700);
//        pos6.setProductQntity(1);
//        pos7.setProductName("Bag");
//        pos7.setProductPrize(800);
//        pos7.setProductQntity(1);
//
//
//        myList.add(pos);
//        myList.add(pos1);
//        myList.add(pos2);
//        myList.add(pos3);
//        myList.add(pos4);
//        myList.add(pos5);
//        myList.add(pos6);
//        myList.add(pos7);
//
//        Intent intent = new Intent(getActivity(), Order.class);
//        // intent.putExtra("i",myList);
//        Bundle mBundle = new Bundle();
//        mBundle.putSerializable("cart_details", myList);
//        intent.putExtras(mBundle);
//        getActivity().startActivity(intent);
//    }


    // final PosOrder finalRowItem = posOrder;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ODataRow row = OCursorUtils.toDatarow((Cursor) listAdapter.getItem(position));
//        loadActivity(row);
        // Toast.makeText(getActivity()," Select view"+view, Toast.LENGTH_LONG).show();
        System.out.println("View ==" + view);
        // Toast.makeText(getActivity()," Select parent"+parent, Toast.LENGTH_LONG).show();
        System.out.println("Parent ==" + parent);
        //Toast.makeText(getActivity()," Select position"+position, Toast.LENGTH_LONG).show();
        System.out.println("Position==" + position);
        //Toast.makeText(getActivity()," Select row"+row, Toast.LENGTH_LONG).show();
        System.out.println("Parent ==" + row);


        TextView product_name, product_price;
        //product_id;
        // final ViewHolder viewHolderFinal = holder;
        //final PosOrder finalRowItem = myList;

        product_name = (TextView) view.findViewById(R.id.posname);
        product_price = (TextView) view.findViewById(R.id.posprice);
        // product_id =  (TextView)view.findViewById(R.id.posid);
        PosOrder pos = new PosOrder();
        // pos.setProductId(product_id.getText().toString());
        //String strid = product_id.getText().toString();
        //  Integer nt=Integer.parseInt(strid);
        pos.setProductId(row.getInt(OColumn.ROW_ID));

        //System.out.println("ID" + _id);
        pos.setProductName(product_name.getText().toString());
        String strprize = product_price.getText().toString();
        Float f = Float.parseFloat(strprize);
        pos.setProductPrize(f);
        pos.setProductQntity(1);
        Log.i("SomeTag", "Persons: " + gv.getCount());
        count++;
        String countString=String.valueOf(count);
        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1),
                Toast.LENGTH_SHORT);
//        Toast.makeText(getActivity(),
//                countString, Toast.LENGTH_LONG).show();
        menuItem.setTitle(countString);
//        listViewClickCounter++;
//        changeActionBarTitle();

        //getItemId();




//            int quantity = pos .getProductQntity(); // get the quantity for this row item
//            pos.setProductQntity(quantity + 1);
//            System.out.println("Quantity ="+quantity);
        // pos.setProductQntity();
//
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
//
//        if (myList.indexOf(pos) >= 0) {
//            Toast.makeText(getActivity(), "ID Avaliable", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getActivity(), "ID Not  Avaliable", Toast.LENGTH_LONG).show();
//
//        }


    }


    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_partner_product, menu);
        RelativeLayout badgeLayout = (RelativeLayout)menu.findItem(R.id.shopping_cart).getActionView();
        badgeLayout.setActivated(true);
        TextView tv = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText("12");
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();


//        getActivity().getMenuInflater().inflate(R.menu.menu_partner_product, menu);
//       menuItem = menu.findItem(R.id.badge);
        //menuItem.setTitle("menuItem");
        //I suppose you create custom ActionBar like this
       // final View addView = getActivity().getLayoutInflater().inflate(R.layout.action_bar_cart_icon, null);
      //  mActionBar.setCustomView(addView);
     //   hotlist_icon=(TextView)addView.findViewById(R.id.hotlist_hot);
      //  hotlist_icon.setText("Whatever");



      //citem = (ClipData.Item) MenuItemCompat.getActionView(searchItem);
       // RelativeLayout badgeLayout = (RelativeLayout) menu.findItem(R.id.badge).getActionView();
      //  hotlist_icon = (TextView) badgeLayout.findViewById(R.id.hotlist_hot);

        setHasSearchView(this, menu, R.id.menu_partner_search);

    }
//    public void changeActionBarTitle() {
//
//       menuItem.setText("number of clicks =" + String.valueOf(listViewClickCounter));
//    }

    //}, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        setHasOptionsMenu(true);
//        inflater.inflate(R.menu.menu_partner_product, menu);
       // setHasSearchView(this, menu, R.id.menu_partner_search);


//    public void initMenuBar() {
//
////        getActivity().getActionBar().setBackgroundDrawable(
////                new ColorDrawable(Color.parseColor("#0077d1")));
//        ActionBar mActionBar = ((OdooActivity)getActivity()).getSupportActionBar();
////        getActivity().getActionBar().setIcon(
////                new ColorDrawable(getResources().getColor(
////                        android.R.color.transparent)));
//
//        mActionBar.setDisplayShowHomeEnabled(true);
//        mActionBar.setDisplayShowTitleEnabled(true);
//        LayoutInflater mInflater = LayoutInflater.from(getActivity());
//        View mCustomView = mInflater.inflate(R.layout.action_bar_cart_icon, null);
//        mActionBar.setCustomView(mCustomView);
//        mActionBar.setDisplayShowCustomEnabled(false);
//
//        mCustomView.setMinimumWidth(100);
//        ImageView hotlist_icon = (ImageView) getActivity().findViewById(
//                R.id.hotlist_bell);
//
//    }
//        ActionBar actionBar = getActivity().getActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.action_bar_cart_icon);
//
        //ImageView hotlist_icon = (ImageView) getActivity().findViewById(R.id.hotlist_bell);
//        hotlist_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //  toggle();
//                Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_LONG).show();
//            }
//        });
//        actionBar.show();
//    }
//        View count = menu.findItem(R.id.badge).getActionView();
//       cart = (TextView) count.findViewById(R.id.hotlist_hot);
//      //  cart.setText(String.valueOf(mNotifCount));
//       // return super.onCreateOptionsMenu();
//    }
//
//    private void setNotifCount(int count) {
//        mNotifCount = count;
//
//     // invalidateOptionsMenu();
//    }
//        //MenuInflater menuInflater = getSupportMenuInflater();
//       // inflater.inflate(R.menu.menu_partner_product, menu);
//    setHasSearchView(this, menu, R.id.menu_partner_search);

        // final View menu_hotlist = menu.findItem(R.id.menu_hotlist).getActionView();
        //ui_hot = (TextView) menu_hotlist.findViewById(R.id.hotlist_hot);
        //updateHotCount(hot_number);
        // new MyMenuItemStuffListener(menu_hotlist, "Show hot message") {
        //  @Override
        // public void onClick(View v) {
        //onHotlistSelected();

//        };
//        inflater.inflate(R.menu.menu_partner_product, menu);
//        setHasSearchView(this, menu, R.id.menu_partner_search);
//    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        public boolean onSearchViewTextChange (String newFilter){
            mCurFilter = newFilter;
            getLoaderManager().restartLoader(0, null, this);
            // Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
            return true;
        }

        @Override
        public void onSearchViewClose () {
            // nothing to do
        }

    }

//}

