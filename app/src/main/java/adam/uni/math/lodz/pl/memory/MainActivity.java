package adam.uni.math.lodz.pl.memory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disableReplaceButtons();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    int counter = 1;

    public void takePhotoOnClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView image = findViewById(R.id.image+counter);
            image.setImageBitmap(imageBitmap);
            if(counter == 4)
            {
                return;
            }
            counter++;
            if(counter == 4)
            {
                enableReplaceButtons();
            }
        }
    }

    public void replacePictureOnClick(View view)
    {
        TextView button = (TextView)view;
        String pictureNumber = button.getText().toString();
        counter = Integer.parseInt(pictureNumber);
        takePhotoOnClick(view);
    }

    public void disableReplaceButtons()
    {
        Button btn1 = (Button) findViewById(R.id.replacePicture1);
        Button btn2 = (Button) findViewById(R.id.replacePicture2);
        Button btn3 = (Button) findViewById(R.id.replacePicture3);
        Button btn4 = (Button) findViewById(R.id.replacePicture4);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
    }

    public void enableReplaceButtons()
    {
        Button btn1 = (Button) findViewById(R.id.replacePicture1);
        btn1.setEnabled(true);
        Button btn2 = (Button) findViewById(R.id.replacePicture2);
        btn2.setEnabled(true);
        Button btn3 = (Button) findViewById(R.id.replacePicture3);
        btn3.setEnabled(true);
        Button btn4 = (Button) findViewById(R.id.replacePicture4);
        btn4.setEnabled(true);
    }
}