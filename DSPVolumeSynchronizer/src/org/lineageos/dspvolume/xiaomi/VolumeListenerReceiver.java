package org.lineageos.dspvolume.xiaomi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

import android.os.Bundle;

public class VolumeListenerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null) {
            return;
        }

        if(intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", 0) == AudioManager.STREAM_MUSIC) {
            AudioManager audioManager = context.getSystemService(AudioManager.class);
            int current = intent.getIntExtra(
                            "android.media.EXTRA_VOLUME_STREAM_VALUE",
                            0
                          );
            int VolStep = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int targetVol = (int) Math.round(current * 15.0 / VolStep);
            audioManager.setParameters("volume_change=" + targetVol + ";flags=8");
        }
    }
}
