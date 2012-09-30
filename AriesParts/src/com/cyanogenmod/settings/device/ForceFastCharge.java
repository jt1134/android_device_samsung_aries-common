package com.cyanogenmod.settings.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;

public class ForceFastCharge implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/kernel/fast_charge/force_fast_charge";

    public static boolean isSupported() {
        boolean supported = true;
        if (!Utils.fileExists(FILE)) {
            supported = false;
        }
        return supported;
    }

    /**
     * Restore fast charge settings from SharedPreferences. (Write to kernel.)
     * @param context       The context to read the SharedPreferences from
     */
    public static void restore(Context context) {
        if (!isSupported()) {
            return;
        }

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        int value;
        value = sharedPrefs.getBoolean(DeviceSettings.KEY_FORCE_FAST_CHARGE, false) ? 1 : 0;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Utils.writeValue(FILE, ((CheckBoxPreference)preference).isChecked() ? "0" : "1");
        return true;
    }

}
