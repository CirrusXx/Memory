package adam.uni.math.lodz.pl.memory;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    int counter = 1;
    private String path1;
    private String path2;
    private String path3;
    private String path4;

    DataBaseManager dataBase = new DataBaseManager(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPlayButton(0);
    }

    public void takePhotoOnClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        String id = getId(view);
        counter = Character.getNumericValue(id.charAt(lengthOfId(id)));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            int viewID = getResources().getIdentifier("image" + counter, "id", getPackageName());
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton image = findViewById(viewID);
            image.setImageBitmap(imageBitmap);
            setPaths(imageBitmap);

            if (counter == 4) {
                setPlayButton(1);
            }
        }
    }

    private void setPaths(Bitmap imageBitmap) {
        setPath1(saveToInternalStorage(imageBitmap));
        setPath2(saveToInternalStorage(imageBitmap));
        setPath4(saveToInternalStorage(imageBitmap));
        setPath3(saveToInternalStorage(imageBitmap));
    }

    public static String getId(View view) {
        return view.getResources().getResourceName(view.getId());
    }

    public int lengthOfId(String id) {
        return id.length() - 1;
    }

    public void setPlayButton(int value) {
        Button play = findViewById(R.id.playButton);
        if (value == 0)
            play.setEnabled(false);
        else
            play.setEnabled(true);
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir" + counter, Context.MODE_PRIVATE);
        File myPath = new File(directory, "profile.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public void startGameActivity(View view) {
        Intent intent = new Intent(this, Game.class);
        List<String> pathList = new ArrayList<>();
        pathList.add(0,getPath1());
        pathList.add(1,getPath1());
        pathList.add(2,getPath2());
        pathList.add(3,getPath2());
        pathList.add(4,getPath3());
        pathList.add(5,getPath3());
        pathList.add(6,getPath4());
        pathList.add(7,getPath4());
        intent.putStringArrayListExtra("listOfPaths", (ArrayList<String>) pathList);
        startActivity(intent);
    }

    public void setPath1(String path)
    {
        if(counter == 1)
        {
            path1 = path;
        }
    }

    public void setPath2(String path)
    {
        if(counter == 2)
        {
            path2 = path;
        }
    }

    public void setPath3(String path)
    {
        if(counter == 3)
        {
            path3 = path;
        }
    }

    public void setPath4(String path)
    {
        if(counter == 4)
        {
            path4 = path;
        }
    }

    public String getPath1()
    {
        return path1;
    }

    public String getPath2()
    {
        return path2;
    }

    public String getPath3()
    {
        return path3;
    }

    public String getPath4()
    {
        return path4;
    }
}