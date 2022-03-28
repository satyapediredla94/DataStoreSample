package com.example.datastoresample.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.datastoresample.WelcomeViewModel
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val welcomePages = listOf(
        WelcomeNavScreens.First,
        WelcomeNavScreens.Second,
        WelcomeNavScreens.Third
    )

    val pagerState = rememberPagerState()

    Column(Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(10f)
        )
        { position ->
            PagerScreen(welcomeNavScreens = welcomePages[position])
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(CenterHorizontally)
                    .weight(1f),
            inactiveColor = Color.Gray,
            activeColor = Color.Black
        )

        FinishButton(modifier = Modifier
            .weight(1f), pagerState = pagerState) {
            welcomeViewModel.saveWelcomeScreenState(true)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }

}

@Composable
fun PagerScreen(welcomeNavScreens: WelcomeNavScreens) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f)
                .background(MaterialTheme.colors.background),
            painter = painterResource(id = welcomeNavScreens.image),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            text = welcomeNavScreens.title,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp)
                .background(MaterialTheme.colors.background),
            text = welcomeNavScreens.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}

@Preview
@Composable
fun PreviewWelcomeScreen() {
    PagerScreen(WelcomeNavScreens.First)
}