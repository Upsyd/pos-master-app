package com.odoo.addons.pos;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshad on 8/7/2015.
 */
public class Order extends AppCompatActivity {
    private static final String TAG = Order.class.getName();
    public OrderAdapter adapter;
    public TextView tvPrdctname;
    public TextView tvPrize;
    TextView grandTotalPrize;
    private Context context;
    PosOrder po;
    public TextView amount;
    ArrayList<PosOrder> array;
    float prizevalue;
    ListView l;
    TextView total;
    public EditText etPrdctQumtity;
    public EditText etPrdctPrize;
    public EditText etPrdctDiscount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_listview);
        l = (ListView) findViewById(R.id.list);
        tvPrdctname = (TextView) findViewById(R.id.productName);
        grandTotalPrize = (TextView) findViewById(R.id.grandTotal);
        etPrdctQumtity = (EditText) findViewById(R.id.prdctQuantity);
        etPrdctPrize = (EditText) findViewById(R.id.prdctPrize);
        etPrdctDiscount = (EditText) findViewById(R.id.discount);
        tvPrize = (TextView) findViewById(R.id.NetPrize);
        array = new ArrayList<PosOrder>();
        array = (ArrayList<PosOrder>) getIntent().getSerializableExtra("cart_details");
        adapter = new OrderAdapter(this, R.layout.order_single_row, array);
        l.setAdapter(adapter);

//        PosOrder poss = new PosOrder();

        // poss=adapter.getItem(i);
        NetAmount();
