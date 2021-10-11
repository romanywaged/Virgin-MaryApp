package com.church.virginmaryapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.church.virginmaryapp.R;
import com.church.virginmaryapp.booking.showEvents.EventListViewActivity;
import com.church.virginmaryapp.followReservations.showFollowEvent.EnterNationalId;
import com.church.virginmaryapp.models.SetUpDatabase;
import com.church.virginmaryapp.models.SharedPrefrenceModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
{

    SharedPrefrenceModel sharedPrefrenceModel;
    private AppUpdateManager appUpdateManager;
    public static final int RC_APP_UPDATE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        ButterKnife.bind(this);

        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE))
                {
                    try {
                        appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.IMMEDIATE,
                                HomeActivity.this, RC_APP_UPDATE );
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        sharedPrefrenceModel = new SharedPrefrenceModel(HomeActivity.this);
        if (!sharedPrefrenceModel.Check())
        {
            SetUpDatabase database = new SetUpDatabase(HomeActivity.this);
            database.readTittle();
            database.setup();
            sharedPrefrenceModel.SaveShared(true);
        }

    }

    @Override
    protected void onResume() {

        super.onResume();
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if (result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
                {
                    try {
                        appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.FLEXIBLE,HomeActivity.this,
                                RC_APP_UPDATE );
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
            if (state.installStatus() == InstallStatus.DOWNLOADED)
            {
                showCompletedUpdate();
            }
        }
    };

    private void showCompletedUpdate() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.dashboard),"New App is ready!",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RC_APP_UPDATE &&resultCode == RESULT_OK)
        {
            Toast.makeText(this,"cancel",Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.taranem_card_view)
    public void openHymensActivity()
    {
        move(HymensActivity.class);
    }


    @OnClick(R.id.bookAodasCardView)
    public void onClickBookAodas()
    {
        Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
        intent.putExtra("eventCategoryId", 1);
        intent.putExtra("eventTypeName", getString(R.string.booking_aodas));
        startActivity(intent);
    }


    @OnClick(R.id.aboutCardView)
    public void openAboutView()
    {
        move(AboutActivity.class);
    }

    @OnClick(R.id.follow_card)
    public void openFollowReservation()
    {
        move(EnterNationalId.class);
    }

    // lma kan 3ndna nahda
    private void openElReservationEventsView(int categoryId, String eventName)
    {
        Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
        intent.putExtra("eventCategoryId", categoryId);
        intent.putExtra("eventTypeName", eventName);
        startActivity(intent);
    }


    private void move(Class c)
    {
        Intent intent = new Intent(HomeActivity.this,c);
        startActivity(intent);
    }
}