package com.bekircaglar.noego

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun `test initial values are correct`() {
        assertThat(viewModel.isEgoOn.value).isTrue()
        assertThat(viewModel.isHappinessOn.value).isFalse()
        assertThat(viewModel.isOptimismOn.value).isFalse()
        assertThat(viewModel.isKindnessOn.value).isFalse()
        assertThat(viewModel.isGivingOn.value).isFalse()
        assertThat(viewModel.isRespectOn.value).isFalse()
        assertThat(viewModel.selectedItems.value).isEqualTo(mutableListOf("MainScreen"))
    }

    @Test
    fun `toggleSwitch turns off all when ego is turned on`() {
        viewModel.toggleSwitch("ego", true)

        assertThat(viewModel.isEgoOn.value).isTrue()
        assertThat(viewModel.isHappinessOn.value).isFalse()
        assertThat(viewModel.isOptimismOn.value).isFalse()
        assertThat(viewModel.isKindnessOn.value).isFalse()
        assertThat(viewModel.isGivingOn.value).isFalse()
        assertThat(viewModel.isRespectOn.value).isFalse()
        assertThat(viewModel.selectedItems.value).isEqualTo(mutableListOf("MainScreen"))
    }

    @Test
    fun `toggleSwitch turns on happiness and adds it to selectedItems`() {
        viewModel.toggleSwitch("happiness", true)

        assertThat(viewModel.isHappinessOn.value).isTrue()
        assertThat(viewModel.selectedItems.value).contains("happiness")
    }

    @Test
    fun `toggleSwitch turns off kindness and removes it from selectedItems`() {
        viewModel.toggleSwitch("kindness", true)
        assertThat(viewModel.selectedItems.value).contains("kindness")

        viewModel.toggleSwitch("kindness", false)
        assertThat(viewModel.selectedItems.value).doesNotContain("kindness")
    }

    @Test
    fun `toggleSwitch does not allow more than 5 selected items`() {
        viewModel.toggleSwitch("happiness", true)
        viewModel.toggleSwitch("optimism", true)
        viewModel.toggleSwitch("kindness", true)
        viewModel.toggleSwitch("giving", true)
        viewModel.toggleSwitch("respect", true)

        viewModel.toggleSwitch("optimism", true)

        assertThat(viewModel.selectedItems.value!!.size).isEqualTo(5)
    }
}
