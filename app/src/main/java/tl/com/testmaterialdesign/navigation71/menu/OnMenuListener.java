package tl.com.testmaterialdesign.navigation71.menu;

/**
 * Created by tianlin on 2018/5/29.
 * Tel : 15071485690
 * QQ : 953108373
 */

public interface OnMenuListener
{
    void onSelectMenu(int menu);

    void onMenuStatusChange(boolean isOpen);

    void onSlideDistance(float distance, float maxValue, float minValue);
}
