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
 * Created by My on 10/2/2015.
 */
public class PosPaymentAdapter  extends ArrayAdapter<PosPayment> {
    private List<PosPayment> payment_type;
    private Context context;

    public PosPaymentAdapter(Context context, int textViewResourceId,
                        List<PosPayment> payment_type) {
        super(context, textViewResourceId, payment_type);
        this.context = context;
        this.payment_type = payment_type;

    }
    public class ViewHolder {
//        TextView payment_type;
//        EditText payment_amount;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        PosPayment posPayment = payment_type.get(position);




        return view;
    }
}





