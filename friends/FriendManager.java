package me.explicit.friends;

import java.util.ArrayList;
import java.util.List;
import me.explicit.config.ConfigManager;

public class FriendManager {

    private List friends;

    public void addFriend(String s) {
        if (this.friends == null) {
            (this.friends = new ArrayList()).clear();
        }

        this.friends.add(s);
        ConfigManager.SaveFriendsFile();
    }

    public void addFriendNoSave(String s) {
        this.friends.add(s);
    }

    public List getFriends() {
        if (this.friends == null) {
            (this.friends = new ArrayList()).clear();
        }

        return this.friends;
    }
}
