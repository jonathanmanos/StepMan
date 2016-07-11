package jonathanmanos.stepman.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Jonny on 7/9/2016.
 */
public class StartStepCounterAtBootReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, StepCounterService.class);
            context.startService(serviceIntent);
        }
    }

}
