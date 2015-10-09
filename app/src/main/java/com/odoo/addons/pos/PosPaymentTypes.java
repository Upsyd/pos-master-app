package com.odoo.addons.pos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.odoo.addons.pos.models.AccountJournal;
import com.odoo.core.orm.ODataRow;
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
    public PosPaymentAdapter posPaymentlistAdapter;
    ListView listPaymentTypes;
    TextView totalPay;
    String paymentMethod;
    TextView defulTitem;
    ArrayAdapter<String> spinnerAdapter;
    PosPayment posPayment;
    ArrayList<PosPayment> arrayListPaymentType;
    String selPayment;
    ArrayList<String> arrayListSpinner;
    Button btnInvoice;
    String payAmount;
    Spinner spinnerPayment;
    TextView paymentName;
    EditText paymentAMount;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_listview);
        OActionBarUtils.setActionBar(this, true);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle(R.string.title_payment);
        }
        listPaymentTypes = (ListView) findViewById(R.id.list_payment);
        defulTitem = (TextView) findViewById(R.id.notitem);
        defulTitem.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        String receivingdata = bundle.getString("Key");
        totalPay = (TextView) findViewById(R.id.grandpayTotal);
        totalPay.setText(receivingdata);
        paymentName = (TextView) findViewById(R.id.payname);
        paymentAMount = (EditText) findViewById(R.id.payAmount);
        btnInvoice = (Button) findViewById(R.id.btnInvoice);
        spinnerPayment = (Spinner) findViewById(R.id.btnPay);
        arrayListPaymentType = new ArrayList<PosPayment>();
        posPaymentlistAdapter = new PosPaymentAdapter(PosPaymentTypes.this, R.layout.payment_single_row, arrayListPaymentType);
        Select();

        spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {

                    String selPayment = spinnerPayment.getSelectedItem().toString();
                    PosPayment posPayment = new PosPayment();
                    spinnerPayment.setSelection(0);
                    posPayment.setPaymentType(selPayment);
                    arrayListPaymentType.add(posPayment);
                    listPaymentTypes.setAdapter(posPaymentlistAdapter);
                   posPaymentlistAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public Boolean Select() {
        AccountJournal posAccountJournal = new AccountJournal(PosPaymentTypes.this, null);
        List<ODataRow> list = posAccountJournal.select();
        for (int i = 0; i < list.size(); i++) {
            ODataRow row = list.get(i);
            paymentMethod = row.getString("name");
            arrayListSpinner = new ArrayList<String>();
            arrayListSpinner.add("Payment Types");
            arrayListSpinner.add(paymentMethod);

        }
        String[] arrayPayment = arrayListSpinner.toArray(new String[arrayListSpinner.size()]);

        ArrayAdapter aa = new ArrayAdapter(PosPaymentTypes.this, android.R.layout.simple_spinner_item, arrayPayment);

        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
       spinnerPayment.setAdapter(aa);
        return true;
    }



public class PosPaymentAdapter extends ArrayAdapter<PosPayment> {
    private Context context;
    ViewHolder holder;
    private List<PosPayment> listPayment;

    public PosPaymentAdapter(Context context, int textViewResourceId,
                             List<PosPayment> payment) {
        super(context, textViewResourceId, payment);
        this.context = context;
        this.listPayment = payment;

    }

    public class ViewHolder {
        TextView PaymentType;
        EditText PaymentAmount;
        ImageButton paymentCancel;

    }

    public View getView(int position, View view, ViewGroup parent) {
        holder = new ViewHolder();
        PosPayment listPosPayment = listPayment.get(position);

        if (view == null) {

            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.payment_single_row, null);
        }


        holder.PaymentType = (TextView) view.findViewById(R.id.payname);
        holder.PaymentType.setTag(listPosPayment);
        holder.PaymentType.setText(listPosPayment.getPaymentType());

        holder.PaymentAmount = (EditText) view.findViewById(R.id.payAmount);
        holder.PaymentAmount.setTag(listPosPayment);
        holder.PaymentAmount.setText(listPosPayment.getPaymentAmount() + "");

        holder.paymentCancel = (ImageButton) view.findViewById(R.id.imgBtnCancel);
        holder.paymentCancel.setFocusable(false);
        holder.paymentCancel.setTag(listPosPayment);

        holder.paymentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PosPayment payment2 = (PosPayment) holder.paymentCancel.getTag();
                listPayment.remove(payment2);
                posPaymentlistAdapter.notifyDataSetChanged();
                listPaymentTypes.setAdapter(posPaymentlistAdapter);

            }
        });

        holder.PaymentAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                PosPayment payment = (PosPayment) holder.PaymentAmount.getTag();
               payAmount = charSequence.toString();
                if (payAmount.matches("")) {
                    payment.setPaymentAmount(0);
                } else {
                    payment.setPaymentAmount(Float.valueOf(payAmount));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;


    }
}

}

