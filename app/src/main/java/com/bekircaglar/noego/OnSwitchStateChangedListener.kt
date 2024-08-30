package com.bekircaglar.noego

interface OnSwitchStateChangedListener {
    fun onSwitchStateChanged(navItem: NavItem, isChecked: Boolean)
}