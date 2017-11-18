package com.bob.bobmobileapp.tools.validators;

import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by user on 05/09/2017.
 */

public abstract class ViewsValidator {

    public ViewsValidator() {}

    public abstract String validate(ArrayList<TextView> textViews);
}
