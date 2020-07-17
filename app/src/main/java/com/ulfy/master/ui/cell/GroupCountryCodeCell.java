package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.GroupCountryCodeCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_group_country_code)
public class GroupCountryCodeCell extends BaseCell {
    @ViewById(id = R.id.groupNameTV) private TextView groupNameTV;
    private GroupCountryCodeCM cm;

    public GroupCountryCodeCell(Context context) {
        super(context);
        init(context, null);
    }

    public GroupCountryCodeCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (GroupCountryCodeCM) model;
        groupNameTV.setText(cm.countryGroup.name);
    }
}