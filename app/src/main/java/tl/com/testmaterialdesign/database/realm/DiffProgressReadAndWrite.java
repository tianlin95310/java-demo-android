package tl.com.testmaterialdesign.database.realm;

import io.realm.RealmObject;

/**
 * Created by tianlin on 2017/7/14.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class DiffProgressReadAndWrite extends RealmObject
{
    private long timestamp;

    private long modTimeStamp;

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public long getModTimeStamp()
    {
        return modTimeStamp;
    }

    public void setModTimeStamp(long modTimeStamp)
    {
        this.modTimeStamp = modTimeStamp;
    }

    @Override
    public String toString()
    {
        return "DiffProgressReadAndWrite{" +
                "timestamp=" + timestamp +
                ", modTimeStamp=" + modTimeStamp +
                '}';
    }
}
