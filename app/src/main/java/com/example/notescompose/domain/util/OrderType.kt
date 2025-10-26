package com.example.notescompose.domain.util


sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}