package com.example.app8c;

import java.util.ArrayList;
import java.util.List;

public class ListePrevisions {
    List<Prevision> periods;

    public ListePrevisions()
    {
        periods = new ArrayList<Prevision>();
    }
    public Prevision get(int i)
    {
        return periods.get(i);
    }
}
