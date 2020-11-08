package th.ac.su.cp.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;



import th.ac.su.cp.speedrecords.model.Speed;

@Dao
public interface UserDao {

    @Query("SELECT * FROM speeds")
    Speed[] getAllSpeed();


    @Insert
    void addSpeed(Speed... speeds);

}
