package com.odoo.addons.pos;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.odoo.R;

import java.util.List;

/**
 * Created by Harshad on 8/13/2015.
 */
public class OrderAdapter  extends ArrayAdapter<CartItem> {
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
    }



        @Override
        public View getView(int position, View view, ViewGroup parent) {

            final ViewHolder holder;
            CartItem posOrder = orderItem.get(position);
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
//                Rs = (TextView) view.findViewById(R.id.currency_symbol);
//                Rs.setText("Rs.");


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

                holder.PrdctQuantity.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        CartItem pos = (CartItem) holder.PrdctQuantity.getTag();
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



