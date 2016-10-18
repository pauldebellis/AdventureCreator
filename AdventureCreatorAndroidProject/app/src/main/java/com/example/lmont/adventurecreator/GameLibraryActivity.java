package com.example.lmont.adventurecreator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.Objects;

public class GameLibraryActivity extends FragmentActivity {

    public static int STORIES = 0;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 1000 times just in order to testAddStoryAndReadStory your "infinite" ViewPager :D
    public final static int LOOPS = 1000;
    public final static int FIRST_PAGE = STORIES * LOOPS / 2;

    public String pagerGenre;

    public GameLibraryPagerAdapter fantasyAdapter;
    public ViewPager fantasyPager;
    public GameLibraryPagerAdapter sciFiAdapter;
    public ViewPager sciFiPager;
    public GameLibraryPagerAdapter horrorAdapter;
    public ViewPager horrorPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_library);

        fantasyPager = (ViewPager) findViewById(R.id.fantasyCarousel);
        fantasyAdapter = new GameLibraryPagerAdapter(this, this.getSupportFragmentManager(), "fantasy");
        setupPager(fantasyPager, fantasyAdapter, "fantasy");

        sciFiPager = (ViewPager) findViewById(R.id.sciFiCarousel);
        sciFiAdapter = new GameLibraryPagerAdapter(this, this.getSupportFragmentManager(), "scifi");
        setupPager(sciFiPager, sciFiAdapter, "scifi");

        horrorPager = (ViewPager) findViewById(R.id.horrorCarousel);
        horrorAdapter = new GameLibraryPagerAdapter(this, this.getSupportFragmentManager(), "horror");
        setupPager(horrorPager, horrorAdapter, "horror");
    }

    public int getNumBooksPerGenre (String genre){
        Models.Story[] storyArray = GameHelper.getInstance(this).getAllStories();
        for (int i = 0; i < storyArray.length; i++) {
            if (Objects.equals(storyArray[i].genre, genre)){
                STORIES++;
            }
        }
        return STORIES;

    }

    private int setupPager (ViewPager pager, GameLibraryPagerAdapter adapter, String genre){
        pager.setAdapter(adapter);
        pager.setPageTransformer(false, adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(5);

        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        pager.setPageMargin(-600);

        Models.Story[] storyArray = GameHelper.getInstance(this).getAllStories();
        for (int i = 0; i < storyArray.length; i++) {
            if (storyArray[i].genre == genre){
                STORIES++;
            }
        }
        return STORIES;
    }
}
