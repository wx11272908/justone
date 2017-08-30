package framework.justone.com.justone.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import framework.justone.com.justone.R;
import framework.justone.com.justone.app.TrapApp;
import framework.justone.com.justone.ui.base.BaseActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }



    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrapApp.exitApp(0);
            }
        });

    }
}
