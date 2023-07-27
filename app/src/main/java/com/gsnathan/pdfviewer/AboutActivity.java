
package com.gsnathan.pdfviewer;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.franmontiel.attributionpresenter.AttributionPresenter;
import com.franmontiel.attributionpresenter.entities.Attribution;
import com.franmontiel.attributionpresenter.entities.License;
import com.gsnathan.pdfviewer.databinding.ActivityAboutBinding;
import com.jaredrummler.cyanea.app.CyaneaAppCompatActivity;

public class AboutActivity extends CyaneaAppCompatActivity {

    private ActivityAboutBinding viewBinding;
    private final String APP_VERSION_RELEASE = "Version " + Utils.getAppVersion();   //contains Version + the version number
    private final String APP_VERSION_DEBUG = "Version " + Utils.getAppVersion() + "-debug";   //contains Version + the version number + debug

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        /*setVersionText();*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*private void setVersionText() {
        // check if app is debug
        if (BuildConfig.DEBUG) {
            viewBinding.versionTextView.setText(APP_VERSION_DEBUG);
        } else {   //if app is release
            viewBinding.versionTextView.setText(APP_VERSION_RELEASE);
        }
    }
*/
    public void showPrivacy(View v) {
        new PrivacyInfoDialog().show(getSupportFragmentManager(), "privacy_dialog");
    }

    public void emailDev(View v) {
        String email = "abdulrocker4@gmail.com";
        try {
            startActivity(Utils.emailIntent(email, "Pdf Viewer Plus", APP_VERSION_RELEASE));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        }
    }

    public void navToGit(View v) {
        startActivity(Utils.linkIntent("https://github.com/JavaCafe01"));
    }

    public void navToSourceCode(View v) {
        startActivity(Utils.linkIntent("https://github.com/JavaCafe01/PdfViewer"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    public static class PrivacyInfoDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            return builder.setTitle(R.string.privacy)
                    .setMessage(R.string.privacy_info)
                    .setPositiveButton(R.string.ok, (dialog, which) -> {})
                    .setIcon(R.drawable.privacy_icon)
                    .create();
        }
    }
}