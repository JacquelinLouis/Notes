package com.jac.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jac.notes.MainNavHost.Destination.CREATE
import com.jac.notes.MainNavHost.Destination.LIST
import com.jac.notes.create.CreateCompose
import com.jac.notes.list.ListCompose

class MainNavHost {

    object Destination {
        const val LIST = "ListCompose"
        const val CREATE = "CreateCompose"
    }

    companion object {

        @Composable
        fun Create(navHostController: NavHostController) {
            NavHost(navHostController, startDestination = LIST) {
                composable(LIST) { ListCompose(onCreateClicked = { navHostController.navigate(CREATE) }) }
                composable(CREATE) { CreateCompose(enabled = true, onSaveClicked = { navHostController.navigate(LIST) }) }
            }
        }

    }
}