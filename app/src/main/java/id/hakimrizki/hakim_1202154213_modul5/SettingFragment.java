package id.hakimrizki.hakim_1202154213_modul5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by hakimrizki on 29/03/18.
 */

public class SettingFragment extends PreferenceFragmentCompat {
    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.layout.setting, rootKey);
    }
}
