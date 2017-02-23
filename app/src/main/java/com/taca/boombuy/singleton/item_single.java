package com.taca.boombuy.singleton;

import com.taca.boombuy.dto.subdto.SelectedItemsDTO;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-17.
 */
public class item_single {
    private static item_single ourInstance = new item_single();

    public static item_single getInstance() {
        return ourInstance;
    }

    private item_single() {
    }

    public ArrayList<SelectedItemsDTO> itemDTOArrayList = new ArrayList<>();
    public SelectedItemsDTO itemDTO;
}
