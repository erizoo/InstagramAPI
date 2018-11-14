package by.boiko.erizo.educationapi.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import by.boiko.erizo.educationapi.R;
import by.boiko.erizo.educationapi.data.ResponseModel.ResponseEdge;
import by.boiko.erizo.educationapi.data.ResponseModel.ResponseModel;
import by.boiko.erizo.educationapi.ui.base.BaseActivity;
import by.boiko.erizo.educationapi.utils.OnLoadMoreListener;

public class ImageActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private ResponseModel responseModel;
    private List<ResponseEdge> responseEdgeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String data = intent.getStringExtra("DATA");
        responseModel = new Gson().fromJson(data, ResponseModel.class);
        try {
            responseEdgeList.addAll(responseModel.getEntryData().getPage().get(0)
                    .getHql().getUser().getMediaEdge().getEdgeList());
        } catch (Exception ignored){ }

        recyclerView = findViewById(R.id.image_rv);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        imageAdapter = new ImageAdapter(recyclerView, responseEdgeList, this);
        recyclerView.setAdapter(imageAdapter);

        imageAdapter.setOnLoadMoreListener(() -> {
            if (responseEdgeList.size() >= 12) {
                responseEdgeList.add(null);
                imageAdapter.notifyItemInserted(responseEdgeList.size() - 1);
                new Handler().postDelayed(() -> {
                    responseEdgeList.remove(responseEdgeList.size() - 1);
                    imageAdapter.notifyItemRemoved(responseEdgeList.size());

                    responseEdgeList.addAll(responseModel.getEntryData().getPage().get(0)
                            .getHql().getUser().getMediaEdge().getEdgeList());
                    runOnUiThread(() -> {
                        imageAdapter.notifyItemRangeInserted(imageAdapter.getItemCount(), responseEdgeList.size() - 1);
                        imageAdapter.setLoaded();
                    });

                }, 5000);
            } else {
                Toast.makeText(ImageActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected int getContentView() {
        return R.layout.image_activity;
    }
}
