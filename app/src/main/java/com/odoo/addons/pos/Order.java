package com.odoo.addons.pos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.odoo.R;
import com.odoo.core.utils.OActionBarUtils;

import java.util.ArrayList;
import java.util.List;


public class Order extends AppCompatActivity {
    private static final String TAG = Order.class.getName();
    public OrderAdapter adapter;
    public TextView tvPrdctname;
    public TextView tvPrize;
    TextView grandTotalPrize;
    private Context context;
    CartItem po;
    public TextView amount;
    ArrayList<CartItem> array;
    float prizevalue;
    ListView l;
    TextView total;
    public EditText etPrdctQumtity;
    public EditText etPrdctPrize;
    public EditText etPrdctDiscount;
    public ImageView Imageproduct;
    Button btnContinueShop;
    int count;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_listview);

        OActionBarUtils.setActionBar(this, true);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle(R.string.cart_items);
        }

        l = (ListView) findViewById(R.id.list_order);
        tvPrdctname = (TextView) findViewById(R.id.productName);
        grandTotalPrize = (TextView) findViewById(R.id.grandTotal);
        etPrdctQumtity = (EditText) findViewById(R.id.prdctQuantity);
        btnContinueShop = (Button) findViewById(R.id.btnContinue);
        etPrdctPrize = (EditText) findViewById(R.id.prdctPrize);
        etPrdctDiscount = (EditText) findViewById(R.id.discount);
        tvPrize = (TextView) findViewById(R.id.NetPrize);
        Imageproduct = (ImageView) findViewById(R.id.productImage);
//        array = new ArrayList<CartItem>();
        array = (ArrayList<CartItem>) getIntent().getSerializableExtra("cart_details");
        adapter = new OrderAdapter(this, R.layout.order_single_row, array);
        l.setAdapter(adapter);
        l.setDividerHeight(0);
        Bundle mBundle = getIntent().getExtras();
        count = mBundle.getInt("items_count");

        System.out.println("Total Selected Item:  " + count);


        btnContinueShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalcartvalue();
            }
        });


        NetAmount();

        final TextView tax = (TextView) findViewById(R.id.taxesTotal);
        tax.setText("0.0");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finalcartvalue();
        }
        return super.onOptionsItemSelected(item);
    }

    private void finalcartvalue() {

        Bundle bundle = new Bundle();
        bundle.putSerializable("cart_details", array);
        System.out.println("Delete this:" + array);
        Intent data = getIntent();
        // Pass relevant data back as a result

        data.putExtra("items_count", count);
        setResult(Activity.RESULT_OK, data);
        System.out.println("value of count: " + count);
        finish();


    }


    public void NetAmount() {

        float grandTotal = 0.0f;

        for (int i = 0; i < adapter.getCount(); i++) {
            CartItem pos = adapter.getItem(i);
            float Discount = (pos.getProductPrize() * pos.getDiscount() / 100);
            float netPrice = ((pos.getProductPrize() - Discount));
            float productTotalAmount = pos.getProductQntity() * netPrice;
            grandTotal += productTotalAmount;
        }
        grandTotalPrize.setText(grandTotal + "");
    }

    public class OrderAdapter extends ArrayAdapter<CartItem> {
        private List<CartItem> orderItem;

        private Context context;

        TextView Rs;


        public OrderAdapter(Context context, int textViewResourceId,
                            List<CartItem> orderItem) {
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
            ImageView prdctImage;
        }


        @Override
        public View getView(final int position, View view, ViewGroup parent) {

            final ViewHolder holder;
            holder = new ViewHolder();
            CartItem posOrder = orderItem.get(position);
            float Discount = (posOrder.getProductPrize() * posOrder.getDiscount() / 100);
            float netPrice = ((posOrder.getProductPrize() - Discount));
            float productTotalAmount = posOrder.getProductQntity() * netPrice;
            posOrder.setNetAmount(productTotalAmount);
            if (view == null) {

                LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.order_single_row, null);
            }

            holder.PrdctQuantity = (EditText) view.findViewById(R.id.prdctQuantity);
            holder.PrdctQuantity.setTag(posOrder);
            holder.PrdctQuantity.setText(String.valueOf(posOrder.getProductQntity()));

            holder.prdctImage = (ImageView) view.findViewById(R.id.productImage);
            holder.prdctImage.setTag(posOrder);
            Bitmap bmp = BitmapFactory.decodeByteArray(posOrder.getImage(), 0, posOrder.getImage().length);
            holder.prdctImage.setImageBitmap(bmp);


            holder.ImgDlt = (ImageButton) view.findViewById(R.id.Imagedelete);
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
//


                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            Order.this);
                    alert.setTitle("Delete");
                    alert.setMessage(R.string.delete_messege);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CartItem ps = (CartItem) holder.ImgDlt.getTag();
                            count = (count - ps.productQntity);
                            System.out.println("this value: " + count);
                            orderItem.remove(ps);
                            adapter.notifyDataSetChanged();
                            l.setAdapter(adapter);
                            NetAmount();


                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }
                    });

                    alert.show();

                }
            });


            holder.PrdctQuantity.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    CartItem pos = (CartItem) holder.PrdctQuantity.getTag();
                    System.out.println("Quantity =" + pos.getProductQntity());
                     String Quantity = s.toString();


                    if (Quantity.matches("")) {
                        pos.setProductQntity (0) ;

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

                    CartItem pos = (CartItem) holder.PrdctPrize.getTag();
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
                    CartItem pos = (CartItem) holder.PrdctDiscount.getTag();
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



        return view;
    }

}
    @Override
    public void onBackPressed() {
        // do something on back.
        finalcartvalue();
   }

}




