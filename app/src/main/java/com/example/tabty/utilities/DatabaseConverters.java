package com.example.tabty.utilities;

import androidx.room.TypeConverter;

import java.util.Date;

public class DatabaseConverters {
    @TypeConverter
    public static Long dateSerialization(Date date){
        if(date == null)
            return null;
        return date.getTime();
    }
    @TypeConverter
    public static Date dateDeserialization(Long value){
            if(value == null)
                return null;
            return new Date(value);
    }
}
