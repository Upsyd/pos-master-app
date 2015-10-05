package com.odoo.addons.pos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.odoo.App;
import com.odoo.R;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.utils.OActionBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 10/2/2015.
 */
public class PosPaymentTypes extends AppCompatActivity{
    private static final String TAG = PosPaymentTypes.class.getName();
    public PosPaymentAdapter adapter;
    ListView pay_amount;
    ArrayList<PosPayment> array;
   PosPayment posPayment;
    App app;
    Button Invoice,Payment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_listview);
        app = (App) getApplicationContext();
        OActionBarUtils.setActionBar(this, true);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle(R.string.title_payment);
        }

        pay_amount = (ListView) findViewById(R.id.list_payment);
//        tvPrdctname = (TextView) findViewById(R.id.productName);
//        grandTotalPrize = (TextView) findViewById(R.id.grandTotal);
           Invoice = (Button) findViewById(R.id.btnInvoice);
            Payment = (Button) findViewById(R.id.btnPay);
//        etPrdctPrize = (EditText) findViewById(R.id.prdctPrize);
//        etPrdctDiscount = (EditText) findViewById(R.id.discount);
//        tvPrize = (TextView) findViewById(R.id.NetPrize);
//        Imageproduct = (ImageView) findViewById(R.id.productImage);
          array = new ArrayList<PosPayment>();
     //   array = (ArrayList<PosOrder>) getIntent().getSerializableExtra("cart_details");
        adapter = new PosPaymentAdapter(this, R.layout.payment_single_row, array);
        pay_amount.setAdapter(adapter);
        pay_amount.setDividerHeight(0);

        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           posPayment = new PosPayment();
                TextView Payname;
                EditText Payamount;
                Payname = (TextView)view.findViewById(R.id.payname);
                Payamount = (EditText)view.findViewById(R.id.payAmount);
                posPayment.setPaymentType(Payname.getText().toString());
                String strpayamt = Payamount.getText().toString();
                Float f = Float.parseFloat(strpayamt);
                posPayment.setPaymentAmount(f);
                array.add(posPayment);









            }
        });





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public class PosPaymentAdapter  extends ArrayAdapter<PosPayment> {
        private List<PosPayment> paymentstype;
        private Context context;

        public PosPaymentAdapter(Context context, int textViewResourceId,
                                 List<PosPayment> paymentstype) {
            super(context, textViewResourceId, paymentstype);
            this.context = context;
            this.paymentstype = paymentstype;

        }
        public class ViewHolder {

            TextView payment_type;
            EditText payment_amount;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {

            final ViewHolder holder;
            holder = new ViewHolder();
//            PosPayment pospay;
            posPayment = paymentstype.get(position);
            if (view == null) {

                LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.payment_single_row, null);
            }
            holder.payment_amount = (EditText) view.findViewById(R.id.payAmount);
            holder.payment_amount.setTag(posPayment);
            holder.payment_amount.setText(String.valueOf(posPayment.getPaymentAmount()));
            holder.payment_type = (TextView) view.findViewById(R.id.payname);
            holder.payment_type.setText(posPayment.getPaymentType());
            view.setTag(holder);
          //  PosPayment pospy = (PosPayment) holder.payment_amount.getTag();
          //  PosPayment pospyt = (PosPayment) holder.payment_type.getTag();





            return view;
        }
    }


}
