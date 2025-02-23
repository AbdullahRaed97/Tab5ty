package com.example.tabty.utilities;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.util.Date;

public class DatabaseConverters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static long dateSerialization(LocalDate date){
        if(date == null)
            return 0;
        return date.toEpochDay();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate dateDeserialization(Long value){
            if(value == null)
                return null;
            return  LocalDate.ofEpochDay(value);
    }
}
