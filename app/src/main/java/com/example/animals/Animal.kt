package com.example.animals

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Animal(val name : String, val continent : String)