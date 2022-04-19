package com.jac.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jac.notes.MainNavHost.Argument.ID
import com.jac.notes.MainNavHost.Destination.CREATE
import com.jac.notes.MainNavHost.Destination.EDIT
import com.jac.notes.MainNavHost.Destination.LIST
import com.jac.notes.MainNavHost.Destination.SETTINGS
import com.jac.notes.edit.EditCompose
import com.jac.notes.list.ListCompose
import com.jac.notes.settings.SettingsCompose

class MainNavHost {

    object Destination {
        const val LIST = "LIST"
        const val CREATE = "CREATE"
        const val EDIT = "EDIT"
        const val SETTINGS = "SETTINGS"
    }

    object Argument {
        const val ID = "ID"
    }

    companion object {

        @Composable
        fun Create(navHostController: NavHostController) {
            NavHost(navHostController, startDestination = LIST) {
                composable(LIST) { ListCompose(
                    onNoteItemClicked = { id -> navHostController.navigate("$EDIT/$id") },
                    onCreateClicked = { navHostController.navigate(CREATE) },
                    onSettingsClicked = { navHostController.navigate(SETTINGS) }
                )}
                composable(CREATE) { EditCompose(enabled = true, onBackClicked = { navHostController.navigate(LIST) }) }
                composable("$EDIT/{$ID}", arguments = listOf(navArgument(ID) { type = NavType.IntType })) { entry ->
                    val id = entry.arguments?.getInt(ID)?:0
                    EditCompose(id = id, onBackClicked = { navHostController.navigate(LIST) })
                }
                composable(SETTINGS) { SettingsCompose() }
            }
        }

    }
}