//       total=(TextView)findViewById.id.tvNetPrize);
        TextView rs1 = (TextView) findViewById(R.id.currency1);
        rs1.setText("Rs.");
        final TextView tax = (TextView) findViewById(R.id.taxesTotal);
        tax.setText("0.0");
        TextView rs2 = (TextView) findViewById(R.id.currency2);
        rs2.setText("Rs.");

    }

    public void NetAmount() {

        float grandTotal = 0.0f;

        for (int i = 0; i < adapter.getCount(); i++) {
            PosOrder pos = adapter.getItem(i);
            float Discount = (pos.getProductPrize() * pos.getDiscount() / 100);
            float netPrice = ((pos.getProductPrize() - Discount));
            float productTotalAmount = pos.getProductQntity() * netPrice;
            grandTotal += productTotalAmount;
        }
        grandTotalPrize.setText(grandTotal + "");
    }
    public class OrderAdapter  extends ArrayAdapter<PosOrder> {
        private List<PosOrder> orderItem;

        private Context context;

        TextView Rs;


        public OrderAdapter(Context context, int textViewResourceId,
                            List<PosOrder> orderItem) {
            super(context, textViewResourceId, orderItem);
            this.context = context;
            this.orderItem = orderItem;

        }

        public class ViewHolder {
            TextView PrdctName;
            TextView PrdctNetAmount;
            EditText PrdctQuantity;
            EditText PrdctPrize;
            EditText PrdctDiscount;
            ImageButton ImgDlt;
        }



        @Override
        public View getView( final  int position, View view, ViewGroup parent) {

            final ViewHolder holder;
            PosOrder posOrder = orderItem.get(position);
            float Discount = (posOrder.getProductPrize() * posOrder.getDiscount() / 100);
            float netPrice = ((posOrder.getProductPrize() - Discount));
            float productTotalAmount = posOrder.getProductQntity() * netPrice;
            posOrder.setNetAmount(productTotalAmount);
            if (view == null) {
                holder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.order_single_row, null);

                holder.PrdctQuantity = (EditText) view.findViewById(R.id.prdctQuantity);
                holder.PrdctQuantity.setTag(posOrder);
                holder.PrdctQuantity.setText(String.valueOf(posOrder.getProductQntity()));


                holder.ImgDlt=(ImageButton)view.findViewById(R.id.Imagedelete);
                holder.ImgDlt.setTag(posOrder);


                holder.PrdctName = (TextView) view.findViewById(R.id.productName);
                holder.PrdctName.setText(posOrder.getProductName());


                holder.PrdctPrize = (EditText) view.findViewById(R.id.prdctPrize);
                holder.PrdctPrize.setTag(posOrder);
                holder.PrdctPrize.setText(String.valueOf(posOrder.getProductPrize()));


                holder.PrdctDiscount = (EditText) view.findViewById(R.id.discount);
                holder.PrdctDiscount.setTag(posOrder);
                holder.PrdctDiscount.setText(String.valueOf(posOrder.getDiscount()));

                holder.PrdctNetAmount = (TextView) view.findViewById(R.id.NetPrize);
                holder.PrdctNetAmount.setText(String.valueOf(posOrder.getNetAmount()));


            holder.ImgDlt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                PosOrder ps = (PosOrder) holder.ImgDlt.getTag();

                    orderItem.remove(ps);
                    adapter.notifyDataSetChanged();
                    l.setAdapter(adapter);

                  }
                    });


                    holder.PrdctQuantity.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count,
                                                      int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            PosOrder pos = (PosOrder) holder.PrdctQuantity.getTag();
                            System.out.println("Quantity =" + pos.getProductQntity());
                            //  TextView     productTotal=(TextView)view.findViewById(R.id.NetPrize);

                            String Quantity = s.toString();
                            System.out.println("Qunt" + Quantity);
                            if (Quantity.matches("")) {
                                pos.setProductQntity(0);

                            } else {
                                pos.setProductQntity(Integer.valueOf(Quantity));
                                float Discount = (pos.getProductPrize() * pos.getDiscount() / 100);
                                float netPrice = ((pos.getProductPrize() - Discount));
                                float productTotalAmount = pos.getProductQntity() * netPrice;
                                pos.setNetAmount(productTotalAmount);
                                System.out.println(pos.getNetAmount() + "Price");
                                holder.PrdctNetAmount.setText(String.valueOf(productTotalAmount));
                                holder.PrdctName.setText(pos.getProductName());
                                NetAmount();

                            }

                        }


                        @Override
                        public void afterTextChanged(Editable s) {


                        }

                    });

                holder.PrdctPrize.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        PosOrder pos = (PosOrder) holder.PrdctPrize.getTag();
                        String Price = holder.PrdctPrize.getText().toString();
                        System.out.println("Qunt" + Price);
                        if (Price.matches("")) {
                            pos.setProductPrize(0);

                        } else {
                            pos.setProductPrize(Float.valueOf(Price));
                            float Discount = (pos.getProductPrize() * pos.getDiscount() / 100);
                            float netPrice = ((pos.getProductPrize() - Discount));
                            float productTotalAmount = pos.getProductQntity() * netPrice;
                            pos.setNetAmount(productTotalAmount);
                            System.out.println(pos.getNetAmount() + "Price");
                            holder.PrdctNetAmount.setText(String.valueOf(productTotalAmount));
                            holder.PrdctName.setText(pos.getProductName());
                            NetAmount();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                holder.PrdctDiscount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        PosOrder pos = (PosOrder) holder.PrdctDiscount.getTag();
                        String discount1 = holder.PrdctDiscount.getText().toString();
                        System.out.println("Qunt" + discount1);
                        if (discount1.matches("")) {
                            pos.setDiscount(0);

                        } else {
                            pos.setDiscount(Float.valueOf(discount1));
                            float Discount = (pos.getProductPrize() * pos.getDiscount() / 100);
                            float netPrice = ((pos.getProductPrize() - Discount));
                            float productTotalAmount = pos.getProductQntity() * netPrice;
                            pos.setNetAmount(productTotalAmount);
                            System.out.println(pos.getNetAmount() + "Price");
                            holder.PrdctNetAmount.setText(String.valueOf(productTotalAmount));
                            holder.PrdctName.setText(pos.getProductName());
                            NetAmount();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
            return view;
        }
    }





}






