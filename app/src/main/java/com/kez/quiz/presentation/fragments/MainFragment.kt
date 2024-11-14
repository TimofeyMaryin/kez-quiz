package com.kez.quiz.presentation.fragments

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.R
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.presentation.ui.CardItem
import com.kez.quiz.presentation.ui.HorizontalCardItem
import com.kez.quiz.presentation.vms.KezViewModel

@Composable
fun MainFragment(
    navController: NavController,
    viewModel: KezViewModel
) {
    val configuration = LocalConfiguration.current
    val infiniteProgression = rememberInfiniteTransition()
    val process by infiniteProgression.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(500),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE  -> {
            HorizontalOrientationMainScreen(progress = process, viewModel = viewModel)
        }
        else -> {
            VerticalOrientationMainScreen(
                progress = process,
                viewModel = viewModel,
                navController = navController
            )
        }

    }

}


@Composable
private fun VerticalOrientationMainScreen(
    navController: NavController,
    progress: Float,
    viewModel: KezViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_bggenerator_com_qwerty),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        if (viewModel.isDoneLoad) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(.9f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(viewModel.cards) { model ->
                    CardItem(model = model) {
                        viewModel.currentModel = model
                        navController.navigate(Screen.PreviewPollScreen.route)
                    }
                }
            }

        } else {
            CircularProgressIndicator(progress = progress)
        }
    }
}


@Composable
private fun HorizontalOrientationMainScreen(
    progress: Float,
    viewModel: KezViewModel
) {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.bg_bggenerator_com_qwerty),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    if (viewModel.isDoneLoad) {
        Box(modifier = Modifier.fillMaxSize(.9f), contentAlignment = Alignment.Center) {

            LazyRow(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.cards) {
                    HorizontalCardItem(model = it) {
                        Toast.makeText(context, "Hello Herlya ${it.theme}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    } else {
        CircularProgressIndicator(progress = progress)
    }

}