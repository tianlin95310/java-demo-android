package tl.com.testmaterialdesign.navigation21.rvpage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.ItemKeyedDataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

public class RVPagingActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private PagedListAdapter mAdapter;

    private LiveData<PagedList> mPagedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_paging);
        ButterKnife.bind(this);

        initView();

        setDataSource();

//        initData();
    }

    private void initData() {

        List<DataBean> dataBeans = FakeDataUtils.loadInitData(60);
        OrdinaryAdapter adapter = new OrdinaryAdapter(dataBeans, this);
        recyclerView.setAdapter(adapter);
    }

    private void setDataSource() {
        mAdapter = new RvPageAdapter(mDiffCallback, this);
        recyclerView.setAdapter(mAdapter);

        PagedList.Config mPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(15)                 // 分页数据的数量。在后面的DataSource之loadRange中，count即为每次加载的这个设定值。
                .setEnablePlaceholders(true)
                .setPrefetchDistance(15)
                .setInitialLoadSizeHint(15)
                .build();

        mPagedList = new LivePagedListBuilder(new MyFactory(), mPagedListConfig)
                .setBoundaryCallback(new PagedList.BoundaryCallback<DataBean>() {
                    @Override
                    public void onZeroItemsLoaded() {
                        Log.d("my", "onZeroItemsLoaded");
                    }

                    @Override
                    public void onItemAtFrontLoaded(@NonNull DataBean itemAtFront) {
                        Log.d("my", "onItemAtFrontLoaded");
                    }

                    @Override
                    public void onItemAtEndLoaded(@NonNull DataBean itemAtEnd) {
                        Log.d("my", "onItemAtEndLoaded");
                    }
                })
                .build();

        mPagedList.removeObservers(this);

        mPagedList.observe(this, new Observer<PagedList>() {
            @Override
            public void onChanged(@Nullable PagedList pagedList) {
                Log.d("my", "onChanged pagedList = " + pagedList.size());
                mAdapter.submitList(pagedList);

//                Log.d("my", "mPagedList.getValue = " + mPagedList.getValue());
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipe.setOnRefreshListener(this);
    }

    public void refresh() {

        recyclerView.invalidate();

        recyclerView.getLayoutManager().requestLayout();
        recyclerView.requestLayout();
    }

    public class MyFactory extends DataSource.Factory<Integer, DataBean> {
        @Override
        public DataSource<Integer, DataBean> create() {
            return new MyDataSource();
        }
    }

    private DiffUtil.ItemCallback<DataBean> mDiffCallback = new DiffUtil.ItemCallback<DataBean>() {

        @Override
        public boolean areItemsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            Log.d("my", "areItemsTheSame");
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            Log.d("my", "areContentsTheSame");
            return TextUtils.equals(oldItem.content, newItem.content);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_1:

                break;

            case R.id.action_3:

                initView();
                break;
        }
        return true;
    }

    private class MyDataSource2 extends PositionalDataSource<DataBean> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DataBean> callback) {
            List<DataBean> dataBeans = FakeDataUtils.loadInitData(params.requestedLoadSize);
            callback.onResult(dataBeans, 2, 2);
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DataBean> callback) {

            List<DataBean> dataBeans = FakeDataUtils.loadPageData(params.startPosition, params.loadSize);
            callback.onResult(dataBeans);
        }
    }

    private class MyDataSource1 extends ItemKeyedDataSource<Integer, DataBean> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<DataBean> callback) {
            List<DataBean> dataBeans = FakeDataUtils.loadInitData(params.requestedLoadSize);
            callback.onResult(dataBeans, 2, 10);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {
            List<DataBean> dataBeans = FakeDataUtils.loadPageData(params.key, params.requestedLoadSize);
            callback.onResult(dataBeans);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull DataBean item) {
            return null;
        }
    }

    private class MyDataSource extends PageKeyedDataSource<Integer, DataBean> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
            Log.d("my", "loadInitial");
            List<DataBean> dataBeans = FakeDataUtils.loadInitData(params.requestedLoadSize);
            callback.onResult(dataBeans, null, 2);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
            Log.d("my", "loadBefore --- params.key = " + params.key);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
            List<DataBean> dataBeans = FakeDataUtils.loadPageData(params.key, params.requestedLoadSize);
            Log.d("my", "loadAfter --- params.key = " + params.key + ", dataBeans.size() = " + dataBeans.size());
            callback.onResult(dataBeans, params.key + 1);

        }
    }
}