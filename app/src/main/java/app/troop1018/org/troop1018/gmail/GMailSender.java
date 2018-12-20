package app.troop1018.org.troop1018.gmail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceManager;
import android.view.KeyEvent;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.troop1018.org.troop1018.R;
import app.troop1018.org.troop1018.SettingsActivity;

public class GMailSender extends javax.mail.Authenticator implements DialogInterface.OnKeyListener {
    private static final String mailhost = "smtp.gmail.com";
    private String sender;
    private Session session;

    private SharedPreferences sharedPref;

    private Context context;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public GMailSender(Context context) {

        this.context = context;

        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    public void sendMail(String subject, String body) {
        String sender = sharedPref.getString(SettingsActivity.KEY_PREF_CHUCKBOX_EMAIL_SENDER, null);
        this.sender = sender;
        new GMailAsyncTask(subject, body, this).execute();
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String senderPassword = sharedPref.getString(SettingsActivity.KEY_PREF_CHUCKBOX_EMAIL_SENDER_PASSWORD, null);
        return new PasswordAuthentication(sender, senderPassword);
    }

    private void emailComplete(final String message) {

        if (message == null) {
            CharSequence text = context.getResources().getString(R.string.rept_sent_to_qm);
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            final String title = context.getString(R.string.qm_rpt_send_err);
            android.support.v7.app.AlertDialog aDialog = new AlertDialog.Builder(context).setMessage(message).setTitle(title)
                    .setNeutralButton(R.string.btn_close, new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int which) {
                            CharSequence text = context.getResources().getString(R.string.rept_not_sent_to_qm);
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }).create();
            aDialog.setOnKeyListener(GMailSender.this);
            aDialog.show();
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //disable the back button
        }
        return true;
    }

    public class GMailAsyncTask extends AsyncTask<Void, Void, Void> {

        private GMailSender gmailSender;

        private String subject;
        private String body;

        private Exception ex;

        /**
         * Constructor.
         *
         */
        private GMailAsyncTask(String subject, String body, GMailSender gmailSender) {
            this.gmailSender = gmailSender;
            this.subject = subject;
            this.body = body;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MimeMessage message = new MimeMessage(session);
                DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
                message.setSender(new InternetAddress(sender));
                message.setSubject(subject);
                message.setDataHandler(handler);
                //        if (recipients.indexOf(',') > 0)
                //            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
                //        else
                String receiver = sharedPref.getString(SettingsActivity.KEY_PREF_CHUCKBOX_EMAIL_RECEIVER, null);
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
                Transport.send(message);
            }
            catch (Exception e) {
                this.ex = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            String msg = null;
           if (ex != null) {
                msg = ex.getLocalizedMessage();
                if (msg == null) {
                    msg = ex.getMessage();
                    if (msg == null) {
                        if (ex.getCause() != null) {
                            msg = ex.getCause().getMessage();
                        }
                        else {
                            if (ex instanceof AuthenticationFailedException) {
                                msg = context.getResources().getString(R.string.authentication_error_msg);
                            }
                            else {
                                msg = ex.toString();
                            }
                        }
                    }
                }
            }
            gmailSender.emailComplete(msg);
        }

        private class ByteArrayDataSource implements DataSource {
            private byte[] data;
            private String type;

            private ByteArrayDataSource(byte[] data, String type) {
                super();
                this.data = data;
                this.type = type;
            }

            public ByteArrayDataSource(byte[] data) {
                super();
                this.data = data;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContentType() {
                if (type == null)
                    return "application/octet-stream";
                else
                    return type;
            }

            public InputStream getInputStream() {

                return new ByteArrayInputStream(data);
            }

            public String getName() {
                return "ByteArrayDataSource";
            }

            public OutputStream getOutputStream() throws IOException {
                throw new IOException("Not Supported");
            }
        }
    }
}