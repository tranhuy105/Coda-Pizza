package com.android.codapizza.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.codapizza.R

enum class Topping(
    @StringRes val toppingName: Int,
    @DrawableRes val pizzaOverlayImage: Int
) {
    Basil(
        toppingName = R.string.topping_basil,
        R.drawable.topping_basil
    ),
    Mushroom(
        toppingName = R.string.topping_mushroom,
        R.drawable.topping_mushroom
    ),
    Olive(
        toppingName = R.string.topping_olive,
        R.drawable.topping_olive
    ),
    Peppers(
        toppingName = R.string.topping_peppers,
        R.drawable.topping_peppers
    ),
    Pepperoni(
        toppingName = R.string.topping_pepperoni,
        R.drawable.topping_pepperoni
    ),
    Pineapple(
        toppingName = R.string.topping_pineapple,
        R.drawable.topping_pineapple
    )

}