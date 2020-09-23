package nanicky.losties.android.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class FileUtils {

        private static final String AVATAR_FILENAME = "avatar.jpg";
        private static final String SN_AVATAR_FILENAME = "sn_avatar.jpg";

        private static final String AVATAR_THUMBNAIL = "thumbnail.jpg";


    public static final int DESIRED_WIDTH = 400;
    public static final int DESIRED_HEIGHT = 300;

    public static boolean writeToDisk(Context context, ResponseBody body, String outputFileName) {
        try {

            File resultFile = new File(context.getFilesDir(), outputFileName);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(resultFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                }

                outputStream.flush();

            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static File thumbnail(Context context, File inputImage) {

        FileOutputStream fos = null;

        try {
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inJustDecodeBounds = true; // obtain the size of the image, without loading it in memory
            BitmapFactory.decodeFile(inputImage.getAbsolutePath(), bitmapOptions);

            // find the best scaling factor for the desired dimensions
            float widthScale = (float)bitmapOptions.outWidth / DESIRED_WIDTH;
            float heightScale = (float)bitmapOptions.outHeight / DESIRED_HEIGHT;
            float scale = Math.min(widthScale, heightScale);

            int sampleSize = 1;
            while (sampleSize < scale) {
                sampleSize *= 2;
            }
            bitmapOptions.inSampleSize = sampleSize; // this value must be a power of 2,
            // this is why you can not have an image scaled as you would like
            bitmapOptions.inJustDecodeBounds = false; // now we want to load the image

            // Let's load just the part of the image necessary for creating the thumbnail, not the whole image
            Bitmap thumbnail = BitmapFactory.decodeFile(inputImage.getAbsolutePath(), bitmapOptions);

            // Save the thumbnail
            File thumbnailFile = new File(context.getFilesDir(), AVATAR_THUMBNAIL);
            fos = new FileOutputStream(thumbnailFile);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();

            // Use the thumbail on an ImageView or recycle it!
            thumbnail.recycle();

            return thumbnailFile;

        } catch (Exception e) {
            return inputImage;
        }

    }




}
