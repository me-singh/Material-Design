package com.example.user.material;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Typeface typeface;
    Bundle bundle;
    AnimatedVectorDrawable checkToClear;
    AnimatedVectorDrawable clearToCheck;
    Boolean tick=true;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        }

        //creating custom font to use as a heading using asset in resource directory
        typeface=Typeface.createFromAsset(getAssets(),"Roboto-BoldItalic.ttf");

        final Button button=findViewById(R.id.button);
        final TextView textView=findViewById(R.id.hello);
        final ImageView avatar=findViewById(R.id.avatar);
        ImageView imageView=findViewById(R.id.imageView);

        //setting custom font
        textView.setTypeface(typeface);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(new Intent(MainActivity.this,AddTodo.class),bundle);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Tangible surfaces-circular reveal effect for recyclerView Item on click
                int finalRadius= (int) Math.hypot(v.getWidth()/2,v.getHeight()/2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator= ViewAnimationUtils.createCircularReveal(
                            v,v.getWidth()/2,v.getHeight()/2,0,finalRadius
                    );
                    v.setBackgroundColor(Color.DKGRAY);
                    animator.start();
                }

                //animations-->fading and sliding view in main_activity
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //transition(it tells how and where the view will move)
                    Slide slide=new Slide();
                    slide.setSlideEdge(Gravity.BOTTOM);
                    slide.setDuration(3000);

//                    //for fadding a perticular view in the activity(extends visibility internaly)
//                    Fade fade=new Fade();
//                    fade.setDuration(3000);
//                    fade.setMode(Fade.MODE_IN);

                    //gives the rootView containing all the views in an activity
                    ViewGroup viewGroup=findViewById(android.R.id.content);
                    TransitionManager.beginDelayedTransition(viewGroup,slide);
                    avatar.setVisibility(View.INVISIBLE);

//                    TransitionManager.beginDelayedTransition(viewGroup,fade);
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //making image circular to use as a avatar in an app
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.test1);
        RoundedBitmapDrawable drawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        drawable.setCircular(true);
        avatar.setImageDrawable(drawable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            checkToClear= (AnimatedVectorDrawable) getDrawable(R.drawable.avd_tick_to_cross);
            clearToCheck= (AnimatedVectorDrawable) getDrawable(R.drawable.avd_cross_to_tick);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                animate(view);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    void animate(View view){
        AnimatedVectorDrawable drawable=tick?checkToClear:clearToCheck;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(drawable);
            drawable.start();
            tick=!tick;
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.todo_main) {
            Intent intent=new Intent(MainActivity.this,TodoMainScreen.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
