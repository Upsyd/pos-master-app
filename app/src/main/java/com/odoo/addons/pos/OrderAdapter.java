package com.odoo.addons.pos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.odoo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshad on 8/13/2015.
 */
public class OrderAdapter  extends ArrayAdapter<PosOrder>{
    private List<PosOrder> orderItem;

    private Context context;
    public OrderAdapter(Context context, int textViewResourceId,
                              List<PosOrder> orderItem) {
        super(context, textViewResourceId, orderItem);
        this.context = context;
        this.orderItem = orderItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.order_row_list, null);
        }
//        ArrayList<PosOrder> array = new ArrayList<PosOrder>();
//        array = (ArrayList<PosOrder>) convertView.getSerializableExtra("cart_details");
       // Order orderlist= new Order();
        float prizetotal = 0;

//
        PosOrder pos=orderItem.get(position);


        if (pos != null) {
            // name
            TextView prdctname = (TextView) view.findViewById(R.id.productName);
            prdctname.setText( pos.getProductName());
            //(String.valueOf(prizetotal))

            prizetotal = (pos.getProductQntity() * pos.getProductPrize());




//


            TextView prdctPrize = (TextView) view.findViewById(R.id.amount_total);
            prdctPrize.setText(String.valueOf(prizetotal));
            pos.getProductPrize();
            /*prdctname.setCompoundDrawablesWithIntrinsicBounds(0,
                    pos.getCartoonPicRes(), 0, 0);*/
            TextView Rs=(TextView)view.findViewById(R.id.currency_symbol);
            Rs.setText("Rs.");

            TextView Qunt=(TextView)view.findViewById(R.id.prdctPrize);
            Qunt.setText(String.valueOf(pos.getProductQntity()) + " Unit(s) at " + (String.valueOf(pos.getProductPrize()) + " Rs." + "/Unit(s)"));
        }
        return view;
    }

}
