package com.example.decoder_app.utils;

import androidx.room.TypeConverter;

import com.example.decoder_app.model.ValueObject;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */

public class DataTypeConverters {

    @TypeConverter
    public static Integer fromValueObject(ValueObject valueObject) {
        return valueObject.getNumber();
    }

    @TypeConverter
    public static ValueObject fromIntegerToStars(Integer number) {
        ValueObject stars = new ValueObject();
        stars.setNumber(number);
        return stars;
    }
}