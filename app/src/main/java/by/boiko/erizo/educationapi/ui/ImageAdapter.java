package by.boiko.erizo.educationapi.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.boiko.erizo.educationapi.R;
import by.boiko.erizo.educationapi.data.ResponseModel.ResponseEdge;
import by.boiko.erizo.educationapi.ui.base.BaseViewHolder;
import by.boiko.erizo.educationapi.utils.OnLoadMoreListener;

public class ImageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Activity activity;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private List<ResponseEdge> edgeList = new ArrayList<>();

    public ImageAdapter(RecyclerView recyclerView, List<ResponseEdge> edgeList, Activity activity) {
        this.edgeList = edgeList;
        this.activity = activity;

        final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = Objects.requireNonNull(gridLayoutManager).getItemCount();
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return edgeList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.onBind(position);
        } else if (holder instanceof LoadingViewHolder) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.itemView.setLayoutParams(layoutParams);
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return edgeList == null ? 0 : edgeList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    private Context context;

    public void setItems(List<ResponseEdge> edgeList) {
        this.edgeList.addAll(edgeList);
    }

    public class ImageViewHolder extends BaseViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @Override
        public void onBind(int position) {
            String image = edgeList.get(position).getNode().getResources().get(4).getSrc();
            Glide.with(context)
                    .load(image)
                    .into(imageView);
        }
    }

    private class LoadingViewHolder extends BaseViewHolder {

        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }
}