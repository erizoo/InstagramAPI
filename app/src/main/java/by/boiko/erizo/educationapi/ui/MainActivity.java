package by.boiko.erizo.educationapi.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.boiko.erizo.educationapi.R;
import by.boiko.erizo.educationapi.data.ResponseModel.ResponseModel;
import by.boiko.erizo.educationapi.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainActivityMvpView {

    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.button_search)
    Button buttonSearch;

    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> presenter;

    private Unbinder unbinder;
    private String data;
    private Intent intent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenComponent().inject(this);
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.button_search)
    public void searchForName() {
        showLoading(this);
        InputMethodManager inputMethodManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
        }
        String userName = this.userName.getText().toString();
        if (!userName.equals("")) {
            Thread thread = new Thread(() -> {
                try {
                    Document doc = Jsoup.connect("https://www.instagram.com/" + this.userName.getText().toString()).get();
                    Elements scriptElements = doc.getElementsByTag("script");
                    List<DataNode> node = scriptElements.get(3).dataNodes();
                    data = node.get(0).getWholeData().substring(20, node.get(0).getWholeData().length() - 1);
                    ResponseModel responseModel = new Gson().fromJson(data, ResponseModel.class);
                    if (responseModel.getEntryData().getPage().get(0).getHql().getUser().getMediaEdge().getEdgeList().size() == 0) {
                        runOnUiThread(() -> {
                            hideLoading();
                            Toast.makeText(MainActivity.this, "У пользователя закрытый аккаунт", Toast.LENGTH_LONG).show();
                        });
                    } else {
                        if (responseModel.getEntryData().getPage().get(0).getHql().getUser().getMediaEdge().getCount() == 0) {
                            runOnUiThread(() -> {
                                hideLoading();
                                Toast.makeText(MainActivity.this, "У пользователя нет публикаций", Toast.LENGTH_LONG).show();
                            });
                        } else {
                            intent = new Intent(this, ImageActivity.class);
                            intent.putExtra("DATA", data);
                            runOnUiThread(this::hideLoading);
                            startActivity(intent);
                        }
                    }

                } catch (Exception e) {
                    runOnUiThread(() -> {
                        hideLoading();
                        Toast.makeText(MainActivity.this, "Такого пользователя не существует", Toast.LENGTH_LONG).show();
                    });
                    e.printStackTrace();
                }
            });
            thread.start();
        } else

        {
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_LONG).show();
        }


    }


    private void showLoading(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Идет загрузка...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        try {
            progressDialog.show();
        } catch (Exception e) {

        }
    }

    private void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void onImagesGeted(ResponseModel responseModel) {

    }
}
