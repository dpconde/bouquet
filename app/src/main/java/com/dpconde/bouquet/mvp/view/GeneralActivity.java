package com.dpconde.bouquet.mvp.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dpconde.bouquet.R;
import com.google.android.material.snackbar.Snackbar;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class GeneralActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public LoadingDialog loadingDialog;
    protected Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get toolbar component
        toolbar = findViewById(R.id.my_toolbar);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    protected void addDrawer(){

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_background)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("David Pérez")
                                .withEmail("dpconde.me@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Elem1");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Elem2");

        //create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("Elem3")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return false;
                    }
                }).build();
    }

    /**
     * Generic method to show messages in every Activity
     * @param stringResource
     */
    public void showMessage(int stringResource){
        Snackbar.make(findViewById(android.R.id.content), stringResource, Snackbar.LENGTH_LONG).show();
    }


    public void showLoading(){
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_general_loading_dialog, null);

        new AlertDialog.Builder(this)
                .setView(view)
                .show();
    }


    public void hideLoading(){
        //loadingDialog.dismiss();
    }


    public static class LoadingDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View view = inflater.inflate(R.layout.activity_general_loading_dialog, null);
            builder.setView(view);

            return builder.create();
        }

        @Override
        public void onResume() {
            super.onResume();
            ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = 200;
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        }
    }

    protected void openCloseDrawer(){
        if(drawer.isDrawerOpen()){
            drawer.closeDrawer();
        }else{
            drawer.openDrawer();
        }
    }


}
