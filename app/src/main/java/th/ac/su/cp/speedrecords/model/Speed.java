package th.ac.su.cp.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "speeds")
public class Speed {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    @ColumnInfo(name = "speed")
    public final String speed;
    @ColumnInfo(name = "duration")
    public final String duration;
    @ColumnInfo(name = "distance")
    public final String distance;
    @ColumnInfo(name = "over_limit")
    public final boolean overLimit;

    public Speed(int id,String speed,String distance,String duration,boolean overLimit){
        this.id=id;
        this.speed=speed;
        this.distance=distance;
        this.duration=duration;
        this.overLimit = overLimit;
    }
}
