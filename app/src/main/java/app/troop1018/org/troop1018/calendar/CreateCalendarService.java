package app.troop1018.org.troop1018.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

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
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;

//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

//import com.google.api.services.calendar.Calendar;
//import com.google.api.services.calendar.CalendarScopes;

import app.troop1018.org.troop1018.fragments.TroopCalendarFragment;

//import static app.troop1018.org.troop1018.fragments.TroopCalendarFragment.PREF_ACCOUNT_NAME;

public class CreateCalendarService  extends AsyncTask<Void, Void, Void> {

    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
//    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String APPLICATION_NAME = "Troop 1018";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private Exception exception;

    private TroopCalendarFragment troopCalendarFragment;

//    private Calendar mService;

    public CreateCalendarService(TroopCalendarFragment troopCalendarFragment) {
        this.troopCalendarFragment = troopCalendarFragment;
    }

    protected Void doInBackground(Void... params) {
        InputStream in = null;
        try {
//            in = TroopCalendarFragment.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//            final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

//            if (clientSecrets.getDetails().getClientId().startsWith("Enter")
//                    || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
//                System.out.println(
//                        "Enter Client ID and Secret from https://code.google.com/apis/console/?api=plus "
//                                + "into plus-cmdline-sample/src/main/resources/client_secrets.json");
//                System.exit(1);
//            }
            // set up authorization code flow
//            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,SCOPES).setDataStoreFactory(
//                    new FileDataStoreFactory(troopCalendarFragment.getContext().getDir(TOKENS_DIRECTORY_PATH,Context.MODE_PRIVATE))).setAccessType("offline").build();
//            // authorize
//            Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//            mService = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
//                    .setApplicationName(APPLICATION_NAME)
//                    .build();
//
//
//            System.out.println("AAAAAAAAAAAAAAAAAAA");







//            final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
//
//            in = TroopCalendarFragment.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//            File folder = troopCalendarFragment.getContext().getDir(TOKENS_DIRECTORY_PATH,Context.MODE_PRIVATE);

            // Build flow and trigger user authorization request.
//            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                    .setDataStoreFactory(new FileDataStoreFactory(folder))
//                    .setAccessType("offline")
//                    .build();
//            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//            Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

//            GoogleAccountCredential credential =
//                    GoogleAccountCredential.usingOAuth2(troopCalendarFragment.getContext(), SCOPES);
//            SharedPreferences settings = troopCalendarFragment.getActivity().getPreferences(Context.MODE_PRIVATE);
//            credential.setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
//            mService =
//                    new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
//                            .setApplicationName("Google-TasksAndroidSample/1.0").build();


//            mService = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
//                    .setApplicationName(APPLICATION_NAME)
//                    .build();

        } catch (Exception e) {
            this.exception = e;
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    // do nothing
                }
            }
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        exception.printStackTrace();
//        troopCalendarFragment.setCalendarService(mService);
    }
}
