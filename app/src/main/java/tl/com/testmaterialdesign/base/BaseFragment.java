package tl.com.testmaterialdesign.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by tianlin on 2017/3/31.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public abstract class BaseFragment extends Fragment implements IBaseView
{
    @Override
    public void showProgressDialog(String msg)
    {
        FragmentActivity fragmentActivity = getActivity();

        if (fragmentActivity instanceof IBaseView)
        {
            ((IBaseView) fragmentActivity).showProgressDialog(msg);
        }
    }

    @Override
    public void showProgressDialog(int msg)
    {
        FragmentActivity fragmentActivity = getActivity();

        if (fragmentActivity instanceof IBaseView)
        {
            ((IBaseView) fragmentActivity).showProgressDialog(msg);
        }
    }

    @Override
    public void hideProgressDialog()
    {
        FragmentActivity fragmentActivity = getActivity();

        if (fragmentActivity instanceof IBaseView)
        {
            ((IBaseView) fragmentActivity).hideProgressDialog();
        }
    }

    @Override
    public void response(Context context, String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void response(Context context, int msg)
    {
        Toast.makeText(context, context.getString(msg), Toast.LENGTH_SHORT).show();
    }
}
