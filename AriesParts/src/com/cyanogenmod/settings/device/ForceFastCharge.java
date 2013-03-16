package com.cyanogenmod.settings.device;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

public class ForceFastCharge implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/kernel/fast_charge/force_fast_charge";

    private static boolean enabled = false;

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean isSupported() {
        return Utils.fileExists(FILE);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        enabled = (Boolean) newValue;
        Utils.writeValue(FILE, enabled ? "1" : "0");
        return true;
    }

}
