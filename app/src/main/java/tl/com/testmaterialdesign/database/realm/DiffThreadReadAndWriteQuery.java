package tl.com.testmaterialdesign.database.realm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tianlin on 2017/11/4.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class DiffThreadReadAndWriteQuery
{
    public static void save(final DiffThreadReadAndWrite diffThreadReadAndWrite)
    {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                realm.copyToRealm(diffThreadReadAndWrite);
            }
        });

        realm.close();
    }

    public static List<DiffThreadReadAndWrite> findAll()
    {

        List<DiffThreadReadAndWrite> list = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<DiffThreadReadAndWrite> readAndWrites = realm.where(DiffThreadReadAndWrite.class)
                .findAll();

        return realm.copyFromRealm(readAndWrites);
    }

    public static void clearAll()
    {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                realm.where(DiffThreadReadAndWrite.class)
                        .findAll().deleteAllFromRealm();
            }
        });

        realm.close();
    }
}
