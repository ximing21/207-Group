package use_case;

public class TodolistInputData {


    private String ingredient;

    public TodolistInputData(String ingr) {
        this.ingredient = ingr;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
