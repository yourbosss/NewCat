package com.example.newcat2.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newcat2.R;
import com.example.newcat2.data.model.FoodItem;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodList;
    private Context context; // Контекст для загрузки изображений и других операций

    public FoodAdapter(List<FoodItem> foodList) {
        this.context = context; // Инициализация контекста
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_card, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = foodList.get(position);
        holder.bind(foodItem);
    }

    @Override
    public int getItemCount() {
        return foodList != null ? foodList.size() : 0; // Проверка на null
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodDescription;
        TextView foodPrice;
        TextView foodCalories;
        Button addButton;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_image);
            foodName = itemView.findViewById(R.id.food_name);
            foodDescription = itemView.findViewById(R.id.food_description);
            foodPrice = itemView.findViewById(R.id.food_price);
            foodCalories = itemView.findViewById(R.id.food_calories);
            addButton = itemView.findViewById(R.id.add_corm_in_map);

            // Установка обработчика нажатий на кнопку
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Обработка нажатия на кнопку
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        FoodItem clickedItem = foodList.get(position);
                        Log.e("asdf", "asdf");

                    }
                }
            });
        }

        public void bind(FoodItem foodItem) {
            // Устанавливаем данные в соответствующие TextViews
            foodName.setText(foodItem.getName());
            foodDescription.setText(foodItem.getDescription());
            foodPrice.setText(String.valueOf(foodItem.getPrice())); // Предполагается, что у FoodItem есть метод getPrice()
            foodCalories.setText(String.valueOf(foodItem.getColorieContent())); // Предполагается, что у FoodItem есть метод getCalories()
        }
    }
}