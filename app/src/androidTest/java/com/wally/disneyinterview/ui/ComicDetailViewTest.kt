package com.wally.disneyinterview.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.wally.disneyinterview.domain.Comic
import org.junit.Rule
import org.junit.Test

class ComicDetailViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun comicDetail_case_showsTitle() {
        val comic = getComic()
        composeTestRule.setContent {
            ComicDetail(comic = comic)
        }

        composeTestRule.onNodeWithTag(Tag.TITLE_TEST_TAG)
            .assertIsDisplayed()
            .assertTextEquals(TITLE)
    }

    private fun getComic() = Comic(TITLE, "", "")

    companion object {
        private const val TITLE = "title"
    }
}