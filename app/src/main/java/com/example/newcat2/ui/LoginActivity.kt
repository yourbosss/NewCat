package com.example.newcat2.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.newcat2.R

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)

        val spinnerTypeFood: Spinner = findViewById(R.id.spinner_type_food)
        val spinnerAgeCategory: Spinner = findViewById(R.id.spinner_age_category)
        val spinnerBudgetPremium: Spinner = findViewById(R.id.spinner_budget_premium)
        val spinnerSterilizedRegular: Spinner = findViewById(R.id.spinner_sterilized_regular)


        val adapterTypeFood = ArrayAdapter.createFromResource(this, R.array.type_food_options, android.R.layout.simple_spinner_item)
        val adapterAgeCategory = ArrayAdapter.createFromResource(this, R.array.age_category_options, android.R.layout.simple_spinner_item)
        val adapterBudgetPremium = ArrayAdapter.createFromResource(this, R.array.budget_premium_options, android.R.layout.simple_spinner_item)
        val adapterSterilizedRegular = ArrayAdapter.createFromResource(this, R.array.sterilized_regular_options, android.R.layout.simple_spinner_item)


        adapterTypeFood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterAgeCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterBudgetPremium.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterSterilizedRegular.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerTypeFood.adapter = adapterTypeFood
        spinnerAgeCategory.adapter = adapterAgeCategory
        spinnerBudgetPremium.adapter = adapterBudgetPremium
        spinnerSterilizedRegular.adapter = adapterSterilizedRegular


        setupSpinnerListener(spinnerTypeFood, "selected_type_food")
        setupSpinnerListener(spinnerAgeCategory, "selected_age_category")
        setupSpinnerListener(spinnerBudgetPremium, "selected_budget_premium")
        setupSpinnerListener(spinnerSterilizedRegular, "selected_sterilized_regular")


        val recordButton: Button = findViewById(R.id.record_button)
        recordButton.setOnClickListener {
            // Получаем значения из спиннеров как строки
            val selectedTypeFood = spinnerTypeFood.selectedItem.toString()
            val selectedAgeCategory = spinnerAgeCategory.selectedItem.toString()
            val selectedBudgetPremium = spinnerBudgetPremium.selectedItem.toString()
            val selectedSterilizedRegular = spinnerSterilizedRegular.selectedItem.toString()




            // Сохраняем значения в SharedPreferences
            with(sharedPreferences.edit()) {
                putString("selected_type_food", selectedTypeFood)
                putString("selected_age_category", selectedAgeCategory)
                putString("selected_budget_premium", selectedBudgetPremium)
                putString("selected_sterilized_regular", selectedSterilizedRegular)

                apply()
            }

            // Создаем Intent и передаем значения
            val intent = Intent(this, MainScene::class.java).apply {
                putExtra("selected_type_food", selectedTypeFood)
                putExtra("selected_age_category", selectedAgeCategory)
                putExtra("selected_budget_premium", selectedBudgetPremium)
                putExtra("selected_sterilized_regular", selectedSterilizedRegular)


            }

            startActivity(intent)
        }

        restoreSelections()
    }

    // Функция для восстановления значений из SharedPreferences
    private fun restoreSelections() {
        val selectedTypeFood = sharedPreferences.getString("selected_type_food", "") ?: ""
        val selectedAgeCategory = sharedPreferences.getString("selected_age_category", "") ?: ""
        val selectedBudgetPremium = sharedPreferences.getString("selected_budget_premium", "") ?: ""
        val selectedSterilizedRegular = sharedPreferences.getString("selected_sterilized_regular", "") ?: ""

        // Устанавливаем выбор в спиннерах на основе строковых значений
        setSpinnerSelection(selectedTypeFood, R.array.type_food_options, R.id.spinner_type_food)
        setSpinnerSelection(selectedAgeCategory, R.array.age_category_options, R.id.spinner_age_category)
        setSpinnerSelection(selectedBudgetPremium, R.array.budget_premium_options, R.id.spinner_budget_premium)
        setSpinnerSelection(selectedSterilizedRegular, R.array.sterilized_regular_options, R.id.spinner_sterilized_regular)
    }

    private fun setSpinnerSelection(selectedValue: String, arrayResId: Int, spinnerId: Int) {
        val spinner: Spinner = findViewById(spinnerId)
        val array = resources.getStringArray(arrayResId)
        val index = array.indexOf(selectedValue)
        if (index >= 0) {
            spinner.setSelection(index)
        }
    }

    private fun setupSpinnerListener(spinner: Spinner, key: String) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedValue = spinner.selectedItem.toString()
                sharedPreferences.edit().putString(key, selectedValue).apply() // Сохраняем как строку
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }
    }
}