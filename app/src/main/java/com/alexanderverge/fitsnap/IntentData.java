package com.alexanderverge.fitsnap;

/**
 * Created by alexanderverge on 15-02-21.
 */
public class IntentData {
    private String name;
    private int id;

    public IntentData(String pName, int pid)
    {
        name = pName;
        id = pid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String pName)
    {
        name = pName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int pid)
    {
        id = pid;
    }

    @Override
    public String toString() {
        return name;
    }
}
