package adam.uni.math.lodz.pl.memory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game extends AppCompatActivity {

    private String path;
    private int viewID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadImageFromStorage();
    }

    public void loadImageFromStorage() {
        try {
            for (int i = 0; i <= 3; i++) {
                setPath(i);
                setViewID(i);
                File f = new File(getPath(), "profile.jpg");
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                ImageButton img = findViewById(getViewID());
                img.setImageBitmap(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPath(int i)
    {
        path = getIntent().getStringArrayListExtra("listOfPaths").get(i);
    }

    public void setViewID(int i)
    {
        viewID = getResources().getIdentifier("picture" + i, "id", getPackageName());
    }

    public String getPath()
    {
        return path;
    }

    public int getViewID()
    {
        return viewID;
    }
}


