package com.jacob.color.picker;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.larswerkman.holocolorpicker.ColorPickerView;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SaturationBar;

/**
 * Package : com.jacob.color.picker
 * Author : jacob
 * Date : 15-9-28
 * Description : 这个类是用来xxx
 */
public class ColorActivity extends FragmentActivity {
    private SaturationBar saturationBar;
    private OpacityBar opacityBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ColorPickerView colorPickerView = (ColorPickerView) findViewById(R.id.color_picker_view);
        colorPickerView.setOnColorChangedListennerD(new ColorPickerView.OnColorChangedListenerD() {
            @Override
            public void onColorChanged(int color, String hexStrColor) {
                if (color != 0) {
                    saturationBar.setColor(color);
                }
            }
        });

        saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
        saturationBar.setOnSaturationChangedListener(new SaturationBar.OnSaturationChangedListener() {
            @Override
            public void onSaturationChanged(int saturation) {
                Log.e("color:",saturation+"");
                opacityBar.setColor(saturation);
            }
        });

        opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
    }


}
