package com.bob.bobmobileapp.realm;

import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;

/**
 * Created by User on 02/02/2018.
 */

public class RealmHelper {

    private static RealmHelper instance;

    public static RealmHelper get() {
        if (instance == null) {
            instance = new RealmHelper();
        }
        return instance;
    }

    public MenuNode makeNewMenuNode(long id, long parentId, String title, String imageUrl) {
        MenuNode menuNode = new MenuNode();
        menuNode.setId(id);
        menuNode .setParentId(parentId);
        menuNode.setTitle(title);
        menuNode.setImageUrl(imageUrl);
        return menuNode;
    }

    public FormItem makeNewFormItem(long id, long parentId, String type) {
        FormItem formItem = new FormItem();
        formItem.setId(id);
        formItem.setParentId(parentId);
        formItem.setType(type);
        return formItem;
    }

    public FormItemProperty makeNewFormItemProperty(long id, long parentId, String key, String value) {
        FormItemProperty formItemProperty = new FormItemProperty();
        formItemProperty.setId(id);
        formItemProperty.setParentId(parentId);
        formItemProperty.setKey(key);
        formItemProperty.setValue(value);
        return formItemProperty;
    }

    public MenuNodeProperty makeNewMenuNodeProperty(long id, long parentId, String key, String value) {
        MenuNodeProperty menuNodeProperty = new MenuNodeProperty();
        menuNodeProperty.setId(id);
        menuNodeProperty.setParentId(parentId);
        menuNodeProperty.setKey(key);
        menuNodeProperty.setValue(value);
        return menuNodeProperty;
    }

}
