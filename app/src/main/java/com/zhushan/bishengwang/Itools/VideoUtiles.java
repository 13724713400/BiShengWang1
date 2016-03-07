package com.zhushan.bishengwang.Itools;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import com.zhushan.bishengwang.Entry.Video;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2015/12/3.
 */
public class VideoUtiles {
    private static VideoUtiles videoUtiles;
    private List<MediaStore.Video> listVideos;
    private VideoUtiles()
    {}
    public VideoUtiles getInstance()
    {
        if (videoUtiles==null) {
            videoUtiles = new VideoUtiles();

        }
        return videoUtiles;
    }
    //得到视频缩略图
    public Bitmap getVideoThumbnail(String videoPaht,int with,int height,int kind)
    {

        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPaht, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap,with,height);
        return  bitmap;
    }

    /**
     * 获取视频的第一针
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap createVideoThumbnail(Context context, Uri uri) {
        Bitmap bitmap = null;
        String className = "android.media.MediaMetadataRetriever";
        Object objectMediaMetadataRetriever = null;
        Method release = null;
        try {

            objectMediaMetadataRetriever = Class.forName(className).newInstance();
            Method setModeMethod = Class.forName(className).getMethod("setMode", int.class);
            setModeMethod.invoke(objectMediaMetadataRetriever,
                    MediaMetadataRetriever.METADATA_KEY_CAPTURE_FRAMERATE);

            Method setDataSourceMethod = Class.forName(className).getMethod(
                    "setDataSource", Context.class, Uri.class);
            setDataSourceMethod.invoke(objectMediaMetadataRetriever, context,uri);

            Method captureFrameMethod = Class.forName(className).getMethod("captureFrame");
            release = Class.forName(className).getMethod("release");

            bitmap = (Bitmap) captureFrameMethod
                    .invoke(objectMediaMetadataRetriever);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {try {
            if (release != null) {
                release.invoke(objectMediaMetadataRetriever);
            }
        } catch (Exception e) {

// Ignore failures while cleaning up.
            e.printStackTrace();
        }
        }
        return bitmap;
    }

    /**
     * 得到本地的视频的集合
     * @param context
     * @return
     */
    public List<Video> getList(Context context) {
        List<Video> list = null;
        if (context != null) {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null,
                    null, null);
            if (cursor != null) {
                list = new ArrayList<Video>();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String title = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    String album = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                    String artist = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                    String displayName = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    String mimeType = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                    String path = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    long duration = cursor
                            .getInt(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    long size = cursor
                            .getLong(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                    Video video = new Video(id, title, album, artist, displayName, mimeType, path, size, duration);
                    list.add(video);
                }
                cursor.close();
            }
        }
        return list;
    }

}
