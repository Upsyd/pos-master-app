package com.odoo.addons.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.R;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.OActionBarUtils;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshad on 8/7/2015.
 */
public class Order extends AppCompatActivity {

    private OrderAdapter adapter;
    //   public static final String KEY_Name = "product_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_listview);
        OActionBarUtils.setActionBar(this, true);
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null) {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle("Cart Details");
        }
        ListView cart_list = (ListView) findViewById(R.id.listView2);
//        TextView product_name_cart = (TextView) findViewById(R.id.productName);
//        TextView product_price_cart = (TextView) findViewById(R.id.prdctPrize);
        ArrayList<PosOrder> array = new ArrayList<PosOrder>();
        array = (ArrayList<PosOrder>) getIntent().getSerializableExtra("cart_details");

//        List orderList = order.getOtherOrderList(order);
        adapter = new OrderAdapter(this, R.layout.order_row_list, array);
        cart_list.setAdapter(adapter);
        float total=0;
        for (int i = 0; i < array.size(); i++) {
        PosOrder po = new PosOrder();
        po = array.get(i);
//            total +=  (po.getProductPrize()*;
            total += (po.getProductQntity() * po.getProductPrize());
        }
            TextView total1=(TextView)findViewById(R.id.untaxedTotal);
            total1.setText(String.valueOf(total));

        TextView rs1=(TextView)findViewById(R.id.currency1);
        rs1.setText("Rs.");

            TextView tax=(TextView)findViewById(R.id.taxesTotal);
        tax.setText("0.0");

        TextView rs2=(TextView)findViewById(R.id.currency2);
        rs2.setText("Rs.");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}

/*
    private ArrayList<PosOrder> getData() {
        // ArrayList<PosOrder> order = new ArrayList<PosOrder>();
        ArrayList<PosOrder> pos=new ArrayList<PosOrder>();
      PosOrder order = (PosOrder) getIntent().getSerializableExtra("i");
        ArrayList<String> strArray = new ArrayList<String>();


        for (int i = 0; i < array.size(); i++) {
            PosOrder po = new PosOrder();

            strArray.add(array.get(i).getProductName());

        }
        return strArray;
    }
}

       // Bundle extras = this.getIntent().getExtras();

      //  if (extras != null) {

        /*PosOrder po1 = (PosOrder) getIntent().getSerializableExtra("a");
        PosOrder po2 = (PosOrder) getIntent().getSerializableExtra("b");
        PosOrder po3 = (PosOrder) getIntent().getSerializableExtra("c");
*/
          //  System.out.println("d = " + array.get(0).getProductName());
        /*System.out.println("e = " + po1.getProductName());
        System.out.println("f = " + po2.getProductName());
        System.out.println("g = " + po3.getProductName());*/

            // String productName;

//            String a=po.getProductName();
           /* String b=po1.getProductName();
            String c=po2.getProductName();
           String d=po3.getProductName();*/

/*

            }



            ArrayAdapter adapter = new ArrayAdapter(this,
                   R.layout.text,R.id.textView,strArray);
            l.setAdapter(adapter);
*/

          //  String[] array=b.getStringArray(key);





