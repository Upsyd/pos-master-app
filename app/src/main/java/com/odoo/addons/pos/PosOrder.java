package com.odoo.addons.pos;

import android.os.Bundle;

import com.odoo.core.orm.fields.OColumn;

import java.io.Serializable;

/**
 * Created by Harshad on 8/10/2015.
 */
public class PosOrder implements Serializable {
    public int getProductId() {
        return productId;
    }

    int productId;

    public void setProductId(int productId) {
        this.productId = productId;
    }

    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    float productPrize;
    public float getProductPrize() {
        return productPrize;
    }

    public void setProductPrize(float productPrize) {
        this.productPrize = productPrize;
    }


    int productQntity;

    public int getProductQntity() {
        return productQntity;
    }

    public void setProductQntity(int productQntity) {
        this.productQntity = productQntity;
    }
}
