package com.cyanogenmod.settings.device;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

public class ForceFastCharge implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/kernel/fast_charge/force_fast_charge";

    public static boolean isSupported() {
        return Utils.fileExists(FILE);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Utils.writeValue(FILE, ((Boolean) newValue) ? "1" : "0");
        return true;
    }

}
