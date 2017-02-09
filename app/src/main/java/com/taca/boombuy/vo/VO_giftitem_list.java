package com.taca.boombuy.vo;

import android.graphics.Bitmap;

/**
 * Created by jimin on 2017-02-03.
 */

public class VO_giftitem_list {
    Bitmap product_imageView_cell;
    String product_title_cell;
    String product_price_cell;

    public VO_giftitem_list() {
    }

    public VO_giftitem_list(Bitmap product_imageView_cell, String product_title_cell, String product_price_cell) {
        this.product_imageView_cell = product_imageView_cell;
        this.product_title_cell = product_title_cell;
        this.product_price_cell = product_price_cell;
    }

    public Bitmap getProduct_imageView_cell() {
        return product_imageView_cell;
    }

    public void setProduct_imageView_cell(Bitmap product_imageView_cell) {
        this.product_imageView_cell = product_imageView_cell;
    }

    public String getProduct_title_cell() {
        return product_title_cell;
    }

    public void setProduct_title_cell(String product_title_cell) {
        this.product_title_cell = product_title_cell;
    }

    public String getProduct_price_cell() {
        return product_price_cell;
    }

    public void setProduct_price_cell(String product_price_cell) {
        this.product_price_cell = product_price_cell;
    }

    @Override
    public String toString() {
        return "VO_giftitem_list{" +
                "product_imageView_cell=" + product_imageView_cell +
                ", product_title_cell='" + product_title_cell + '\'' +
                ", product_price_cell='" + product_price_cell + '\'' +
                '}';
    }
}
