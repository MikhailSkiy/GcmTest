package mikhailskiy.com.gcmtest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mikhail.myapplication.backend.registration.Registration;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mikhail on 14.03.16.
 */
public class SenderTask extends AsyncTask<Void, Void, String> {
    private static Registration regService = null;
    private GoogleCloudMessaging gcm;
    private Context context;
    private AtomicInteger msgId = new AtomicInteger();

    // TODO: change to your own sender ID to Google Developers Console project number, as per instructions above
    private static final String SENDER_ID = "";

    public SenderTask(Context context) {
        this.context = context;
    }

    protected String doInBackground(Void... params) {
        String msg = "";
        try {
            Bundle data = new Bundle();
            data.putString("my_message", "Hello World");
            data.putString("my_action", "SAY_HELLO");
            String id = Integer.toString(msgId.incrementAndGet());

            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(context);
            }

            gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
            msg = "Sent message";
        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
        }
        return msg;
    }


    @Override
    protected void onPostExecute(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}

