package com.octo_spoon.octo_spoon_mobile.Backend;

import com.octo_spoon.octo_spoon_mobile.ViewStructure.MethodologyList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 19-10-2017.
 */

public class CurrentInformationHelper {

    private static CurrentInformationHelper instance = null;

    public List<MethodologyList> userMethodologies = new ArrayList<MethodologyList>();

    private CurrentInformationHelper() {
    }

    public static CurrentInformationHelper getInstance() {
        if(instance == null) {
            instance = new CurrentInformationHelper();
        }
        return instance;
    }
}
