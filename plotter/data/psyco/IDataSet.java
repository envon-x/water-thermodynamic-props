package com.iridiscense.unitoperations.plotter.data.psyco;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 11/16/21.
 * Copyright (c)  Hector Bonifacio. 11/16/21, All rights reserved.
 */
public interface IDataSet<T extends BaseData> {

    /**
     *
     * @return the minimum value of one Axis
     */
    public float getMinAxis();

    /**
     *
     * @return the maximum of the axis
     */
    public float getMaxAxis();

    public int getQuantityOfData();

    public T getDataAtIndex(int index);

    public int getIndexOf(T data);

    /**
     * Filtra y encuentra el valor máximo y minimo de la coleccion de datos
     */
    public void calcMinMaxValue(float firstValue, float lastValue);

    public void removeDataAt(int index);
    public void addDataBeforeAt(int index);
    public void addDataAt(int index);
    public void removeDataAfterAt(int index);
}
