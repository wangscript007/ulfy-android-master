package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List5ChildCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_list5_child)
public class List5ChildCell extends BaseCell {
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    private List5ChildCM cm;

    public List5ChildCell(Context context) {
        super(context);
        init(context, null);
    }

    public List5ChildCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (List5ChildCM) model;
        nameTV.setText(cm.name);
    }
}