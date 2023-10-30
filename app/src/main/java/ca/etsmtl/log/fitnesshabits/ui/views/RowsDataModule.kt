package ca.etsmtl.log.fitnesshabits.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable

data class RowDataUiState(
    val title: String,
    val edit : () -> Unit,
    val delete : () -> Unit,
    val content: @Composable () -> Unit?
)

@Composable
fun RowsDataModule(
    modifier : Modifier = Modifier,
    rowsDataUiState : List<RowDataUiState>
) {
    LazyColumn(modifier){
        items(rowsDataUiState) {
            RowData(rowDataUiState = it)
        }
    }
}

@Composable
private fun RowData(
    modifier : Modifier = Modifier,
    rowDataUiState : RowDataUiState
) {
    Column {
        Spacer(modifier = Modifier.padding(2.dp))

        Row(
            modifier = modifier.fillMaxSize()
        ){
            Column(modifier = modifier.fillMaxWidth(0.7f)){
                Text(
                    text = rowDataUiState.title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                rowDataUiState.content()
            }
            ButtonsRowData(rowDataUiState = rowDataUiState)
        }

        Spacer(modifier = Modifier.padding(2.dp))
        Divider(color = Color.Gray, thickness = 0.5.dp)
    }


}

@Composable
private fun ButtonsRowData(
    rowDataUiState : RowDataUiState
) {
    CircularButtonWithIcon(rowDataUiState.edit, R.drawable.icon_edit_pencil, "Edit Button")
    CircularButtonWithIcon(rowDataUiState.delete, R.drawable.icon_delete_trashbin, "Delete Button")
}

@Preview(showSystemUi = true)
@Composable
private fun RowDataPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowData(
            modifier = it,
            RowDataUiState(
                title = "Titre",
                edit = {},
                delete = {},
                content = {}
            )
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun RowsDataPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowsDataModule(
            modifier = it,
            listOf(
                RowDataUiState(
                    title = "Titre1",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre2",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre3",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre4",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre5",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre6",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre7",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre8",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre9",
                    edit = {},
                    delete = {},
                    content = {}
                ),
                RowDataUiState(
                    title = "Titre10",
                    edit = {},
                    delete = {},
                    content = {}
                )
            )
        )
    }
}

