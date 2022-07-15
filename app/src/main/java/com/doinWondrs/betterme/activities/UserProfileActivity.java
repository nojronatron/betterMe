package com.doinWondrs.betterme.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.amplifyframework.core.Amplify;
import com.doinWondrs.betterme.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";
    private String s3ImageKey = "";
    ActivityResultLauncher<Intent> activityResultLauncher;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

//    TODO:Adjust to Better me
    private void setUpAddImageButton(){
        Button addImageButton = findViewById(R.id.taskDetailAddImageB);
        addImageButton.setOnClickListener(v -> {
            launchImageSelectionIntent();
        });
    }

//    TODO:Adjust to Better me
    private void getS3ImageKey(){
        s3ImageKey = taskToEdit.getTaskImageKey();
        if (s3ImageKey != null && !s3ImageKey.isEmpty()){
            Amplify.Storage.downloadFile(
                    s3ImageKey,
                    new File(getApplication().getFilesDir(), s3ImageKey),
                    success -> {
                        ImageView productImageView = findViewById(R.id.taskDetailImageV);
                        productImageView.setImageBitmap(BitmapFactory.decodeFile(success.getFile().getPath()));
                    },
                    failure -> Log.e(TAG, "Unable to get image from S3 for the task with s3 key: " + s3ImageKey + "with error: " + failure.getMessage(), failure)
            );
        }
    }

    private void launchImageSelectionIntent(){
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageFilePickingIntent.setType("*/*");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg","image/png"});
        activityResultLauncher.launch(imageFilePickingIntent);
    }

    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Uri pickedImageUri = result.getData().getData();
                        try{
                            InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageUri);
                            String pickedImageFileName = getFileNameFromUri(pickedImageUri);
                            uploadInputStreamToS3(pickedImageInputStream, pickedImageFileName, pickedImageUri);
                            Log.i(TAG, "Succeeded in getting input stream from a file on our phone");
                        } catch (FileNotFoundException fnfe){
                            Log.e(TAG, "Could not get file from phone: " + fnfe.getMessage(), fnfe);
                        }
                    }
                }
        );
    }

//    TODO:Adjust to Better me
    private void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFileName, Uri pickedImageUri){
        Amplify.Storage.uploadInputStream(
                pickedImageFileName,
                pickedImageInputStream,
                success -> {
                    Log.i(TAG, "Succeeded in uploading file to s3: " + success.getKey());
                    s3ImageKey = success.getKey();
                    ImageView taskImageView = findViewById(R.id.taskDetailImageV);
                    InputStream pickedImageInputStreamCopy = null;
                    try{
                        pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageUri);
                    } catch(FileNotFoundException fnfe){
                        Log.e(TAG, "Could not get input stream from uri: " + fnfe.getMessage(), fnfe);
                    }
                    taskImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
                },
                failure -> Log.e(TAG, "Failure in uploading file to S3 with filename: " + pickedImageFileName + " with error: " + failure.getMessage())
        );
    }

    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                assert cursor != null;
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}