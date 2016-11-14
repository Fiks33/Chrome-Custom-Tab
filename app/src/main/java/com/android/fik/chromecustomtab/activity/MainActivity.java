package com.android.fik.chromecustomtab.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.fik.chromecustomtab.R;
import com.android.fik.chromecustomtab.utils.CustomTabActivityHelper;
import com.android.fik.chromecustomtab.webview.WebviewFallback;

/**
 * Created by Mochamad Taufik on 14-Nov-16.
 * Email   : thidayat13@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mLoadGoogle;
    private Button mLoadAnother;
    private EditText mInputUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLoadGoogle = (Button) findViewById(R.id.load_google);
        mLoadAnother = (Button) findViewById(R.id.another_site);

        setSupportActionBar(mToolbar);

        mLoadGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                        .setShowTitle(true)
                        .setToolbarColor(Color.parseColor("#00796B"))
                        .build();

                CustomTabActivityHelper.openCustomTab(
                        MainActivity.this,// activity
                        customTabsIntent,
                        Uri.parse("http://www.google.com"),
                        new WebviewFallback()
                );
            }
        });

        mLoadAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogInput();
            }
        });
    }

    public void showDialogInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(!mInputUrl.getText().toString().isEmpty()) {

                    CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                            .setShowTitle(true)
                            .setToolbarColor(Color.parseColor("#00796B"))
                            .build();

                    CustomTabActivityHelper.openCustomTab(
                            MainActivity.this,// activity
                            customTabsIntent,
                            Uri.parse(mInputUrl.getText().toString()),
                            new WebviewFallback()
                    );
                }else{
                    Toast.makeText(getApplicationContext(),"Please fill form",Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Batal",null);
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_input, null);

        mInputUrl   = (EditText)dialogLayout.findViewById(R.id.url);

        dialog.setView(dialogLayout);
        dialog.show();

    }

}
