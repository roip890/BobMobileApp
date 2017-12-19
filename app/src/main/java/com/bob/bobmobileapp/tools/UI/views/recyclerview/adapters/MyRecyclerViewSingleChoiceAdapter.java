package com.bob.bobmobileapp.tools.UI.views.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import java.util.ArrayList;

/**
 * Created by User on 17/12/2017.
 */

public class MyRecyclerViewSingleChoiceAdapter extends RecyclerView.Adapter<MyRecyclerViewSingleChoiceAdapter.ViewHolder> {

    private ArrayList<RadioButton> radioButtons;
    private RadioButton defaultCheckedRadioButton, checkedRadioButton;


    public MyRecyclerViewSingleChoiceAdapter(ArrayList<RadioButton> radioButtons, int checkedIndex) {
        this.radioButtons = radioButtons;
        this.defaultCheckedRadioButton = this.radioButtons.get(checkedIndex);
        this.checkedRadioButton = this.radioButtons.get(checkedIndex);

        for (int i = 0; i < this.radioButtons.size(); i++) {
            this.setOnCheckedListener(this.radioButtons.get(i));
            if (i == checkedIndex){
                this.radioButtons.get(i).setChecked(true);
                checkedRadioButton = this.radioButtons.get(i);
            } else {
                this.radioButtons.get(i).setChecked(false);
            }
        }
    }

    public MyRecyclerViewSingleChoiceAdapter(ArrayList<RadioButton> radioButtonsList, RadioButton checkedRadioButton) {
        this(radioButtonsList, radioButtonsList.indexOf(checkedRadioButton));
    }

    public MyRecyclerViewSingleChoiceAdapter(ArrayList<RadioButton> radioButtons) {
        this(radioButtons, 0);
    }

    public MyRecyclerViewSingleChoiceAdapter() {
        this(new ArrayList<RadioButton>(), 0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        return new ViewHolder(linearLayout);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections
        holder.setRadioButton(this.radioButtons.get(position));

    }

    @Override
    public int getItemCount() {
        return radioButtons.size();
    }

    public boolean isRadioButtonExist(RadioButton radioButtonToFind) {
        for (RadioButton radioButton : this.radioButtons) {
            if (radioButton == radioButtonToFind) {
                return true;
            }
        }
        return false;
    }

    public void addRadioButton(RadioButton radioButtonToAdd) {
        if (!this.isRadioButtonExist(radioButtonToAdd)) {
            this.setOnCheckedListener(radioButtonToAdd);
            this.radioButtons.add(radioButtonToAdd);
            if (this.radioButtons.size() > 1) {
                radioButtonToAdd.setChecked(false);
            } else {
                radioButtonToAdd.setChecked(true);
                checkedRadioButton = radioButtonToAdd;
            }
        }
        this.notifyDataSetChanged();
    }

    public void removeRadioButton(RadioButton radioButtonToRemove) {
        if (this.isRadioButtonExist(radioButtonToRemove)) {
            if (radioButtonToRemove == this.defaultCheckedRadioButton) {
                this.defaultCheckedRadioButton = null;
            }
            if ((radioButtonToRemove.isChecked()) && (this.radioButtons.size() > 1)) {
                if (this.defaultCheckedRadioButton == null) {
                    this.radioButtons.get(0).setChecked(true);
                    this.checkedRadioButton = this.radioButtons.get(0);
                } else {
                    this.defaultCheckedRadioButton.setChecked(true);
                    this.checkedRadioButton = this.defaultCheckedRadioButton;
                }
            }
            this.radioButtons.remove(radioButtonToRemove);
        }
        this.notifyDataSetChanged();
    }

    public void removeRadioButton(int indexToRemove) {
        this.removeRadioButton(this.radioButtons.get(indexToRemove));
    }

    public RadioButton getCheckedRadioButton() {
        return this.checkedRadioButton;
    }

    public int getCheckedRadioButtonIndex() {
        return this.radioButtons.indexOf(this.checkedRadioButton);
    }

    public void setDefaultCheckedRadioButton(RadioButton defaultCheckedRadioButton) {
        this.defaultCheckedRadioButton = defaultCheckedRadioButton;
    }

    public void setDefaultCheckedRadioButton(int defaultCheckedRadioButtonIndex) {
        this.setDefaultCheckedRadioButton(this.radioButtons.get(defaultCheckedRadioButtonIndex));
    }

    private void setOnCheckedListener(RadioButton radioButton) {
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton radioButtonView, boolean isChecked) {
                if (!isChecked) return;
                for (RadioButton radioButton : radioButtons) {
                    if (radioButton == radioButtonView) {
                        radioButton.setChecked(true);
                        checkedRadioButton = radioButton;
                    } else {
                        radioButton.setChecked(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private RadioButton radioButton;

        public ViewHolder(LinearLayout linearLayout) {
            super(linearLayout);
            this.linearLayout = linearLayout;
        }

        public RadioButton getRadioButton() {
            return radioButton;
        }

        public void setRadioButton(RadioButton radioButton) {
            this.linearLayout.removeAllViews();
            this.linearLayout.addView(radioButton);
            this.radioButton = radioButton;
        }

    }
}
