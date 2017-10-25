package tl.com.testmaterialdesign.utils.ui;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by tianlin on 2017/3/16.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class TlTimePickerFragment extends DialogFragment
{
    private int hourOfDay;
    private int minute;
    private boolean is24;
    private String divider = "-";

    public TlTimePickerFragment initTime(int hourOfDay, int minute)
    {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        return this;
    }

    public TlTimePickerFragment setIs24(boolean is24)
    {
        this.is24 = is24;
        return this;
    }

    public interface OnTlTimeSetListener
    {
        void onTimeSet(String time);
    }

    OnTlTimeSetListener onTlTimeSetListener;

    public TlTimePickerFragment setOnTlTimeSetListener(OnTlTimeSetListener onTlTimeSetListener)
    {
        this.onTlTimeSetListener = onTlTimeSetListener;
        return this;
    }

    public TlTimePickerFragment setDivider(String divider)
    {
        this.divider = divider;
        return this;
    }

    public static TlTimePickerFragment newInstance()
    {
        TlTimePickerFragment fragment = new TlTimePickerFragment();
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // 初始化时间
        Calendar calendar = Calendar.getInstance();
        initTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                if(onTlTimeSetListener != null)
                {
                    StringBuilder sb = new StringBuilder(20)
                            .append(String.format("%02d", hourOfDay))
                            .append(divider)
                            .append(String.format("%02d", minute));

                    onTlTimeSetListener.onTimeSet(sb.toString());
                }
            }
        }, hourOfDay, minute, is24)
        {
            @Override
            protected void onStop()
            {
            }
        };
        return dialog;
    }
}
