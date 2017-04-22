package br.com.des.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by diogobernard on 22/04/17.
 */
public class Util {

    public static Date convert(String dateStr) throws ParseException {
        if(dateStr == null){
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(dateStr);
        return date;
    }

}
