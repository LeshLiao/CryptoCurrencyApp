package com.palettex.cryptocurrencyapp.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.palettex.cryptocurrencyapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    isCollapse: Boolean,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var isButtonEnabled by remember { mutableStateOf(true) }
    LaunchedEffect(quantity) {
        // To disable click events for 0.3 second while the animation is running
        if (quantity == 0 || quantity == 1) {
            isButtonEnabled = false
            scope.launch {
                delay(300)
                isButtonEnabled = true
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
            .border(BorderStroke(1.dp, Color.Transparent))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .animateContentSize()
    ) {
        if (quantity > 0 && !isCollapse) {
            Card(
                modifier = Modifier
                    .size(32.dp)
                    .border(
                        BorderStroke(1.dp, colorResource(id = R.color.teal_200)),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable(enabled = isButtonEnabled)  { onQuantityChange(if (quantity == 1) 0 else quantity - 1) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.white)),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (quantity <= 1) R.drawable.ic_trash_bin else R.drawable.ic_minus
                        ),
                        contentDescription = if (quantity == 1) "Remove item" else "Decrease quantity",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier.width(32.dp).height(32.dp),
                contentAlignment = Alignment.Center
            ) {
                BasicText(
                    text = "$quantity",
                    style = TextStyle(
                        fontWeight = FontWeight.W600,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.purple_500)
                    ),
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
        Card(
            modifier = Modifier
                .size(32.dp)
                .border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(16.dp))
                .clickable(enabled = isButtonEnabled)  { onQuantityChange(quantity + 1)  },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                if (quantity == 0) colorResource(id = R.color.white) else colorResource(id = R.color.purple_700)
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isCollapse && quantity > 0) {
                    BasicText(
                        text = "$quantity",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 14.sp,
                            color = Color.White
                        ),
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "Increase quantity",
                        tint = if (quantity == 0) Color.Black else Color.White
                    )
                }
            }
        }
    }
}