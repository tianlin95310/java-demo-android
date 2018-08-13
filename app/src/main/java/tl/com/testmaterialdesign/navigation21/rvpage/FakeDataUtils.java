package tl.com.testmaterialdesign.navigation21.rvpage;

import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianlin on 2018/7/11.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class FakeDataUtils {

    static List<DataBean> list = new ArrayList<>();
    static {
        for(int i = 0; i < 100; i++) {
            DataBean dataBean = new DataBean();
            dataBean.id = i;
            dataBean.content = "content---" + i;
            list.add(dataBean);
        }
    }

    public static List<DataBean> loadInitData(int initSize) {
//        return new ArrayList<>();
        Log.d("my", "loadInitData initSize = " + initSize);
        SystemClock.sleep(1000);
        return list.subList(0, initSize);
    }

    public static List<DataBean> loadPageData(int page, int size) {

        SystemClock.sleep(2000);
        Log.d("my", "loadPageData加载 页数 = " + page);

        int totalPage = list.size() % size == 0 ? list.size() / size : list.size() / size + 1;

        if(page < totalPage) {
            return list.subList((page - 1) * size, page * size);
        }
        else if(page == totalPage){
            return list.subList((page - 1) * size, list.size());
        }
        else {
            return new ArrayList<>();
        }
    }
}
