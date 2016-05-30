package com.example.zhihu;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zhihu.Theme.ThemeFragment;
import com.example.zhihu.adapter.LeftAdapter;
import com.example.zhihu.base.BaseActivity;
import com.example.zhihu.bean.LeftEntity;
import com.example.zhihu.home.center.HomeFragment;
import com.example.zhihu.left.presenter.LeftpresenterImpl;
import com.example.zhihu.left.view.Ileftview;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements Ileftview {


    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    LeftpresenterImpl leftpresenterImpl;
    @Bind(R.id.nav_view)
    NavigationView navView;
    LeftAdapter mAdapter;
    List<LeftEntity.OthersBean> themesEntities = new ArrayList<>();
    HomeFragment homeFragment;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    ThemeFragment themeFragment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        leftpresenterImpl = new LeftpresenterImpl((Ileftview) this);
        leftpresenterImpl.getleft();
        toolbar.setNavigationIcon(R.drawable.ic_list_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();

            }
        });
        /**动态加载fragment*/
//        mainFragment = new HomeFragment();
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        /*content_main中的id*/
//        ft.add(R.id.relative, mainFragment);
//        ft.commit();

        homeFragment = new HomeFragment();
        fragmentManager = this.getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.home_framelayout, homeFragment);
        fragmentTransaction.commit();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void Ileftview(final LeftEntity leftEntity) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
        mAdapter = new LeftAdapter((ArrayList<LeftEntity.OthersBean>) leftEntity.getOthers(), mcontext);
        recyclerview.setAdapter(mAdapter);

        /**点击事件的回调*/
        mAdapter.setOnItemClickListener(new LeftAdapter.onItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                if (view.getId() == R.id.left_home) {
                    Log.i("lhd", "onItemclick: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    homeFragment = new HomeFragment();
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.home_framelayout, homeFragment);
                    fragmentTransaction.commit();
                } else {
                    themeFragment=new ThemeFragment();
                    Bundle bundle=new Bundle();
                    int id = leftEntity.getOthers().get(position-1).getId();
                    Log.i("lhd", "onItemclick: "+id+leftEntity.getOthers().get(position-1).getName());
                    bundle.putInt("menuID",id);
                    themeFragment.setArguments(bundle);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.home_framelayout, themeFragment);
                    fragmentTransaction.commit();
            }
                closeMenu();
            }

        });
    }

    public void openMenu() {
        drawer.openDrawer(navView);
    }

    public void closeMenu() {
        drawer.closeDrawer(navView);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.zhihu/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.zhihu/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
