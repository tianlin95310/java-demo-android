package tl.com.testmaterialdesign.database.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tianlin on 2017/9/27.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class DiffThreadReadAndWrite extends RealmObject
{
    private String id;

    private String name;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "DiffThreadReadAndWrite{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
