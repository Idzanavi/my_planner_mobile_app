package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myplanner.databinding.ActivityItemBinding;
import com.example.myplanner.databinding.ActivityLoginBinding;

public class ItemActivity extends MyPlannerActivity {

    private ActivityItemBinding binding;
    int week;
    int day;
    int slot;

    boolean exists;

    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoadData();

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exists){
                    PatchItemController controller = new PatchItemController(ItemActivity.this,
                            week, day, slot, binding.textTitle.getText().toString(),
                            binding.textText.getText().toString(), color);
                    controller.patch();
                }else{
                    PostItemController controller = new PostItemController(ItemActivity.this,
                            week, day, slot, binding.textTitle.getText().toString(),
                            binding.textText.getText().toString(), color);
                    controller.post();
                }
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteItemController controller = new DeleteItemController(ItemActivity.this,
                        week, day, slot);
                controller.delete();
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemActivity.this.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadData();
    }

    @Override
    protected void checkAuthStatus(){
        if(!AppState.getInstance().getAuthController().isConnected()){
            this.finish();
        }
    }

    private void LoadData(){
        week = AppState.getInstance().getPlannerPos().getWeek();
        day = AppState.getInstance().getPlannerPos().getDay();
        slot = getIntent().getIntExtra("slot", -1);

        if(slot >= 0){
            ItemData item = AppState.getInstance().getItem(day, slot);
            if(item == null){
                binding.textTitle.setText("");
                binding.textText.setText("");
                color = "#FFFFAA";
                exists = false;
                binding.buttonDelete.setEnabled(false);
            }else{
                binding.textTitle.setText(item.getTitle());
                binding.textText.setText(item.getText());
                color = item.color;
                exists = true;
                binding.buttonDelete.setEnabled(true);
            }
        }
    }
}