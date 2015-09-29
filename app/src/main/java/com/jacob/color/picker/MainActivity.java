package com.jacob.color.picker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_a).setOnClickListener(this);
        findViewById(R.id.button_b).setOnClickListener(this);
        findViewById(R.id.button_c).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button_a:
                intent = new Intent(MainActivity.this, OfficialActivity.class);
                startActivity(intent);
                break;
            case R.id.button_b:
                intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
                break;
            case R.id.button_c:
                intent = new Intent(MainActivity.this, SaturationActivity.class);
                startActivity(intent);
                break;
        }
    }

}
