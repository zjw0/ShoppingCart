package com.zhao.shoppingcart.net;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author by Jack_Zhao, Date on 2019/8/29.
 * PS: Not easy to write code, please indicate.
 */
public class API {

//    public static final String IpAll = "http://10.0.2.2:8080/androidlogin/servlet/LoginDateServlet?username=aaa&password=123";
    // 请求方式和请求url网络热点
//    public static final String IpAll = "http://192.168.43.35:8080/androidlogin/servlet/LoginDateServlet?username=aaa&password=123";
    // 请求方式和请求url网络WIFI
    public static final String IpAll = "http://192.168.1.107:8080/androidlogin/servlet/LoginDateServlet?username=aaa&password=123";


    public static final String IP = "https://www.easy-mock.com/mock/5d7200d233988f61eab0d9ba";

    public static final String URL = IP + "/example/";

    public static final String userInfo = URL + "userInfo";

    public static final String IMAGE_URL = IP + "";

}
