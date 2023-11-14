package com.annguyenhoang.learningcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun ScreenA() {

//    val localLifecycleOwner = LocalLifecycleOwner.current

//    DisposableEffect(localLifecycleOwner) {
//
//        onDispose {
//
//        }
//    }

    // Side-effect
    // loi 1: cai nay minh se state. State - la gia tri
    // khi thay doi se re-compose lai.
    // Immutable state: khong bao gio thay doi.
    // mutable state: gia tri co the thay doi
    // loi 2: khi cai compose re-render (re-composition)
    // thi count dc khoi tao lai.
    // => remember
    var lifecycString by remember {
        mutableStateOf("")
    }

    val localLifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = localLifecycleOwner) {
        val lifecycleEvent = LifecycleEventObserver { _, activityLifecycleEvent ->
            when (activityLifecycleEvent) {
                Lifecycle.Event.ON_CREATE -> {
                    lifecycString = "ON_CREATE"
                }

                Lifecycle.Event.ON_START -> {
                    lifecycString = "ON_START"
                }

                Lifecycle.Event.ON_RESUME -> {
                    lifecycString = "ON_RESUME"
                }

                else -> Unit
            }
        }

        localLifecycleOwner.lifecycle.addObserver(lifecycleEvent)
        onDispose {
            localLifecycleOwner.lifecycle.removeObserver(lifecycleEvent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            btnText = "Click me",
            onBtnTapped = {
//                lifecycString = !lifecycString
            }
        )

        Text(text = "lifecycle: $lifecycString")
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    btnText: String,
    onBtnTapped: () -> Unit
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Red.copy(.8f))
            .clickable {
                onBtnTapped()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(btnText, fontSize = 18.sp, color = Color.White)
    }
}