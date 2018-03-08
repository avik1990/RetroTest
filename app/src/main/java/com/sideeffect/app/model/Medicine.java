package com.sideeffect.app.model;

/**
 * Created by User1 on 03-03-2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbl_medicine")
public class Medicine extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int _id;

    @ColumnInfo(name = "entity_id")
    @SerializedName("entity_id")
    @Expose
    String entity_id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    String name;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    @Expose
    String price;

    @ColumnInfo(name = "oldprice")
    @SerializedName("oldprice")
    @Expose
    String oldprice;

    public Medicine() {
    }

    public Medicine(String entity_id, String name, String price, String oldprice) {
        this.entity_id = entity_id;
        this.name = name;
        this.price = price;
        this.oldprice = oldprice;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "entity_id='" + entity_id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", oldprice='" + oldprice + '\'' +
                '}';
    }
}
