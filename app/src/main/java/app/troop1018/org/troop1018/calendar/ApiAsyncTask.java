package app.troop1018.org.troop1018.calendar;

import android.os.AsyncTask;
//import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
//import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
//import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.troop1018.org.troop1018.TroopCalendarFragment;

public class ApiAsyncTask extends AsyncTask<Void, Void, Void> {

    private TroopCalendarFragment mActivity;

    /**
     * Constructor.
     * @param activity MainActivity that spawned this task.
     */
    public ApiAsyncTask(TroopCalendarFragment activity) {
        this.mActivity = activity;
    }

    /**
     * Background task to call Google Calendar API.
     * @param params no parameters needed for this task.
     */
    @Override
    protected Void doInBackground(Void... params) {
//        try {
//            mActivity.clearResultsText();
//            mActivity.updateResultsText(getDataFromApi());
//
//        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
//            mActivity.showGooglePlayServicesAvailabilityErrorDialog(
//                    availabilityException.getConnectionStatusCode());
//
//        } catch (UserRecoverableAuthIOException userRecoverableException) {
//            mActivity.startActivityForResult(
//                    userRecoverableException.getIntent(),
//                    TroopCalendarFragment.REQUEST_AUTHORIZATION);
//
//        } catch (IOException e) {
//            mActivity.updateStatus("The following error occurred: " +
//                    e.getMessage());
//        }
        return null;
    }

    /**
     * Fetch a list of the next 10 events from the primary calendar.
     * @return List of Strings describing returned events.
     * @throws IOException
     */
    private List<String> getDataFromApi() throws IOException {
        // List the next 10 events from the primary calendar.
//        DateTime now = new DateTime(System.currentTimeMillis());
        List<String> eventStrings = new ArrayList<String>();
//        Events events = mActivity.mService.events().list("troop1018.org_8ueo9i1n1klmd15ips6k9e77a4@group.calendar.google.com")
//                .setMaxResults(10)
//                .setTimeMin(now)
//                .setOrderBy("startTime")
//                .setSingleEvents(true)
//                .execute();
//        List<Event> items = events.getItems();
//
//        for (Event event : items) {
//            DateTime start = event.getStart().getDateTime();
//            if (start == null) {
//                // All-day events don't have start times, so just use
//                // the start date.
//                start = event.getStart().getDate();
//            }
//            eventStrings.add(
//                    String.format("%s (%s)", event.getSummary(), start));
//        }
        return eventStrings;
    }

}