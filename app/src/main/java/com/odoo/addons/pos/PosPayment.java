package com.odoo.addons.pos;

import java.io.Serializable;

/**
 * Created by My on 10/2/2015.
 */
public class PosPayment  implements Serializable {
    public int getPaymentId() {
    return paymentId;
}

int paymentId;

        public void setPaymentId(int paymentId) {
            this.paymentId = paymentId;
        }

String paymentType;

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType (String paymentType) {
            this.paymentType = paymentType;
        }

float paymentAmount;
        public float getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(float paymentAmount) {
            this.paymentAmount = paymentAmount;
        }







}

