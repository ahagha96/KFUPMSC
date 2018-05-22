package agha.kfupmscapp.Utilities;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by User-Sai on 1/15/2018.
 */

public class Utility {

    public static int calculateNoOfColumns(Context context, int size){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 100);
    }
}
