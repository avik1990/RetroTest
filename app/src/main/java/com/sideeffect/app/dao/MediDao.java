package com.sideeffect.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sideeffect.app.model.Medicine;

import java.util.List;

/**
 * Created by User1 on 06-03-2018.
 */
@Dao
public interface MediDao {

    @Query("SELECT * FROM tbl_medicine")
    List<Medicine> getAll();

    /*@Query("SELECT * FROM tbl_medicine where first_name LIKE  :firstName AND last_name LIKE :lastName")
    Medicine findByName(String firstName, String lastName);*/

    @Query("SELECT COUNT(*) from tbl_medicine")
    int countUsers();

    @Insert
    void insertAll(List<Medicine> tbl_medicine);

    @Delete
    void delete(Medicine tbl_medicine);

}
