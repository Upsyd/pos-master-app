package com.odoo.addons.pos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.App;
import com.odoo.R;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.utils.OActionBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.datatype.Duration;

/**
 * Created by My on 10/2/2015.
 */
public class PosPaymentTypes extends AppCompatActivity {
    private static final String TAG = PosPaymentTypes.class.getName();
     public PosPaymentAdapter adapter;
    ListView pay_amount;
    ArrayAdapter<String> adapter_state;
    //ArrayList<PosPayment> array;
    PosPayment posPayment;
    ArrayList<PosPayment> array;
    App app;
   // ArrayAdapter<String> adapter;
    Button Invoice;
    Spinner spnPayment;

    TextView Paymentname;
    private String[] payment = {"Payment Types", "Cash", "Bank"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_listview);
        pay_amount = (ListView) findViewById(R.id.list_payment);
        //array = new ArrayList<PosPayment>();
        array = new ArrayList<PosPayment>();
        Paymentname = (TextView) findViewById(R.id.payname);
        Invoice = (Button) findViewById(R.id.btnInvoice);
        spnPayment = (Spinner) findViewById(R.id.btnPay);
        System.out.println("Activity created");
        adapter_state = new ArrayAdapter<String>(PosPaymentTypes.this, android.R.layout.simple_spinner_item, payment);
        adapter_state.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnPayment.setAdapter(adapter_state);
        adapter = new PosPaymentAdapter(PosPaymentTypes.this,R.layout.payment_single_row,array);


        // spnPayment.setSelection(1);


        /*Payment.setVisibility(View.VISIBLE);
            @Override
                    Invoice.setVisibility(View.VISIBLE);*/


       spnPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boolean selectionControl = true;
               // spnPayment.setSelection(0);
            if(i==0){

            }else {


    String selPayment = (String) spnPayment.getSelectedItem();

               spnPayment.setSelection(0);

     PosPayment posPayment1 = new PosPayment();
     posPayment1.setPaymentType(selPayment);
        array.add(posPayment1);
                pay_amount.setAdapter(adapter);

}

            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*app = (App) getApplicationContext();
        OActionBarUtils.setActionBar(this, true);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle(R.string.title_payment);
        }
*/

//        tvPrdctname = (TextView) findViewById(R.id.productName);
//        grandTotalPrize = (TextView) findViewById(R.id.grandTotal);

      /*  final Bundle bundle = new Bundle();
        bundle.putString("PaymentMethod", "Cash");*/

//        etPrdctPrize = (EditText) findViewById(R.id.prdctPrize);
//        etPrdctDiscount = (EditText) findViewById(R.id.discount);
//        tvPrize = (TextView) findViewById(R.id.NetPrize);
//        Imageproduct = (ImageView) findViewById(R.id.productImage);
        // array = new ArrayList<PosPayment>();
        //   array = (ArrayList<PosOrder>) getIntent().getSerializableExtra("cart_details");
        /*adapter = new PosPaymentAdapter(this, R.layout.payment_single_row, array);
        pay_amount.setAdapter(adapter);*/
        //pay_amount.setDividerHeight(0);

     /*  spnPayment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // for (int i = 0; i < adapter.getCount(); i++) {


               *//* posPayment = new PosPayment();
                TextView Payname;
                EditText Payamount;
                Payname = (TextView) view.findViewById(R.id.payname);
                Payamount = (EditText) view.findViewById(R.id.payAmount);
                //  posPayment.setPaymentId(row.getInt(OColumn.ROW_ID));
                posPayment.setPaymentType("Cash");
                String strpayamt = Payamount.getText().toString();
                Float f = Float.parseFloat(strpayamt);
                posPayment.setPaymentAmount(f);
                array.add(posPayment);
*//*
               pay_amount.setAdapter(adapter);
           }

       });
*/
    }

    public class PosPaymentAdapter extends ArrayAdapter<PosPayment>  {
        private Context context;
         ViewHolder holder;
        private List<PosPayment> payment;

        public PosPaymentAdapter(Context context, int textViewResourceId,
                                 List<PosPayment> payment) {
            super(context, textViewResourceId, payment);
            this.context = context;
            this.payment = payment;

        }

        public class ViewHolder {
            TextView Paymenttypa;
            EditText PaymentAmount;
            ImageButton paymentCancel;

        }

        public View getView(int position, View view, ViewGroup parent) {

            holder = new ViewHolder();
            PosPayment payment1 = payment.get(position);
            System.out.println("Inside Get View");
            Toast.makeText(PosPaymentTypes.this,"Get View", Toast.LENGTH_SHORT).show();

            if (view == null) {

                LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.payment_single_row, null);
            }


                holder.Paymenttypa = (TextView) view.findViewById(R.id.payname);
                holder.Paymenttypa.setTag(payment1);

                holder.PaymentAmount = (EditText) view.findViewById(R.id.payAmount);
                holder.PaymentAmount.setTag(payment1);

                holder.paymentCancel = (ImageButton) view.findViewById(R.id.imgBtnCancel);
                holder.paymentCancel.setFocusable(false);
                holder.paymentCancel.setTag(payment1);

            holder.paymentCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PosPayment payment2=(PosPayment)holder.paymentCancel.getTag();
                    payment.remove(payment2);
                    adapter.notifyDataSetChanged();
                    pay_amount.setAdapter(adapter);
                    System.out.println("Delete Click Event");
                    Toast.makeText(PosPaymentTypes.this,"Delete", Toast.LENGTH_SHORT).show();

                }
            });





            /*posPayment.getPaymentType();
            posPayment.getPaymentAmount();*/
            // edtAmount.setText(edtAmount.getText().toString());
            return view;


        }


    }
}

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           finish();
        }
        return super.onOptionsItemSelected(item);
    }*/
   /* public class PosPaymentAdapter  extends ArrayAdapter<PosPayment> {
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

            ViewHolder holder;
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
            holder.payment_amount.setText("10000");
            //holder.payment_amount.setText(String.valueOf(posPayment.getPaymentAmount()));
            holder.payment_type = (TextView) view.findViewById(R.id.payname);
            holder.payment_amount.setTag(posPayment);
           // holder.payment_type.setText(posPayment.getPaymentType());
            holder.payment_amount.setText("Cash");
           // view.setTag(holder);
          //  PosPayment pospy = (PosPayment) holder.payment_amount.getTag();
          //  PosPayment pospyt = (PosPayment) holder.payment_type.getTag();

            return view;
        }
    }*/


//}
