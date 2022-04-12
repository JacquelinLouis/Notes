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
import com.jac.notes.edit.EditCompose
import com.jac.notes.list.ListCompose

class MainNavHost {

    object Destination {
        const val LIST = "LIST"
        const val CREATE = "CREATE"
        const val EDIT = "EDIT"
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
                    onCreateClicked = { navHostController.navigate(CREATE) }) }
                composable(CREATE) { EditCompose(enabled = true, onSaveClicked = { navHostController.navigate(LIST) }) }
                composable("$EDIT/{$ID}", arguments = listOf(navArgument(ID) { type = NavType.IntType })) { entry ->
                    val id = entry.arguments?.getInt(ID)?:0
                    EditCompose(id = id, onSaveClicked = { navHostController.navigate(LIST) })
                }
            }
        }

    }
}