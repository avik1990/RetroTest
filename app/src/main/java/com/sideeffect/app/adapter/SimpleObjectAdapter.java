package com.sideeffect.app.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.sideeffect.app.R;
import com.sideeffect.app.databinding.RowMedicineListBinding;
import com.sideeffect.app.model.Medicine;

import java.util.List;

public class SimpleObjectAdapter extends RecyclerView.Adapter<SimpleObjectAdapter.MedicineHolder> {

    List<Medicine> item;
    Context mContext;
    RowMedicineListBinding binding;

    public SimpleObjectAdapter(Context mContext, List<Medicine> list) {
        super();
        this.item = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_medicine_list, parent, false);
        return new MedicineHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineHolder holder, int position) {
        holder.bindConnection(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MedicineHolder extends RecyclerView.ViewHolder {
        RowMedicineListBinding binding;

        public MedicineHolder(RowMedicineListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bindConnection(Medicine obj) {
            binding.setVariable(BR.simpledataobject, obj);
            binding.executePendingBindings();

        }
    }
}


