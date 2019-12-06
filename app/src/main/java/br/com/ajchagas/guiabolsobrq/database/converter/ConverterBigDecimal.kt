package br.com.ajchagas.guiabolsobrq.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class ConverterBigDecimal {

    @TypeConverter
    fun fromDoubleToBigDecimal(value : Double?) :BigDecimal? {
        return if (value == null) null else BigDecimal(value)
    }

    @TypeConverter
    fun bigDecimalToDouble(valor : BigDecimal?) : Double? {
        return valor?.toDouble()
    }

}
