package app.troop1018.org.troop1018;

//import android.accounts.AccountManager;
//import android.app.Dialog;
import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.android.http.AndroidHttp;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.util.ExponentialBackOff;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.api.services.calendar.Calendar;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import app.troop1018.org.troop1018.calendar.ApiAsyncTask;
//import app.troop1018.org.troop1018.calendar.CreateCalendarService;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class TroopCalendarFragment extends Fragment {

//    public Calendar mService;

//    GoogleAccountCredential credential;
//    Credential credential;
    private TextView mStatusText;
    private TextView mResultsText;
//    final HttpTransport transport = AndroidHttp.newCompatibleTransport();
//    final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    public static final int REQUEST_ACCOUNT_PICKER = 1000;
    public static final int REQUEST_AUTHORIZATION = 1001;
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    public static final String PREF_ACCOUNT_NAME = "accountName";
//    public static final String[] SCOPES = { CalendarScopes.CALENDAR_READONLY };
//    private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR_READONLY);

    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStatusText = getView().findViewById(R.id.mStatusText);
//        mStatusText.setText(getString(R.string.retrieving_data_msg));

        mResultsText = getView().findViewById(R.id.mResultsText);
        mResultsText.setPadding(16, 16, 16, 16);
        mResultsText.setVerticalScrollBarEnabled(true);
        mResultsText.setMovementMethod(new ScrollingMovementMethod());

        MyDynamicCalendar troopCalendar = getView().findViewById(R.id.troopCalendar);

        troopCalendar.setCalendarBackgroundColor("#eeeeee");
        troopCalendar.setHeaderBackgroundColor("#c3c388");
        troopCalendar.setHeaderTextColor("#000000");
        troopCalendar.setNextPreviousIndicatorColor("#245675");
        troopCalendar.setWeekDayLayoutBackgroundColor("#E3E9FF");
        troopCalendar.setWeekDayLayoutTextColor("#4830E4");
        troopCalendar.setExtraDatesOfMonthBackgroundColor("#bac7de");
        troopCalendar.setExtraDatesOfMonthTextColor("#000000");
        troopCalendar.setDatesOfMonthBackgroundColor("#dde3ee");
        troopCalendar.setDatesOfMonthTextColor("#000000");
        troopCalendar.setCurrentDateBackgroundColor("#FFF7D7");
        troopCalendar.setCurrentDateTextColor("#000000");
        troopCalendar.setBelowMonthEventTextColor("#425684");
        troopCalendar.setBelowMonthEventDividerColor("#635478");

        troopCalendar.showMonthViewWithBelowEvents();

