package com.jacob.color.picker;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.larswerkman.holocolorpicker.ColorPanelView;

/**
 * Package : com.jacob.color.picker
 * Author : jacob
 * Date : 15-9-28
 * Description : 这个类是用来xxx
 */
public class SaturationActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturation);
        ColorPanelView panelView = (ColorPanelView) findViewById(R.id.color_panel_view);
        panelView.setOnSaturationChangeListener(new OnSaturationListener() {
            @Override
            public void onSaturationChanged(int saturation) {
                Log.e("saturation:", saturation + "");
            }
        });
    }


}
