package tl.com.testmaterialdesign.navigation21;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation21.jobservice.JobServiceActivity;
import tl.com.testmaterialdesign.navigation21.photoshow.PhotoShowActivity;
import tl.com.testmaterialdesign.navigation21.requestpremission.ContactsActivity;

/**
 * Created by tianlin on 2017/3/8.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Fragment21 extends Fragment
{
    @BindView(R.id.bt_new_take_photo)
    Button btNewTakePhoto;
    @BindView(R.id.bt_request_p)
    Button btRequestP;
    @BindView(R.id.bt_job_service)
    Button btJobService;
    Unbinder unbinder;

    File file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment21, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_new_take_photo, R.id.bt_request_p, R.id.bt_job_service})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.bt_new_take_photo:
                /**
                 * 拍照相关
                 */
                SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd-HHmmss");
                // 构建文件名
                String fileName = "IMAGE" + simple.format(new Date());
                String imgDir = "Android/data/tl.com.testmaterialdesign/files/Pictures";
                // 图片的相对目录
                // 存贮卡目录
                File root_dir = Environment.getExternalStorageDirectory().getAbsoluteFile();
                Log.d("my", "root_dir = " + root_dir.getAbsolutePath());
                // 图片的绝对目录
                File img_dir = new File(root_dir, imgDir);

                takePhoto(getActivity(), img_dir, fileName);
                break;
            case R.id.bt_request_p:

                Intent intent = new Intent(getActivity(), ContactsActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_job_service:

                Intent intent1 = new Intent(getActivity(), JobServiceActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(file.exists())
        {
            Toast.makeText(getActivity(), "file exist " + file.getPath(), Toast.LENGTH_SHORT).show();
            Log.d("my", "file exist " + file.getPath());

            Intent intent = new Intent(getActivity(), PhotoShowActivity.class);
            intent.putExtra("file", file.getPath());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getActivity(), "file not exist ", Toast.LENGTH_SHORT).show();
        }

    }

    private void takePhoto(Context context, File img_dir, String fileName)
    {

        if (!img_dir.exists())
        {
            boolean bool = img_dir.mkdirs();
            Log.d("my", "创建目录成功！" + bool + " " + img_dir.getPath());
        }

        file = new File(img_dir, fileName + ".jpg");

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
        {
            // file开头的uri
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 1);
        }
        else
        {
            // content开头的
            Uri uri = FileProvider.getUriForFile(context, context.getString(R.string.authorities), file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 让照相的activity具有该临时权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, 1);
        }

    }
}
