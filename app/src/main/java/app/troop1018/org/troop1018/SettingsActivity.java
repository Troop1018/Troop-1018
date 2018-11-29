package app.troop1018.org.troop1018;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_CHUCKBOX_EMAIL_SENDER = "chuck_check_email_sender";
    public static final String KEY_PREF_CHUCKBOX_EMAIL_SENDER_PASSWORD = "chuck_check_email_sender_password";
    public static final String KEY_PREF_CHUCKBOX_EMAIL_RECEIVER = "chuck_check_email_receiver";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}

