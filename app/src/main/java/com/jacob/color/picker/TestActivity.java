package com.jacob.color.picker;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

/**
 * Package : com.jacob.color.picker
 * Author : jacob
 * Date : 15-9-28
 * Description : 这个类是用来xxx
 */
public class TestActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        ((ColorPickerPreference) findPreference("color2")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
                return true;
            }

        });
        ((ColorPickerPreference) findPreference("color2")).setAlphaSliderEnabled(true);
    }
}
