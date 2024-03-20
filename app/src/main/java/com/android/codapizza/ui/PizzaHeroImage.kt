package com.android.codapizza.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.codapizza.R
import com.android.codapizza.model.Pizza
import com.android.codapizza.model.Topping
import com.android.codapizza.model.ToppingPlacement
import com.android.codapizza.model.ToppingPlacement.Left
import com.android.codapizza.model.ToppingPlacement.Right
import com.android.codapizza.model.ToppingPlacement.All

@Preview
@Composable
private fun PizzaHeroImagePreview() {
    PizzaHeroImage(
        pizza = Pizza(
            toppings = mapOf(
                Topping.Pineapple to ToppingPlacement.All,
                Topping.Pepperoni to ToppingPlacement.Left,
                Topping.Basil to ToppingPlacement.Right
            )
        )
    )
}

@Composable
fun PizzaHeroImage(
    pizza: Pizza,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.aspectRatio(1f)) {
        Image(
            painter = painterResource(id = R.drawable.pizza_crust),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        pizza.toppings.forEach {  (topping, placement) ->
            Image(
                painter = painterResource(id = topping.pizzaOverlayImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = when (placement) {
                    Left -> Alignment.TopStart
                    Right -> Alignment.TopEnd
                    All -> Alignment.Center
                },
                modifier = Modifier
                    .focusable(false)
                    .aspectRatio(
                        when (placement) {
                            Left -> 0.5f
                            Right -> 0.5f
                           All -> 1f
                        }
                    )
                    .align(when (placement) {
                        Left -> Alignment.CenterStart
                        Right -> Alignment.CenterEnd
                        All -> Alignment.Center
                    })
            )
        }
    }

}