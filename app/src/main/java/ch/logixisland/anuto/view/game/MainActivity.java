package ch.logixisland.anuto.view.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import java.io.InputStream;

import ch.logixisland.anuto.R;
import ch.logixisland.anuto.game.GameEngine;
import ch.logixisland.anuto.game.business.GameManager;
import ch.logixisland.anuto.game.data.Level;
import ch.logixisland.anuto.view.menu.LevelSelectFragment;

public class MainActivity extends Activity {

    GameView view_tower_defense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        int themeid = getIntent().getIntExtra("theme", 0);

        GameEngine.getInstance().setResources(getResources());
        GameEngine.getInstance().setTheme(themeid);
        setContentView(R.layout.activity_main);

        view_tower_defense = (GameView)findViewById(R.id.view_tower_defense);

        int levelId = getIntent().getIntExtra(LevelSelectFragment.SELECTED_LEVEL, R.raw.level_1);

        try {
            InputStream inStream = getResources().openRawResource(levelId);

            try {
                GameManager.getInstance().setLevel(Level.deserialize(inStream));
            } finally {
                inStream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load level!", e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        GameEngine.getInstance().start();
        view_tower_defense.start();
    }

    @Override
    public void onStop() {
        super.onStop();

        view_tower_defense.stop();
        GameEngine.getInstance().stop();
    }
}
