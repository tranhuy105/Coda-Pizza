package com.android.codapizza.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.codapizza.R
import com.android.codapizza.model.Topping
import com.android.codapizza.model.ToppingPlacement
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.android.codapizza.model.Pizza
import java.text.NumberFormat

/*
    MutableState is like a wrapper object that keep track of a single value.
    Whenever the value inside one of these state objects is reassigned, Compose is immediately notified of the change.
    Every composable that accesses the state object will then automatically update with the new value held in the state object.

    Delegation using by makes the property look like a normal property syntactically –
    but reads and writes will go through the MutableState so that Compose can keep track of state changes.
*/


@Preview
@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {
    var pizza by remember { mutableStateOf(Pizza()) }

    Column(
        modifier = modifier
    ) {
        ToppingsList(
            pizza = pizza,
            onEditPizza = {it: Pizza -> pizza = it},
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        OrderButton(
            pizza = pizza,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
private fun ToppingsList(
    modifier: Modifier = Modifier,
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit
) {
    var toppingBeingAdd by remember {
        mutableStateOf<Topping?>(null)
    }

    toppingBeingAdd?.let {
        topping ->
        ToppingPlacementDialog(
            topping = topping,
            onSetToppingPlacement = {
                placement ->
                onEditPizza(pizza.withTopping(topping, placement = placement))
            },
            onDismissRequest = {
                toppingBeingAdd = null
            }
        )
    }

    LazyColumn(
        modifier = modifier
    ) {
        item { 
            PizzaHeroImage(pizza = pizza, modifier = Modifier.padding(16.dp))
        }
        
        items(Topping.entries) { topping ->
            val placement = pizza.toppings[topping]
            ToppingCell(
                topping = topping,
                placement = placement,
                onClickTopping = {
                    toppingBeingAdd = topping
                }
            )
        }

    }
}

@Composable
private fun OrderButton(
    modifier: Modifier = Modifier,
    pizza: Pizza
) {
    Button(
        modifier = modifier,
        onClick = { /*TODO*/ },
    ) {
        val currencyFormatter = remember {
            NumberFormat.getCurrencyInstance()
        }
        val price = currencyFormatter.format(pizza.price)
        Text(
            text = stringResource(id = R.string.place_order_button, price)
                .toUpperCase(Locale.current)
        )
    }
}