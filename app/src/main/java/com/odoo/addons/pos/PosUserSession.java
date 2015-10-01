package com.odoo.addons.pos;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.App;
import com.odoo.OdooActivity;
import com.odoo.R;
import com.odoo.addons.pos.models.PosSession;
import com.odoo.addons.pos.models.PosSessionOpening;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.OValues;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.IOnSearchViewChangeListener;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.OActionBarUtils;
import com.odoo.core.utils.OFragmentUtils;
import com.odoo.core.utils.OdooRecordUtils;

import java.util.ArrayList;
import java.util.List;

import odoo.helper.OArguments;

/**
 * Created by My on 8/12/2015.
 */
public class PosUserSession extends BaseFragment implements ISyncStatusObserverListener, LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener,
        OCursorListAdapter.OnViewBindListener, View.OnClickListener {
    // AdapterView.OnItemClickListener,OCursorListAdapter.OnViewBindListener, View.OnClickListener{
    public static final String KEY = PosUserSession.class.getSimpleName();
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    private View mView;
    ActionBar mActionBar;

    private String mCurFilter = null;
    private boolean syncRequested = false;
    //    private OCursorListAdapter listAdapter;
//    private ArrayList<PosOrder> myList;
    PosOrder posOrder;
    App mApp;

    static int mNotifCount = 0;


    // private ProductTemplate  producttemp;

    public enum Type {
        Session
    }

    private Type mType = Type.Session;

    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> items = new ArrayList<>();
        items.add(new ODrawerItem(KEY).setTitle("POS")
                .setIcon(R.drawable.ic_pos_icon2)
                .setExtra(extra(Type.Session))
                .setInstance(new PosUserSession()));


        return items;
    }

    @Override
    public Class<PosSessionOpening> database() {
        return PosSessionOpening.class;
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
        return inflater.inflate(R.layout.pos_user_session, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mPartnersList.setFastScrollAlwaysVisible(true);
        //  bar.setCustomView(R.layout.action_bar_cart_icon);
        setHasSwipeRefreshView(view, R.id.swipe_container, this);
        mView = view;
        mApp = (App) getActivity().getApplicationContext();

        ActionBar actionBar = ((OdooActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar_pos);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView actionbarTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        actionbarTitle.setText("POS");
//        mActionBar = ((OdooActivity)getActivity()).getSupportActionBar();
//        if(mActionBar != null){
////            mActionBar.getCustomView().setVisibility(View.INVISIBLE);
//            mActionBar.setHomeButtonEnabled(true);
//            mActionBar.setDisplayHomeAsUpEnabled(true);
//        }
        setHasSyncStatusObserver(KEY, this, db());
        getLoaderManager().initLoader(0, null, this);
        //initMenuBar();

    }


    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
//        Bitmap img;
//        if (row.getString("image").equals("false")) {
//            img = BitmapFactory.decodeResource(getResources(), R.drawable.ic_odoo_icon);
//            // img =    myImageView.setImageResource(R.drawable.icon);
//            // setImageDrawable(getResources().getDrawable(R.drawable.ic_));
//            // img = BitmapUtils.getAlphabetImage(getActivity(), row.getString("name"));
//        } else {
//            img = BitmapUtils.getBitmapImage(getActivity(), row.getString("image"));
//        }
//
//        OControls.setImage(view, R.id.productimag, img);
//        OControls.setText(view, R.id.posname, row.getString("name"));
//        OControls.setText(view, R.id.posprice, row.getFloat("list_price").toString());
//
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
        String where = "";
        List<String> args = new ArrayList<>();
        switch (mType) {
            case Session:
                where = "_is_active = ?";
                // where = " = ?";
                break;

        }
//        args.add(user().getUserId().toString());
        args.add("true");
//        if (mCurFilter != null) {
//            where += " and name like ? ";
//            args.add(mCurFilter + "%");
//        }
        String selection = (args.size() > 0) ? where : null;
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
//        return new CursorLoader(getActivity(), db().uri(),
//                null, selection, selectionArgs, "name");

        return new CursorLoader(getActivity(), db().uri(), null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        listAdapter.changeCursor(data);
        Button btnNewSession = (Button) mView.findViewById(R.id.btnNewSession);
        Button btnResumeSession = (Button) mView.findViewById(R.id.btnResumeSession);
        Button btnCloseSession = (Button) mView.findViewById(R.id.btnCloseSession);
        checkPOSSession();

        if (data != null && data.getCount() > 0) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    OControls.setGone(mView, R.id.loadingProgress);
//                    OControls.setVisible(mView, R.id.swipe_container);
//                    OControls.setGone(mView, R.id.product_no_items);
            setHasSwipeRefreshView(mView, R.id.swipe_container, PosUserSession.this);
            btnNewSession.setVisibility(View.GONE);
            btnResumeSession.setVisibility(View.VISIBLE);
            btnCloseSession.setVisibility(View.VISIBLE);

            btnResumeSession.setOnClickListener(this);
            btnCloseSession.setOnClickListener(this);
//                }
//            }, 500);
        } else {
            btnNewSession.setVisibility(View.VISIBLE);
            btnResumeSession.setVisibility(View.GONE);
            btnCloseSession.setVisibility(View.GONE);

            btnNewSession.setOnClickListener(this);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    OControls.setGone(mView, R.id.loadingProgress);
//                    OControls.setGone(mView, R.id.swipe_container);
//                    OControls.setVisible(mView, R.id.product_no_items);
//                    setHasSwipeRefreshView(mView, R.id.product_no_items, PosUserSession.this);
//                    OControls.setImage(mView, R.id.icon, R.drawable.ic_pos_icon2);
//                    OControls.setText(mView, R.id.title, _s(R.string.label_no_pro_found));
//                    OControls.setText(mView, R.id.subTitle, "");
//            Button btnCloseSession = (Button)mView.findViewById(R.id.btnCloseSession);
//            btnCloseSession.setOnClickListener(this);
//            btnCloseSession.setText("New Session");
//                }
//            }, 500);


//            if (db().isEmptyTable() && !syncRequested) {
//                // Request for sync
//                syncRequested = true;
//                onRefresh();
//            }

        }
    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(PosSessionOpening.AUTHORITY);
            setSwipeRefreshing(true);
        } else {
            hideRefreshingProgress();
            //Toast.makeText(getActivity(), _s(R.string.toast_network_required), Toast.LENGTH_LONG)
            //.show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        listAdapter.changeCursor(null);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fabButton:
//                loadActivity(null);
//                break;
//        }
//    }

    @Override
    public void onStatusChange(Boolean refreshing) {
        // Sync Status
        getLoaderManager().restartLoader(0, null, this);

    }

    @Override
    public void onClick(View view) {
                PosSessionOpening posSessionOpening;
        OModel model = OModel.get(getActivity(), "pos.session", user().getUsername());
        System.out.println("Model--> "+ model.getModelName());

        switch (view.getId()) {
            case R.id.btnNewSession:
//                posSessionOpening = new PosSessionOpening(getActivity(), null);
//                System.out.println(posSessionOpening.getServerDataHelper().callMethod("open_session_cb", null));

                OValues dataValues = new OValues();/*
                dataValues.put("user_id", model.getUser().getUserId());
//                dataValues.put("currency_id", "1");

                int result = model.getServerDataHelper().createOnServer((OdooRecordUtils.toRecordValues(dataValues.toDataRow())));
                System.out.println("Session inserted:--- "+result);*/

                loadActivity(null);
                break;
            case R.id.btnResumeSession:
               /* posSessionOpening = new PosSessionOpening(getActivity(), null);
                OArguments arguments = new OArguments();
                OValues values = new OValues();
                values.put("pos_state", "0");
                values.put("pos_state", "progress");
                arguments.add(values);
                posSessionOpening.getServerDataHelper().callMethod("open_session_cb", arguments);*/
                loadActivity(null);
                break;
            case R.id.btnCloseSession:
                checkPOSSession();
                break;
        }
    }

    private void checkPOSSession() {
        PosSessionOpening posSessionOpening = new PosSessionOpening(getActivity(), null);
//        List<ODataRow> sessionData =  posSession.select(new String[]{"name"}, "user_id = ?", new String[]{user().toString()});
//        ODataRow sessionData = posSessionOpening.browse(new String[]{"pos_state_str"}, "user_id = ?", new String[]{posSessionOpening.getUser().getUserId().toString()});
        ODataRow sessionData = posSessionOpening.browse(new String[]{"pos_state_str"}, null, null);
        if (sessionData != null && sessionData.size() > 0) {
            System.out.println("Resume Session: "+sessionData.getString("pos_state_str"));
            Toast.makeText(getActivity(), "Resume Session: "+sessionData.getString("pos_state_str"), Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("New Session");
            Toast.makeText(getActivity(), "New Session..", Toast.LENGTH_SHORT).show();

        }
    }

    private void loadActivity(ODataRow row) {
        Bundle data = null;
        if (row != null) {
            data = row.getPrimaryBundleData();

        }
        OFragmentUtils fragmentUtils = new OFragmentUtils((OdooActivity) getActivity(), data);
        fragmentUtils.startFragment(new Product(), true, data);

//        IntentUtils intentUtils = new IntentUtils();
//        intentUtils.startActivity(getActivity(), ProductList.class, data);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        //inflater.inflate(R.menu.menu_partners, menu);
      //  getActivity().getMenuInflater().inflate(R.menu.menu_partner_product, menu);
//        RelativeLayout badgeLayout = (RelativeLayout)menu.findItem(R.id.shopping_cart).getActionView();
//        badgeLayout.setActivated(true);
//        TextView tv = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
//        tv.setText("12");


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

//        setHasSearchView(this, menu, R.id.menu_partner_search);

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
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onSearchViewTextChange(String newFilter) {
//        mCurFilter = newFilter;
//        getLoaderManager().restartLoader(0, null, this);
//        // Toast.makeText(getActivity()," No select itemt", Toast.LENGTH_LONG).show();
//        return true;
//    }
//
//    @Override
//    public void onSearchViewClose() {
//        // nothing to do
//    }

}

//}

