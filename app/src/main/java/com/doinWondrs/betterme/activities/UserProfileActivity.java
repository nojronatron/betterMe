package com.doinWondrs.betterme.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.doinWondrs.betterme.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";
    private String s3ImageKey = "";
    private User userInfo;
    ActivityResultLauncher<Intent> activityResultLauncher;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setUpAddImageButton();
        setUpLogout();
    }

    // Makes the Profile Image clickable to upload profile pic
    private void setUpAddImageButton(){
        ImageView addImageButton = findViewById(R.id.userProfileImg);
        addImageButton.setOnClickListener(v -> {
            launchImageSelectionIntent();
        });
    }

    // Grabs s3Image key from current user
    //TODO: pull user from database and save to userInfo to use .getProfileImgKey();
    private void getS3ImageKey(){
        s3ImageKey = userInfo.getProfileImgKey();
        if (s3ImageKey != null && !s3ImageKey.isEmpty()){
            Amplify.Storage.downloadFile(
                    s3ImageKey,
                    new File(getApplication().getFilesDir(), s3ImageKey),
                    success -> {
                        ImageView profileImage = findViewById(R.id.userProfileImg);
                        profileImage.setImageBitmap(BitmapFactory.decodeFile(success.getFile().getPath()));
                    },
                    failure -> Log.e(TAG, "Unable to get image from S3 for the task with s3 key: " + s3ImageKey + "with error: " + failure.getMessage(), failure)
            );
        }
    }

    // Launches image selector
    //TODO: Inform User only PNG and JPEG work
    private void launchImageSelectionIntent(){
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageFilePickingIntent.setType("*/*");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg","image/png"});
        activityResultLauncher.launch(imageFilePickingIntent);
    }

    // Grabs input stream from selected file on our phone and calls to upload to S3
    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
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
        );
    }

    // Uploads image to S3 buckets and sets the image immediately as user profile image
    private void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFileName, Uri pickedImageUri){
        Amplify.Storage.uploadInputStream(
                pickedImageFileName,
                pickedImageInputStream,
                success -> {
                    Log.i(TAG, "Succeeded in uploading file to s3: " + success.getKey());
                    s3ImageKey = success.getKey();
                    ImageView profileImg = findViewById(R.id.userProfileImg);
                    InputStream pickedImageInputStreamCopy = null;
                    try{
                        pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageUri);
                    } catch(FileNotFoundException fnfe){
                        Log.e(TAG, "Could not get input stream from uri: " + fnfe.getMessage(), fnfe);
                    }
                    profileImg.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
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

    // Attaches onClick listener to logout container. Clears backstack and starts loginpage activity
    //TODO: Verify Works
    private void setUpLogout(){
        ConstraintLayout logout = findViewById(R.id.profileLogoutContainer);
        logout.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () ->
                    {
                        Log.i(TAG, "Logout succeeded!");
                    },
                    failure ->
                    {
                        Log.i(TAG, "Logout failed: " + failure);
                    }
            );
            Toast.makeText(UserProfileActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
            Intent goToLoginIntent = new Intent(UserProfileActivity.this, LogInActivity.class);
            goToLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(goToLoginIntent);
        });
    }

    // TODO: Setup Intent to go to AboutUs Activity
    // TODO: Setup update info
}