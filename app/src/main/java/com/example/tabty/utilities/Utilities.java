package com.example.tabty.utilities;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;

import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tabty.R;

public class Utilities {
    public static String getCountryNameCode(String countryName) {
        countryName = countryName.toLowerCase();

        switch (countryName) {
            case "american": return "us";
            case "british": return "gb";
            case "canadian": return "ca";
            case "chinese": return "cn";
            case "croatian": return "hr";
            case "dutch": return "nl";
            case "egyptian": return "eg";
            case "french": return "fr";
            case "greek": return "gr";
            case "indian": return "in";
            case "irish": return "ie";
            case "italian": return "it";
            case "jamaican": return "jm";
            case "japanese": return "jp";
            case "kenyan": return "ke";
            case "malaysian": return "my";
            case "mexican": return "mx";
            case "moroccan": return "ma";
            case "polish": return "pl";
            case "portuguese": return "pt";
            case "russian": return "ru";
            case "spanish": return "es";
            case "thai": return "th";
            case "tunisian": return "tn";
            case "turkish": return "tr";
            case "vietnamese": return "vn";
            case "filipino": return "ph";
            case "ukrainian": return "ua";
            case "uruguayan": return "uy";
            case "norwegian": return "no";
            default: return countryName;
        }
    }
    public static void openDrawer(Activity activity){
        DrawerLayout myDrawer = activity.findViewById(R.id.mainDrawer);
        myDrawer.open();
    }
}
