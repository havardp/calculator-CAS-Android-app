package com.havardp.calculator.interpreter

import java.io.Serializable
import java.util.*

abstract class Result: Serializable

data class OrdinaryResult(val input: String, val result: String, val solveSteps: Stack<String>, val explanationSteps: Stack<String>, var error: String? = null): Result()

data class QuadraticResult(val input: String, val quadraticFormula: String, val root1: OrdinaryResult, val root2: OrdinaryResult): Result()