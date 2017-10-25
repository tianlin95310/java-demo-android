package tl.com.testmaterialdesign.utils.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by tianlin on 2017/3/16.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class TlDatePickerFragment extends DialogFragment
{
    /**
     * 时间分隔线,
     */
    private String divider = "-";

    private OnTlDateSetListener onTlDateSetListener;

    private int year;
    private int monthOfYear;
    private int dayOfMonth;

    public TlDatePickerFragment setDivider(String divider)
    {
        this.divider = divider;
        return this;
    }

    public TlDatePickerFragment setOnDateSetListener(OnTlDateSetListener onTlDateSetListener)
    {
        this.onTlDateSetListener = onTlDateSetListener;
        return this;
    }

    public interface OnTlDateSetListener
    {
        void onDateSet(String date);
    }

    public TlDatePickerFragment initDate(int year, int monthOfYear, int dayOfMonth)
    {
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public static TlDatePickerFragment newInstance()
    {
        TlDatePickerFragment fragment = new TlDatePickerFragment();

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // 初始化日期
        Calendar calendar = Calendar.getInstance();
        initDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                if (onTlDateSetListener != null)
                {
                    StringBuilder sb = new StringBuilder(20)
                            .append(String.format("%04d", year))
                            .append(divider)
                            .append(String.format("%02d", month + 1))
                            .append(divider)
                            .append(String.format("%02d", dayOfMonth));

                    onTlDateSetListener.onDateSet(sb.toString());
                }
            }
        }, year, monthOfYear, dayOfMonth)
        {
            // 解决调用两次的问题
            @Override
            protected void onStop()
            {
            }
        };
        return dialog;
    }
}
