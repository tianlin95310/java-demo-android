package tl.com.testmaterialdesign.utils.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import org.w3c.dom.Comment;

import java.util.Calendar;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by tianlin on 2017/3/23.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class TlYearMonthPicker extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // 隐藏日期选择数字选择，但高版本无效
        {
            DatePicker datePicker = dialog.getDatePicker();
            if (datePicker == null)
                return dialog;
            Log.d("my", "datePicker = " + datePicker.getChildCount());

            ViewGroup viewGroup1 = (ViewGroup) (datePicker.getChildAt(0));
            if (viewGroup1 == null)
                return dialog;
            Log.d("my", "viewGroup1 = " + viewGroup1.getChildCount());

            ViewGroup viewGroup2 = (ViewGroup) viewGroup1.getChildAt(0);
            if (viewGroup2 == null)
                return dialog;
            Log.d("my", "viewGroup2 = " + viewGroup2.getChildCount());

            View view = viewGroup2.getChildAt(2);
            if (view == null)
            {
                return dialog;
            }
            view.setVisibility(View.GONE);
        }
        return dialog;
    }
}
