package com.zhao.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhao.shoppingcart.utils.ToastUtil;
import com.zhao.shoppingcart.net.API;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author by Jack_Zhao, Date on 2019/8/29.
 * PS: Not easy to write code, please indicate.
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password_number)
    EditText etPasswordNumber;
    @BindView(R.id.tv_login_in)
    TextView tvLoginIn;

    private String phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {

    }

    private void initData() {

    }

    private void goLogin(String phone, String password) {
        OkGo.<String>get(API.IpAll)                            // 请求方式和请求url
                .params("username",phone)
                .params("password",password)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        ToastUtil.showToast(getApplicationContext(),response.message());
                        String s = response.body();
                        int s1 = response.code();
                        Gson gson = new Gson();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                        ToastUtil.showToast(getApplicationContext(),response.message());

                    }
                });
    }

    @OnClick({ R.id.et_phone_number, R.id.et_password_number, R.id.tv_login_in })
    public void onSelect(View view) {
        switch (view.getId()) {
            case R.id.et_phone_number:
                break;
            case R.id.et_password_number:
                break;
            case R.id.tv_login_in:
                phone = etPhoneNumber.getText().toString().trim();
                password = etPasswordNumber.getText().toString().trim();
                if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)){
                    goLogin(phone, password);
                }else {
                    ToastUtil.showToast(this,"账号和密码不能为空！");
                }
                break;
        }

    }
}
