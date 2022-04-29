package com.example.datn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Post;
import com.amplifyframework.datastore.generated.model.PostStatus;
import com.google.android.material.tabs.TabLayout;

public class MenuDisplayActivity extends AppCompatActivity {
    private final String TAG = MenuDisplayActivity.class.getSimpleName();
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_display);
        TabLayout tabLayout = findViewById(R.id.tabBar);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        Post post = Post.builder()
            .title("My First Post")
            .status(PostStatus.PUBLISHED)
            .rating(10)
            .build();

        Amplify.DataStore.save(post,
            saved -> Log.i("MyAmplifyApp", "Saved a post."),
            failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
        );

        //ClassicSingleton instance = ClassicSingleton.getInstance();
        //instance.getCordinates();

        PagerAdapter pagerAdapter = new
                PagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void logout(View view){
        ImageView logout = view.findViewById(R.id.logout);
        logout.setImageResource(R.drawable.ic_vpn_key_yellow_24dp);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}