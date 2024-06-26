package com.android.codapizza.ui


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.android.codapizza.R
import com.android.codapizza.model.Topping
import com.android.codapizza.model.ToppingPlacement


//@Preview
//@Composable
//fun ToppingPlacementDialogPreview() {
//    ToppingPlacementDialog(Topping.Pineapple) {}
//}

@Composable
fun ToppingPlacementDialog(
    topping: Topping,
    onSetToppingPlacement: (placement: ToppingPlacement?) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column {
                val toppingName = stringResource(id = topping.toppingName)
                Text(
                    text = stringResource(R.string.placement_prompt , toppingName),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )

                ToppingPlacement.entries.forEach {
                    toppingPlacement -> ToppingPlacementOption(
                    placementName = toppingPlacement.label,
                    onClick = {
                        onSetToppingPlacement(toppingPlacement)
                        onDismissRequest()
                    })
                }

                ToppingPlacementOption(
                    placementName = R.string.placement_none,
                    onClick = {
                        onSetToppingPlacement(null)
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@Composable
private fun ToppingPlacementOption(
    @StringRes placementName: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = placementName),
            modifier = Modifier.padding(8.dp)
        )
    }
}