//        new CreateCalendarService(this).execute();

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    InputStream in = TroopCalendarFragment.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//                    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
//                    File folder = getContext().getDir(TOKENS_DIRECTORY_PATH,Context.MODE_PRIVATE);
//                    // Build flow and trigger user authorization request.
//                    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                            transport, jsonFactory, clientSecrets, SCOPES)
//                            .setDataStoreFactory(new FileDataStoreFactory(folder))
//                            .setAccessType("offline")
//                            .build();
//                    com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//                    credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//                    mService = new com.google.api.services.calendar.Calendar.Builder(
//                            transport, jsonFactory, credential)
//                            .setApplicationName("Troop 1018")
//                            .build();
//                    System.out.println("XXXXXXXX");
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        // Initialize credentials and service object.
//        SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);
//        credential = GoogleAccountCredential.usingOAuth2(
//                getContext(), SCOPES)
//                .setBackOff(new ExponentialBackOff())
////                .setSelectedAccountName(PREF_ACCOUNT_NAME);
//                .setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_troop_calendar, container, false);

    }

    /**
     * Called whenever this activity is pushed to the foreground, such as after
     * a call to onCreate().
     */
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (isGooglePlayServicesAvailable()) {
//            refreshResults();
//        } else {
//            mStatusText.setText("Google Play Services required: " +
//                    "after installing, close and relaunch this app.");
//        }
//    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode code indicating the result of the incoming
     *     activity result.
     * @param data Intent (containing result data) returned by incoming
     *     activity result.
     */
//    @Override
//    public void onActivityResult(
//            int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch(requestCode) {
//            case REQUEST_GOOGLE_PLAY_SERVICES:
//                if (resultCode == RESULT_OK) {
//                    refreshResults();
//                } else {
//                    isGooglePlayServicesAvailable();
//                }
//                break;
//            case REQUEST_ACCOUNT_PICKER:
//                if (resultCode == RESULT_OK && data != null &&
//                        data.getExtras() != null) {
//                    String accountName =
//                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
//                    if (accountName != null) {
//                        credential.setSelectedAccountName(accountName);
//                        SharedPreferences settings =
//                                getActivity().getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString(PREF_ACCOUNT_NAME, accountName);
//                        editor.apply();
//                        refreshResults();
//                    }
//                } else if (resultCode == RESULT_CANCELED) {
//                    mStatusText.setText("Account unspecified.");
//                }
//                break;
//            case REQUEST_AUTHORIZATION:
//                if (resultCode == RESULT_OK) {
//                    refreshResults();
//                } else {
//                    chooseAccount();
//                }
//                break;
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    /**
     * Attempt to get a set of data from the Google Calendar API to display. If the
     * email address isn't known yet, then call chooseAccount() method so the
     * user can pick an account.
     */
//    private void refreshResults() {
//        if (credential.getSelectedAccountName() == null) {
//            chooseAccount();
//        } else {
//            if (isDeviceOnline()) {
//                new ApiAsyncTask(this).execute();
//            } else {
//                mStatusText.setText("No network connection available.");
//            }
//        }
//    }

    /**
     * Clear any existing Google Calendar API data from the TextView and update
     * the header message; called from background threads and async tasks
     * that need to update the UI (in the UI thread).
     */
    public void clearResultsText() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mStatusText.setText(getString(R.string.retrieving_data_msg));
                mResultsText.setText("");
            }
        });
    }

    /**
     * Fill the data TextView with the given List of Strings; called from
     * background threads and async tasks that need to update the UI (in the
     * UI thread).
     * @param dataStrings a List of Strings to populate the main TextView with.
     */
    public void updateResultsText(final List<String> dataStrings) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dataStrings == null) {
                    mStatusText.setText(getString(R.string.error_retrieving_data_msg));
                } else if (dataStrings.size() == 0) {
                    mStatusText.setText(getString(R.string.no_data_found_msg));
                } else {
                    mStatusText.setText("Data retrieved using" +
                            " the Google Calendar API:");
                    mResultsText.setText(TextUtils.join("\n\n", dataStrings));
                }
            }
        });
    }

    /**
     * Show a status message in the list header TextView; called from background
     * threads and async tasks that need to update the UI (in the UI thread).
     * @param message a String to display in the UI header TextView.
     */
    public void updateStatus(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mStatusText.setText(message);
            }
        });
    }

    /**
     * Starts an activity in Google Play Services so the user can pick an
     * account.
     */
    private void chooseAccount() {
//        startActivityForResult(
//                credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }

//    public void setCalendarService(Calendar service) {
//        mService = service;
//    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date. Will
     * launch an error dialog for the user to update Google Play Services if
     * possible.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
//    private boolean isGooglePlayServicesAvailable() {
//        final int connectionStatusCode =
//                GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
//        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
//            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
//            return false;
//        } else return connectionStatusCode == ConnectionResult.SUCCESS;
//    }

    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
//    public void showGooglePlayServicesAvailabilityErrorDialog(
//            final int connectionStatusCode) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
//                        connectionStatusCode,
//                        getActivity(),
//                        REQUEST_GOOGLE_PLAY_SERVICES);
//                dialog.show();
//            }
//        });
//    }
}
