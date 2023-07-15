package com.example.todoappmvvm.data

import androidx.room.TypeConverter
import com.example.todoappmvvm.data.models.Priority

class Converter {

    //nesneyi string e çeviriyor. veriyi write etmesi için string e çevirir.(yani database e eklerken)
    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }


    //string i nesneye çeviriyor. veriyi reading etmesi için nesneye çevirir.(database den verileri alırken)
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }



    /**
     * room veritabanı yalnızca string, integer, boolean, float  gibi ilkel türlere izin verir. Priority sınıfı gibi custom(özel) nesnelere izin vermiyor. Bu yüzden type converter yapmamız gerekiyor.
     * veri tabanımıza yazarken öncelikle nesneyi stringe, veritabanımızdan okurken string i Priority nesnesine dönüştürmemiz gerekiyor.
     * bir Priority öğesinin veritabanına kaydedilmesi için bir String'e dönüştürülmesi ve
    veritabanından alındığında bir Priority öğesi olarak yeniden oluşturulması gerekmektedir.*/
}