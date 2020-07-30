package com.example.mytedrssfeed.main

import com.example.mytedrssfeed.main.data.Item

interface MainView {
      fun setItems(items: List<Item>)
}