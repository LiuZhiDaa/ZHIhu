package com.example.zhihu;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.zhihu.bean.CommentEntity;
import com.example.zhihu.constant.DataListener;

/**
 * Created by 44744 on 2016/4/28.
 */
public class modelTest extends AndroidTestCase {
    public void testModel(){
        CommentMdelImpl i = new CommentMdelImpl();
        i.getShortComment(8227249, new DataListener() {
            @Override
            public void onSuccess(Object o) {
                Log.i("lhd", "onSuccess: "+((CommentEntity)o).getComments().get(0).getContent());
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
