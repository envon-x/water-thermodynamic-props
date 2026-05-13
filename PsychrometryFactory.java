package com.iridiscense.unitoperations;

import com.iridiscense.unitoperations.plotter.data.Entry;

import java.util.HashMap;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 11/2/21.
 * Copyright (c)  Hector Bonifacio. 11/2/21, All rights reserved.
 */
public class PsychrometryFactory {

    private static HashMap humidRelativityEntryMap = new HashMap();

    /**
     * Create new Point, data or Entry to add Series
     * @param collection name of serie
     * @return a virtual Entry, based on Flyweight pattern
     */
    public static Entry createEntry(String collection) {
        Entry entry = (Entry) humidRelativityEntryMap.get(collection);
        if (entry == null) {
            entry = new Entry(collection);
            humidRelativityEntryMap.put(collection, entry);
        }
        return entry;
    }
}
