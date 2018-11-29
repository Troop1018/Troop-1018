package app.troop1018.org.troop1018;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChuckCheckFragment extends Fragment {

    private List<CheckBox> drawer1Checkboxes = new ArrayList<>();
    private List<CheckBox> drawer2Checkboxes = new ArrayList<>();
    private List<CheckBox> drawer3Checkboxes = new ArrayList<>();
    private List<CheckBox> drawer4Checkboxes = new ArrayList<>();

    private static final int POTS_PANS_TAB = 0;
    private static final int BOWLS_PLATES_TAB = 1;
    private static final int UTENSILS_TAB = 2;
    private static final int SUPPLIES_TAB = 3;

    private int currentChuckTab = POTS_PANS_TAB;

    private PopupWindow quartermasterReportPopUp;

    private String notesToQM;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TabHost host = getView().findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec(getString(R.string.tab_pots_and_pans));
        spec.setContent(R.id.tab1);
        spec.setIndicator(getString(R.string.tab_pots_and_pans));
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec(getString(R.string.tab_bowls_and_plates));
        spec.setContent(R.id.tab2);
        spec.setIndicator(getString(R.string.tab_bowls_and_plates));
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec(getString(R.string.tab_utensils));
        spec.setContent(R.id.tab3);
        spec.setIndicator(getString(R.string.tab_utensils));
        host.addTab(spec);

        //Tab 4
        spec = host.newTabSpec(getString(R.string.tab_supplies));
        spec.setContent(R.id.tab4);
        spec.setIndicator(getString(R.string.tab_supplies));
        host.addTab(spec);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
                currentChuckTab = host.getCurrentTab();
            }
        });

        drawer1Checkboxes.clear();
        drawer2Checkboxes.clear();
        drawer3Checkboxes.clear();
        drawer4Checkboxes.clear();

        final TextView drawer1Pct = getView().findViewById(R.id.pots_pans_pct);
        final TextView drawer2Pct = getView().findViewById(R.id.bowls_plates_pct);
        final TextView drawer3Pct = getView().findViewById(R.id.utensils_pct);
        final TextView drawer4Pct = getView().findViewById(R.id.supplies_pct);
        final TextView totalPct = getView().findViewById(R.id.total_pct);

        final ProgressBar progressBarDrawer1 = getView().findViewById(R.id.progressBarDrawer1);
        final ProgressBar progressBarDrawer2 = getView().findViewById(R.id.progressBarDrawer2);
        final ProgressBar progressBarDrawer3 = getView().findViewById(R.id.progressBarDrawer3);
        final ProgressBar progressBarDrawer4 = getView().findViewById(R.id.progressBarDrawer4);
        final ProgressBar progressBarTotal = getView().findViewById(R.id.progressBarTotal);

        LinearLayout ll1 = getView().findViewById(R.id.ll1);
        int childCount = ll1.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = ll1.getChildAt(i);
            if (v instanceof CheckBox) {
                drawer1Checkboxes.add((CheckBox)v);
                ((CheckBox)v).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        drawerCheckboxChanged(drawer1Pct, totalPct, progressBarDrawer1, progressBarTotal, drawer1Checkboxes);
                    }
                });
            }
        }
        progressBarDrawer1.setMax(drawer1Checkboxes.size());

        LinearLayout ll2 = getView().findViewById(R.id.ll2);
        childCount = ll2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = ll2.getChildAt(i);
            if (v instanceof CheckBox) {
                drawer2Checkboxes.add((CheckBox)v);
                ((CheckBox)v).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        drawerCheckboxChanged(drawer2Pct, totalPct, progressBarDrawer2, progressBarTotal, drawer2Checkboxes);
                    }
                });
            }
        }
        progressBarDrawer2.setMax(drawer2Checkboxes.size());

        LinearLayout ll3 = getView().findViewById(R.id.ll3);
        childCount = ll3.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = ll3.getChildAt(i);
            if (v instanceof CheckBox) {
                drawer3Checkboxes.add((CheckBox)v);
                ((CheckBox)v).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        drawerCheckboxChanged(drawer3Pct, totalPct, progressBarDrawer3, progressBarTotal, drawer3Checkboxes);
                    }
                });
            }
        }
        progressBarDrawer3.setMax(drawer3Checkboxes.size());

        LinearLayout ll4 = getView().findViewById(R.id.ll4);
        childCount = ll4.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = ll4.getChildAt(i);
            if (v instanceof CheckBox) {
                drawer4Checkboxes.add((CheckBox)v);
                ((CheckBox)v).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        drawerCheckboxChanged(drawer4Pct, totalPct, progressBarDrawer4, progressBarTotal, drawer4Checkboxes);
                    }
                });
            }
        }
        progressBarDrawer4.setMax(drawer4Checkboxes.size());
        progressBarTotal.setMax(drawer1Checkboxes.size() + drawer2Checkboxes.size() +
                drawer3Checkboxes.size() + drawer4Checkboxes.size());

        Button btnClear = getView().findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCurrentChuckTab(currentChuckTab);
            }
        });

        Button btnClearAll = getView().findViewById(R.id.btnClearAll);
        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllChuckTabs();
            }
        });

        Button btnReportQM = getView().findViewById(R.id.btnReportQM);
        btnReportQM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("0".equals(totalPct.getText())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(getString(R.string.empty_chuck_check))
                            .setCancelable(false)
                            .setPositiveButton(R.string.ok_btn, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    showQuartermasterReportPopup();
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.chuck_check, container, false);
    }

    private void clearCurrentChuckTab(int curTab) {

        switch(curTab) {
            case POTS_PANS_TAB:
                clearChuckTab(drawer1Checkboxes);
                break;
            case BOWLS_PLATES_TAB:
                clearChuckTab(drawer2Checkboxes);
                break;
            case UTENSILS_TAB:
                clearChuckTab(drawer3Checkboxes);
                break;
            case SUPPLIES_TAB:
                clearChuckTab(drawer4Checkboxes);
                break;
        }
    }

    private void clearAllChuckTabs() {

        for (int i = 0; i < 4; i++) {
            clearCurrentChuckTab(i);
        }
    }

    private void clearChuckTab(List<CheckBox> drawerCheckboxes) {

        for (CheckBox drawerCheckbox : drawerCheckboxes) {
            drawerCheckbox.setChecked(false);
        }
    }

    private void drawerCheckboxChanged(TextView drawerPct, TextView totalPct,
                                       ProgressBar progressBarDrawer, ProgressBar progressBarTotal, List<CheckBox> drawerCheckboxes) {

        double totNum = drawerCheckboxes.size();
        double chkNum = 0;
        for (CheckBox cb : drawerCheckboxes) {
            if (cb.isChecked()) {
                chkNum++;
            }
        }

        double totPct = 0.0;
        if (chkNum > 0) {
            totPct = (chkNum / totNum) * 100;
        }

        drawerPct.setText(String.valueOf((int) Math.round(totPct)));
        progressBarDrawer.setProgress((int)Math.round(chkNum));

        totNum = drawer1Checkboxes.size() + drawer2Checkboxes.size() + drawer3Checkboxes.size() + drawer4Checkboxes.size();
        chkNum = 0;
        for (CheckBox cb : drawer1Checkboxes) {
            if (cb.isChecked()) {
                chkNum++;
            }
        }
        for (CheckBox cb : drawer2Checkboxes) {
            if (cb.isChecked()) {
                chkNum++;
            }
        }
        for (CheckBox cb : drawer3Checkboxes) {
            if (cb.isChecked()) {
                chkNum++;
            }
        }
        for (CheckBox cb : drawer4Checkboxes) {
            if (cb.isChecked()) {
                chkNum++;
            }
        }

        totPct = 0.0;
        if (chkNum > 0) {
            totPct = (chkNum / totNum) * 100;
        }

        totalPct.setText(String.valueOf((int) Math.round(totPct)));
        progressBarTotal.setProgress((int)Math.round(chkNum));
    }

    private void showQuartermasterReportPopup() {

        Spinner patrolSpinner = getView().findViewById(R.id.patrolSpinner);
        final String patrol = (String) patrolSpinner.getSelectedItem();

        RelativeLayout viewGroup = getView().findViewById(R.id.chuckboxReportPopup);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = layoutInflater.inflate(R.layout.activity_quartermaster_report, viewGroup);

        LinearLayout chuckCheck = getView().findViewById(R.id.chuck_check);
        int popupHeight = chuckCheck.getHeight() - (int) (chuckCheck.getHeight() * .25);

        // Creating the PopupWindow
        quartermasterReportPopUp = new PopupWindow(getContext());
        quartermasterReportPopUp.setContentView(layout);
        quartermasterReportPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        quartermasterReportPopUp.setHeight(popupHeight);
        quartermasterReportPopUp.setFocusable(true);
        quartermasterReportPopUp.setOutsideTouchable(false);

        // Clear the default translucent background
        quartermasterReportPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        quartermasterReportPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);

        TextView itemsMissing = layout.findViewById(R.id.itemsMissing);
        itemsMissing.setMovementMethod(new ScrollingMovementMethod());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        String formattedDate = df.format(c.getTime());

        TextView currDate = layout.findViewById(R.id.currDate);
        currDate.setText(formattedDate);

        final String missingChuckboxItems = getMissingChuckboxItems();

        if (missingChuckboxItems.length() > 0) {
            itemsMissing.setText(missingChuckboxItems);
        }
        else {
            itemsMissing.setText(getString(R.string.all_items_accounted_for));
        }

        TextView patrolName = layout.findViewById(R.id.patrolName);
        patrolName.setText(patrol);
        EditText qmNotes = layout.findViewById(R.id.qmNotes);
        if (notesToQM != null && notesToQM.trim().length() > 0) {
            qmNotes.setText(notesToQM, TextView.BufferType.EDITABLE);
        }

        int w = (int) ((28 / Resources.getSystem().getDisplayMetrics().density)) * 2;
        int h = (int) ((24 / Resources.getSystem().getDisplayMetrics().density)) * 2;
        int l = 12;

        Drawable icon = getContext().getResources().getDrawable(R.drawable.chuckcheck_rpt);
        icon.setBounds(l, 0, l + w, h);
        Button rpt = layout.findViewById(R.id.chuckcheckReport);
        rpt.setCompoundDrawables(icon, null , null , null);
        rpt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String subject = getSubjectForReport(patrol);
                EditText qmNotes = layout.findViewById(R.id.qmNotes);
                notesToQM = qmNotes.getText().toString();
                quartermasterReportPopUp.dismiss();
                String body = generateQuartermasterReport(subject, missingChuckboxItems, notesToQM);
                sendChuckCheckReport(subject, body);
            }
        });

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = layout.findViewById(R.id.closeCuckboxReport);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText qmNotes = layout.findViewById(R.id.qmNotes);
                notesToQM = qmNotes.getText().toString();
                quartermasterReportPopUp.dismiss();
            }
        });
    }

    private String generateQuartermasterReport(String subject, String missingItems, String qmNotes) {

        StringBuilder sb = new StringBuilder();
        sb.append(subject).append("\n").append("\n").append(getString(R.string.items_missing_lbl)).append("\n").append("\n").append(missingItems);
        if (qmNotes != null && qmNotes.trim().length() > 0) {
            sb.append("\n").append("\n").append(getString(R.string.notes_to_qm_lbl)).append("\n").append("\n").append(qmNotes);
        }

        return sb.toString();
    }

    private String getSubjectForReport(String patrol) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        String formattedDate = df.format(c.getTime());

        Object[] args = { patrol, formattedDate};
        return getString(R.string.chuck_check_email_subject, args); // + patrol + " patrol - " + formattedDate;
    }

    private String getMissingChuckboxItems() {

        StringBuilder missingItems = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (CheckBox cb : drawer1Checkboxes) {
            if (! cb.isChecked()) {
                if (sb.toString().trim().length() < 1) {
                    sb.append(getString(R.string.tab_pots_and_pans));
                }
                sb.append("\n").append(" ").append("\u2022").append(" ").append(cb.getText());
            }
        }
        if (sb.toString().trim().length() > 0) {
            missingItems.append(sb);
        }
        sb.delete(0, sb.length());
        for (CheckBox cb : drawer2Checkboxes) {
            if (! cb.isChecked()) {
                if (sb.toString().trim().length() < 1) {
                    if (missingItems.toString().trim().length() > 0) {
                        sb.append("\n").append("\n");
                    }
                    sb.append(getString(R.string.tab_bowls_and_plates));
                }
                sb.append("\n").append(" ").append("\u2022").append(" ").append(cb.getText());
            }
        }
        if (sb.toString().trim().length() > 0) {
            missingItems.append(sb);
        }
        sb.delete(0, sb.length());
        for (CheckBox cb : drawer3Checkboxes) {
            if (! cb.isChecked()) {
                if (sb.toString().trim().length() < 1) {
                    if (missingItems.toString().trim().length() > 0) {
                        sb.append("\n").append("\n");
                    }
                    sb.append(getString(R.string.tab_utensils));
                }
                sb.append("\n").append(" ").append("\u2022").append(" ").append(cb.getText());
            }
        }
        if (sb.toString().trim().length() > 0) {
            missingItems.append(sb);
        }
        sb.delete(0, sb.length());
        for (CheckBox cb : drawer4Checkboxes) {
            if (! cb.isChecked()) {
                if (sb.toString().trim().length() < 1) {
                    if (missingItems.toString().trim().length() > 0) {
                        sb.append("\n").append("\n");
                    }
                    sb.append(getString(R.string.tab_supplies));
                }
                sb.append("\n").append(" ").append("\u2022").append(" ").append(cb.getText());
            }
        }
        if (sb.toString().trim().length() > 0) {
            missingItems.append(sb);
        }
        return missingItems.toString().trim();
    }

    private void sendChuckCheckReport(final String subject, final String body) {

        GMailSender sender = new GMailSender(ChuckCheckFragment.this.getContext());
        sender.sendMail(subject, body);
    }
}
